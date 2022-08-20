package br.com.www.support.database;

import java.sql.Connection;

public interface IDatabaseExec {

    public Object insert(Connection conn, Object object);
    public Object delete(Connection conn, Object object);
    public Object update(Connection conn, Object object);
}
