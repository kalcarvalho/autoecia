package br.com.www.application.database;

import br.com.www.support.database.IDatabaseFinder;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import br.com.www.application.domain.Usuario;
import br.com.www.application.domain.Perfil;
import br.com.www.support.exception.SystemException;
import java.util.ArrayList;

public class UsuarioDatabaseFinder implements IDatabaseFinder {

    public UsuarioDatabaseFinder() {}
    
    public Collection findAll(Connection conn) {
    	StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" usuario.chave, usuario.senha, usuario.nome, usuario.status, ");
        sql.append(" usuario.email, usuario.ddd, usuario.ramal, usuario.dataCriacao, ");
        sql.append(" usuario.dataAlteracao, usuario.chaveResponsavel, usuario.telefone, ");
        sql.append(" usuario.codigoPerfil ");
        sql.append(" FROM usuario ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql.toString());
            Collection result = new ArrayList();
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usuario = makeUsuarioObject(rs);
                if (rs.getString("chaveResponsavel") !=null &&
                    rs.getString("chaveResponsavel").trim().length() > 0) {
                    Usuario responsavel = findUsuarioResponsavel(conn,
                            rs.getString("chaveResponsavel"));
                    if (responsavel != null) {
                    	usuario.setUsuarioResponsavel(responsavel);
                    }
                }
                if (rs.getString("codigoPerfil") !=null &&
                    rs.getString("codigoPerfil").trim().length() > 0) {
                    PerfilDatabaseFinder pFinder = new PerfilDatabaseFinder();
                    usuario.setPerfil((Perfil)pFinder.findByPK(conn, rs.getString("codigoPerfil").trim()));
                }
                result.add(usuario);
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

    
    
    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * ");
        sql.append(" FROM usu_usuario ");
        sql.append(" WHERE ");
        sql.append(" usu_codigo = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String chave = (String) pk;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, chave.trim());
            rs = pstm.executeQuery();
            if (rs.next()) {
                Usuario usuario = makeUsuarioObject(rs);
                
                if (rs.getString("usu_perfil") != null &&
                    rs.getString("usu_perfil").trim().length() > 0) {
                    PerfilDatabaseFinder pFinder = new PerfilDatabaseFinder();
                    usuario.setPerfil((Perfil)pFinder.findByPK(conn, rs.getString("usu_perfil").trim()));
                }
                return usuario;
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
    
    public Object findByLogin(Connection conn, String login) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * ");
        sql.append(" FROM usu_usuario ");
        sql.append(" WHERE ");
        sql.append(" usu_login = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, login.trim());
            rs = pstm.executeQuery();
            if (rs.next()) {
                Usuario usuario = makeUsuarioObject(rs);
                /* if (rs.getString("chaveResponsavel") !=null &&
                    rs.getString("chaveResponsavel").trim().length() > 0) {
                    Usuario responsavel = findUsuarioResponsavel(conn,
                            rs.getString("chaveResponsavel"));
                    if (responsavel !=null) {
                    	usuario.setUsuarioResponsavel(responsavel);
                    }
                } */
                if (rs.getString("usu_perfil") != null &&
                    rs.getString("usu_perfil").trim().length() > 0) {
                    PerfilDatabaseFinder pFinder = new PerfilDatabaseFinder();
                    usuario.setPerfil((Perfil)pFinder.findByPK(conn, rs.getString("usu_perfil").trim()));
                }
                return usuario;
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

    public Usuario findUsuarioResponsavel(Connection conn, String chave) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" usuario.chave, usuario.senha, usuario.nome, usuario.status, ");
        sql.append(" usuario.email, usuario.ddd, usuario.ramal, usuario.telefone, ");
        sql.append(" usuario.dataCriacao, usuario.dataAlteracao ");
        sql.append(" FROM usuario ");
        sql.append(" WHERE ");
        sql.append(" usuario.chave = ? ");
        PreparedStatement pstmResponsavel = null;
        ResultSet rsResponsavel = null;
        try {
            pstmResponsavel = conn.prepareStatement(sql.toString());
            pstmResponsavel.setString(1, chave.trim());
            rsResponsavel = pstmResponsavel.executeQuery();
            if (rsResponsavel.next()) {
                Usuario usuario = makeUsuarioObject(rsResponsavel);
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new SystemException(e);
        } finally {
            try {
                rsResponsavel.close();
            } catch (SQLException e) {
                rsResponsavel = null;
            }
            try {
                pstmResponsavel.close();
            } catch (SQLException e) {
                pstmResponsavel = null;
            }
        }
    }

    public Collection findUsuariosByChaveNome(Connection conn, String chaveNome) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" usuario.chave, usuario.senha, usuario.nome, usuario.status, ");
        sql.append(" usuario.email, usuario.ddd, usuario.ramal, usuario.dataCriacao, ");
        sql.append(" usuario.dataAlteracao, usuario.chaveResponsavel, usuario.telefone, ");
        sql.append(" usuario.codigoPerfil ");
        sql.append(" FROM usuario ");
        sql.append(" WHERE ");
        sql.append(" usuario.chave like ? ");
        sql.append(" or usuario.nome like ? ");
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, "%" + chaveNome.trim() + "%");
            pstm.setString(2, "%" + chaveNome.trim() + "%");
            Collection result = new ArrayList();
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usuario = makeUsuarioObject(rs);
                if (rs.getString("chaveResponsavel") !=null &&
                    rs.getString("chaveResponsavel").trim().length() > 0) {
                    Usuario responsavel = findUsuarioResponsavel(conn,
                            rs.getString("chaveResponsavel"));
                    if (responsavel != null) {
                    	usuario.setUsuarioResponsavel(responsavel);
                    }
                }
                if (rs.getString("codigoPerfil") !=null &&
                    rs.getString("codigoPerfil").trim().length() > 0) {
                    PerfilDatabaseFinder pFinder = new PerfilDatabaseFinder();
                    usuario.setPerfil((Perfil)pFinder.findByPK(conn, rs.getString("codigoPerfil").trim()));
                }
                result.add(usuario);
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

    private Usuario makeUsuarioObject(ResultSet rs) throws SQLException {
        String chave = rs.getString("usu_login").trim();
        String password = rs.getString("usu_senha").trim();
        String nome = rs.getString("usu_nome").trim();
        String ddd = rs.getString("usu_ddd").trim();
        String telefone = rs.getString("usu_telefone").trim();
        String ramal = (rs.getString("usu_ramal") == null)? "" : rs.getString("usu_ramal").trim();
        String email = rs.getString("usu_email").trim();
        String cidade = rs.getString("usu_cidade").trim();
        String uf = rs.getString("usu_estado").trim();
        
        Boolean ativado = null;
        if (rs.getString("usu_status") != null &&
            rs.getString("usu_status").trim().equalsIgnoreCase("1")) {
            ativado = new Boolean(true);
        } else {
            ativado = new Boolean(false);
        }
        java.util.Date dataCriacao = null;
        if (rs.getTimestamp("usu_criacao") != null) {
            dataCriacao = new java.util.Date(rs.getTimestamp("usu_criacao").getTime());
        }
        java.util.Date dataAlteracao = null;
        if (rs.getTimestamp("usu_alteracao") != null) {
            dataAlteracao = new java.util.Date(rs.getTimestamp("usu_alteracao").getTime());
        } else dataAlteracao = dataCriacao;

        Usuario usuario = new Usuario();
        usuario.setChave(chave);
        usuario.setPassword(password);
        usuario.setNome(nome);
        usuario.setDdd(ddd);
        usuario.setTelefone(telefone);
        usuario.setRamal(ramal);
        usuario.setEmail(email);
        usuario.setAtivado(ativado);
        usuario.setCidade(cidade);
        usuario.setUf(uf);
        usuario.setDataAlteracao(dataAlteracao);
        usuario.setDataCriacao(dataCriacao);
        

        return usuario;
    }
}
