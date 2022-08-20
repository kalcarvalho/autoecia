/*
 * Anunciante.java
 *
 * Created on 13 de Fevereiro de 2007, 19:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.www.application.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Administrador
 */
public class Anunciante extends Registro {
    
    private int codigo;
    private Usuario usuario;
    private Boolean particular = new Boolean(false);
    private Collection carros = new ArrayList();
    private String logo = "";
    
    /** Creates a new instance of Anunciante */
    public Anunciante() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getParticular() {
        return particular;
    }

    public void setParticular(Boolean particular) {
        this.particular = particular;
    }

    public Collection getCarros() {
        return carros;
    }

    public void setCarros(Collection carros) {
        this.carros = carros;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
