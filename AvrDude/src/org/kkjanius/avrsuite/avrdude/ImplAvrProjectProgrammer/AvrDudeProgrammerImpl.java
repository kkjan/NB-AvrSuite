/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ImplAvrProjectProgrammer;

/**
 *
 * @author janschml
 */

import java.util.List;
import org.kkjanius.avrsuite.api.avrprogrammer.AvrProgrammer;
import org.kkjanius.avrsuite.api.avrprogrammer.MCu;
public class AvrDudeProgrammerImpl implements AvrProgrammer{
    
    private AvrDudeProfile Profile;
    
    @Override
    public void SetProfile(String name){
        this.Profile=new AvrDudeProfile(name);
        
    }
    @Override
    public String GetMcuSignature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    @Override
    public List<String> GetSuportedMCUs() {
      return  Profile.getSupportedMcu();
    }

    @Override
    public String GetUploadCmd(String File) {
       return Profile.getUploadCmd(File);
    }
    
    @Override
    public void SetMCU(String mcu) {
        Profile.setMCU(mcu);
    }

    @Override
    public String GetConnectedMcu() {
        return Profile.getConectedMCU("");
    }

    @Override
    public AvrDudeProgrammerImpl AvrProgrammer(String Name) {
        Profile=new AvrDudeProfile(Name);
        return this;
    }

    @Override
    public String GetName() {
        return Profile.getName();
    }
    @Override
    public List<String> GetConfiguretProgrammers() {
        return AvrDudeProfiles.getAllProfiles();
    }
    
}
