/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.mcuhardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.kkjanius.avrsuite.avrproject.project.GlobalConstant;
import org.netbeans.modules.cnd.api.toolchain.CompilerSet;
import org.netbeans.modules.cnd.api.toolchain.CompilerSetManager;
import org.netbeans.modules.cnd.api.toolchain.PlatformTypes;
import org.netbeans.modules.cnd.api.toolchain.PredefinedToolKind;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironmentFactory;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

/**
 *
 * @author janschml
 */
public class AvrMcu {

    private String Name;

    public static List<String> getMcuList(CompilerSet toolchain) {

        List<String> mcuList = new ArrayList<>();
        CompilerSetManager csm = CompilerSetManager.get(ExecutionEnvironmentFactory.getLocal());
        int platform;

        if (toolchain == null) {
            platform = csm.getPlatform();
            if (platform == PlatformTypes.PLATFORM_WINDOWS) {

                platform = csm.getPlatform();
                toolchain = csm.getCompilerSet(GlobalConstant.PROPERTY_WINDOWS_COMPILERSET);
            }
            if (platform == PlatformTypes.PLATFORM_LINUX) {
                toolchain = csm.getCompilerSet(GlobalConstant.PROPERTY_LINUX_COMPILERSET);
            }

        }
        // CompilerSet toolchain = null;
        //toolchain = csm.getCompilerSet(GlobalConstant.PROPERTY_LINUX_COMPILERSET);

        if (toolchain != null) {
            String line;
            boolean start = false;
            InputOutput io = IOProvider.getDefault().getIO("Avr-gcc:", false);
            io.select();
            try (OutputWriter w = io.getOut()) {
                w.println("");
                String avrgccPath = toolchain.getTool(PredefinedToolKind.CCompiler).getPath();
                try {
                    Runtime rt = Runtime.getRuntime();
                    Process pr = rt.exec(avrgccPath + " --target-help");
                    BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    
                    while ((line = input.readLine()) != null) {
                        w.println(line);
                        if ("Known MCU names:".equals(line)) {
                            
                            start = true;
                        } else if (start && !line.startsWith(" ")) {
                            // finished
                            start = false;
                        } else if (start) {
                            String[] names = line.split(" ");
                            for (String mcuid : names) {
                                
                                if ((mcuid == null) || (mcuid.startsWith("avr")) || (mcuid.trim().equals(""))) {
                                    // some mcuid are generic and should not be
                                    // included
                                    continue;
                                }
                                
                                mcuList.add(mcuid);
                            }
                        } else {
                            // a line outside of the "Known MCU names:" section
                        }
                    }
                } catch (IOException e) {
                }
            }        }
        return mcuList;
    }
}
