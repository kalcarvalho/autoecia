/*
 * Opcional.java
 *
 * Created on 13 de Fevereiro de 2007, 18:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

/**
 *
 * @author Administrador
 */
public class Opcional extends Registro {
    
    private int codigo;
    private String descricao;
    private Boolean importante = new Boolean(false);
    /** Creates a new instance of Opcional */
    public Opcional() {
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

    public Boolean getImportante() {
        return importante;
    }

    public void setImportante(Boolean importante) {
        this.importante = importante;
    }
    
}
