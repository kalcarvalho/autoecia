package br.com.www.application.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import br.com.www.support.command.CommandFactory;
import br.com.www.application.handler.ExceptionHandler;
import br.com.www.application.util.RequestConstants;

public class ServletController extends HttpServlet implements RequestConstants {

	public void init() throws ServletException {
        CommandFactory.getInstance(
            ServletController.class.getResourceAsStream(
                REQUEST_COMMAND_PROPERTIES_FILE_NAME)).setCommandParameterName(
                    REQUEST_PARAMETER_COMMAND);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doProcess(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String commandName = request.getParameter(CommandFactory.getInstance().getCommandParameterName());
        try {
            CommandFactory.getInstance().executeCommand(commandName, request, response);
        } catch (Exception ex) {
            ExceptionHandler handler = new ExceptionHandler();
            handler.doException(request, response, ex);
        }
    }
}
