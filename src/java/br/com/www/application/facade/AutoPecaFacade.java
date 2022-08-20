/*
 * AutoPecaFacade.java
 *
 * Created on 26 de Fevereiro de 2007, 12:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.facade;

import br.com.www.application.database.AutoPecaDatabaseFinder;
import br.com.www.application.database.JdbcFactory;
import br.com.www.application.util.MessageConstants;
import br.com.www.support.database.DatabaseAbstractFactory;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.util.Collection;

/**
 *
 * @author kalcarvalho
 */
public class AutoPecaFacade implements MessageConstants {
    
    /**
     * Creates a new instance of AutoPecaFacade
     */
    public AutoPecaFacade() {
        
    }
    
    public Collection listAtivos() {
        Connection conn = null;
        
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        try {
            conn = factory.openConnection();
            AutoPecaDatabaseFinder  finder = (AutoPecaDatabaseFinder) factory.
                    createFinder(AutoPecaDatabaseFinder.class);
            Collection coll = finder.listAll(conn);
            return coll;
            
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
    
}
