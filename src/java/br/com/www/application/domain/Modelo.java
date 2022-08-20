/*
 * Modelo.java
 *
 * Created on 13 de Fevereiro de 2007, 18:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

/**
 *
 * @author Administrador
 */
public class Modelo extends Registro {
    
    private int codigo;
    private Marca marca = null;
    private String descricao = "";
    
    /** Creates a new instance of Modelo */
    public Modelo() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
