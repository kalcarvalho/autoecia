package br.com.www.application.database;

import br.com.www.support.database.IDatabaseFinder;
import java.sql.Connection;
import java.util.Collection;
import br.com.www.application.domain.Perfil;
import br.com.www.application.domain.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.www.support.exception.SystemException;
import java.util.ArrayList;
import java.util.Date;
import br.com.www.application.domain.Funcao;

public class FuncaoDatabaseFinder implements IDatabaseFinder {

    public FuncaoDatabaseFinder() {}

    public Collection listFuncoesPerfil(Connection conn, Perfil perfil) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT fun_funcao.* ");
        sql.append(" FROM fun_funcao ");
        sql.append(" LEFT OUTER JOIN ptf_perfil_tem_funcao ");
        sql.append(" ON ptf_funcao = fun_codigo ");
        sql.append(" WHERE ");
        sql.append(" ptf_perfil = ? ");
        sql.append(" ORDER BY fun_ordem ");
        
        String codigo = perfil.getCodigo();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Funcao funcao = makeFuncaoObject(rs);
                result.add(funcao);
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
    
    public Funcao findByParameter(Connection conn, Object par) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * ");
        sql.append(" FROM fun_funcao ");
        sql.append(" WHERE ");
        sql.append(" fun_parametro LIKE ? ");
        
        String parametro = (String) par;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, parametro.trim());
            rs = pstm.executeQuery();
             if (rs.next()) {
                Funcao funcao = makeFuncaoObject(rs);
                return funcao;
             }
            return null;
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

    public Object findByPK(Connection conn, Object pk) {
        return null;
    }

    public Collection listAll(Connection conn) {
        return null;
    }

    private Funcao makeFuncaoObject(ResultSet rs) throws SQLException {
        String codigo = rs.getString("fun_codigo").trim();
        String descricao = rs.getString("fun_descricao").trim();
        String acao = rs.getString("fun_acao").trim();
        String parametro = rs.getString("fun_parametro").trim();
        String pathMenu = rs.getString("fun_pathmenu").trim();
        
        Boolean ativado = null;
        if (rs.getString("fun_status") != null &&
            rs.getString("fun_status").trim().equalsIgnoreCase("1")) {
            ativado = new Boolean(true);
        } else {
            ativado = new Boolean(false);
        }
        
        Boolean funcaoDeMenu = null;
        if (rs.getString("fun_menu") != null &&
            rs.getString("fun_menu").trim().equalsIgnoreCase("1")) {
            funcaoDeMenu = new Boolean(true);
        } else {
            funcaoDeMenu = new Boolean(false);
        }
        
        Boolean restrito = null;
        if (rs.getString("fun_menurestrito") != null &&
            rs.getString("fun_menurestrito").trim().equalsIgnoreCase("1")) {
            restrito = new Boolean(true);
        } else {
            restrito = new Boolean(false);
        }
        
        java.util.Date dataCriacao = null;
        if (rs.getTimestamp("fun_criacao") != null) {
            dataCriacao = new Date(rs.getTimestamp("fun_criacao").getTime());
        }
        java.util.Date dataAlteracao = null;
        if (rs.getTimestamp("fun_alteracao") != null) {
            dataAlteracao = new Date(rs.getTimestamp("fun_alteracao").getTime());
        } else dataAlteracao = dataCriacao;
        
        Funcao funcao = new Funcao();
        funcao.setCodigo(codigo);
        funcao.setDescricao(descricao);
        funcao.setAcao(acao);
        funcao.setDataCriacao(dataCriacao);
        funcao.setDataAlteracao(dataAlteracao);
        funcao.setAtivado(ativado);
        funcao.setMenuFunction(funcaoDeMenu);
        funcao.setMenuRestrito(restrito);
        funcao.setParametro(parametro);
        funcao.setPathMenu(pathMenu);
        
        return funcao;
    }

}
