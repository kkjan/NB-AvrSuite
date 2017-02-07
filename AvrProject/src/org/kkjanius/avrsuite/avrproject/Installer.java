/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import org.openide.modules.ModuleInstall;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        System.out.println("restoring"); 
        initFriends();
        super.restored();
    }

    @Override
    public void validate() throws IllegalStateException {
        System.out.println("validating"); 
        initFriends();
        
    }
private void initFriends() throws IllegalStateException {
        try {
            Class<?> main = Class.forName("org.netbeans.core.startup.Main", false, Thread.currentThread().getContextClassLoader());
            Method getModuleSystem = main.getMethod("getModuleSystem"); //NOI18N
            Object moduleSystem = getModuleSystem.invoke(null, new Object[0]);
            Method getManager = moduleSystem.getClass().getMethod("getManager"); //NOI18N
            Object moduleManager = getManager.invoke(moduleSystem, new Object[0]);
            Method moduleMeth = moduleManager.getClass().getMethod("get", String.class); //NOI18N
               super.restored(); 
            // Let's became a friend of Common Tests Runner API
            Object persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.api.project"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewriter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.api.remote"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
             // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.makeproject"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.remote"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.toolchain"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd.utils"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.cnd"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
            // Let's became a friend of C/C++ Tests Runner
            persistence = moduleMeth.invoke(moduleManager, "org.netbeans.modules.dlight.nativeexecution"); //NOI18N
            if (persistence != null) {
                Field frField = persistence.getClass().getSuperclass().getDeclaredField("friendNames"); //NOI18N
                frField.setAccessible(true);
                @SuppressWarnings(value = "unchecked")
                Set<String> friends = (Set<String>) frField.get(persistence);
                if(friends == null) {
                    friends = new HashSet<String>();
                }
                friends.add("org.kkjanius.avrsuite.avrproject.makefilewritter"); //NOI18N
                friends.add("org.kkjanius.avrsuite.avrproject.avrnewprojectwizard"); 
                friends.add("org.kkjanius.avrsuite.avrproject.toolchain");
                friends.add("org.kkjanius.avrsuite.avrproject");
            }
                       
        } catch (Exception ex) {
            new IllegalStateException("Cannot fix dependencies for unit.test.output.handler.example.", ex); //NOI18N
        }
    }
}
