/*
 * Cambio.java
 *
 * Created on 13 de Fevereiro de 2007, 18:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

/**
 *
 * @author Administrador
 */
public class Cambio extends Registro {
    
    private int codigo;
    private String descricao = "";
    /** Creates a new instance of Cambio */
    public Cambio() {
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
    
}
