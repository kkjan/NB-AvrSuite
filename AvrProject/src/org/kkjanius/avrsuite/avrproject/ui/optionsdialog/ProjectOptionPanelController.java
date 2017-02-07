/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.ui.optionsdialog;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

@OptionsPanelController.SubRegistration(location = "AVR",
displayName = "#AdvancedOption_DisplayName_Projectoption",
keywords = "#AdvancedOption_Keywords_Projectoption",
keywordsCategory = "AVR/Projectoption",
position = 100)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_Projectoption=Project option", "AdvancedOption_Keywords_Projectoption=Project option"})
public final class ProjectOptionPanelController extends OptionsPanelController implements LookupListener {
 
    private ProjectOptionPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean changed;
//    private Lookup.Result<AvrdudePanelController> profiles=null;
    @Override
    public void update() {
        getPanel().load();
        changed = false;
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
//        profiles=masterLookup.lookupResult(AvrdudePanelController.class);
//        profiles.addLookupListener(this);
        resultChanged(null);
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

    private ProjectOptionPanel getPanel() {
        if (panel == null) {
            panel = new ProjectOptionPanel(this);         
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
    public void resultChanged(LookupEvent ev) {
             getPanel().load();
    }
    public PropertyChangeSupport getPropertChangeListener(){
        return pcs;
    }
}
