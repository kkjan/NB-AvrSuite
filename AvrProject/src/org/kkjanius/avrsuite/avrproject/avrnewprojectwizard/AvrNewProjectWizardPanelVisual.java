/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.avrnewprojectwizard;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.Preferences;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.kkjanius.avrsuite.avrproject.mcuhardware.AvrMcu;
import org.kkjanius.avrsuite.avrproject.project.GlobalConstant;
import org.kkjanius.avrsuite.avrproject.projectconfig.ProjectConfig;
import org.kkjanius.avrsuite.api.avrprogrammer.AvrProgrammer;
import org.netbeans.api.options.OptionsDisplayer;
import org.netbeans.modules.cnd.api.toolchain.CompilerSet;
import org.netbeans.modules.cnd.api.toolchain.CompilerSetManager;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironmentFactory;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

public class AvrNewProjectWizardPanelVisual extends JPanel implements DocumentListener {

    public static final String PROP_PROJECT_NAME = "projectName";
    private final AvrNewProjectWizardWizardPanel panel;
    Preferences pref = NbPreferences.forModule(ProjectConfig.class);

    public AvrNewProjectWizardPanelVisual(AvrNewProjectWizardWizardPanel panel) {
        initComponents();
        this.panel = panel;
        // Register listener on the textFields to make the automatic updates
        projectNameTextField.getDocument().addDocumentListener(this);
        projectLocationTextField.getDocument().addDocumentListener(this);
        pref.addPreferenceChangeListener((PreferenceChangeEvent evt) -> {
            if (evt.getKey().equals("prgm.default")) {
                jTextFieldAvrPrgmProfile.setText(evt.getNewValue());
            }
        });
    }

    public String getProjectName() {
        return this.projectNameTextField.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectNameLabel = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        projectLocationLabel = new javax.swing.JLabel();
        projectLocationTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        createdFolderLabel = new javax.swing.JLabel();
        createdFolderTextField = new javax.swing.JTextField();
        frequencyField = new javax.swing.JTextField();
        frequencyLabel = new javax.swing.JLabel();
        hertzLabel = new javax.swing.JLabel();
        mcuLabel = new javax.swing.JLabel();
        mcuComboBox = new javax.swing.JComboBox();
        GetConnectedjButton = new javax.swing.JButton();
        jComboBoxToolchain = new javax.swing.JComboBox();
        jLabelToolchain = new javax.swing.JLabel();
        jLabelAvrDudeProfile = new javax.swing.JLabel();
        jTextFieldAvrPrgmProfile = new javax.swing.JTextField();
        ProfilesButton = new javax.swing.JButton();

        projectNameLabel.setLabelFor(projectNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectNameLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.projectNameLabel.text")); // NOI18N

        projectLocationLabel.setLabelFor(projectLocationTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectLocationLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.projectLocationLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseButton, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.browseButton.text")); // NOI18N
        browseButton.setActionCommand(org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.browseButton.actionCommand")); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        createdFolderLabel.setLabelFor(createdFolderTextField);
        org.openide.awt.Mnemonics.setLocalizedText(createdFolderLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.createdFolderLabel.text")); // NOI18N

        createdFolderTextField.setEditable(false);

        frequencyField.setText(org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.frequencyField.text")); // NOI18N

        frequencyLabel.setLabelFor(frequencyField);
        org.openide.awt.Mnemonics.setLocalizedText(frequencyLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.frequencyLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(hertzLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.hertzLabel.text")); // NOI18N

        mcuLabel.setLabelFor(mcuComboBox);
        org.openide.awt.Mnemonics.setLocalizedText(mcuLabel, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.mcuLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(GetConnectedjButton, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.GetConnectedjButton.text")); // NOI18N
        GetConnectedjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetConnectedjButtonActionPerformed(evt);
            }
        });

        jComboBoxToolchain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxToolchainActionPerformed(evt);
            }
        });

        jLabelToolchain.setLabelFor(jComboBoxToolchain);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelToolchain, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.jLabelToolchain.text")); // NOI18N

        jLabelAvrDudeProfile.setLabelFor(jTextFieldAvrPrgmProfile);
        org.openide.awt.Mnemonics.setLocalizedText(jLabelAvrDudeProfile, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.jLabelAvrDudeProfile.text")); // NOI18N

        jTextFieldAvrPrgmProfile.setEditable(false);
        jTextFieldAvrPrgmProfile.setText(org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.jTextFieldAvrPrgmProfile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ProfilesButton, org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.ProfilesButton.text")); // NOI18N
        ProfilesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfilesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectNameLabel)
                    .addComponent(projectLocationLabel)
                    .addComponent(createdFolderLabel)
                    .addComponent(frequencyLabel)
                    .addComponent(mcuLabel)
                    .addComponent(jLabelToolchain)
                    .addComponent(jLabelAvrDudeProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frequencyField)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(projectLocationTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(createdFolderTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(mcuComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxToolchain, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldAvrPrgmProfile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(hertzLabel)
                        .addComponent(GetConnectedjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProfilesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(browseButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectLocationLabel)
                    .addComponent(projectLocationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdFolderLabel)
                    .addComponent(createdFolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxToolchain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelToolchain))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frequencyLabel)
                    .addComponent(hertzLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mcuLabel)
                    .addComponent(mcuComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GetConnectedjButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAvrPrgmProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAvrDudeProfile)
                    .addComponent(ProfilesButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mcuLabel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "AvrNewProjectWizardPanelVisual.mcuLabel.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        String command = evt.getActionCommand();
        if ("BROWSE".equals(command)) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select Project Location");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String path = this.projectLocationTextField.getText();
            if (path.length() > 0) {
                File f = new File(path);
                if (f.exists()) {
                    chooser.setSelectedFile(f);
                }
            }
            if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(this)) {
                File projectDir = chooser.getSelectedFile();
                projectLocationTextField.setText(FileUtil.normalizeFile(projectDir).getAbsolutePath());
            }
            panel.fireChangeEvent();
        }

    }//GEN-LAST:event_browseButtonActionPerformed

    private void GetConnectedjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetConnectedjButtonActionPerformed

        AvrProgrammer prgm = Lookup.getDefault().lookup(AvrProgrammer.class).AvrProgrammer(NbPreferences.forModule(ProjectConfig.class).get("prgm.default", ""));
        String Mcu = prgm.GetConnectedMcu();
        mcuComboBox.setSelectedItem(Mcu);
    }//GEN-LAST:event_GetConnectedjButtonActionPerformed

    private void ProfilesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfilesButtonActionPerformed
        OptionsDisplayer.getDefault().open("AVR");
    }//GEN-LAST:event_ProfilesButtonActionPerformed

    private void jComboBoxToolchainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxToolchainActionPerformed
        Object selectedItem = jComboBoxToolchain.getSelectedItem();
        if (selectedItem instanceof ToolCollectionItem) {
            ToolCollectionItem tc = (ToolCollectionItem) selectedItem;
            CompilerSet cs = tc.getCompilerSet();
            updateMcuList(mcuComboBox, cs);
        }
    }//GEN-LAST:event_jComboBoxToolchainActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GetConnectedjButton;
    private javax.swing.JButton ProfilesButton;
    private javax.swing.JButton browseButton;
    private javax.swing.JLabel createdFolderLabel;
    private javax.swing.JTextField createdFolderTextField;
    private javax.swing.JTextField frequencyField;
    private javax.swing.JLabel frequencyLabel;
    private javax.swing.JLabel hertzLabel;
    private javax.swing.JComboBox jComboBoxToolchain;
    private javax.swing.JLabel jLabelAvrDudeProfile;
    private javax.swing.JLabel jLabelToolchain;
    private javax.swing.JTextField jTextFieldAvrPrgmProfile;
    private javax.swing.JComboBox mcuComboBox;
    private javax.swing.JLabel mcuLabel;
    private javax.swing.JLabel projectLocationLabel;
    private javax.swing.JTextField projectLocationTextField;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
        projectNameTextField.requestFocus();
    }

    boolean valid(WizardDescriptor wizardDescriptor) {

        if (projectNameTextField.getText().length() == 0) {
            // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_ERROR_MESSAGE:
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Project Name is not a valid folder name.");
            return false; // Display name not specified
        }
        File f = FileUtil.normalizeFile(new File(projectLocationTextField.getText()).getAbsoluteFile());
        if (!f.isDirectory()) {
            String message = "Project Folder is not a valid path.";
            wizardDescriptor.putProperty("WizardPanel_errorMessage", message);
            return false;
        }
        final File destFolder = FileUtil.normalizeFile(new File(createdFolderTextField.getText()).getAbsoluteFile());

        File projLoc = destFolder;
        while (projLoc != null && !projLoc.exists()) {
            projLoc = projLoc.getParentFile();
        }
        if (projLoc == null || !projLoc.canWrite()) {
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Project Folder cannot be created.");
            return false;
        }

        if (FileUtil.toFileObject(projLoc) == null) {
            String message = "Project Folder is not a valid path.";
            wizardDescriptor.putProperty("WizardPanel_errorMessage", message);
            return false;
        }

        File[] kids = destFolder.listFiles();
        if (destFolder.exists() && kids != null && kids.length > 0) {
            // Folder exists and is not empty
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Project Folder already exists and is not empty.");
            return false;
        }
        
        if(mcuComboBox.getSelectedItem()==null){
            wizardDescriptor.putProperty("WizardPanel_errorMessage", "Please select correct avr target device and change Tool collection.");
            return false;
        }
        wizardDescriptor.putProperty("WizardPanel_errorMessage", "");
        return true;
    }

    void store(WizardDescriptor d) {
        String name = projectNameTextField.getText().trim();
        String folder = createdFolderTextField.getText().trim();

        d.putProperty("projdir", new File(folder));
        d.putProperty("name", name);
        Object selectedItem = jComboBoxToolchain.getSelectedItem();
        if (selectedItem instanceof ToolCollectionItem) {
            ToolCollectionItem item = (ToolCollectionItem) selectedItem;
            //d.putProperty(WizardConstants.PROPERTY_TOOLCHAIN, item.getCompilerSet());
            //d.putProperty(WizardConstants.PROPERTY_TOOLCHAIN_DEFAULT, item.isDefaultCompilerSet());
        }
        d.putProperty("mmcu", (String) mcuComboBox.getSelectedItem());
        d.putProperty("frequency", frequencyField.getText());
    }

    void read(WizardDescriptor settings) {
        File projectLocation = (File) settings.getProperty("projdir");
        if (projectLocation == null || projectLocation.getParentFile() == null || !projectLocation.getParentFile().isDirectory()) {
            projectLocation = ProjectChooser.getProjectsFolder();
        } else {
            projectLocation = projectLocation.getParentFile();
        }
        this.projectLocationTextField.setText(projectLocation.getAbsolutePath());

        String projectName = (String) settings.getProperty("name");
        if (projectName == null) {
            projectName = "AvrNewProjectWizard";
        }
        this.projectNameTextField.setText(projectName);
        this.projectNameTextField.selectAll();
        updateToolchains(jComboBoxToolchain);

        Object selectedItem = jComboBoxToolchain.getSelectedItem();
        if (selectedItem instanceof ToolCollectionItem) {
            ToolCollectionItem item = (ToolCollectionItem) selectedItem;
            updateMcuList(mcuComboBox, item.getCompilerSet());
        }


        jTextFieldAvrPrgmProfile.setText(NbPreferences.forModule(ProjectConfig.class).get("prgm.default", ""));
    }

    void validate(WizardDescriptor d) throws WizardValidationException {
        // nothing to validate
    }

    // Implementation of DocumentListener --------------------------------------
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateTexts(e);
        if (this.projectNameTextField.getDocument() == e.getDocument()) {
            firePropertyChange(PROP_PROJECT_NAME, null, this.projectNameTextField.getText());
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateTexts(e);
        if (this.projectNameTextField.getDocument() == e.getDocument()) {
            firePropertyChange(PROP_PROJECT_NAME, null, this.projectNameTextField.getText());
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateTexts(e);
        if (this.projectNameTextField.getDocument() == e.getDocument()) {
            firePropertyChange(PROP_PROJECT_NAME, null, this.projectNameTextField.getText());
        }
    }

    /**
     * Handles changes in the Project name and project directory,
     */
    private void updateTexts(DocumentEvent e) {

        Document doc = e.getDocument();

        if (doc == projectNameTextField.getDocument() || doc == projectLocationTextField.getDocument()) {
            // Change in the project name

            String projectName = projectNameTextField.getText();
            String projectFolder = projectLocationTextField.getText();

            //if (projectFolder.trim().length() == 0 || projectFolder.equals(oldName)) {
            createdFolderTextField.setText(projectFolder + File.separatorChar + projectName);
            //}

        }
        panel.fireChangeEvent(); // Notify that the panel changed
    }

    private void updateToolchains(JComboBox toolchainComboBox) {


        CompilerSetManager csm = CompilerSetManager.get(ExecutionEnvironmentFactory.getLocal());

        toolchainComboBox.removeAllItems();
        csm.getCompilerSets().stream().map((cs) -> {
            toolchainComboBox.addItem(new ToolCollectionItem(cs, false));
            return cs;
        }).filter((cs) -> ((cs.getName().equals(GlobalConstant.PROPERTY_LINUX_COMPILERSET)) || (cs.getName().equals(GlobalConstant.PROPERTY_WINDOWS_COMPILERSET)))).forEachOrdered((_item) -> {
            toolchainComboBox.setSelectedIndex(toolchainComboBox.getItemCount() - 1);
        });

    }
    /*
     * package
     */ private void updateMcuList(JComboBox mcuListComboBox, CompilerSet cs) {

        List<String> mcuList = AvrMcu.getMcuList(cs);
        mcuListComboBox.removeAllItems();
        mcuList.forEach((mcu) -> {
            mcuListComboBox.addItem(mcu);
        });


    }

    public final static class ToolCollectionItem {

        private final boolean defaultCompilerSet;
        private final CompilerSet compilerSet;

        private ToolCollectionItem(CompilerSet compilerSet, boolean defaultCompilerSet) {
            this.defaultCompilerSet = defaultCompilerSet;
            this.compilerSet = compilerSet;
        }
        private static ResourceBundle bundle;

        private static String getString(String s) {
            if (bundle == null) {
                bundle = NbBundle.getBundle(AvrNewProjectWizardPanelVisual.class);
            }
            return bundle.getString(s);
        }

        @Override
        public String toString() {
            String name = NbBundle.getMessage(AvrNewProjectWizardPanelVisual.class, "Toolchain_Name_Text", compilerSet.getName(), compilerSet.getDisplayName());

            return name;

        }

        public boolean isDefaultCompilerSet() {
            return defaultCompilerSet;
        }

        public CompilerSet getCompilerSet() {
            return compilerSet;
        }
    }
}
