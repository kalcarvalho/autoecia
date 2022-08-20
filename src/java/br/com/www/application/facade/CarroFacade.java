/*
 * CarroFacade.java
 *
 * Created on 15 de Fevereiro de 2007, 19:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.facade;

import br.com.www.application.database.CarroDatabaseExec;
import br.com.www.application.database.CarroDatabaseFinder;
import br.com.www.application.database.JdbcFactory;
import br.com.www.application.domain.Carro;
import br.com.www.application.util.MessageConstants;
import br.com.www.support.database.DatabaseAbstractFactory;
import br.com.www.support.exception.BusinessException;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.util.Collection;

/**
 *
 * @author Administrador
 */
public class CarroFacade implements MessageConstants {
    
    /** Creates a new instance of CarroFacade */
    public CarroFacade() {
    }
    
    public Collection listDestaques() {
        Connection conn = null;
        
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        try {
            conn = factory.openConnection();
            CarroDatabaseFinder  finder = (CarroDatabaseFinder) factory.
                    createFinder(CarroDatabaseFinder.class);
            Collection coll = finder.findByParameter(conn, "car_destaque", "1");
            return coll;
            
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }

    public Carro detalhar(Carro carro) throws BusinessException {
        Connection conn = null;
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        
        try {
        	conn = factory.openConnection();
                CarroDatabaseFinder finder = (CarroDatabaseFinder) factory.
                                               createFinder(CarroDatabaseFinder.class);
        	carro = (Carro) finder.findByPK(conn,  Integer.toString(carro.getCodigo()));

        	
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
        
        return carro;
    }
    
    public void updateVisitas(Carro carro) {
        Connection conn = null;
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        
        try {
        	conn = factory.openConnection();
                CarroDatabaseExec exec = (CarroDatabaseExec) factory.
                                               createExec(CarroDatabaseExec.class);
        	carro = (Carro) exec.updateVisita(conn, carro);

        	
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
    
    public void updateProposta(Carro carro) {
        Connection conn = null;
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        
        try {
        	conn = factory.openConnection();
                CarroDatabaseExec exec = (CarroDatabaseExec) factory.
                                               createExec(CarroDatabaseExec.class);
        	carro = (Carro) exec.updateProposta(conn, carro);

        	
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }
    
}
