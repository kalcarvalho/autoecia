/*
 * CarroDatabaseExec.java
 *
 * Created on 22 de Fevereiro de 2007, 15:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.database;

import br.com.www.application.domain.Carro;
import br.com.www.support.database.IDatabaseExec;
import br.com.www.support.exception.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kalcarvalho
 */
public class CarroDatabaseExec implements IDatabaseExec {
    
    /** Creates a new instance of CarroDatabaseExec */
    public CarroDatabaseExec() {
    }
    
    public Object insert(Connection conn, Object object) {
        return null;
    }
    
    public Object delete(Connection conn, Object object) {
        return null;
    }
    
    public Object update(Connection conn, Object object) {
        return null;
    }
    
    public Object updateVisita(Connection conn, Object object) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE car_carro SET ");
        sql.append(" car_visita = car_visita + 1 ");
        sql.append(" WHERE ");
        sql.append(" car_codigo = ? ");
        PreparedStatement pstm = null;
        Carro carro = (Carro) object;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, carro.getCodigo());
            pstm.executeUpdate();
            CarroDatabaseFinder finder = new CarroDatabaseFinder();
            return finder.findByPK(conn, String.valueOf(carro.getCodigo()));
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
    
    public Object updateProposta(Connection conn, Object object) {
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE car_carro SET ");
        sql.append(" car_proposta = car_proposta + 1 ");
        sql.append(" WHERE ");
        sql.append(" car_codigo = ? ");
        PreparedStatement pstm = null;
        Carro carro = (Carro) object;
        try {
            pstm = conn.prepareStatement(sql.toString());
            pstm.setInt(1, carro.getCodigo());
            pstm.executeUpdate();
            CarroDatabaseFinder finder = new CarroDatabaseFinder();
            return finder.findByPK(conn, String.valueOf(carro.getCodigo()));
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
