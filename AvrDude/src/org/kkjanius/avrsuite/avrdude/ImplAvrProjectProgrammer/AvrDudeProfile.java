/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ImplAvrProjectProgrammer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.kkjanius.avrsuite.api.avrprogrammer.MCu;
import org.openide.util.NbPreferences;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author janschml
 */
public class AvrDudeProfile {

    private String ProfileName = "";
    private String Port = "";
    private String BaudRate = "";
    private String ExecLocation = "";
    private String ConfPath = "";
    private String Programmer = "";
    private String Bitclock = "";
    private String Delay = "";
    private String ExtraParams = "";
    private boolean VerifyCheckDis;
    private boolean SignCheckDis;
    private boolean SimulationMode;
    private boolean AutoEraseFlashDis;
    private String Mcu = "";
    private static AvrDudeProfile instance = null;

    public AvrDudeProfile() {
    }

    public AvrDudeProfile(String name) {
        this.setProfile(name);
    }

    public static AvrDudeProfile getInstance() {
        return (instance == null) ? (instance = new AvrDudeProfile()) : instance;
    }

    public static AvrDudeProfile getInstance(String name) {
        if (instance == null) {
            instance = new AvrDudeProfile(name);
            return instance;
        }
        instance.setProfile(name);
        return instance;

    }

    public AvrDudeProfile(String ProfileName,
            String ExecLocation,
            String ConfPath,
            String Programmer,
            String Port,
            String BaudRate,
            String Bitlock,
            String Delay,
            String ExtraParams,
            boolean VerifyCheckDis,
            boolean SignCheckDis,
            boolean SimulationMode,
            boolean AutoEraseFlashDis) {

        this.ProfileName = ProfileName;
        this.Port = Port;
        this.BaudRate = BaudRate;
        this.ExecLocation = ExecLocation;
        this.ConfPath = ConfPath;
        this.Programmer = Programmer;
        this.Bitclock = Bitlock;
        this.Delay = Delay;
        this.ExtraParams = ExtraParams;
        this.VerifyCheckDis = VerifyCheckDis;
        this.SignCheckDis = SignCheckDis;
        this.SimulationMode = SimulationMode;
        this.AutoEraseFlashDis = AutoEraseFlashDis;


    }

    public void setMCU(String mcu) {
        this.Mcu = mcu;
    }

    public void setProfile(String profile) {
        this.ProfileName = profile;
        this.Port = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".Port", "");
        this.BaudRate = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".BaudRate", "");
        this.ExecLocation = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".ExecLocation", "");
        this.ConfPath = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".ConfPath", "");
        this.Programmer = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".Programmer", "");
        this.Bitclock = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".Bitclock", "");
        this.Delay = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".Delay", "");
        this.ExtraParams = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profile." + profile + ".ExtraParams", "");
        this.VerifyCheckDis = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").getBoolean("Profile." + ProfileName + ".VerifyCheckDis", false);
        this.SignCheckDis = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").getBoolean("Profile." + ProfileName + ".SignCheckDis", false);
        this.SimulationMode = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").getBoolean("Profile." + ProfileName + ".SimulationMode", false);
        this.AutoEraseFlashDis = NbPreferences.forModule(AvrDudeProfile.class).node("profiles").getBoolean("Profile." + ProfileName + ".AutoEraseFlashDis", false);
    }

    public String getName() {
        return this.ProfileName;
    }

    public String getPort() {
        return this.Port;
    }

    public String getBaudRate() {
        return this.BaudRate;
    }

    public String getExecLocation() {
        return this.ExecLocation;
    }

    public String getConfPath() {
        return this.ConfPath;
    }

    public String getProgrammer() {
        return this.Programmer;
    }

    public String getBitclock() {
        return this.Bitclock;
    }

    public String getDelay() {
        return this.Delay;
    }

    public String getExtraParams() {
        return this.ExtraParams;
    }

    public boolean getVerifyCheckDis() {
        return this.VerifyCheckDis;
    }

    public boolean getSignCheckDis() {
        return this.SignCheckDis;
    }

    public boolean getSimulationMode() {
        return this.SimulationMode;
    }

    public boolean getAutoEraseFlashDis() {
        return this.AutoEraseFlashDis;
    }

    public void StoreOptions(String ProfileName) {

        List<String> Profiles = new ArrayList<String>();
        Profiles = AvrDudeProfiles.getAllProfiles();

        int index = Profiles.indexOf(ProfileName);
        if (index == -1) {
            Profiles.add(ProfileName);
        }
        String strProfiles = "";
        for (String tmp : Profiles) {
            if (!tmp.isEmpty()) {
                strProfiles = strProfiles + tmp + "\";\"";
            }
        }

        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profiles", strProfiles);

        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".ExecLocation", ExecLocation);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".ConfPath", ConfPath);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".Programmer", Programmer);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".Port", Port);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".BaudRate", BaudRate);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".Bitlock", Bitclock);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".Delay", Delay);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profile." + ProfileName + ".ExtraParams", ExtraParams);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").putBoolean("Profile." + ProfileName + ".VerifyCheckDis", VerifyCheckDis);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").putBoolean("Profile." + ProfileName + ".SignCheckDis", SignCheckDis);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").putBoolean("Profile." + ProfileName + ".SimulationMode", SimulationMode);
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").putBoolean("Profile." + ProfileName + ".AutoEraseFlashDis", AutoEraseFlashDis);

    }

    public String getBasicCmd() {
        String cmd = "";
        if (ExecLocation.length() > 0) {
            cmd = cmd + ExecLocation;
        }
        if (ConfPath.length() > 0) {
            cmd = cmd + " -C " + ConfPath;
        }

        if (Mcu.length() > 0) {
            cmd = cmd + " -p " + getMcuIdFromNameSignature(Mcu);
        }

        if (Programmer.length() > 0) {
            cmd = cmd + " -c " + Programmer;
        }

        if (Port.length() > 0) {
            cmd = cmd + " -P " + Port;
        }
        if (BaudRate.replace(" ", "").length() > 0) {
            cmd = cmd + " -b " + BaudRate;
        }
        if (Bitclock.length() > 0) {
            cmd = cmd + " -B " + Bitclock;
        }
        if (Delay.length() > 0) {
            cmd = cmd + " -i " + Delay;
        }
        if (ExtraParams.length() > 0) {
            cmd = cmd + " " + ExtraParams + " ";
        }
        if (VerifyCheckDis) {
            cmd = cmd + " -V ";
        }
        if (SignCheckDis) {
            cmd = cmd + " -F ";
        }
        if (SimulationMode) {
            cmd = cmd + " -n ";
        }
        if (AutoEraseFlashDis) {
            cmd = cmd + " -D ";
        }
        return cmd;
    }

    public String getUploadCmd(String FilePath) {
        String cmd = getBasicCmd();
        if (cmd.length() > 0) {
            return getBasicCmd() + " -U flash:w:" + FilePath + ":a";
        }
        return "";
    }

    public String getConectedMCU(String predefMcu) {
        String ret = "";
        String signature = "";
        String[] mcus = {predefMcu, "m16", "x128a3"};
        for (String mcu : mcus) {
            List<String> prgmOut = new ArrayList<String>();

            PrgmHW.getInstance().run_command(getBasicCmd() + " -p " + mcu, prgmOut, null);

            for (String str : prgmOut) {
                if (str.matches(".+(0x[\\da-fA-F]{6})")) {
                    signature = str.replaceAll(".+0x", "0x");
                }
            }

        }


        return getMcuFromSignature(signature);
    }

    private String getMcuFromSignature(String signature) {

        String ret = "";
        try {
            String inpath = System.getProperty("netbeans.user") + "/config/Preferences" + NbPreferences.forModule(AvrDudeProfile.class).absolutePath() + "/mcu" + this.ProfileName + ".xml";
            FileInputStream finstream = new FileInputStream(inpath);
            DataInputStream in = new DataInputStream(finstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(inpath));

            NodeList nodes = doc.getElementsByTagName("mcu");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element el = (Element) nodes.item(i);
                if (el.getAttribute("signature").equals(signature)) {
                    ret = el.getAttribute("name");
                    return ret;
                }
            }

        } catch (Exception e) {//Catch exception if any
        }
        return ret;
    }

    private String getMcuIdFromNameSignature(String name) {

        String ret = "";
        try {
            String inpath = System.getProperty("netbeans.user") + "/config/Preferences" + NbPreferences.forModule(AvrDudeProfile.class).absolutePath() + "/mcu" + this.ProfileName + ".xml";
            FileInputStream finstream = new FileInputStream(inpath);
            DataInputStream in = new DataInputStream(finstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(inpath));

            NodeList nodes = doc.getElementsByTagName("mcu");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element el = (Element) nodes.item(i);
                if (el.getAttribute("name").equals(name)) {
                    ret = el.getAttribute("id");
                    return ret;
                }
            }

        } catch (Exception e) {//Catch exception if any
        }
        return ret;
    }

    public List<String> getFusesMem() {
        return getMemNames("fuse");
    }
    public List<String> getLockBitMem() {
        return getMemNames("lockbyte");
    }
    private List<String> getMemNames(String MemType) {
  
        List<String> strList= new ArrayList<String>();
        strList.clear();
        try {
            String inpath = System.getProperty("netbeans.user") + "/config/Preferences" + NbPreferences.forModule(AvrDudeProfile.class).absolutePath() + "/mcu" + this.ProfileName + ".xml";
            FileInputStream finstream = new FileInputStream(inpath);
            DataInputStream in = new DataInputStream(finstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(inpath));

            NodeList nodes = doc.getElementsByTagName("_"+this.Mcu);
          
                Element el = (Element) nodes.item(0);
                

                    NodeList  nodesEl = el.getElementsByTagName(MemType);
                    for(int i=0;i<nodesEl.getLength();i++){
                        strList.add(nodesEl.item(i).getTextContent());
                    }      

        } catch (Exception e) {//Catch exception if any
            e.printStackTrace();
        }
        return strList;
    }
    
    public List<String> getSupportedMcu() {
        MCu mcu;
        List<String> mcus = new ArrayList<String>();
        if(this.ProfileName==null){
  
        }

        try {
            
                String inpath = System.getProperty("netbeans.user") + "/config/Preferences" + NbPreferences.forModule(AvrDudeProfile.class).absolutePath() + "/mcu" + this.ProfileName + ".xml";
            FileInputStream finstream = new FileInputStream(inpath);
            DataInputStream in = new DataInputStream(finstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(new File(inpath));

            NodeList nodes = doc.getElementsByTagName("mcu");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element el = (Element) nodes.item(i);
                mcus.add(el.getAttribute("name"));
            }

        } catch (Exception e) {//Catch exception if any
            e.printStackTrace();
        }
        return mcus;
    }
}
