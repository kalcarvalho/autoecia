/*
 * Noticia.java
 *
 * Created on 1 de Fevereiro de 2007, 13:08
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kalcarvalho
 */
public class Noticia extends Registro implements Serializable {
    
    private String codigo = "";
    private String texto = "";
    private String titulo = "";
    private String autor = "";
    private Date datanoticia = new Date();
    private Boolean ativado = new Boolean(false);
        
    /** Creates a new instance of Noticia */
    public Noticia() {
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataNoticia() {
        return datanoticia;
    }

    public void setDataNoticia(Date datanoticia) {
        this.datanoticia = datanoticia;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
      
}
