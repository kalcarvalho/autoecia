package br.com.www.application.util;

import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;

import br.com.www.application.domain.Funcao;
import br.com.www.application.domain.Perfil;
import br.com.www.application.domain.Usuario;
import br.com.www.application.exception.PermissionException;
import br.com.www.support.command.CommandFactory;
import br.com.www.support.exception.SystemException;

public class ServletUtil implements RequestConstants, SessionConstants, PageConstants, MessageConstants {
    
    public static void forward(String page, String pageTitle, HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        if (page.startsWith("/")) {
            page = page.substring(1, page.length());
        }
        request.setAttribute(REQUEST_PARAMETER_PAGE_NAME, page);
        request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, pageTitle);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_DEFAULT_DIRECTORY + PAGE_NAME_FRAME);
        
        dispatcher.forward(request, response);
        return;
    }
    
    public static void sendRedirect(String page, String pageTitle, HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        if (page.startsWith("/")) {
            page = page.substring(1, page.length());
        }
        request.setAttribute(REQUEST_PARAMETER_PAGE_NAME, page);
        request.setAttribute(REQUEST_PARAMETER_PAGE_TITLE, pageTitle);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_DEFAULT_DIRECTORY + page);
        
        dispatcher.forward(request, response);
        return;
    }
    
    public static String loadParameter(String parameterName, HttpServletRequest request) throws ServletException, IOException {
        String result = request.getParameter(parameterName.trim());
        if (result == null || result.trim().length() == 0) {
            result = (String)request.getAttribute(parameterName.trim());
        }
        if (result == null || result.trim().length() == 0) {
            result = "";
        }
        return result;
    }
    
    public static String[] loadParameterValues(String parameterName, HttpServletRequest request) throws ServletException, IOException {
        String[] result = request.getParameterValues(parameterName.trim());
        if (result == null || result.length == 0) {
            result = (String[])request.getAttribute(parameterName.trim());
        }
        if (result == null || result.length == 0) {
            result = null;
        }
        return result;
    }
    
    public static String getPageName(HttpServletRequest request) {
        try {
            String pageName = loadParameter(REQUEST_PARAMETER_PAGE_NAME, request);
            String menu = loadParameter(REQUEST_PARAMETER_MENU, request);
            if (pageName.trim().length() == 0) {
                if(menu.trim().length() == 0) {
                    return PAGE_NAME_PAGE;
                } else if (menu.equals(REQUEST_PARAMETER_MENU_RESTRITA)) {
                    return PAGE_NAME_LOGIN;
                } else return PAGE_NAME_PAGE;
            }
            return pageName;
        } catch (Exception e) {
            return PAGE_NAME_LOGIN;
        }
    }
    
    public static String getPageTitle(HttpServletRequest request) {
        try {
            String pageTitle = loadParameter(REQUEST_PARAMETER_PAGE_TITLE, request);
            String menu = loadParameter(REQUEST_PARAMETER_MENU, request);
            if (pageTitle.trim().length() == 0) {
                if(menu.trim().length() == 0) {
                    return PAGE_TITLE_FRAME;
                } else if (menu.equals(REQUEST_PARAMETER_MENU_RESTRITA)) {
                    return PAGE_TITLE_LOGIN;
                } else return PAGE_TITLE_FRAME;
            }
            return pageTitle;
        } catch (Exception e) {
            return PageConstants.PAGE_TITLE_LOGIN;
        }
    }
    
    public static void checkUserPermission(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
        }
        Usuario usuario = (Usuario) session.getAttribute(SessionConstants.USER_SESSION);
        if (usuario == null || usuario.isAtivado().booleanValue() == false) {
            throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
        }
        Perfil perfil = usuario.getPerfil();
        if (perfil == null || perfil.isAtivado().booleanValue() == false) {
            throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
        }
        Collection funcoes = perfil.listFuncoes();
        if (funcoes == null || funcoes.size() == 0) {
            throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
        }
        CommandFactory factory = CommandFactory.getInstance();
        String commandParameterName = factory.getCommandParameterName();
        if (commandParameterName == null ||
                commandParameterName.trim().length() == 0) {
            throw new SystemException(MESSAGE_ERROR_MISSING_COMMAND);
        }
        String command = ServletUtil.loadParameter(commandParameterName.trim(),
                request);
        if (command == null || command.trim().length() == 0) {
            throw new SystemException(MESSAGE_ERROR_MISSING_COMMAND);
        }
        Iterator it = funcoes.iterator();
        while (it.hasNext()) {
            Funcao funcao = (Funcao) it.next();
            if (funcao != null && funcao.isAtivado().booleanValue() == true &&
                    funcao.getAcao().equalsIgnoreCase(command.trim())) {
                return;
            }
        }
        throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
    }
    
    public static Usuario getUsuarioFromSession(HttpServletRequest request) throws PermissionException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute(SessionConstants.USER_SESSION);
        if (usuario == null || usuario.isAtivado().booleanValue() == false) {
            throw new PermissionException(MESSAGE_ERROR_ACCESS_DENIED);
        }
        return usuario;
    }
    
    public static Usuario getUsuarioOf(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute(SessionConstants.USER_SESSION);
        if (usuario == null || usuario.isAtivado().booleanValue() == false) {
            return null;
        }
        return usuario;
    }
    
    public static boolean compareImage(BufferedImage image1, BufferedImage image2) {
        if(image1.getWidth()  != image2.getWidth() ||
                image1.getHeight() != image2.getHeight()) {
            return(false);
        }
        for(int x = 0; x < image1.getWidth(); x++) {
            for(int y = 0; y < image1.getHeight(); y++) {
                if(image1.getRGB(x, y)!= image2.getRGB(x, y)){
                    return(false);
                }
            }
        }
        return(true);
    }
    
}
