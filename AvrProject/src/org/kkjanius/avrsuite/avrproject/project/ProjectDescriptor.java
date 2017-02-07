/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.project;

import org.netbeans.api.project.Project;

/**
 *
 * @author janschml
 */
public class ProjectDescriptor {
     Project proj;
    ProjectDescriptor() {
        proj=org.netbeans.api.project.ui.OpenProjects.getDefault().getMainProject();
                }
}
