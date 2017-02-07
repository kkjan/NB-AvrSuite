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
public interface AvrProgrammer {
    AvrProgrammer AvrProgrammer(String Name);
    void SetProfile(String Name);
    String GetName();
    String GetMcuSignature();
    List<String> GetSuportedMCUs();
    String GetUploadCmd(String File);
    String GetConnectedMcu();
    List<String> GetConfiguretProgrammers(); 
    void SetMCU(String mcu);
       
    
}
