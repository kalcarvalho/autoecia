/*
 * AnuncianteDatabaseFinder.java
 *
 * Created on 20 de Fevereiro de 2007, 14:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Anunciante;
import br.com.www.application.domain.Usuario;
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
public class AnuncianteDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of AnuncianteDatabaseFinder */
    public AnuncianteDatabaseFinder() {
    }

    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT * ");
        sql.append(" FROM anu_anunciante ");
        sql.append(" WHERE ");
        sql.append(" anu_codigo = ? ");
        
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                Anunciante anunciante = makeAnuncianteObject(rs);
                
                if (rs.getString("anu_usuario") != null &&
                        rs.getString("anu_usuario").trim().length() > 0) {
                    UsuarioDatabaseFinder finder = new UsuarioDatabaseFinder();
                    anunciante.setUsuario((Usuario) finder.findByPK(conn, rs.getString("anu_usuario")));
                }
                
                return anunciante;
                
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

    private Anunciante makeAnuncianteObject(ResultSet rs) throws SQLException {
        
        int codigo = rs.getInt("anu_codigo");
        String logo = rs.getString("anu_logo");
        
        Anunciante anunciante = new Anunciante();
        anunciante.setCodigo(codigo);
        anunciante.setLogo(logo);
        return anunciante;
    }
    
}
