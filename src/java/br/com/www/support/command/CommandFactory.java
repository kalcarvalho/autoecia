package br.com.www.support.command;

import java.util.Properties;
import java.net.URL;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

public class CommandFactory {

    private static CommandFactory factory = null;

    private static Properties properties = new Properties();
    private static String commandParameterName = "";

    private CommandFactory() {}

    private CommandFactory(Properties properties) {
        this.properties = properties;
    }

    private CommandFactory(InputStream is) {
        try {
            properties.load(is);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private CommandFactory(File file) {
        try {
            properties.load(new FileInputStream(file));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private CommandFactory(URL url) {
        try {
            properties.load(url.openStream());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static CommandFactory getInstance() {
        return (factory == null) ? new CommandFactory() : factory;
    }

    public static CommandFactory getInstance(Properties properties) {
        return (factory == null) ? new CommandFactory(properties) : factory;
    }

    public static CommandFactory getInstance(InputStream inputStream) {
        return (factory == null) ? new CommandFactory(inputStream) : factory;
    }

    public static CommandFactory getInstance(File file) {
        return (factory == null) ? new CommandFactory(file) : factory;
    }

    public static CommandFactory getInstance(URL url) {
        return (factory == null) ? new CommandFactory(url) : factory;
    }

    public Command createCommand(String commandName) {
        try {
            return (Command) Class.forName(properties.getProperty(commandName)).
                    newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void executeCommand(String commandName, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Command command = createCommand(commandName);
        executeCommand(command, request, response);
    }

    public void executeCommand(Command command, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        command.preProcess(request, response);
        command.process(request, response);
        command.postProcess(request, response);
    }

    public void setCommandParameterName(String commandParameterName) {
        this.commandParameterName = commandParameterName;
    }

    public String getCommandParameterName() {
        return this.commandParameterName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }
}
