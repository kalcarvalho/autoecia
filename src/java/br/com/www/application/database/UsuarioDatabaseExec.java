package br.com.www.application.database;

import br.com.www.support.database.IDatabaseExec;
import java.sql.Connection;
import br.com.www.support.exception.SystemException;
import java.sql.*;
import java.util.Date;

import br.com.www.application.domain.Usuario;

public class UsuarioDatabaseExec implements IDatabaseExec {

    public UsuarioDatabaseExec() {}

    public Object insert(Connection conn, Object object) {
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO usu_usuario ");
        sql.append(" (usu_senha, usu_login, usu_nome, usu_status, usu_email, usu_ddd, usu_telefone, usu_ramal, ");
        sql.append(" usu_criacao, usu_alteracao, usu_responsavel, ");
        sql.append(" usu_perfil) ");
        sql.append(" VALUES ");
        sql.append(" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        PreparedStatement pstm = null;
        Usuario usuario = (Usuario)object;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, usuario.getChave().trim().toUpperCase());
            pstm.setString(2, usuario.getPassword().trim());
            pstm.setString(3, usuario.getNome().trim());
            if (usuario.isAtivado().booleanValue() == true) {
                pstm.setString(4, "1");
            } else {
                pstm.setString(4, "0");
            }
            pstm.setString(5, usuario.getEmail().trim());
            pstm.setString(6, usuario.getDdd().trim());
            pstm.setString(7, usuario.getTelefone().trim());
            pstm.setString(8, usuario.getRamal().trim());
            pstm.setTimestamp(9, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(10, null);
            pstm.setString(11, usuario.getUsuarioResponsavel().getChave());
            pstm.setString(12, usuario.getPerfil().getCodigo());
            pstm.executeUpdate();
            UsuarioDatabaseFinder finder = new UsuarioDatabaseFinder();
            return finder.findByPK(conn, usuario.getChave().trim());
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            try {
                pstm.close();
            } catch (SQLException ex) {
                pstm = null;
            }
        }
    }

    public Object delete(Connection conn, Object object) {
        StringBuffer sql = new StringBuffer();
        sql.append(" DELETE FROM usuario ");
        sql.append(" WHERE chave = ? ");
        PreparedStatement pstm = null;
        String chave = (String) ((Usuario) object).getChave();
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, chave);
            pstm.executeUpdate();
            return null;
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            try {
                pstm.close();
            } catch (SQLException ex) {
                pstm = null;
            }
        }
    }

    public Object update(Connection conn, Object object) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE usuario SET ");
        sql.append(" nome = ?, status = ?, email = ?, ");
        sql.append(" ddd = ?, telefone = ?, ramal = ?, ");
        sql.append(" dataAlteracao = ?, chaveResponsavel = ?, ");
        sql.append(" codigoPerfil = ? ");
        sql.append(" WHERE ");
        sql.append(" chave = ? ");
        PreparedStatement pstm = null;
        Usuario usuario = (Usuario)object;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, usuario.getNome().trim());
            if (usuario.isAtivado().booleanValue() == true) {
                pstm.setString(2, "1");
            } else {
                pstm.setString(2, "0");
            }
            pstm.setString(3, usuario.getEmail().trim());
            pstm.setString(4, usuario.getDdd().trim());
            pstm.setString(5, usuario.getTelefone().trim());
            pstm.setString(6, usuario.getRamal().trim());
            pstm.setTimestamp(7, new Timestamp(new Date().getTime()));
            pstm.setString(8, usuario.getUsuarioResponsavel().getChave());
            pstm.setString(9, usuario.getPerfil().getCodigo());
            pstm.setString(10, usuario.getChave().trim());
            pstm.executeUpdate();
            UsuarioDatabaseFinder finder = new UsuarioDatabaseFinder();
            return finder.findByPK(conn, usuario.getChave().trim());
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            try {
                pstm.close();
            } catch (SQLException ex) {
                pstm = null;
            }
        }
    }
}
