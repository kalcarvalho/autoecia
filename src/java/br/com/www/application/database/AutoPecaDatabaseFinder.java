/*
 * AutoPecaDatabaseFinder.java
 *
 * Created on 26 de Fevereiro de 2007, 12:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.AutoPeca;
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
 * @author kalcarvalho
 */
public class AutoPecaDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of AutoPecaDatabaseFinder */
    public AutoPecaDatabaseFinder() {
    }

    public Object findByPK(Connection conn, Object pk) {
        return null;
    }

    public Collection listAll(Connection conn) {
       StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM aut_autopeca WHERE aut_finalizaem > CURRENT_TIMESTAMP() "  );
        
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            
            
            rs = pstm.executeQuery();
            Collection result = new ArrayList();
            
            while (rs.next()) {
                AutoPeca autopeca = makeAutoPecaObject(rs);
                
                result.add(autopeca);
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

    private AutoPeca makeAutoPecaObject(ResultSet rs) throws SQLException {
        int codigo = rs.getInt("aut_codigo");
        String descricao = rs.getString("aut_descricao");
        String url = rs.getString("aut_link");
        double valor = rs.getDouble("aut_valor");
        
        Boolean mercadoLivre = null;
        if (rs.getString("aut_mercadolivre") != null &&
            rs.getString("aut_mercadolivre").trim().equalsIgnoreCase("1")) {
            mercadoLivre = new Boolean(true);
        } else {
            mercadoLivre = new Boolean(false);
        }
        
        java.util.Date dataCriacao = null;
        if (rs.getTimestamp("aut_datacadastro") != null) {
            dataCriacao = new java.util.Date(rs.getTimestamp("aut_datacadastro").getTime());
        }
        
        java.util.Date dataFinaliza = null;
        if (rs.getTimestamp("aut_finalizaem") != null) {
            dataFinaliza = new java.util.Date(rs.getTimestamp("aut_finalizaem").getTime());
        }
        
        
        AutoPeca autopeca = new AutoPeca();
        autopeca.setCodigo(codigo);
        autopeca.setDescricao(descricao);
        autopeca.setUrl(url);
        autopeca.setMercadoLivre(mercadoLivre);
        autopeca.setValor(valor);
        autopeca.setDataCriacao(dataCriacao);
        autopeca.setFinaliza(dataFinaliza);
                
        return autopeca;
    }
    
    
    
}
