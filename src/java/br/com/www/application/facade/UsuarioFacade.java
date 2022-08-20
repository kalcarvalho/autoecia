package br.com.www.application.facade;

import br.com.www.application.domain.Usuario;
import br.com.www.application.util.PageConstants;
import br.com.www.application.util.ServletUtil;
import br.com.www.support.exception.BusinessException;
import br.com.www.application.util.Validator;
import br.com.www.application.database.JdbcFactory;
import java.sql.Connection;
import br.com.www.application.database.UsuarioDatabaseFinder;
import br.com.www.application.database.PerfilDatabaseFinder;
import br.com.www.application.domain.Perfil;
import java.util.Collection;
import br.com.www.support.database.DatabaseAbstractFactory;
import br.com.www.application.util.MessageConstants;
import br.com.www.support.exception.SystemException;
import br.com.www.application.database.UsuarioDatabaseExec;

public class UsuarioFacade implements MessageConstants {

    public UsuarioFacade() {}

    public Collection consultarUsuarios(String textToFind) throws BusinessException {
        
        Connection conn = null;
        Collection coll = null;
        
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        try {
        	conn = factory.openConnection();
            UsuarioDatabaseFinder userFinder = (UsuarioDatabaseFinder) factory.
                                               createFinder(UsuarioDatabaseFinder.class);
        	
        	if (Validator.isStringEmpty(textToFind)) {
                coll = userFinder.findAll(conn);
                
            } else {
	            coll = userFinder.findUsuariosByChaveNome(conn, textToFind);
	         }

        	if (coll.size() == 0) throw new BusinessException(MESSAGE_USUARIO_SEARCH_RESULT_IS_BLANK);
            return coll;
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }

    public Usuario efetuarLogin(String userName, String password) throws BusinessException {
        if (Validator.isStringEmpty(userName) || Validator.isStringEmpty(password)) {
            throw new BusinessException(MESSAGE_USUARIO_LOGIN_FILEDS_ARE_BLANK);
        }
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        Connection conn = null;
        try {
            conn = factory.openConnection();
            UsuarioDatabaseFinder userFinder = (UsuarioDatabaseFinder) factory.
                                               createFinder(
                    UsuarioDatabaseFinder.class);
            Usuario usuario = (Usuario) userFinder.findByLogin(conn, userName.trim().toUpperCase());
            if (usuario == null || usuario.isAtivado().booleanValue() == false ||
                usuario.getPerfil() == null ||
                usuario.getPerfil().isAtivado().booleanValue() == false) {
                throw new BusinessException(MESSAGE_ERROR_ACCESS_DENIED);
            }
            return usuario;
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
    }

    public Usuario incluir(Usuario usuario) throws BusinessException {
       
    	Validator.validateUsuario(usuario);
        Validator.validateUserKey(usuario);
       
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        Connection conn = null;
        try {
	        conn = factory.openConnection();
	        UsuarioDatabaseExec userExec = (UsuarioDatabaseExec) factory.createExec(UsuarioDatabaseExec.class);
	        
	        usuario = (Usuario) userExec.insert(conn, usuario);
	        
	        
        } catch (SystemException ex){
        	throw ex;
        } finally {
        	factory.closeConnection(conn);
        }
        
        return usuario;
        
    }

    public void excluir(Usuario usuario) throws BusinessException {
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        Connection conn = null;
        try {
	        conn = factory.openConnection();
	        UsuarioDatabaseExec userExec = (UsuarioDatabaseExec) factory.createExec(UsuarioDatabaseExec.class);
	        
	        usuario = (Usuario) userExec.delete(conn, usuario);
	        
        } catch (SystemException ex){
        	throw ex;
        } finally {
        	factory.closeConnection(conn);
        }
        
    }

    public Usuario detalhar(Usuario usuario) throws BusinessException {
    	Connection conn = null;
        DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        
        try {
        	conn = factory.openConnection();
            UsuarioDatabaseFinder userFinder = (UsuarioDatabaseFinder) factory.
                                               createFinder(UsuarioDatabaseFinder.class);
        	usuario = (Usuario) userFinder.findByPK(conn,  usuario.getChave());

        	
        } catch (SystemException ex) {
            throw ex;
        } finally {
            factory.closeConnection(conn);
        }
        
        return usuario;
    	
    }

    public Usuario atualizar(Usuario usuario) throws BusinessException {
    	Validator.validateUsuario(usuario);
    	DatabaseAbstractFactory factory = JdbcFactory.getInstance();
        Connection conn = null;
        try {
	        conn = factory.openConnection();
	        UsuarioDatabaseExec userExec = (UsuarioDatabaseExec) factory.createExec(UsuarioDatabaseExec.class);
	        
	        usuario = (Usuario) userExec.update(conn, usuario);
        } catch (SystemException ex){
        	throw ex;
        } finally {
        	factory.closeConnection(conn);
        }
        
        return usuario;
        
    }
}
