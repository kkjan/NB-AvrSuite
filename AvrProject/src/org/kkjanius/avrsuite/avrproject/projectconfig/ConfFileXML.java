/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrproject.projectconfig;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author janschml
 */


 
public class ConfFileXML {
    
     private String  path;
 private DocumentBuilderFactory  ProjDbf;
 private File ProjCfgFullFile;
 private  DocumentBuilder ProjBuilder;
 private  Document ProjDoc;
 
  
    public ConfFileXML(String FilePath){
     try{
        this.ProjDbf=DocumentBuilderFactory.newInstance();
        this.ProjDbf=DocumentBuilderFactory.newInstance();
    this.ProjCfgFullFile=new File(FilePath);
    this.ProjBuilder=ProjDbf.newDocumentBuilder();
    this.ProjDoc=ProjBuilder.parse(ProjCfgFullFile);
    }catch(IOException | ParserConfigurationException | SAXException e){
           Exceptions.printStackTrace(e);
       }
    }
    
    public NodeList GetNode(String NodeName){
        return ProjDoc.getElementsByTagName(NodeName); 
    }
    
    public void UpdateNodeValue(String NodeName){
        
    }
    
    public void AddNode(String NodeName){
        
    }    
    public void Save(){
        try{
            TransformerFactory tf=TransformerFactory.newInstance();
      Transformer writer = tf.newTransformer();
      writer.transform(new DOMSource(ProjDoc), new StreamResult(ProjCfgFullFile));
        }catch(TransformerException e){
           Exceptions.printStackTrace(e);
       } 
    }
    
}
