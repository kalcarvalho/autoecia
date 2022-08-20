/*
 * Marca.java
 *
 * Created on 13 de Fevereiro de 2007, 18:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

/**
 *
 * @author Administrador
 */
public class Marca extends Registro {
    
    private int codigo;
    private String descricao = "";
    private String logo = "";
    
    
    /** Creates a new instance of Marca */
    public Marca() {
        
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
