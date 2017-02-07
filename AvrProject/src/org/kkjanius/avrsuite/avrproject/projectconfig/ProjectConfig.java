/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.projectconfig;

/**
 *
 * @author janschml
 */
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProjectConfig {

 
    private static class command {
        public String Switch;
        public String Value;
        
        public command() {
        }
         public command(String Switch, String Value) {
             this.Switch=Switch;
             this.Value=Value;
        }
    }
    


 
  private final String PROJ_CFG_FILENAME="configurations.xml";
  private final String PROJ_PRIVATE_CFG_FILENAME="configurations.xml";
  private String path;

public ProjectConfig(String path){
    this.path=path;
}
    

public void NewMMCUcfg(){

    
}

    
 public void updateToolchain(String Toolchain){
     String FilePath=path+"/nbproject/"+PROJ_CFG_FILENAME;
     
     ModifyTagsXML("compilerSet",Toolchain+"|"+Toolchain,FilePath);  
      
  }
 
 public void updateRunCommand(String RunCommand){
     String FilePath=path+"/nbproject/private/"+PROJ_PRIVATE_CFG_FILENAME;
     ModifyTagsXML("runcommand",RunCommand,FilePath);
      
  }
 
  public void updatePlatform(String platform){
     String FilePath=path+"/nbproject/private/"+PROJ_CFG_FILENAME;
     
     ModifyTagsXML("platform",platform,FilePath);  
      
  }
 public void SetCmdXML(String Switch, String value){
    String FilePath=path+"/nbproject/"+PROJ_CFG_FILENAME;
    ConfFileXML ConfFile=new ConfFileXML(FilePath);
    NodeList nl=ConfFile.GetNode("cTool");
   
    
    for (int i=0;i<nl.getLength();i++){
        Node node=nl.item(i);
        String cmdLine=GetTagValuesXML("commandLine",node.getChildNodes());
        String commandLine=SetCommandLine(cmdLine,Switch,value);
                ModifyChildTagsXML("commandLine","cTool",commandLine,FilePath);
     
    }
      
  }


 
 private void ModifyChildTagsXML(String tag,String parent, String value, String FilePath){
              
              ConfFileXML ConfFile=new ConfFileXML(FilePath);
    NodeList nl = ConfFile.GetNode(parent); 
    boolean isExist;
    for (int i=0; i<nl.getLength();i++){
        isExist=false;
     Node e=nl.item(i);
     NodeList child=e.getChildNodes();
     for (int j=0; j<child.getLength();j++){
           String name=child.item(j).getNodeName(); 
     if (name.equals(tag)){
         isExist=true;
        child.item(j).setTextContent(value); 
     }
   
     }
     
     if (!isExist){
//TODO: Dokoncit pridanie commandLine do xml
     // e.appendChild(e)
    }
     }
    ConfFile.Save();
 }
    
    
    
            
         
 
    
  private void ModifyTagsXML(String tag,String value, String FilePath){
    ConfFileXML ConfFile=new ConfFileXML(FilePath);
    NodeList nl = ConfFile.GetNode(tag);  
    for (int i=0; i<nl.getLength();i++){
     Node e=nl.item(i);
    e.setTextContent(value);
     }
    
    ConfFile.Save();        
          
 }
  
  
  private String GetTagValuesXML(String tag,NodeList nl){
              
 String val=null;
 int size=nl.getLength();
    for (int i=0; i<size;i++){
 
     Node e=nl.item(i);
     if (e.getNodeName().equals(tag)){
        val=(e.getTextContent());
     }
     }
            
     return val;
 
}
      
  private List<command> ComandLineParse(String cmdLine){
      List<command> cmd=new ArrayList<>();
      String [] cmdSwitches=cmdLine.split(" ");
       boolean isExist=false;
       for (String cmd1 : cmdSwitches){
           if(cmd1.startsWith("-")){
             if(cmd1.contains("=")){  
               String[] cmd2=cmd1.split("=");
               cmd.add(new command(cmd2[0],cmd2[1]));
             }else{
                 cmd.add(new command(cmd1,""));
             }
           }
           
       }
      return cmd;
  }
   private String SetCommandLine(String cmdLine, String Switch,String value){
       String Output="";
       List<command> cmd=ComandLineParse(cmdLine);
      
       boolean isExist=false;
       for (int i=0;i<cmd.size();i++){
           command md1=cmd.get(i);
           if(md1.Switch.equals(Switch)){
               isExist=true;
               md1.Value=value;
               cmd.set(i, md1);
               
           }
         if(md1.Value.length()>0)
           Output=Output+" "+md1.Switch+"="+md1.Value;
         else
             Output=Output+" "+md1.Switch;
       }
       if(!isExist){
           cmd.add(new command(Switch,value));
         if(value.length()>0)
           Output=Output+" "+Switch+"="+value;
         else
             Output=Output+" "+Switch;
       }
       return Output;
   }
   
   
   public String getMCU(){
       String mcu="";
       String FilePath=path+"/nbproject/"+PROJ_CFG_FILENAME;
    ConfFileXML ConfFile=new ConfFileXML(FilePath);
    NodeList nl=ConfFile.GetNode("cTool");
   
    
  Node node=nl.item(0);
        String cmdLine=GetTagValuesXML("commandLine",node.getChildNodes());
        List<command> commandLine=ComandLineParse(cmdLine);
               
    for(command md:commandLine){
        if (md.Switch.equals("-mmcu"))
            mcu=md.Value;
    }
    return mcu;
   }
   
   public String getCompilerSet(){
      String cs;
       String FilePath=path+"/nbproject/"+PROJ_CFG_FILENAME;
    ConfFileXML ConfFile=new ConfFileXML(FilePath);

       NodeList nl =ConfFile.GetNode("compilerSet");
        
        cs=nl.item(0).getTextContent();
        return cs.substring(0, cs.indexOf("|"));
       
   }
}

 
