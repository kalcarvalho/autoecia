/*
 * Versao.java
 *
 * Created on 13 de Fevereiro de 2007, 18:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

/**
 *
 * @author Administrador
 */
public class Versao extends Registro {
    
    private int codigo;
    private Modelo modelo = null;
    private String descricao = "";
    private int ano;
    /** Creates a new instance of Versao */
    public Versao() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
