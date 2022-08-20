package br.com.www.support.database;

import java.sql.Connection;

public abstract class DatabaseAbstractFactory {

    public DatabaseAbstractFactory() {}

    public abstract Connection openConnection();
    public abstract void closeConnection(Connection conn);

    public IDatabaseFinder createFinder(Class finderClass) {
        try {
            return (IDatabaseFinder) finderClass.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public IDatabaseExec createExec(Class execClass) {
        try {
            return (IDatabaseExec) execClass.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
