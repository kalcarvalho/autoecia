/*
 * XMLFile.java
 *
 * Created on 18 de Janeiro de 2007, 10:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.io;

import br.com.www.support.io.AbstractFile;
import java.io.*;
import java.util.*;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.jdom.Document;




/* import org.w3c.dom.Element;
import org.w3c.dom.Document; */



/**
 *
 * @author kalcarvalho
 */
public class XMLMenuFile extends AbstractFile {
    
    private Element menu;
    private Element options;
    private Collection buttons = new ArrayList();
    String xml = "";
    File dir;
    File swf;
    
    Document document;
    
    /** Creates a new instance of XMLFile */
    public XMLMenuFile(String xml, String swf, String dir) {
        String dest;
        
        this.dir = new File(dir);
        file = new File(xml);
        this.swf = new File(swf);
        this.dir.mkdir();
        dest = this.dir + "\\" + this.swf.getName();
        
        this.swf.renameTo(new File(dest));
        
    }
    
    public void setAttribute(Object object, String name, String value) {
        
        Element element = (Element) object;
        element.setAttribute(name,value);
        
    }
    
    public void addContent(Object parent, Object node) {
        Element element = (Element) parent;
        element.addContent((Element) node);
    }
    
    public void save(Object object) {
        
        document = new Document();
        document.setRootElement((Element) object);
        
        XMLOutputter xout = new XMLOutputter();
        
        
        try {
            
            filewriter = new FileWriter(file);
            
            xout.output(document, filewriter);
            filewriter.close();
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public Object load() {
        return null;
    }
    
    public Element getMenu() {
        return menu;
    }
    
    public void setMenu(Element menu) {
        this.menu = menu;
    }
    
    public Element getOptions() {
        return options;
    }
    
    public void setOptions(Element options) {
        this.options = options;
    }
    
    public Element getButton(int index) {
        return ((Element) buttons.toArray()[index-1]);
    }
    
    public int addButton(Element button) {
        if (buttons != null) {
            buttons.add(button);
        }
        
        return buttons.size();
    }
    
    public void finalize() throws Throwable {
        
      /*  file.delete();
        dir.delete(); */
        
    }
    
}
