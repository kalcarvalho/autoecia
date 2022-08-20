package br.com.www.application.util;

import br.com.www.support.exception.BusinessException;
import br.com.www.application.database.UsuarioDatabaseFinder;
import br.com.www.application.domain.Usuario;
import br.com.www.application.facade.UsuarioFacade;

public class Validator {

    public static boolean isStringEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double d = new Double(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void validateUserKey(Usuario usuario) throws BusinessException {
    	
    	UsuarioFacade facade = new UsuarioFacade();
    	
    	try {
    		usuario = facade.detalhar(usuario);
    		
    		if ((usuario == null)) {
    			return;
    		} else {
    			Double d = new Double("a");
    		}
    		
    		
    	} catch (Exception ex) {
    		
    		throw new BusinessException(MessageConstants.MESSAGE_USUARIO_ALREADY_EXISTS);
    	}
    	
    }
    


    public static void validateUsuario(Usuario usuario)
            throws BusinessException {
    	
    	
    	
    	Boolean erro = Boolean.FALSE;
    	String campos = "";
    	String msg = "";
    	
    	if (isStringEmpty(usuario.getChave())) {
    		erro = Boolean.TRUE;
    		campos += "chave,";
    	}
    	
    	if (isStringEmpty(usuario.getPassword())) {
    		erro = Boolean.TRUE;
    		campos += " senha,";
    	}
    	
    	if (isStringEmpty(usuario.getNome())) {
    		erro = Boolean.TRUE;
    		campos += " nome,";
    	}
    	if (isStringEmpty(usuario.getEmail())) {
    		erro = Boolean.TRUE;
    		campos += " e-mail,";
    	}
    	if (isStringEmpty(usuario.getDdd())) {
    		erro = Boolean.TRUE;
    		campos += " DDD,";
    	}
    	if (isStringEmpty(usuario.getTelefone())) {
    		erro = Boolean.TRUE;
    		campos += " Telefone,";
    	}
    	if (isStringEmpty(usuario.getRamal())) {
    		erro = Boolean.TRUE;
    		campos += " Ramal,";
    	}
    	
    	if (erro.booleanValue() == true) {
    		msg = " não informado(s).";
    		campos = "Campo(s) " + campos.substring(0,campos.length()-1);
    		campos += msg;
    		throw new BusinessException(campos);
    	}
        
       
    }
}
