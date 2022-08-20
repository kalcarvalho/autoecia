package br.com.www.application.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.www.support.exception.BusinessException;
import br.com.www.application.exception.NotificationException;
import br.com.www.application.exception.PermissionException;
import br.com.www.application.util.PageConstants;
import br.com.www.application.util.RequestConstants;
import br.com.www.application.util.ServletUtil;
import br.com.www.support.command.CommandFactory;
import java.io.*;
import javax.servlet.*;
import java.util.Properties;
import br.com.www.support.exception.SystemException;

public class ExceptionHandler implements RequestConstants, PageConstants {
    
    public ExceptionHandler() {
    }
    
    public void doException(HttpServletRequest request,
            HttpServletResponse response,
            Throwable exception) {
        try {
            if (exception instanceof PermissionException) {
                //Send Login Page
                request.setAttribute(REQUEST_PARAMETER_ERROR_MESSAGE, exception.getMessage());
                String commandName = REQUEST_COMMAND_EFETUAR_LOGIN;
                String callBackPage = getCallbackPage(commandName);
                String pageTitle = PAGE_TITLE_LOGIN;
                ServletUtil.forward(callBackPage, pageTitle, request, response);
                return;
            }
            if (exception instanceof NotificationException) {
                //Send Success or Notification messages.
                request.setAttribute(REQUEST_PARAMETER_SUCCESS_MESSAGE, exception.getMessage());
                String commandName = ServletUtil.loadParameter(REQUEST_PARAMETER_COMMAND, request);
                String callBackPage = getCallbackPage(commandName);
                ServletUtil.forward(callBackPage, REQUEST_PARAMETER_PAGE_TITLE, request, response);
                return;
            }
            if (exception instanceof BusinessException) {
                request.setAttribute(REQUEST_PARAMETER_ERROR_MESSAGE, exception.getMessage());
                String commandName = ServletUtil.loadParameter(REQUEST_PARAMETER_COMMAND, request);
                String callBackPage = getCallbackPage(commandName);
                String pageTitle = ServletUtil.loadParameter(REQUEST_PARAMETER_PAGE_TITLE, request);
                if (callBackPage.equals("proposta.jsp")) {
                    request.setAttribute(REQUEST_PARAMETER_FATAL_ERROR_EXCEPTION, exception);
                    ServletUtil.sendRedirect(PAGE_NAME_FATAL_ERROR, PAGE_TITLE_FATAL_ERROR, request, response);
                } else ServletUtil.forward(callBackPage, pageTitle, request, response);
                return;
            }
            //Send Fatal Error
            request.setAttribute(REQUEST_PARAMETER_FATAL_ERROR_EXCEPTION, exception);
            ServletUtil.forward(PAGE_NAME_FATAL_ERROR, PAGE_TITLE_FATAL_ERROR, request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String getCallbackPage(String command) {
        Properties properties = new Properties();
        try {
            properties.load(ExceptionHandler.class.getResourceAsStream(
                    REQUEST_CALLBACK_ERROR_PROPERTIES_FILE_NAME));
            return properties.getProperty(command);
        } catch (IOException ex) {
            throw new SystemException(ex);
        }
    }
}