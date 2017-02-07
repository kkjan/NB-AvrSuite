/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ImplAvrProjectProgrammer;

import java.util.List;
import java.util.Observable;

/**
 *
 * @author janschml
 */
public class AvrDudeRunnable extends Observable implements Runnable {

    private String command;
    private List<String> out;
    private String cmdName;
    private AvrDudeExitStatus status;
    public AvrDudeRunnable(String cmdName, String command) {
        
        
        this.command = command;
        this.cmdName=cmdName;
    }


    @Override
    public void run() {
        int exitValue=PrgmHW.getInstance().run_command(this.command, this.out);
        setChanged();
        if(exitValue==0){
            status.failed=false;
            status.status="DONE...";
            status.command=cmdName;
            status.textInfo=cmdName+" "+status.status;
        notifyObservers(status);
        }else{
            status.failed=true;
            status.status="FAILED...";
            status.command=cmdName;
            status.textInfo=cmdName+" "+status.status;
           notifyObservers(status); 
        }
    }

}
