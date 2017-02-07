/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.makefilewriter;

/**
 *
 * @author janschml
 */
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import org.kkjanius.avrsuite.avrproject.projectconfig.ProjectConfig;
import org.kkjanius.avrsuite.api.avrprogrammer.AvrDefaultProgrammer;
import org.kkjanius.avrsuite.api.avrprogrammer.AvrProgrammer;
import org.netbeans.modules.cnd.api.toolchain.*;

import org.netbeans.modules.cnd.makeproject.api.MakeArtifact;
import org.netbeans.modules.cnd.makeproject.api.configurations.*;
import org.netbeans.modules.cnd.makeproject.spi.DatabaseProjectProvider;
import org.netbeans.modules.cnd.utils.CndPathUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public class AVRConfigurationMakefileWriter {
    //TODO Change and add gcc option for make file

    private MakeConfigurationDescriptor projectDescriptor;
    private static final Logger LOGGER = Logger.getLogger("org.kkjanius.avrtoolchain.MakefileWriter"); // NOI18N

    public AVRConfigurationMakefileWriter(MakeConfigurationDescriptor projectDescriptor) {
        this.projectDescriptor = projectDescriptor;
    }

    public static String getCompilerName(MakeConfiguration conf, PredefinedToolKind tool) {
        CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
        if (compilerSet != null) {
            Tool compiler = (Tool) compilerSet.getTool(tool);
            if (compiler != null) {
                BasicCompilerConfiguration compilerConf = null;
                switch (tool) {
                    case CCompiler:
                        compilerConf = conf.getCCompilerConfiguration();
                        break;
                    case CCCompiler:
                        compilerConf = conf.getCCCompilerConfiguration();
                        break;
                    case FortranCompiler:
                        compilerConf = conf.getFortranCompilerConfiguration();
                        break;
                    case Assembler:
                        compilerConf = conf.getAssemblerConfiguration();
                        break;
                }
                if (compilerConf != null && compilerConf.getTool().getModified()) {
                    return compilerConf.getTool().getValue();
                } else if (compiler.getName().length() > 0) {
                    return compiler.getName();
                } else {
                    // Fake tool, get name from the descriptor (see IZ#174566).
                    String[] names = compiler.getDescriptor().getNames();
                    if (names != null && names.length > 0) {
                        return names[0];
                    }
                }
            }
        }
        return ""; // NOI18N
    }

    public static void writePrelude(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
        if (compilerSet == null) {
            return;
        }
        AbstractCompiler cCompiler = (AbstractCompiler) compilerSet.getTool(PredefinedToolKind.CCompiler);
        AbstractCompiler ccCompiler = (AbstractCompiler) compilerSet.getTool(PredefinedToolKind.CCCompiler);
        AbstractCompiler fortranCompiler = (AbstractCompiler) compilerSet.getTool(PredefinedToolKind.FortranCompiler);
        AbstractCompiler assemblerCompiler = (AbstractCompiler) compilerSet.getTool(PredefinedToolKind.Assembler);

        bw.write("#\n"); // NOI18N
        bw.write("# Generated Makefile by atmel AVR plugin- do not edit!\n"); // NOI18N
        bw.write("#\n"); // NOI18N
        bw.write("# Edit the Makefile in the project folder instead (../Makefile). Each target\n"); // NOI18N
        bw.write("# has a -pre and a -post target defined where you can add customized code.\n"); // NOI18N
        bw.write("#\n"); // NOI18N
        bw.write("# This makefile implements configuration specific macros and targets.\n"); // NOI18N
        bw.write("\n"); // NOI18N
        bw.write("\n"); // NOI18N
        bw.write("# Environment\n"); // NOI18N
        bw.write("MKDIR=mkdir\n"); // NOI18N
        bw.write("CP=cp\n"); // NOI18N
        bw.write("GREP=grep\n"); // NOI18N
        bw.write("NM=nm\n"); // NOI18N
        bw.write("CCADMIN=CCadmin\n"); // NOI18N
        bw.write("RANLIB=" + conf.getArchiverConfiguration().getRanlibTool().getValue() + "\n"); // NOI18N
        bw.write("CC=" + getCompilerName(conf, PredefinedToolKind.CCompiler) + "\n"); // NOI18N
        bw.write("CCC=" + getCompilerName(conf, PredefinedToolKind.CCCompiler) + "\n"); // NOI18N
        bw.write("CXX=" + getCompilerName(conf, PredefinedToolKind.CCCompiler) + "\n"); // NOI18N
//        bw.write("FC=" + getCompilerName(conf, PredefinedToolKind.FortranCompiler) + "\n"); // NOI18N
        bw.write("AS=" + getCompilerName(conf, PredefinedToolKind.Assembler) + "\n"); // NOI18N
        bw.write("AS=" + getCompilerName(conf, PredefinedToolKind.Assembler) + "\n"); // NOI18N

        DatabaseProjectProvider provider = Lookup.getDefault().lookup(DatabaseProjectProvider.class);
        if (provider != null) {
            provider.writePrelude(projectDescriptor, conf, bw);
        }

        if (conf.getArchiverConfiguration().getTool().getModified()) {
            bw.write("AR=" + conf.getArchiverConfiguration().getTool().getValue() + "\n"); // NOI18N
        }

        bw.write("\n"); // NOI18N

        bw.write("# Macros\n"); // NOI18N
        bw.write("CND_PLATFORM=" + "AVR" + "\n"); // NOI18N
        bw.write("CND_CONF=" + conf.getName() + "\n"); // NOI18N
        bw.write("CND_DISTDIR=" + MakeConfiguration.DIST_FOLDER + "\n"); // NOI18N
        bw.write("CND_BUILDDIR=" + MakeConfiguration.BUILD_FOLDER + "\n"); // NOI18N
        bw.write("\n"); // NOI18N

        if (!projectDescriptor.getProjectMakefileName().isEmpty()) {
            bw.write("# Include project Makefile\n"); // NOI18N
            bw.write("include " + projectDescriptor.getProjectMakefileName() + "\n"); // NOI18N
            bw.write("\n"); // NOI18N
        }
        bw.write("# Object Directory\n"); // NOI18N
        bw.write(MakeConfiguration.OBJECTDIR_MACRO_NAME + "=" + getObjectDir(conf) + "\n"); // NOI18N
        bw.write("\n"); // NOI18N
        bw.write("# Object Files\n"); // NOI18N
        bw.write("OBJECTFILES=" + getObjectFiles(projectDescriptor, conf) + "\n"); // NOI18N
        bw.write("\n"); // NOI18N


        if (cCompiler != null) {
            bw.write("# C Compiler Flags\n"); // NOI18N
            bw.write("CFLAGS=" + conf.getCCompilerConfiguration().getCFlags(cCompiler) + "\n"); // NOI18N
            bw.write("\n"); // NOI18N
        }
        if (ccCompiler != null) {
            bw.write("# CC Compiler Flags\n"); // NOI18N
            bw.write("CCFLAGS=" + conf.getCCCompilerConfiguration().getCCFlags(ccCompiler) + "\n"); // NOI18N
            bw.write("CXXFLAGS=" + conf.getCCCompilerConfiguration().getCCFlags(ccCompiler) + "\n"); // NOI18N
            bw.write("\n"); // NOI18N
        }
        if (fortranCompiler != null) {
            bw.write("# Fortran Compiler Flags\n"); // NOI18N
            bw.write("FFLAGS=" + conf.getFortranCompilerConfiguration().getFFlags(fortranCompiler) + "\n"); // NOI18N
            bw.write("\n"); // NOI18N
        }
        if (assemblerCompiler != null) {
            bw.write("# Assembler Flags\n"); // NOI18N
            bw.write("ASFLAGS=" + conf.getAssemblerConfiguration().getAsFlags(assemblerCompiler) + "\n"); // NOI18N
            bw.write("\n"); // NOI18N
        }
        bw.write("# Link Libraries and Options\n"); // NOI18N
        String oicLibOptionsPrefix = ""; //NOI18N
        String oicLibOptionsPostfix = ""; //NOI18N
        if (provider != null) {
            oicLibOptionsPrefix = provider.getLibraryOptionsPrefix(projectDescriptor, conf);
            oicLibOptionsPostfix = provider.getLibraryOptionsPostfix(projectDescriptor, conf);
        }
        bw.write("LDLIBSOPTIONS=" + oicLibOptionsPrefix + conf.getLinkerConfiguration().getLibraryItems() + oicLibOptionsPostfix + "\n"); // NOI18N
        bw.write("\n"); // NOI18N

        if (conf.isQmakeConfiguration()) {
            String qmakeSpec = conf.getQmakeConfiguration().getQmakeSpec().getValue();
            // Bug 159594 - Can't build Qt project on OpenSolaris (64 bit)
            // on unix platforms not passing -spec seems to generate correct makefiles
            // on mac/win32 not passing -spec leads to some problems:
            //      on mac - qmake generates xcode project
            //      on windows - problems with slashes vs. backslashes

            if (qmakeSpec.length() == 0 && (conf.getDevelopmentHost().getBuildPlatform() == PlatformTypes.PLATFORM_MACOSX
                    || conf.getDevelopmentHost().getBuildPlatform() == PlatformTypes.PLATFORM_WINDOWS)) {
            }
            if (!qmakeSpec.isEmpty()) {
                qmakeSpec = "-spec " + qmakeSpec + " "; // NOI18N
            }
            bw.write("nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".mk: nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".pro FORCE\n"); // NOI18N
            // It is important to generate makefile in current directory, and then move it to nbproject/.
            // Otherwise qmake will complain that sources are not found.
            bw.write("\t${QMAKE} VPATH=. " + qmakeSpec + "-o qttmp-" + MakeConfiguration.CND_CONF_MACRO + ".mk nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".pro\n"); // NOI18N
            bw.write("\tmv -f qttmp-" + MakeConfiguration.CND_CONF_MACRO + ".mk nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".mk\n"); // NOI18N

            // Removed paths tweak for Windows as when -spec is used everything works....
            // See comment above.
            // Still if project is created out of the QT tree, problem described 
            // in http://bugreports.qt.nokia.com/browse/QTBUG-10633 exists.
            // To work-around it us following trick.

            if (conf.getDevelopmentHost().getBuildPlatform() == PlatformTypes.PLATFORM_WINDOWS) {
                // qmake uses backslashes on Windows, this code corrects them to forward slashes
                // bw.write("\t@sed -e 's:\\\\\\(.\\):/\\1:g' nbproject/qt-"+MakeConfiguration.CND_CONF_MACRO+".mk >nbproject/qt-"+MakeConfiguration.CND_CONF_MACRO+".tmp\n"); // NOI18N
                bw.write("\t@sed -e 's/\\/qt\\/bin/\\/qt\\/bin\\//g' nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".mk >nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".tmp\n"); // NOI18N
                bw.write("\t@mv -f nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".tmp nbproject/qt-" + MakeConfiguration.CND_CONF_MACRO + ".mk\n"); // NOI18N
            }
            bw.write('\n'); // NOI18N
            bw.write("FORCE:\n\n"); // NOI18N
        }
    }

    protected void writeBuildTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, BufferedWriter bw) throws IOException {
        CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
        if (compilerSet == null) {
            bw.write(".build-conf:\n"); // NOI18N
            bw.write("\t@echo 'Tool collection " + conf.getCompilerSet().getCompilerSetName().getValue() // NOI18N
                    + " was missing when this makefile was generated'\n"); // NOI18N
            bw.write("\t@echo 'Please specify existing tool collection in project properties'\n"); // NOI18N
            bw.write("\t@exit 1\n\n"); // NOI18N

        }
    }

    protected void writeBuildTestTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, BufferedWriter bw) throws IOException {
    }

    public static void writeQTTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
    }

    public static void writeBuildTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
        String output = getOutput(conf, compilerSet);
        bw.write("# Build Targets\n"); // NOI18N
        bw.write(".build-conf: ${BUILD_SUBPROJECTS}\n"); // NOI18N
        bw.write("\t${MAKE} " // NOI18N
                + " -f nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk " // NOI18N
                + output + ".elf" + "\n"); // NOI18N

        bw.write("\t${MAKE} " // NOI18N
                + " -f nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk " // NOI18N
                + output + ".lss" + "\n"); // NOI18N

        bw.write("\t${MAKE} " // NOI18N
                + " -f nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk " // NOI18N
                + output + ".hex" + "\n"); // NOI18N
        bw.write("\t${MAKE} " // NOI18N
                + " -f nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk " // NOI18N
                + output + ".eep" + "\n"); // NOI18N
        bw.write("\t${MAKE} " // NOI18N
                + " -f nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk " // NOI18N
                + "sizedummy" + "\n"); // NOI18N

        //There is path to solve bug with make.exe on Myngw on windows platform
        if (conf.getDevelopmentHost().getBuildPlatform()==PlatformTypes.PLATFORM_WINDOWS) {
            try {
                File file = new File(projectDescriptor.getProjectDir() + "/nbproject/Makefile-impl.mk");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = "", oldtext = "";
                while ((line = reader.readLine()) != null) {
                    oldtext += line + "\r\n";
                }
                reader.close();
                // replace a word in a file
                String newtext = oldtext.replaceAll("\"\\$\\{MAKE\\}\"", "\\$\\{MAKE\\}");

                FileWriter writer = new FileWriter(projectDescriptor.getProjectDir() + "/nbproject/Makefile-impl.mk");
                writer.write(newtext);
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void writeBuildTestTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
    }

    public static void writeLinkTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        AvrProgrammer prgm = Lookup.getDefault().lookup(AvrProgrammer.class).AvrProgrammer(NbPreferences.forModule(ProjectConfig.class).get("prgm.default", ""));
        if (prgm == null) {
            prgm = new AvrDefaultProgrammer();
        }

        ProjectConfig projCfg = new ProjectConfig(projectDescriptor.getProjectDir());
        String mcu=projCfg.getMCU();
        prgm.SetMCU(mcu);

        CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
        String output = getOutput(conf, compilerSet);
        String output_elf = output + ".elf";
        String output_lss = output + ".lss";
        String output_hex = output + ".hex";
        String output_eep = output + ".eep";
        LinkerConfiguration linkerConfiguration = conf.getLinkerConfiguration();
        String command = getLinkerTool(projectDescriptor, conf, conf.getLinkerConfiguration(), compilerSet);
        command += " -Wl,-Map," + output + ".map " + linkerConfiguration.getOutputOptions() + ".elf" + " "; // NOI18N
        command += "${OBJECTFILES}" + " "; // NOI18N
        command += "${LDLIBSOPTIONS}" + " "; // NOI18N
        String[] additionalDependencies = linkerConfiguration.getAdditionalDependencies().getValues();
        for (int i = 0; i < additionalDependencies.length; i++) {
            bw.write(output_elf + ": " + additionalDependencies[i] + "\n\n"); // NOI18N
        }
        for (LibraryItem lib : linkerConfiguration.getLibrariesConfiguration().getValue()) {
            String libPath = lib.getPath();
            if (libPath != null && libPath.length() > 0) {
                bw.write(output_elf + ": " + CndPathUtilities.escapeOddCharacters(libPath) + "\n\n"); // NOI18N
            }
        }

        bw.write(output_elf + ": ${OBJECTFILES}\n"); // NOI18N
        String folders = CndPathUtilities.getDirName(output_elf);
        if (folders != null) {
            bw.write("\t${MKDIR} -p " + folders + "\n"); // NOI18N
        }
        bw.write("\t" + command + "\n"); // NOI18N

        bw.write("\t@echo 'Finished building target: " + output_elf + "'\n\t@echo ' '\n\n");
        //aditional output

        bw.write(output_lss + ":" + output_elf + "\n");
        bw.write("\t@echo 'Invoking: AVR Create Extended Listing'\n");
        bw.write("\t-avr-objdump -h -S " + output_elf + " >" + output_lss + "\n");
        bw.write("\t@echo 'Finished building target: " + output_lss + "'\n\t@echo ' '\n\n");

        bw.write(output_hex + ":" + output_elf + "\n");
        bw.write("\t@echo 'Create Flash image (ihex format)'\n");
        bw.write("\t-avr-objcopy -R .eeprom -O ihex " + output_elf + " " + output_hex + "\n");
        bw.write("\t@echo 'Finished building target: " + output_hex + "'\n\t@echo ' '\n\n");

        bw.write(output_eep + ":" + output_elf + "\n");
        bw.write("\t@echo 'Create eeprom image (ihex format)'\n");
        bw.write("\t-avr-objcopy -j .eeprom --no-change-warnings --change-section-lma .eeprom=0 -O ihex " + output_elf + " " + output_eep + "\n");
        bw.write("\t@echo 'Finished building target: " + output_eep + "'\n\t@echo ' '\n\n");

        bw.write("sizedummy" + ":" + output_elf + "\n");
        bw.write("\t@echo 'Invoking: Print Size'\n");
        bw.write("\t-avr-size --format=avr --mcu="+mcu+" " + output_elf + "\n");
        bw.write("\t@echo 'Finished building: " + output_elf + "'\n\t@echo ' '\n\n");

        bw.write("# upload Targets\n"); // NOI18N 
        bw.write(".upload-conf: ${BUILD_SUBPROJECTS}\n"); // NOI18N
        bw.write("\t@echo 'Upload started'\n");
        bw.write("\t-" + prgm.GetUploadCmd(output_hex) + "\n");
        bw.write("\t@echo \"Upload finished\"\n\n"); // NOI18N
    }

    private static String getLinkerTool(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, LinkerConfiguration linkerConfiguration, CompilerSet compilerSet) {
        if (linkerConfiguration.getTool().getModified()) {
            return linkerConfiguration.getTool().getValue() + " "; // NOI18N
        }
        String getPreferredCompiler = null;
        if (compilerSet != null) {
            getPreferredCompiler = compilerSet.getCompilerFlavor().getToolchainDescriptor().getLinker().getPreferredCompiler();
        }
        if (getPreferredCompiler != null) {
            if ("c".equals(getPreferredCompiler)) { // NOI18N
                return "${LINK.c}" + " "; // NOI18N
            } else if ("cpp".equals(getPreferredCompiler)) { // NOI18N
                return "${LINK.cc}" + " "; // NOI18N
            } else if ("fortran".equals(getPreferredCompiler)) { // NOI18N
                return "${LINK.f}" + " "; // NOI18N
            }
        }
        if (conf.hasCPPFiles(projectDescriptor)) {
            return "${LINK.cc}" + " "; // NOI18N
        } else if (conf.hasFortranFiles(projectDescriptor)) {
            return "${LINK.f}" + " "; // NOI18N
        }
        return "${LINK.c}" + " "; // NOI18N
    }

    public static void writeLinkTestTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
    }

    public static void writeArchiveTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
    }

    public static void writeCompileTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        Item[] items = projectDescriptor.getProjectItems();
        if (conf.isCompileConfiguration()) {
            String target = null;
            String folders = null;
            String file = null;
            String command = null;
            String comment = null;
            String additionalDep = null;
            for (int i = 0; i < items.length; i++) {
                final Folder folder = items[i].getFolder();
                if (folder.isTest() || folder.isTestLogicalFolder() || folder.isTestRootFolder()) {
                    continue;
                }
                ItemConfiguration itemConfiguration = items[i].getItemConfiguration(conf); //ItemConfiguration)conf.getAuxObject(ItemConfiguration.getId(items[i].getPath()));
                if (itemConfiguration.getExcluded().getValue()) {
                    continue;
                }
                CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
                if (compilerSet == null) {
                    continue;
                }

                file = CndPathUtilities.escapeOddCharacters(items[i].getPath(true));
                DatabaseProjectProvider provider = Lookup.getDefault().lookup(DatabaseProjectProvider.class);
                if (provider != null) {
                    if (provider.isProCItem(items[i])) {
                        file = provider.getProCOutput(items[i], conf);
                    }
                }

                command = ""; // NOI18N
                comment = null;
                additionalDep = null;
                if (itemConfiguration.isCompilerToolConfiguration()) {
                    AbstractCompiler compiler = (AbstractCompiler) compilerSet.getTool(itemConfiguration.getTool());
                    BasicCompilerConfiguration compilerConfiguration = itemConfiguration.getCompilerConfiguration();
                    target = compilerConfiguration.getOutputFile(items[i], conf, false);
                    if (compiler != null && compiler.getDescriptor() != null) {
                        String fromLinker = ""; // NOI18N
                        command += compilerConfiguration.getOptions(compiler);
                        if (provider != null) {
                            if (provider.isProCItem(items[i])) {
                                command += provider.getCompileOptions(items[i], conf);
                            }
                        }

                        command += fromLinker + " "; // NOI18N
                        if (conf.getDependencyChecking().getValue() && compiler.getDependencyGenerationOption().length() > 0) {
                            command = "${RM} $@.d\n\t" + command + compiler.getDependencyGenerationOption() + " "; // NOI18N
                        }
                        if (items[i].hasHeaderOrSourceExtension(false, false)) {
                            String flags = compiler.getDescriptor().getPrecompiledHeaderFlags();
                            if (flags == null) {
                                command = "# command to precompile header "; // NOI18N
                                comment = "Current compiler does not support header precompilation"; // NOI18N
                            } else {
                                command += compiler.getDescriptor().getPrecompiledHeaderFlags() + " "; // NOI18N
                            }
                        } else {
                            command += compiler.getDescriptor().getOutputObjectFileFlags() + target + " "; // NOI18N
                        }
                        command += file;
                    }
                    additionalDep = compilerConfiguration.getAdditionalDependencies().getValue();
                } else if (itemConfiguration.getTool() == PredefinedToolKind.CustomTool) {
                    CustomToolConfiguration customToolConfiguration = itemConfiguration.getCustomToolConfiguration();
                    if (customToolConfiguration.getModified()) {
                        target = customToolConfiguration.getOutputs().getValue();
                        command = customToolConfiguration.getCommandLine().getValue();
                        comment = customToolConfiguration.getDescription().getValue();
                        additionalDep = customToolConfiguration.getAdditionalDependencies().getValue();
                    } else {
                        continue;
                    }
                } else {
                    assert false;
                }
                StringTokenizer tokennizer = new StringTokenizer(target);
                StringBuilder foldersBuffer = new StringBuilder();
                while (tokennizer.hasMoreTokens()) {
                    String dir = CndPathUtilities.getDirName(tokennizer.nextToken());
                    if (dir != null) {
                        foldersBuffer.append(dir);
                        foldersBuffer.append(" "); // NOI18N
                    }
                }
                folders = foldersBuffer.toString().trim();
                bw.write("\n"); // NOI18N

                if (target.contains(" ")) { // NOI18N
                    bw.write(".NO_PARALLEL:" + target + "\n"); // NOI18N
                }
                bw.write(target + ": "); // NOI18N
                // See IZ #151465 for explanation why Makefile is listed as dependency.
                if (conf.getRebuildPropChanged().getValue()) {
                    bw.write("nbproject/Makefile-" + MakeConfiguration.CND_CONF_MACRO + ".mk "); // NOI18N
                }
                if (additionalDep != null) {
                    bw.write(file + " " + additionalDep + "\n"); // NOI18N
                } else {
                    bw.write(file + "\n"); // NOI18N
                }

                if (folders != null && folders.length() > 0) {
                    bw.write("\t${MKDIR} -p " + folders + "\n"); // NOI18N
                }
                if (comment != null) {
                    bw.write("\t@echo " + comment + "\n"); // NOI18N
                }
                bw.write("\t" + command + "\n"); // NOI18N
            }


        }
    }

    public static void writeCompileTestTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
    }

    private static String changeToNoMain(String target, String name) {
        String nomainTarget;
        if (target.indexOf("/") >= 0) { // NOI18N
            String baseDir = CndPathUtilities.getDirName(target);
            String baseName = CndPathUtilities.getBaseName(target);
            nomainTarget = baseDir + "/" + baseName.replace(name, name + "_nomain"); // NOI18N;
        } else {
            nomainTarget = target.replace(name, name + "_nomain"); // NOI18N
        }
        return nomainTarget;
    }

    public static void writeCompileTargetsWithoutMain(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        Item[] items = projectDescriptor.getProjectItems();
        if (conf.isCompileConfiguration()) {
            String target = null;
            String folders = null;
            String file = null;
            String command = null;
            String comment = null;
            String additionalDep = null;
            for (int i = 0; i < items.length; i++) {
                final Folder folder = items[i].getFolder();
                if (folder.isTest() || folder.isTestLogicalFolder() || folder.isTestRootFolder()) {
                    continue;
                }
                ItemConfiguration itemConfiguration = items[i].getItemConfiguration(conf); //ItemConfiguration)conf.getAuxObject(ItemConfiguration.getId(items[i].getPath()));
                if (itemConfiguration.getExcluded().getValue()) {
                    continue;
                }
                CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
                if (compilerSet == null) {
                    continue;
                }


                DatabaseProjectProvider provider = Lookup.getDefault().lookup(DatabaseProjectProvider.class);
                if (provider != null) {
                    if (provider.isProCItem(items[i])) {
                        file = provider.getProCOutput(items[i], conf);
                    }
                }

                command = ""; // NOI18N
                comment = null;
                additionalDep = null;
                String name = items[i].getName().replaceAll("\\..*", ""); // NOI18N
                String nomainTarget;
                if (itemConfiguration.isCompilerToolConfiguration()) {
                    AbstractCompiler compiler = (AbstractCompiler) compilerSet.getTool(itemConfiguration.getTool());
                    BasicCompilerConfiguration compilerConfiguration = itemConfiguration.getCompilerConfiguration();
                    target = compilerConfiguration.getOutputFile(items[i], conf, false);

                    nomainTarget = changeToNoMain(target, name);

                    if (compiler != null && compiler.getDescriptor() != null) {
                        String fromLinker = ""; // NOI18N
                        if (conf.getConfigurationType().getValue() == MakeConfiguration.TYPE_DYNAMIC_LIB) {
                            if (conf.getLinkerConfiguration().getPICOption().getValue()) {
                                fromLinker = " " + conf.getLinkerConfiguration().getPICOption(compilerSet); // NOI18N
                            }
                        }
                        command += compilerConfiguration.getOptions(compiler);

                        if (provider != null) {
                            if (provider.isProCItem(items[i])) {
                                command += provider.getCompileOptions(items[i], conf);
                            }
                        }

                        command += fromLinker + " -Dmain=__nomain "; // NOI18N
                        if (conf.getDependencyChecking().getValue() && compiler.getDependencyGenerationOption().length() > 0) {
                            command = "${RM} $@.d;\\\n\t    " + command + compiler.getDependencyGenerationOption() + " "; // NOI18N
                        }
                        if (items[i].hasHeaderOrSourceExtension(false, false)) {
                            String flags = compiler.getDescriptor().getPrecompiledHeaderFlags();
                            if (flags == null) {
                                command = "# command to precompile header "; // NOI18N
                                comment = "Current compiler does not support header precompilation"; // NOI18N
                            } else {
                                command += compiler.getDescriptor().getPrecompiledHeaderFlags() + " "; // NOI18N
                            }
                        } else {
                            command += compiler.getDescriptor().getOutputObjectFileFlags() + nomainTarget + " "; // NOI18N
                        }
                        command += file;
                    }
                    additionalDep = compilerConfiguration.getAdditionalDependencies().getValue();
                } else if (itemConfiguration.getTool() == PredefinedToolKind.CustomTool) {
                    CustomToolConfiguration customToolConfiguration = itemConfiguration.getCustomToolConfiguration();
                    if (customToolConfiguration.getModified()) {
                        target = customToolConfiguration.getOutputs().getValue();
                        command = customToolConfiguration.getCommandLine().getValue();
                        comment = customToolConfiguration.getDescription().getValue();
                        additionalDep = customToolConfiguration.getAdditionalDependencies().getValue();
                    } else {
                        continue;
                    }
                } else {
                    assert false;
                }
                nomainTarget = changeToNoMain(target, name);
                folders = CndPathUtilities.getDirName(target);
                bw.write("\n"); // NOI18N
                if (target.contains(" ")) { // NOI18N
                    bw.write(".NO_PARALLEL:" + target + "\n"); // NOI18N
                }
                // See IZ #151465 for explanation why Makefile is listed as dependency.
                if (additionalDep != null) {
                    bw.write(nomainTarget + ": " + target + " " + file + " " + additionalDep + "\n"); // NOI18N
                } else {
                    bw.write(nomainTarget + ": " + target + " " + file + "\n"); // NOI18N
                }
                if (folders != null) {
                    bw.write("\t${MKDIR} -p " + folders + "\n"); // NOI18N
                }
                if (comment != null) {
                    bw.write("\t@echo " + comment + "\n"); // NOI18N
                }

                bw.write("\t@NMOUTPUT=`${NM} " + target + "`; \\\n"); // NOI18N
                bw.write("\tif (echo \"$$NMOUTPUT\" | ${GREP} '|main$$') || \\\n"); // NOI18N
                bw.write("\t   (echo \"$$NMOUTPUT\" | ${GREP} 'T main$$') || \\\n"); // NOI18N
                bw.write("\t   (echo \"$$NMOUTPUT\" | ${GREP} 'T _main$$'); \\\n"); // NOI18N
                bw.write("\tthen  \\\n"); // NOI18N
                bw.write("\t    " + command + ";\\\n"); // NOI18N
                bw.write("\telse  \\\n"); // NOI18N
                bw.write("\t    ${CP} " + target + " " + nomainTarget + ";\\\n"); // NOI18N
                bw.write("\tfi\n"); // NOI18N
            }
            bw.write("\n"); // NOI18N
        }
    }

    public static void writeRunTestTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        if (hasTests(projectDescriptor)) {
            CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
            bw.write("# Run Test Targets\n"); // NOI18N
            bw.write(".test-conf:\n"); // NOI18N

            bw.write("\t@if [ \"${TEST}\" = \"\" ]; \\\n"); // NOI18N
            bw.write("\tthen  \\\n"); // NOI18N
            for (Folder folder : getTests(projectDescriptor)) {
                String target = folder.getFolderConfiguration(conf).getLinkerConfiguration().getOutputValue();
                bw.write("\t    " + target + " || true; \\\n"); // NOI18N
            }
            bw.write("\telse  \\\n"); // NOI18N
            bw.write("\t    ./${TEST} || true; \\\n"); // NOI18N
            bw.write("\tfi\n\n"); // NOI18N
        }
    }

    public static void writeMakefileTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        MakefileConfiguration makefileConfiguration = conf.getMakefileConfiguration();
        String target = makefileConfiguration.getOutput().getValue();
        String cwd = makefileConfiguration.getBuildCommandWorkingDirValue();
        String command = makefileConfiguration.getBuildCommand().getValue();
        bw.write("# Build Targets\n"); // NOI18N
        bw.write(".build-conf: ${BUILD_SUBPROJECTS}\n"); // NOI18N
        //bw.write(target + ":" + "\n"); // NOI18N
        bw.write("\tcd " + CndPathUtilities.escapeOddCharacters(CndPathUtilities.normalizeSlashes(cwd)) + " && " + command + ".elf" + "\n"); // NOI18N
    }

    public static void writeSubProjectBuildTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        bw.write("\n"); // NOI18N
        bw.write("# Subprojects\n"); // NOI18N
        bw.write(".build-subprojects:" + "\n"); // NOI18N
        LibrariesConfiguration librariesConfiguration = null;
        if (conf.isLinkerConfiguration()) {
            librariesConfiguration = conf.getLinkerConfiguration().getLibrariesConfiguration();

            for (LibraryItem item : librariesConfiguration.getValue()) {
                if (item instanceof LibraryItem.ProjectItem) {
                    LibraryItem.ProjectItem projectItem = (LibraryItem.ProjectItem) item;
                    MakeArtifact makeArtifact = projectItem.getMakeArtifact();
                    String location = makeArtifact.getWorkingDirectory();
                    if (!makeArtifact.getBuild()) {
                        continue;
                    }
                    bw.write("\tcd " + CndPathUtilities.escapeOddCharacters(CndPathUtilities.normalizeSlashes(location)) + " && " + makeArtifact.getBuildCommand() + "\n"); // NOI18N
                }
            }
        }

        for (LibraryItem.ProjectItem item : conf.getRequiredProjectsConfiguration().getValue()) {
            MakeArtifact makeArtifact = item.getMakeArtifact();
            String location = makeArtifact.getWorkingDirectory();
            if (!makeArtifact.getBuild()) {
                continue;
            }
            bw.write("\tcd " + CndPathUtilities.escapeOddCharacters(CndPathUtilities.normalizeSlashes(location)) + " && " + makeArtifact.getBuildCommand() + "\n"); // NOI18N
        }
        bw.write("\n"); // NOI18N
    }

    public static void writeSubProjectCleanTargets(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        bw.write("\n"); // NOI18N
        bw.write("# Subprojects\n"); // NOI18N
        bw.write(".clean-subprojects:" + "\n"); // NOI18N
        LibrariesConfiguration librariesConfiguration = null;
        if (conf.isLinkerConfiguration()) {
            librariesConfiguration = conf.getLinkerConfiguration().getLibrariesConfiguration();

            for (LibraryItem item : librariesConfiguration.getValue()) {
                if (item instanceof LibraryItem.ProjectItem) {
                    LibraryItem.ProjectItem projectItem = (LibraryItem.ProjectItem) item;
                    MakeArtifact makeArtifact = projectItem.getMakeArtifact();
                    String location = makeArtifact.getWorkingDirectory();
                    if (!makeArtifact.getBuild()) {
                        continue;
                    }
                    bw.write("\tcd " + CndPathUtilities.escapeOddCharacters(CndPathUtilities.normalizeSlashes(location)) + " && " + makeArtifact.getCleanCommand() + "\n"); // NOI18N
                }
            }
        }

        for (LibraryItem.ProjectItem item : conf.getRequiredProjectsConfiguration().getValue()) {
            MakeArtifact makeArtifact = item.getMakeArtifact();
            String location = makeArtifact.getWorkingDirectory();
            if (!makeArtifact.getBuild()) {
                continue;
            }
            bw.write("\tcd " + CndPathUtilities.escapeOddCharacters(CndPathUtilities.normalizeSlashes(location)) + " && " + makeArtifact.getCleanCommand() + "\n"); // NOI18N
        }
    }

    public static void writeCleanTarget(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        bw.write("# Clean Targets\n"); // NOI18N
        bw.write(".clean-conf: ${CLEAN_SUBPROJECTS}"); // NOI18N

        bw.write('\n'); // NOI18N
        if (conf.isCompileConfiguration()) {
            CompilerSet compilerSet = conf.getCompilerSet().getCompilerSet();
            String output = getOutput(conf, compilerSet);
            String output_elf = output + ".elf";
            String output_lss = output + ".lss";
            String output_hex = output + ".hex";
            String output_eep = output + ".eep";
            bw.write("\t${RM} -r " + MakeConfiguration.CND_BUILDDIR_MACRO + '/' + MakeConfiguration.CND_CONF_MACRO + "\n"); // UNIX path // NOI18N
            bw.write("\t${RM} " + output_elf + "\n"); // NOI18N
            bw.write("\t${RM} " + output_lss + "\n"); // NOI18N
            bw.write("\t${RM} " + output_hex + "\n"); // NOI18N
            bw.write("\t${RM} " + output_eep + "\n"); // NOI18N
            bw.write("\t${RM} " + output + ".map" + "\n"); // NOI18N

        }

        writeSubProjectCleanTargets(projectDescriptor, conf, bw);
    }

    public static void writeDependencyChecking(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf, Writer bw) throws IOException {
        if (conf.getDependencyChecking().getValue() && !conf.isMakefileConfiguration() && !conf.isQmakeConfiguration() && conf.getCompilerSet().getCompilerSet() != null) {
            // if conf.getCompilerSet().getCompilerSet() == null and we write this to makefile,
            // make would give confusing error message (see IZ#168540)
            bw.write("\n"); // NOI18N
            bw.write("# Enable dependency checking\n"); // NOI18N
            bw.write(".dep.inc: .depcheck-impl\n"); // NOI18N
            bw.write("\n"); // NOI18N
            bw.write("include .dep.inc\n"); // NOI18N
        }
    }

    private static String getOutput(MakeConfiguration conf, CompilerSet compilerSet) {
        String output = conf.getOutputValue();

        if (compilerSet != null) {
        }
        return output;
    }

    public static String getObjectDir(MakeConfiguration conf) {
        return MakeConfiguration.CND_BUILDDIR_MACRO + '/' + MakeConfiguration.CND_CONF_MACRO + '/' + MakeConfiguration.CND_PLATFORM_MACRO; // UNIX path // NOI18N
    }

    private static String getObjectFiles(MakeConfigurationDescriptor projectDescriptor, MakeConfiguration conf) {
        Item[] items = projectDescriptor.getProjectItems();
        StringBuilder linkObjects = new StringBuilder();
        if (conf.isCompileConfiguration()) {
            for (int x = 0; x < items.length; x++) {
                final Folder folder = items[x].getFolder();
                if (folder.isTest() || folder.isTestLogicalFolder() || folder.isTestRootFolder()) {
                    continue;
                }
                ItemConfiguration itemConfiguration = items[x].getItemConfiguration(conf); //ItemConfiguration)conf.getAuxObject(ItemConfiguration.getId(items[x].getPath()));
                //String commandLine = ""; // NOI18N
                if (itemConfiguration.getExcluded().getValue()) {
                    continue;
                }
                if (!itemConfiguration.isCompilerToolConfiguration()) {
                    continue;
                }
                if (items[x].hasHeaderOrSourceExtension(false, false)) {
                    continue;
                }
                BasicCompilerConfiguration compilerConfiguration = itemConfiguration.getCompilerConfiguration();
                linkObjects.append(" \\\n\t"); // NOI18N
                linkObjects.append(compilerConfiguration.getOutputFile(items[x], conf, false));
            }
        }
        return linkObjects.toString();
    }

    private static boolean hasTests(MakeConfigurationDescriptor projectDescriptor) {
        return !getTests(projectDescriptor).isEmpty();
    }

    private static List<Folder> getTests(MakeConfigurationDescriptor projectDescriptor) {
        Folder root = projectDescriptor.getLogicalFolders();
        Folder testRootFolder = null;
        for (Folder folder : root.getFolders()) {
            if (folder.isTestRootFolder()) {
                testRootFolder = folder;
                break;
            }
        }
        if (testRootFolder != null) {
            return testRootFolder.getAllTests();
        }
        return Collections.<Folder>emptyList();
    }

    /**
     * Look up i18n strings here
     */
    private static String getString(String s) {
        return NbBundle.getMessage(AVRConfigurationMakefileWriter.class, s);
    }

    private static String getString(String s, String arg1) {
        return NbBundle.getMessage(AVRConfigurationMakefileWriter.class, s, arg1);
    }

    private static String getString(String s, String arg1, String arg2) {
        return NbBundle.getMessage(AVRConfigurationMakefileWriter.class, s, arg1, arg2);
    }
}
