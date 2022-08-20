package br.com.www.application.command;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.www.application.facade.UsuarioFacade;
import br.com.www.application.util.ServletUtil;

public class ListarUsuariosCommand extends MainCommand {

	public ListarUsuariosCommand() {
		super();
	}

	public void preProcess(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_LISTAR_USUARIOS);
		ServletUtil.checkUserPermission(request);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = ServletUtil.loadParameter(REQUEST_PARAMETER_ACTION, request);
		if (action.equalsIgnoreCase(REQUEST_ACTION_PERFORM)) {
			performAction(request, response);
		} else {
			ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response);
		}
	}
	
	public void performAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String textToFind = ServletUtil.loadParameter(REQUEST_PARAMETER_TEXT_TO_FIND, request);
		
		if (textToFind.trim().length() == 0) {
			textToFind = (String)request.getSession().getAttribute(USUARIO_TEXT_TO_FIND_SESSION);
			textToFind = (textToFind == null) ? "" : textToFind.trim();
		}
		
		UsuarioFacade facade = new UsuarioFacade();
		Collection result = facade.consultarUsuarios(textToFind);
		
		request.getSession().setAttribute(USUARIO_TEXT_TO_FIND_SESSION, textToFind.trim());
		request.setAttribute(REQUEST_PARAMETER_SEARCH_RESULT, result);
		request.setAttribute(REQUEST_PARAMETER_SUCCESS_MESSAGE, String.valueOf(result.size()) + ((result.size() > 1) ? " usuários encontrados" : " usuario encontrado"));
		ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response);
		
		 
	}

}
