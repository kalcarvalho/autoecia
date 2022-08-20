package br.com.www.support.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

public abstract class Command {

    public Command() {
    }

    public abstract void process(HttpServletRequest request,
                        HttpServletResponse response) throws Exception;

    public void preProcess(HttpServletRequest request,
                     HttpServletResponse response) throws Exception {}

    public void postProcess(HttpServletRequest request,
                     HttpServletResponse response) throws Exception {}
}
