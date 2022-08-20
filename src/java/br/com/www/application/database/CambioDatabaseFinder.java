/*
 * CambioDatabaseFinder.java
 *
 * Created on 14 de Fevereiro de 2007, 20:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Cambio;
import br.com.www.support.database.IDatabaseFinder;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Administrador
 */
public class CambioDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of CambioDatabaseFinder */
    public CambioDatabaseFinder() {
    }
    
    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT * ");
        sql.append(" FROM cam_cambio ");
        sql.append(" WHERE ");
        sql.append(" cam_codigo = ? ");
        
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                Cambio cambio = makeCambioObject(rs);
                return cambio;
                
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
    
    private Cambio makeCambioObject(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("cam_codigo");
        String descricao = rs.getString("cam_descricao").trim();
        
        
        Cambio cambio = new Cambio();
        cambio.setCodigo(codigo);
        cambio.setDescricao(descricao);
        return cambio;
    }
    
}
