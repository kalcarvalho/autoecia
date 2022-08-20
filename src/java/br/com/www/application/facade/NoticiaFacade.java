/*
 * NoticiaFacade.java
 *
 * Created on 1 de Fevereiro de 2007, 13:14
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.facade;

import br.com.www.application.database.NoticiaDatabaseFinder;
import br.com.www.application.util.MessageConstants;
import br.com.www.support.database.DatabaseAbstractFactory;
import br.com.www.application.database.JdbcFactory;
import br.com.www.application.domain.Noticia;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.util.Collection;

/**
 *
 * @author kalcarvalho
 */
public class NoticiaFacade implements MessageConstants {
    
    /** Creates a new instance of NoticiaFacade */
    public NoticiaFacade() {
    }
    
    public Noticia findByPK(String pk) {
    	
    	Connection conn = null;
        
    	
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
    	try {
        	conn = factory.openConnection();
            NoticiaDatabaseFinder finder = (NoticiaDatabaseFinder) factory.
                                               createFinder(NoticiaDatabaseFinder.class);
        
            Noticia noticia = (Noticia) finder.findByPK(conn, pk);
            return noticia;       
        
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
    
    public Collection listNoticias() {
        
        Connection conn = null;
    	
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
    	try {
        	conn = factory.openConnection();
            NoticiaDatabaseFinder finder = (NoticiaDatabaseFinder) factory.
                                               createFinder(NoticiaDatabaseFinder.class);
        
            
            return (finder.listAll(conn));       
            
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
        
        
        
    }
}
