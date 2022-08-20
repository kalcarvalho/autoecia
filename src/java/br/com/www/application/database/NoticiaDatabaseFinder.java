/*
 * NoticiaDatabaseFinder.java
 *
 * Created on 1 de Fevereiro de 2007, 15:56
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.database;

import br.com.www.support.database.IDatabaseFinder;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.www.application.domain.Noticia;
import br.com.www.support.exception.SystemException;

/**
 *
 * @author kalcarvalho
 */
public class NoticiaDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of NoticiaDatabaseFinder */
    public NoticiaDatabaseFinder() {
    }
    
    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM not_noticia ");
        sql.append(" WHERE not_codigo = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String codigo = (String) pk;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.toString());
            rs = pstm.executeQuery();
            Noticia noticia = null;
            
            if (rs.next()) {
                
                noticia = makeNoticiaObject(rs);
                
            }
            return noticia;
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
        
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM not_noticia ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            Collection result = new ArrayList();
            
            while (rs.next()) {
                
                Noticia noticia = makeNoticiaObject(rs);
                
                result.add(noticia);
                
                
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
    
    public Noticia makeNoticiaObject(ResultSet rs) throws SQLException {
        
        String codigo = rs.getString("not_codigo").trim();
        String texto = rs.getString("not_texto").trim();
        String titulo = rs.getString("not_titulo").trim();
        String autor = rs.getString("not_autor").trim();
        
        Boolean ativado = null;
        if (rs.getString("not_ativado") != null &&
                rs.getString("not_ativado").trim().equalsIgnoreCase("1")) {
            ativado = new Boolean(true);
        } else {
            ativado = new Boolean(false);
        }
        
        Date datanoticia = null;
        if (rs.getTimestamp("not_data") != null) {
            datanoticia = new Date(rs.getTimestamp("not_data").getTime());
        }
        
        Noticia noticia = new Noticia();
        
        noticia.setCodigo(codigo);
        noticia.setTexto(texto);
        noticia.setDataNoticia(datanoticia);
        noticia.setAtivado(ativado);
        noticia.setTitulo(titulo);
        noticia.setAutor(autor);
        
        return noticia;
    }
    
    
    
}
