package br.com.www.application.command;

import br.com.www.application.util.SessionConstants;
import br.com.www.support.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.www.application.util.ServletUtil;

public class LogoffCommand extends MainCommand {

    public LogoffCommand() {}

    public void process(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(USER_SESSION, null);
        }
        ServletUtil.forward(PAGE_NAME_LOGIN, PAGE_TITLE_LOGIN, request, response);
    }
}
