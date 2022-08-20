package br.com.www.application.domain;

import java.io.Serializable;
import java.util.*;

public class Funcao extends Registro implements Serializable {

    private String codigo = "";
    private String descricao = "";
    private String acao = "";
    private Boolean ativado = new Boolean(false);
    private Boolean menuFunction = new Boolean(false);
    private Boolean menuRestrito = new Boolean(false);
    private String parametro = "";
    private String pathMenu = "";

    private Collection perfis = new ArrayList();
    private Collection mensagem = new ArrayList();

    public Funcao() {
    }

    public String getAcao() {
        return acao;
    }

    public Boolean isAtivado() {
        return ativado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean isMenuFunction() {
        return menuFunction;
    }
    
    public Boolean isMenuRestrito() {
        return menuRestrito;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMenuFunction(Boolean menuFunction) {
        this.menuFunction = menuFunction;
    }
    
    public void setMenuRestrito(Boolean menuRestrito) {
        this.menuRestrito = menuRestrito;
    }

    public void addPerfil(Perfil perfil) {
        if (perfil != null) {
            perfis.add(perfil);
        }
    }
    
    public Collection listPerfis() {
        return perfis;
    }
    
    public Collection listMensagem() {
        return mensagem;
    }

    public Perfil findPerfil(String codigo) {
        Iterator it = perfis.iterator();
        while (it.hasNext()) {
            Perfil perfil = (Perfil) it.next();
            if (perfil.getCodigo().equalsIgnoreCase(codigo)) {
                return perfil;
            }
        }
        return null;
    }
    
    

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getPathMenu() {
        return pathMenu;
    }

    public void setPathMenu(String pathMenu) {
        this.pathMenu = pathMenu;
    }
    
    
}
