/*
 * AutoPeca.java
 *
 * Created on 26 de Fevereiro de 2007, 12:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

import java.util.Date;

/**
 *
 * @author kalcarvalho
 */
public class AutoPeca extends Registro {
    
    private int codigo;
    private String descricao;
    private String url;
    private Boolean mercadoLivre = new Boolean(false);
    private double valor;
    private Date finaliza = new Date();
    
    /**
     * Creates a new instance of AutoPeca
     */
    public AutoPeca() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isMercadoLivre() {
        return mercadoLivre;
    }

    public void setMercadoLivre(Boolean mercadoLivre) {
        this.mercadoLivre = mercadoLivre;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFinaliza() {
        return finaliza;
    }

    public void setFinaliza(Date finaliza) {
        this.finaliza = finaliza;
    }
    
}
