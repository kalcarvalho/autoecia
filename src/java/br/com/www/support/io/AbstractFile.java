/*
 * NewInterface.java
 *
 * Created on 18 de Janeiro de 2007, 10:40
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.support.io;
import java.io.*;

/**
 *
 * @author kalcarvalho
 */
public abstract class AbstractFile {
    
    protected FileWriter filewriter;
    protected File file;
    
    public abstract void save(Object object);
    public abstract Object load();
    
     
    
}
