/*
 * ModeloDatabaseFinder.java
 *
 * Created on 16 de Fevereiro de 2007, 13:51
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Marca;
import br.com.www.application.domain.Modelo;
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
public class ModeloDatabaseFinder implements IDatabaseFinder  {
    
    /** Creates a new instance of ModeloDatabaseFinder */
    public ModeloDatabaseFinder()  {
    }

    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT * ");
        sql.append(" FROM mod_modelo ");
        sql.append(" WHERE ");
        sql.append(" mod_codigo = ? ");
        
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                Modelo modelo = makeModeloObject(rs);
                
                 if (rs.getString("mod_marca") != null && rs.getString("mod_marca").trim().length() > 0) {
                    MarcaDatabaseFinder finder = new MarcaDatabaseFinder();
                    modelo.setMarca((Marca) finder.findByPK(conn, rs.getString("mod_marca").trim()));
                }
                
                return modelo;
                
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

    private Modelo makeModeloObject(ResultSet rs) throws SQLException {
        
        int codigo = rs.getInt("mod_codigo");
        String descricao = rs.getString("mod_descricao");
        
        Modelo modelo = new Modelo();
        modelo.setCodigo(codigo);
        modelo.setDescricao(descricao);
        
        return modelo;
    }
    
}
