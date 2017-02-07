/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kkjanius.avrsuite.avrdude.configfile;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;
import org.w3c.dom.*;

/**
 *
 * @author janschml
 */
public class ParseConfigFile {

    private String path;

    ParseConfigFile() {
    }

    ParseConfigFile(String path) {
        this.path = path;

    }

    public static void AvrDudeConf2XMl(String inpath, String outpath) throws FileNotFoundException, IOException {
        try {
            FileInputStream finstream = new FileInputStream(inpath);
            DataInputStream in = new DataInputStream(finstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element file = doc.createElement("avrdude-device-file");
            doc.appendChild(file);
            Element mculist = doc.createElement("mculist");
            file.appendChild(mculist);
            Element mcudetail = doc.createElement("mcudetail");
            file.appendChild(mcudetail);
            Element fuses=null;
             Element lockbyte=null;

            String strLine;
            InputOutput io = IOProvider.getDefault().getIO("AvrDude", false);
            io.select();
            OutputWriter w = io.getOut();
            boolean startPart = false;
            boolean startMemory = false;
            String id = "";
            String name = "";
            String signature = "";
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.replaceAll("\\s", "");
                if (strLine.startsWith("part")) {
                    startPart = true;
                    fuses = doc.createElement("fuses");
                    lockbyte = doc.createElement("lockbyte");
                }
                
                if (startPart && (strLine.startsWith("memory"))) {
                    startMemory = true;
                     String strMem = strLine.substring(strLine.indexOf("\""), strLine.lastIndexOf("\""));
                    if (strMem.contains("fuse")) {
                        Element fus = doc.createElement("fuse");
                        fus.setTextContent(strMem.replaceAll("\"", ""));//TODO setting defaul value 
                        Node appendChild1;
                        appendChild1 = fuses.appendChild(fus);

                    }
                    if (strMem.contains("lock")) {
                      
                        lockbyte.setTextContent(strMem.replaceAll("\"", ""));//TODO setting defaul value 
                        

                    }
                    
                }
                if (startMemory){
                   
                }
                if (startPart && (strLine.equals(";") && !startMemory)) {
                    startPart = false;

                    Element mcu = doc.createElement("mcu");
                    Element mcuDetail=doc.createElement("_"+name.toLowerCase());
                    mcu.setAttribute("id", id);
                    mcu.setAttribute("name", name.toLowerCase());
                    mcu.setAttribute("signature", signature.toLowerCase());




                   
                    mculist.appendChild(mcu);
                     mcuDetail.appendChild(fuses);
                     mcuDetail.appendChild(lockbyte);
                     mcudetail.appendChild(mcuDetail);
                    id = "";
                    name = "";
                    signature = "";


                }
                if (startPart && (strLine.equals(";") && startMemory)) {
                    startMemory = false;
                }
                if (startPart) {

                    String strUp = strLine.replaceAll(" ", "");
                    strUp = strUp.replaceAll("\"", "");
                    strUp = strUp.replaceAll(";", "");
                    if (strUp.startsWith("id=")) {
                        id = strUp.replaceAll("id=", "");
                    }
                    if (strUp.startsWith("signature=")) {
                        strUp = strUp.replaceAll("signature=", "");
                        strUp = strUp.replaceAll("0x", "");
                        signature = "0x" + strUp;
                    }
                    if (strUp.startsWith("desc=")) {
                        name = strUp.replaceAll("desc=", "");
                    }
                }
            }


            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer out = tf.newTransformer();
            out.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            out.setParameter(OutputKeys.ENCODING, "utf8");
            out.setOutputProperty(OutputKeys.INDENT, "yes");
            out.transform(new DOMSource(doc), new StreamResult(new File(outpath)));
            w.close();
            in.close();

        } catch (IOException e) {
            //Catch exception if any
            //Catch exception if any
        } catch (IllegalArgumentException e) {
            //Catch exception if any

        } catch (ParserConfigurationException e) {
            //Catch exception if any

        } catch (TransformerException e) {
            //Catch exception if any

        } catch (DOMException e) {
            //Catch exception if any

        }
    }
}
