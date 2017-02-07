/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ImplAvrProjectProgrammer;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.NbPreferences;

/**
 *
 * @author janschml
 */
public class AvrDudeProfiles {
    public static List<String> getAllProfiles(){
        List<String> ret= new ArrayList<String>();
        String tmp =NbPreferences.forModule(AvrDudeProfile.class).node("profiles").get("Profiles", "");
        String [] Profiles=tmp.split("\";\"");
    
        for (String Profile:Profiles){
            if (!Profile.isEmpty())
                ret.add(Profile);
        }
        return ret;
     }
     
     public static void DeleteOptions(String ProfileName){
         
         List<String> Profiles=new ArrayList<String>();
         Profiles=AvrDudeProfiles.getAllProfiles();
        
         int index=Profiles.indexOf(ProfileName);
         Profiles.remove(index);
         
         String strProfiles="";
         for (String tmp:Profiles){
             if(!tmp.isEmpty())
                strProfiles=strProfiles+tmp+"\";\"";
         }
       
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").put("Profiles", strProfiles);
       
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".ExecLocation");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".ConfPath");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".Programmer");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".Port");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".BaudRate");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".Bitlock");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".Delay");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".ExtraParams");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".VerifyCheckDis");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".SignCheckDis");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".SimulationMode");
        NbPreferences.forModule(AvrDudeProfile.class).node("profiles").remove("Profile."+ProfileName+".AutoEraseFlashDis");
        
    }
}
