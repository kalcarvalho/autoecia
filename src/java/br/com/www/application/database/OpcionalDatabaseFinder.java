/*
 * OpcionalDatabaseFinder.java
 *
 * Created on 18 de Fevereiro de 2007, 17:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Carro;
import br.com.www.application.domain.Opcional;
import br.com.www.support.database.IDatabaseFinder;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Administrador
 */
public class OpcionalDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of OpcionalDatabaseFinder */
    public OpcionalDatabaseFinder() {
    }

    public Object findByPK(Connection conn, Object pk) {
        return null;
    }

    public Collection listAll(Connection conn) {
        return null;
    }
    
    public Collection listOpcionaisCarro(Connection conn, Carro carro) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT opc_opcional.*, cto_importante ");
        sql.append(" FROM opc_opcional ");
        sql.append(" LEFT OUTER JOIN cto_carro_tem_opcional ");
        sql.append(" ON cto_opcional = opc_codigo ");
        sql.append(" WHERE ");
        sql.append(" cto_carro = ? ");
        sql.append(" ORDER BY opc_descricao ");
        
        int codigo = carro.getCodigo();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Opcional opcional = makeOpcionalObject(rs);
                result.add(opcional);
            }
            return result;
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                rs = null;
            }
            try {
                pstm.close();
            } catch (SQLException e) {
                pstm = null;
            }
        }
    }

    private Opcional makeOpcionalObject(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("opc_codigo");
        String descricao = rs.getString("opc_descricao");
        
        Boolean importante = new Boolean(false);
        if (rs.getString("cto_importante") != null &&
            rs.getString("cto_importante").trim().equalsIgnoreCase("1")) {
            importante = new Boolean(true);
        } else {
            importante = new Boolean(false);
        }
        Opcional opcional = new Opcional();
        opcional.setCodigo(codigo);
        opcional.setDescricao(descricao);
        opcional.setImportante(importante);
        
        return opcional;
    }
    
}
