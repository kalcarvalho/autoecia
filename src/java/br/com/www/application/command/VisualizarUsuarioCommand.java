package br.com.www.application.command;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.www.application.domain.Usuario;
import br.com.www.application.facade.UsuarioFacade;
import br.com.www.application.util.ServletUtil;
import br.com.www.support.exception.SystemException;

public class VisualizarUsuarioCommand extends MainCommand {

	public VisualizarUsuarioCommand() {
		super();
	}

	public void preProcess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_VISUALIZAR_CARRO);
		ServletUtil.checkUserPermission(request);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = ServletUtil.loadParameter(REQUEST_PARAMETER_ACTION, request);
		if (action.equalsIgnoreCase(REQUEST_ACTION_PERFORM)) {
			performAction(request, response);
		}
		if (action.equalsIgnoreCase(REQUEST_ACTION_BACK)) {
			backAction(request, response);
		}
	}
	
    private void backAction(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		String textToFind = ServletUtil.loadParameter( REQUEST_ACTION_BACK , request);
		if (textToFind.trim().length() == 0) {
			textToFind = (String)request.getSession().getAttribute(USUARIO_TEXT_TO_FIND_SESSION);
			textToFind = (textToFind == null) ? "" : textToFind.trim();
		}
		
		UsuarioFacade facade = new UsuarioFacade();
		Collection result = facade.consultarUsuarios(textToFind);
		
		request.getSession().setAttribute(USUARIO_TEXT_TO_FIND_SESSION, textToFind.trim());
		request.setAttribute(REQUEST_PARAMETER_SEARCH_RESULT, result);
		ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response);
	}
	
	private void performAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = new Usuario();
		UsuarioFacade facade = new UsuarioFacade();
		
		usuario.setChave(request.getParameter(REQUEST_USUARIO_CHAVE));
		
		usuario = (Usuario) facade.detalhar(usuario);
		
		request.setAttribute(REQUEST_USUARIO_CHAVE,usuario.getChave());
		request.setAttribute(REQUEST_USUARIO_NOME,usuario.getNome());
		request.setAttribute(REQUEST_USUARIO_CODIGO_PERFIL,usuario.getPerfil().getCodigo());
		request.setAttribute(REQUEST_USUARIO_PERFIL, usuario.getPerfil().getDescricao());
		request.setAttribute(REQUEST_USUARIO_DDD,usuario.getDdd());
		request.setAttribute(REQUEST_USUARIO_EMAIL,usuario.getEmail());
		request.setAttribute(REQUEST_USUARIO_RAMAL,usuario.getRamal());
		request.setAttribute(REQUEST_USUARIO_STATUS,(usuario.isAtivado().booleanValue() == true) ? "Ativado" : "Desativado");
		request.setAttribute(REQUEST_USUARIO_TELEFONE,usuario.getTelefone());
		
		ServletUtil.forward(PAGE_NAME_VISUALIZAR_USUARIO, PAGE_TITLE_VISUALIZAR_USUARIO, request, response);
	}
}