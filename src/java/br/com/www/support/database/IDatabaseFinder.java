package br.com.www.support.database;

import java.sql.Connection;
import java.util.Collection;

public interface IDatabaseFinder {

    public Object findByPK(Connection conn, Object pk);
    public Collection listAll(Connection conn);
}
