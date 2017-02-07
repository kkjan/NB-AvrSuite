/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.action;

import org.kkjanius.avrsuite.avrproject.ui.action.McuSelector;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.netbeans.api.project.Project;

import org.openide.windows.WindowManager;
public final class AvrMainAction implements ActionListener {

    private final Project context;
    private Frame f=WindowManager.getDefault().getMainWindow();
    private McuSelector mcuSelector;
    public AvrMainAction(Project context) {

        this.context = context;
      mcuSelector=new McuSelector(f,true,context);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        mcuSelector.setVisible(true);

   
    }
}
