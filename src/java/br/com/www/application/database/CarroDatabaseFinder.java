/*
 * CarroDatabaseFinder.java
 *
 * Created on 14 de Fevereiro de 2007, 19:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Anunciante;
import br.com.www.application.domain.Cambio;
import br.com.www.application.domain.Carro;
import br.com.www.application.domain.Opcional;
import br.com.www.application.domain.Versao;
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
 * @author Administrador
 */
public class CarroDatabaseFinder implements IDatabaseFinder {
    
    /** Creates a new instance of CarroDatabaseFinder */
    public CarroDatabaseFinder() {
    }
    
    public Object findByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM car_carro ");
        sql.append(" WHERE car_codigo = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String codigo = (String) pk;
        Carro carro = null;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.toString());
            
            rs = pstm.executeQuery();
            
            
            if (rs.next()) {
                carro = makeCarroObject(rs);
                
                
                 
                 if (rs.getString("car_anunciante") != null &&
                        rs.getString("car_anunciante").trim().length() > 0) {
                    AnuncianteDatabaseFinder afinder = new AnuncianteDatabaseFinder();
                    carro.setAnunciante((Anunciante) afinder.findByPK(conn, rs.getString("car_anunciante")));
                }
                
                if (rs.getString("car_cambio") != null && rs.getString("car_cambio").trim().length() > 0) {
                    CambioDatabaseFinder finder = new CambioDatabaseFinder();
                    carro.setCambio((Cambio) finder.findByPK(conn, rs.getString("car_cambio").trim()));
                }
                
                OpcionalDatabaseFinder ofinder = new OpcionalDatabaseFinder();
                Collection opcionais =  ofinder.listOpcionaisCarro(conn, carro);
                carro.setOpcional(opcionais);
                
                
                if (rs.getString("car_versao") != null && rs.getString("car_versao").trim().length() > 0) {
                    VersaoDatabaseFinder finder = new VersaoDatabaseFinder();
                    carro.setVersao((Versao) finder.findByPK(conn, rs.getString("car_versao").trim()));
                }
                
                carro.setFotos(this.listFotosByCarroByPK(conn, pk));
                
            }
            return carro;
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
    
    public Collection findByParameter(Connection conn, String parameter, String value) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM car_carro ");
        sql.append(" WHERE " + parameter + " = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, value.toString());
            
            rs = pstm.executeQuery();
            Collection result = new ArrayList();
            
            while (rs.next()) {
                Carro carro = makeCarroObject(rs);
                
                /*
                 @todo Implementar Anunciante
                 if (rs.getString("car_anunciante") !=null &&
                        rs.getString("car_anunciante").trim().length() > 0) {
                    PerfilDatabaseFinder pFinder = new PerfilDatabaseFinder();
                    usuario.setPerfil((Perfil)pFinder.findByPK(conn, rs.getString("codigoPerfil").trim()));
                }*/
                
                if (rs.getString("car_cambio") != null && rs.getString("car_cambio").trim().length() > 0) {
                    CambioDatabaseFinder finder = new CambioDatabaseFinder();
                    carro.setCambio((Cambio) finder.findByPK(conn, rs.getString("car_cambio").trim()));
                }
                
                 /*
                  @todo Implementar opcionais
                  */
                
                if (rs.getString("car_versao") != null && rs.getString("car_versao").trim().length() > 0) {
                    VersaoDatabaseFinder finder = new VersaoDatabaseFinder();
                    carro.setVersao((Versao) finder.findByPK(conn, rs.getString("car_versao").trim()));
                }
                
                result.add(carro);
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
    
    public Collection listFotosByCarroByPK(Connection conn, Object pk) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM caf_carrofotos ");
        sql.append(" WHERE caf_carro = ? ");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String codigo = (String) pk;
        
        try {
            
            pstm = conn.prepareStatement(sql.toString());
            pstm.setString(1, codigo.toString());
            
            rs = pstm.executeQuery();
            Collection result = new ArrayList();
            
            while (rs.next()) {
                
                result.add(rs.getString("caf_pathfoto"));
                
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
    
    private Carro makeCarroObject(ResultSet rs) throws SQLException {
        
        int codigo = rs.getInt("car_codigo");
        long quilometragem = rs.getLong("car_quilometragem");
        String cor = rs.getString("car_cor").trim();
        int placa = rs.getInt("car_placa");
        String combustivel = rs.getString("car_combustivel").trim();
        int portas = rs.getInt("car_portas");
        String observacoes = rs.getString("car_observacoes").trim();
        String descricao = rs.getString("car_descricao").trim();
        double valor = rs.getDouble("car_valor");
        
        int visitas = rs.getInt("car_visita");
        long proposta = rs.getLong("car_proposta");
        
        Boolean novo = null;
        if (rs.getString("car_novo") != null &&
                rs.getString("car_novo").trim().equalsIgnoreCase("1")) {
            novo = new Boolean(true);
        } else {
            novo = new Boolean(false);
        }
        
        Boolean blindado = null;
        if (rs.getString("car_blindado") != null &&
                rs.getString("car_blindado").trim().equalsIgnoreCase("1")) {
            blindado = new Boolean(true);
        } else {
            blindado = new Boolean(false);
        }
        
        Boolean deficiente = null;
        if (rs.getString("car_deficiente") != null &&
                rs.getString("car_deficiente").trim().equalsIgnoreCase("1")) {
            deficiente = new Boolean(true);
        } else {
            deficiente = new Boolean(false);
        }
        
        Boolean garantia = null;
        if (rs.getString("car_garantia") != null &&
                rs.getString("car_garantia").trim().equalsIgnoreCase("1")) {
            garantia = new Boolean(true);
        } else {
            garantia = new Boolean(false);
        }
        
        Boolean unicoDono = null;
        if (rs.getString("car_unico_dono") != null &&
                rs.getString("car_unico_dono").trim().equalsIgnoreCase("1")) {
            unicoDono = new Boolean(true);
        } else {
            unicoDono = new Boolean(false);
        }
        
        Boolean possuiFoto = null;
        if (rs.getString("car_tem_foto") != null &&
                rs.getString("car_tem_foto").trim().equalsIgnoreCase("1")) {
            possuiFoto = new Boolean(true);
        } else {
            possuiFoto = new Boolean(false);
        }
        
        Carro carro = new Carro();
        carro.setCodigo(codigo);
        carro.setQuilometragem(quilometragem);
        carro.setCor(cor);
        carro.setPlaca(placa);
        carro.setPortas(portas);
        carro.setBlindado(blindado);
        carro.setDeficiente(deficiente);
        carro.setCombustivel(combustivel);
        carro.setGarantia(garantia);
        carro.setUnicoDono(unicoDono);
        carro.setValor(valor);
        carro.setDescricao(descricao);
        carro.setObservacoes(observacoes);
        carro.setPossuiFoto(possuiFoto);
        carro.setVisitas(visitas);
        carro.setPropostas(proposta);
        return carro;
    }
    
}
