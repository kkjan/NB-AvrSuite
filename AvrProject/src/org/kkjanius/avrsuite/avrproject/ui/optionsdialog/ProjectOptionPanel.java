/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.ui.optionsdialog;

import java.util.Collection;
import java.util.List;
import org.kkjanius.avrsuite.avrproject.projectconfig.ProjectConfig;
import org.kkjanius.avrsuite.api.avrprogrammer.AvrProgrammer;
import org.openide.util.ChangeSupport;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;

final class ProjectOptionPanel extends javax.swing.JPanel {

    private final ProjectOptionPanelController controller;
     private final ChangeSupport changeSupport = new ChangeSupport(this);
    ProjectOptionPanel(ProjectOptionPanelController controller) {
        this.controller = controller;
        initComponents();
       
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ActiveProgramerLabel = new javax.swing.JLabel();
        ActiveProgramerComboBox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        ActiveProgramerLabel.setLabelFor(ActiveProgramerComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(ActiveProgramerLabel, org.openide.util.NbBundle.getMessage(ProjectOptionPanel.class, "ProjectOptionPanel.ActiveProgramerLabel.text")); // NOI18N

        ActiveProgramerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ActiveProgramerComboBox.setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxx");

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ProjectOptionPanel.class, "ProjectOptionPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ActiveProgramerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ActiveProgramerComboBox, 0, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ActiveProgramerLabel)
                    .addComponent(ActiveProgramerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.load();
    }//GEN-LAST:event_jButton1ActionPerformed

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(ProjectOptionPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(ProjectOptionPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        ActiveProgramerComboBox.removeAllItems();
        Collection<? extends AvrProgrammer>prgmList=Lookup.getDefault().lookupAll(AvrProgrammer.class);
        if(prgmList!=null){
            prgmList.forEach((prgm) -> {
                prgm.GetConfiguretProgrammers().forEach((strPrgm) -> {
                    ActiveProgramerComboBox.addItem(strPrgm);
                });
 });
        }
        ActiveProgramerComboBox.setSelectedItem(NbPreferences.forModule(ProjectConfig.class).get("prgm.default", ""));
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(ProjectOptionPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(ProjectOptionPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        if (ActiveProgramerComboBox.getSelectedItem()!=null){
            NbPreferences.forModule(ProjectConfig.class).put("prgm.default", (String)ActiveProgramerComboBox.getSelectedItem());
        }
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    public void setProfiles(List<String> prof){
        ActiveProgramerComboBox.removeAllItems();
        prof.forEach((str) -> {
            ActiveProgramerComboBox.addItem(str);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ActiveProgramerComboBox;
    private javax.swing.JLabel ActiveProgramerLabel;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
