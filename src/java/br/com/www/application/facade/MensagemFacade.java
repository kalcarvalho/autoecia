/*
 * MensagemFacade.java
 *
 * Created on 29 de Janeiro de 2007, 17:25
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.facade;

import br.com.www.application.util.MessageConstants;
import br.com.www.application.domain.Mensagem;
import br.com.www.application.domain.Funcao;
import br.com.www.application.database.MensagemDatabaseFinder;
import br.com.www.support.database.DatabaseAbstractFactory;
import br.com.www.application.database.JdbcFactory;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.util.Collection;


/**
 *
 * @author kalcarvalho
 */
public class MensagemFacade implements MessageConstants {
    
    /** Creates a new instance of MensagemFacade */
    public MensagemFacade() {
    }
    
    public Collection listMensagemFuncoes(Funcao funcao) {
        
        Connection conn = null;
        
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        try {
            conn = factory.openConnection();
            MensagemDatabaseFinder finder = (MensagemDatabaseFinder) factory.
                    createFinder(MensagemDatabaseFinder.class);
            
            
            return (finder.listMensagemFuncoes(conn, funcao));
            
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
        
        
        
    }
    
}
