/*
 * MarcaDatabaseFinder.java
 *
 * Created on 16 de Fevereiro de 2007, 14:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Marca;
import br.com.www.support.database.IDatabaseFinder;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author kalcarvalho
 */
public class MarcaDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of MarcaDatabaseFinder */
    public MarcaDatabaseFinder() {
    }

    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT * ");
        sql.append(" FROM mar_marca ");
        sql.append(" WHERE ");
        sql.append(" mar_codigo = ? ");
        
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                Marca marca = makeMarcaObject(rs);
                
                return marca;
                
            } else {
                return null;
            }
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

    public Collection listAll(Connection conn) {
        return null;
    }

    private Marca makeMarcaObject(ResultSet rs) throws SQLException {
        
        int codigo = rs.getInt("mar_codigo");
        String descricao = rs.getString("mar_descricao");
        String logo = rs.getString("mar_logo");
        
        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setDescricao(descricao);
        marca.setLogo(logo);
        
        return marca;
    }
    
}
