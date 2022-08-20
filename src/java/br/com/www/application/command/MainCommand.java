package br.com.www.application.command;

import br.com.www.support.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.www.application.util.RequestConstants;
import br.com.www.application.util.SessionConstants;
import br.com.www.application.util.PageConstants;
import br.com.www.application.util.MessageConstants;

public abstract class MainCommand extends Command implements SessionConstants,
        RequestConstants, PageConstants, MessageConstants {

    public MainCommand() {}

    public abstract void process(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception;
}
