/*
 * VersaoDatabaseFinder.java
 *
 * Created on 16 de Fevereiro de 2007, 13:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Modelo;
import br.com.www.application.domain.Versao;
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
public class VersaoDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of VersaoDatabaseFinder */
    public VersaoDatabaseFinder() {
    }
    
    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        
        sql.append(" SELECT * ");
        sql.append(" FROM ver_versao ");
        sql.append(" WHERE ");
        sql.append(" ver_codigo = ? ");
        
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                Versao versao = makeVersaoObject(rs);
                
                if (rs.getString("ver_modelo") != null && rs.getString("ver_modelo").trim().length() > 0) {
                    ModeloDatabaseFinder finder = new ModeloDatabaseFinder();
                    versao.setModelo((Modelo) finder.findByPK(conn, rs.getString("ver_modelo").trim()));
                }
                
                return versao;
                
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

    private Versao makeVersaoObject(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("ver_codigo");
        int ano = rs.getInt("ver_ano");
        String descricao = rs.getString("ver_descricao");
        
        Versao versao = new Versao();
        versao.setCodigo(codigo);
        versao.setAno(ano);
        versao.setDescricao(descricao);
        
        return versao;
    }
    
}
