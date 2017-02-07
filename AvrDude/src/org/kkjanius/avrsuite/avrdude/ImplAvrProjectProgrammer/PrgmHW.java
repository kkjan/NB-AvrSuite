/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ImplAvrProjectProgrammer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.regex.Pattern;
import org.openide.util.NbPreferences;
import org.openide.windows.IOColorLines;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author janschml
 */
public class PrgmHW extends Observable {

    private String prgmID;
    private String prgmDescription;
    private static PrgmHW instance = null;

    public PrgmHW() {
    }

    public PrgmHW(String prgmID, String prgmDescription) {
        this.prgmID = prgmID;
        this.prgmDescription = prgmDescription;
    }

    public String getPrgmID() {
        return this.prgmID;
    }

    public String getPrgmDescription() {
        return this.prgmDescription;
    }

    @Override
    public String toString() {
        String ret;
        if (this.prgmID.length() > 2) {
            ret = this.prgmID + ": " + this.prgmDescription;
        } else {
            ret = "";
        }
        return ret;
    }

    public PrgmHW getProgrammer(String prgmID, String avrDudeConf) {
        String avrDudeConfEsc;
        avrDudeConfEsc = avrDudeConf.replace("/", "_");
        avrDudeConfEsc = avrDudeConfEsc.replace(".", "_");
        PrgmHW Dudecnf = new PrgmHW(prgmID, NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").get("Programmer." + avrDudeConfEsc + ".prgmid." + prgmID, ""));
        return Dudecnf;
    }

    public static PrgmHW getInstance() {
        return (instance == null) ? (instance = new PrgmHW()) : instance;
    }


 

    public int run_command(String cmd, List<String> out) {
        InputOutput io = IOProvider.getDefault().getIO("AvrDude", false);
        return run_command(cmd, out, io);
    }

    public synchronized int run_command(String cmd, List<String> out, InputOutput io) {
        OutputWriter w = null;
        int ret = 0;
        String line;
        if (io != null) {
            io.select();
            w = io.getOut();
            w.println("AvrDude: " + cmd);
            w.println("");
        }


        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            //String pattern="\\s*(\\S+)\\s*=\\s*(.+?)\\s*\\[(.+):(\\d+)\\]\\.*";

            while ((line = input.readLine()) != null) {

                if (out != null) {
                    out.add(line);
                }
                if (w != null) {
                    w.println(line);
                }

            }
            pr.waitFor();
            ret = pr.exitValue();
            if (ret != 0) {
                io.getErr().println("FAILED");
            } else {
                IOColorLines.println(io, "DONE", Color.GREEN);
            }
        } catch (IOException e) {
        } catch (InterruptedException e) {
        }
        if (w != null) {
            w.close();
        }
        return ret;
    }

    public List<PrgmHW> getProgrammerList(String avrdudeExec, String avrDudeConf) {
        List<PrgmHW> prgmList = new ArrayList<PrgmHW>();


        String avrDudeConfEsc;
        avrDudeConfEsc = avrDudeConf.replace("/", "_");
        avrDudeConfEsc = avrDudeConfEsc.replace(".", "_");

        File f = new File(avrDudeConf);

        NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").put("Programmer." + avrDudeConfEsc, avrDudeConf);

        Long LastChange = NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").getLong("Programmer." + avrDudeConfEsc + ".lastmodified", 0);
        if (f.lastModified() != LastChange) {

            String cmd = avrdudeExec + " -C" + avrDudeConf + " -c?";
            List<String> strList = new ArrayList<String>();
            run_command(cmd, strList);
            String pattern = "\\s+=\\s+";
            Pattern splitter = Pattern.compile(pattern);
            String prgms = "";

            for (String line : strList) {
                String[] val = splitter.split(line);
                if (val.length > 1) {
                    if ((val[0] != null) && (val[1] != null)) {
                        String tmp0 = val[0].replaceAll("\\s+", "");
                        String tmp1 = val[1].replaceAll("\\[.*?\\]", "");

                        NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").put("Programmer." + avrDudeConfEsc + ".prgmid." + tmp0, tmp1);
                        prgmList.add(new PrgmHW(tmp0, tmp1));
                        prgms = prgms + tmp0 + "\";\"";

                        //.substring(0, length)
                        // jComboBoxProgrammer.addItem( label);

                    }
                }
            }

            NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").putLong("Programmer." + avrDudeConfEsc + ".lastmodified", f.lastModified());
            NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").put("Programmer." + avrDudeConfEsc + ".prgmIDs", prgms);


        } else {
            String[] prgms = NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").get("Programmer." + avrDudeConfEsc + ".prgmIDs", "").split("\";\"");
            for (String prgm : prgms) {
                String prgmID1 = prgm;
                String prgmDesc = NbPreferences.forModule(AvrDudeProfile.class).node("avrprgmhw").get("Programmer." + avrDudeConfEsc + ".prgmid." + prgm, "");
                prgmList.add(new PrgmHW(prgmID1, prgmDesc));
            }
        }


        return prgmList;

    }
}
