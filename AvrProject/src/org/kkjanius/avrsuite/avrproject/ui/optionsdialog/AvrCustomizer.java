/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.ui.optionsdialog;

import org.netbeans.modules.cnd.makeproject.api.ui.configurations.CustomizerNode;

import org.openide.nodes.Sheet;
import org.openide.util.Lookup;

/**
 *
 * @author janschml
 */
public class AvrCustomizer extends CustomizerNode {
    
        public AvrCustomizer(String name, String displayName, CustomizerNode[] children, Lookup lookup) {
        super(name, displayName, children, lookup);
    }
    public Sheet getRequiredProjectsSheet() {
        Sheet sheet = new Sheet();
       

        Sheet.Set set2 = new Sheet.Set();
        set2.setName("AVR Hardware"); // NOI18N
        set2.setDisplayName("Avr");
        set2.setShortDescription("AVR");
        //set2.put(new RequiredProjectsNodeProp(getRequiredProjectsConfiguration(), project, conf, getBaseFSPath(), texts));
        sheet.put(set2);

        return sheet;
    }

    
}

