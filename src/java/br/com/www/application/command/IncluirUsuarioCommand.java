package br.com.www.application.command;

import java.util.Collection;
import java.util.Date;


import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.www.application.controller.ServletController;
import br.com.www.application.domain.Usuario;
import br.com.www.application.domain.Perfil;
import br.com.www.application.facade.PerfilFacade;
import br.com.www.application.facade.UsuarioFacade;
import br.com.www.application.util.ServletUtil;
import br.com.www.support.exception.BusinessException;
import br.com.www.support.exception.SystemException;

public class IncluirUsuarioCommand extends MainCommand {

	public IncluirUsuarioCommand() {
		super();
	}

	public void preProcess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_INCLUIR_USUARIO);
		ServletUtil.checkUserPermission(request);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = ServletUtil.loadParameter(REQUEST_PARAMETER_ACTION, request);
		if (action.equalsIgnoreCase(REQUEST_ACTION_PERFORM)) {
			performAction(request, response);
		}
		if (action.equalsIgnoreCase(REQUEST_ACTION_SHOW_FORM)) {
			showForm(request, response);
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
	 
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServletUtil.forward(PAGE_NAME_INCLUIR_USUARIO, PAGE_TITLE_INCLUIR_USUARIO, request, response);
		
	}
	
	private void performAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = new Usuario();
		Perfil perfil = new Perfil();
		perfil.setCodigo(request.getParameter(REQUEST_USUARIO_CODIGO_PERFIL));
		
		
		usuario.setChave(request.getParameter(REQUEST_USUARIO_CHAVE));
		usuario.setPassword(request.getParameter(REQUEST_USUARIO_SENHA));
		usuario.setDdd(request.getParameter(REQUEST_USUARIO_DDD));
		usuario.setEmail(request.getParameter(REQUEST_USUARIO_EMAIL));
		usuario.setNome(request.getParameter(REQUEST_USUARIO_NOME));
		usuario.setPassword(request.getParameter(REQUEST_USUARIO_SENHA));
		usuario.setPerfil(perfil);
		usuario.setRamal(request.getParameter(REQUEST_USUARIO_RAMAL));
		usuario.setTelefone(request.getParameter(REQUEST_USUARIO_TELEFONE));
		usuario.setUsuarioResponsavel(ServletUtil.getUsuarioFromSession(request));
		usuario.setAtivado((request.getParameter(REQUEST_USUARIO_STATUS).equalsIgnoreCase("0")) ? Boolean.FALSE : Boolean.TRUE);
		
		UsuarioFacade facade = new UsuarioFacade();
		
		Usuario u = usuario;
		
		request.setAttribute(REQUEST_USUARIO_CHAVE, u.getChave());
		request.setAttribute(REQUEST_USUARIO_NOME, u.getNome());
		request.setAttribute(REQUEST_USUARIO_DDD, u.getDdd());
		request.setAttribute(REQUEST_USUARIO_EMAIL, u.getEmail());
		request.setAttribute(REQUEST_USUARIO_TELEFONE , u.getTelefone());
		request.setAttribute(REQUEST_USUARIO_RAMAL, u.getRamal());
		request.setAttribute(REQUEST_USUARIO_PERFIL, perfil.getDescricao());
		request.setAttribute(REQUEST_USUARIO_CODIGO_PERFIL, perfil.getCodigo());
		request.setAttribute(REQUEST_USUARIO_STATUS, (u.isAtivado().booleanValue() == true) ? "1" : "0");
		
		usuario = facade.incluir(usuario);
		
		Collection result = facade.consultarUsuarios(usuario.getChave());
		
		
		request.getSession().setAttribute(USUARIO_TEXT_TO_FIND_SESSION, usuario.getChave().trim());
		request.setAttribute(REQUEST_PARAMETER_SEARCH_RESULT, result);
		request.setAttribute(REQUEST_PARAMETER_SUCCESS_MESSAGE, MESSAGE_USUARIO_SUCCESS_ON_INSERT);
		ServletUtil.forward(PAGE_NAME_LISTAR_USUARIOS, PAGE_TITLE_LISTAR_USUARIOS, request, response);
		
		
	}
}
