    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.ui.avrdudeoptions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(location = "AVR",
displayName = "#AdvancedOption_DisplayName_AvrDude",
keywords = "#AdvancedOption_Keywords_AvrDude",
keywordsCategory = "AVR/AvrDude",
position=300)
public final class AvrdudePanelController extends OptionsPanelController {

    private AvrDudePanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean changed;
    private Lookup lkp=null;
    
    @Override
    public void update() {
        getPanel().load();
        changed = false;
//        ProjectOptionPanelController propc=lkp.lookup(ProjectOptionPanelController.class);
//        propc.update();
    }

    @Override
    public void applyChanges() {
     
        getPanel().store();
        changed = false;
    }

    @Override
    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    @Override
    public boolean isValid() {
        return getPanel().valid();
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    @Override
    public JComponent getComponent(Lookup masterLookup) {
        lkp=masterLookup;
        
        return getPanel();
        
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private AvrDudePanel getPanel() {
        if (panel == null) {
            panel = new AvrDudePanel(this);
        }
        return panel;
    }

    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
        
    }
    
    @Override
    public Lookup getLookup(){
        
        return  lkp;
    }

}
