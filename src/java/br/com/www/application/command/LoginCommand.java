package br.com.www.application.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.www.application.domain.Usuario;
import br.com.www.application.facade.UsuarioFacade;
import br.com.www.application.util.ServletUtil;

public class LoginCommand extends MainCommand {

    public LoginCommand() {
        super();
    }

    public void preProcess(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

    	HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.setAttribute(USER_SESSION, null);
        
    }


    public void process(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String action = ServletUtil.loadParameter(REQUEST_PARAMETER_ACTION, request);
        request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, PAGE_TITLE_LOGIN);
        if (action.trim().equalsIgnoreCase(REQUEST_ACTION_PERFORM)) {
        	performAction(request, response);
        } else {
        	showForm(request, response);
        }
    }
    
    private void showForm(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	ServletUtil.forward(PAGE_NAME_LOGIN, PAGE_TITLE_LOGIN, request, response);
    }
    
    private void performAction(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	String chave = ServletUtil.loadParameter(REQUEST_USUARIO_CHAVE, request);
        String senha = ServletUtil.loadParameter(REQUEST_USUARIO_SENHA, request);
        Usuario usuario = new UsuarioFacade().efetuarLogin(chave,  senha);
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_SESSION, usuario);
        ServletUtil.forward(PAGE_NAME_WELCOME, PAGE_TITLE_WELCOME, request, response);
    }
}