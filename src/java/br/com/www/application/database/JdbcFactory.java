package br.com.www.application.database;

import br.com.www.support.database.DatabaseAbstractFactory;
import java.sql.Connection;
import br.com.www.support.exception.SystemException;
import java.sql.DriverManager;

public class JdbcFactory extends DatabaseAbstractFactory {

    private static JdbcFactory factory = null;

    static {
        factory = (factory == null) ? new JdbcFactory() : factory;
    }

    private JdbcFactory() {
        super();
    }

    public static JdbcFactory getInstance() {
        return factory;
    }

    public Connection openConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/autoecia";
            String user = "kalcarvalho";
            String password = "ksc1327";
            String driverClass = "com.mysql.jdbc.Driver";
            Class.forName(driverClass).newInstance();
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
    }

    public void closeConnection(Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (Exception ex) {
            conn = null;
        }
    }
}
