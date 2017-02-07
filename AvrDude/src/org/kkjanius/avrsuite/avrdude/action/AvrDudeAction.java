/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.action;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.kkjanius.avrsuite.avrdude.ui.action.AvrDudeTool;
import org.netbeans.api.project.Project;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(category = "AVR",
id = "org.kkjanius.avrsuite.avrdude.ui.avrdudeaction.AvrDudeAction")
@ActionRegistration(displayName = "#CTL_AvrDudeAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools/AVR Suite", position = 200)
})
@Messages("CTL_AvrDudeAction=AvrDude")
public final class AvrDudeAction implements ActionListener {
    
    private final Project context;
    private final Frame f= WindowManager.getDefault().getMainWindow();
    private final AvrDudeTool avrDudeTool;
    
    public AvrDudeAction(Project context) {
        this.context = context;
        
         avrDudeTool=new AvrDudeTool(f,false);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        avrDudeTool.setVisible(true);
    }
}
