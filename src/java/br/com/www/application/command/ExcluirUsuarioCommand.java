package br.com.www.application.command;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.www.application.domain.Usuario;
import br.com.www.application.facade.UsuarioFacade;
import br.com.www.application.util.ServletUtil;
import br.com.www.support.exception.SystemException;

public class ExcluirUsuarioCommand extends MainCommand {

	public ExcluirUsuarioCommand() {
		super();
	}

	public void preProcess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_REMOVER_USUARIO);
		ServletUtil.checkUserPermission(request);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = new Usuario();
				
		usuario.setChave(request.getParameter(REQUEST_USUARIO_CHAVE));
		
		UsuarioFacade facade = new UsuarioFacade();
		facade.excluir(usuario);
		Collection result = facade.consultarUsuarios(" ");
		
		request.getSession().setAttribute(USUARIO_TEXT_TO_FIND_SESSION, " ");
		request.setAttribute(REQUEST_PARAMETER_SEARCH_RESULT, result);
		request.setAttribute(REQUEST_PARAMETER_SUCCESS_MESSAGE, MESSAGE_USUARIO_SUCCESS_ON_DELETE);
		ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response);
		
			
		
	}
}
