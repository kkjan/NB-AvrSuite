/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kkjanius.avrsuite.avrproject.makefilewriter;

/**
 *
 * @author janschml
 */


import java.io.IOException;
import java.io.Writer;
import org.netbeans.modules.cnd.makeproject.api.configurations.MakeConfiguration;
import org.netbeans.modules.cnd.makeproject.api.configurations.MakeConfigurationDescriptor;
import org.netbeans.modules.cnd.makeproject.spi.configurations.MakefileWriter;

@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.cnd.makeproject.spi.configurations.MakefileWriter.class)

public class AVRDefaultMakefileWriter implements MakefileWriter {

    /**
     * Writes first section of generated makefile
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
       @Override
    public void writePrelude(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writePrelude(confDescriptor, conf, writer);
    }
    

    /**
     * Writes main build target
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeBuildTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeBuildTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes build test target
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeBuildTestTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeBuildTestTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes test target
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeRunTestTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeRunTestTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes all compile targets (only for managed projects)
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeCompileTargets(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeCompileTargets(confDescriptor, conf, writer);
    }

    /**
     * Writes all compile test targets (only for managed projects)
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeCompileTestTargets(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeCompileTestTargets(confDescriptor, conf, writer);
    }

    /**
     * Writes link target (only for linked projects)
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeLinkTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeLinkTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes link target (only for linked projects)
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeLinkTestTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeLinkTestTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes writes archive target (only for archive projects)
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeArchiveTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeArchiveTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes target for unmanaged projects
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeMakefileTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeMakefileTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes target for QT projects
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeQTTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeQTTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes clean target
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeCleanTarget(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeCleanTarget(confDescriptor, conf, writer);
    }

    /**
     * Writes targets for sub projects
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeSubProjectBuildTargets(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeSubProjectBuildTargets(confDescriptor, conf, writer);
    }

    /**
     * Writes dependency checking target
     *
     * @param confDescriptor  project configuration descriptor
     * @param conf  current project configuration
     * @param writer  output stream to generated makefile
     */
    @Override
    public void writeDependencyChecking(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeDependencyChecking(confDescriptor, conf, writer);
    }
    
    @Override
    public void writeSubProjectCleanTargets(MakeConfigurationDescriptor confDescriptor, MakeConfiguration conf, Writer writer) throws IOException {
        AVRConfigurationMakefileWriter.writeSubProjectCleanTargets(confDescriptor, conf, writer);
    }
}
