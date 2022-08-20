package br.com.www.application.database;

import br.com.www.support.database.IDatabaseFinder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.www.support.exception.SystemException;
import br.com.www.application.domain.Perfil;
import br.com.www.application.domain.Usuario;
import java.util.Date;

public class PerfilDatabaseFinder implements IDatabaseFinder{

    public PerfilDatabaseFinder() {}

    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" per_codigo, per_descricao, per_status, ");
        sql.append(" per_criacao , per_alteracao ");
        sql.append(" FROM per_perfil ");
        sql.append(" WHERE ");
        sql.append(" per_codigo = ? ");
        String codigo = (String) pk;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.trim());
            rs = pstm.executeQuery();
            if (rs.next()) {
                Perfil perfil = makePerfilObject(rs);
                /*if (rs.getString("chaveResponsavel") !=null &&
                    rs.getString("chaveResponsavel").trim().length() > 0) {
                    UsuarioDatabaseFinder uFinder = new UsuarioDatabaseFinder();
                    /* Usuario responsavel = uFinder.findUsuarioResponsavel(conn,
                            rs.getString("chaveResponsavel"));
                    if (responsavel != null) {
                    	perfil.setUsuarioResponsavel(responsavel);
                    } 
                } */
                FuncaoDatabaseFinder fFinder = new FuncaoDatabaseFinder();
                Collection funcoes = fFinder.listFuncoesPerfil(conn, perfil);
                perfil.setFuncoes(funcoes);
                return perfil;
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

    public Collection listAllAtivos(Connection conn) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" perfil.codigo, perfil.descricao, perfil.status, ");
        sql.append(" perfil.dataCriacao , perfil.dataAlteracao ");
        sql.append(" FROM perfil ");
        sql.append(" WHERE ");
        sql.append(" perfil.status = 1 ");
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection result = new ArrayList();
        try {
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Perfil perfil = makePerfilObject(rs);
                result.add(perfil);
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
    
    

    private Perfil makePerfilObject(ResultSet rs) throws SQLException {
        String codigo = rs.getString("per_codigo").trim();
        String descricao = rs.getString("per_descricao").trim();
        Boolean ativado = null;
        if (rs.getString("per_status") != null &&
            rs.getString("per_status").trim().equalsIgnoreCase("1")) {
            ativado = new Boolean(true);
        } else {
            ativado = new Boolean(false);
        }
        java.util.Date dataCriacao = null;
        if (rs.getTimestamp("per_criacao") != null) {
            dataCriacao = new Date(rs.getTimestamp("per_criacao").getTime());
        }
        java.util.Date dataAlteracao = null;
        if (rs.getTimestamp("per_alteracao") != null) {
            dataAlteracao = new Date(rs.getTimestamp("per_alteracao").getTime());
        } else dataAlteracao = dataCriacao;
                
        Perfil perfil = new Perfil();
        perfil.setAtivado(ativado);
        perfil.setCodigo(codigo);
        perfil.setDataAlteracao(dataAlteracao);
        perfil.setDataCriacao(dataCriacao);
        perfil.setDescricao(descricao);

        return perfil;
    }
}