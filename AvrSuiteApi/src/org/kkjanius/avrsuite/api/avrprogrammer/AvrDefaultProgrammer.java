/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.api.avrprogrammer;

import java.util.List;

/**
 *
 * @author janschml
 */
public class AvrDefaultProgrammer implements AvrProgrammer{

    @Override
    public org.kkjanius.avrsuite.api.avrprogrammer.AvrProgrammer AvrProgrammer(String Name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void SetProfile(String Name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetMcuSignature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> GetSuportedMCUs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetUploadCmd(String File) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String GetConnectedMcu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> GetConfiguretProgrammers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void SetMCU(String mcu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
