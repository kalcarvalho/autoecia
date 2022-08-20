/*
 * FuncaoFacade.java
 *
 * Created on 29 de Janeiro de 2007, 16:44
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.facade;

import br.com.www.application.database.JdbcFactory;
import br.com.www.application.database.FuncaoDatabaseFinder;

import br.com.www.support.database.DatabaseAbstractFactory;
import java.sql.Connection;
import java.util.Collection;

import br.com.www.support.exception.SystemException;
import br.com.www.application.util.MessageConstants;
import br.com.www.application.domain.Funcao;

/**
 *
 * @author kalcarvalho
 */
public class FuncaoFacade implements MessageConstants {
    
    /** Creates a new instance of FuncaoFacade */
    public FuncaoFacade() {
    }
    
    public Funcao findByParameter(String par) {
    	
    	Connection conn = null;
    	
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
    	try {
        	conn = factory.openConnection();
            FuncaoDatabaseFinder finder = (FuncaoDatabaseFinder) factory.
                                               createFinder(FuncaoDatabaseFinder.class);
        
            
            return (finder.findByParameter(conn, par));       
        
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
    
}
