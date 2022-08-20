/*
 * MensagemDatabaseFinder.java
 *
 * Created on 29 de Janeiro de 2007, 15:41
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.database;

import br.com.www.support.database.IDatabaseFinder;

import java.util.Collection;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.www.application.domain.Mensagem;
import br.com.www.application.domain.Funcao;
import br.com.www.support.exception.SystemException;


/**
 *
 * @author kalcarvalho
 */
public class MensagemDatabaseFinder  implements IDatabaseFinder {
    
    /** Creates a new instance of MensagemDatabaseFinder */
    public MensagemDatabaseFinder() {    }
    
    public Object findByPK(Connection conn, Object pk) {
        
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM men_mensagem ");
        sql.append(" WHERE ");
        sql.append(" men_codigo = ? ");
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                
                Mensagem mensagem = makeMensagemObject(rs);
                
                return mensagem;
                
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
    
    public Collection listMensagemFuncoes(Connection conn, Funcao funcao) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT men_mensagem.* ");
        sql.append(" FROM men_mensagem ");
        sql.append(" WHERE ");
        sql.append(" men_funcao = ? ");
        sql.append(" ORDER BY men_ordem ");
        
        String codigo = funcao.getCodigo();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Mensagem mensagem = makeMensagemObject(rs);
                result.add(mensagem);
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
    
    public Collection listAll(Connection conn) {
        return null;
    }
    
    private Mensagem makeMensagemObject(ResultSet rs) throws SQLException {
        
        String codigo = rs.getString("men_codigo").trim();
        String descricao = rs.getString("men_descricao").trim();
        String pathfigura = rs.getString("men_pathfigura").trim();
        String acao = rs.getString("men_acao").trim();
        
        Mensagem mensagem = new Mensagem();
        mensagem.setCodigo(codigo);
        mensagem.setDescricao(descricao);
        mensagem.setPathFigura(pathfigura);
        mensagem.setAcao(acao);
        
        return mensagem;
    }
    
    
}
