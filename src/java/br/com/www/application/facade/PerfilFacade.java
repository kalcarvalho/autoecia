package br.com.www.application.facade;

import java.util.Collection;

import br.com.www.application.database.JdbcFactory;
import br.com.www.application.database.PerfilDatabaseFinder;

import br.com.www.support.database.DatabaseAbstractFactory;
import java.sql.Connection;

import br.com.www.support.exception.SystemException;
import br.com.www.application.util.MessageConstants;


public class PerfilFacade implements MessageConstants {

    public PerfilFacade() {}

    public Collection listAllAtivos() {
    	
    	Connection conn = null;
    	Collection coll = null;
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
    	try {
        	conn = factory.openConnection();
            PerfilDatabaseFinder finder = (PerfilDatabaseFinder) factory.
                                               createFinder(PerfilDatabaseFinder.class);
        	coll = finder.listAllAtivos(conn);       
        	        	
            return coll;
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
}
