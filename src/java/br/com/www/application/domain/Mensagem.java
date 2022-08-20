/*
 * Mensagem.java
 *
 * Created on 29 de Janeiro de 2007, 14:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package br.com.www.application.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author kalcarvalho
 */
public class Mensagem extends Registro implements Serializable {
    
    private String codigo = "";
    private String descricao = "";
    private String pathfigura = "";
    private int ordem;
    private String acao = "";
    private Collection funcao = new ArrayList();
    
    /** Creates a new instance of Mensagem */
    public Mensagem() {
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPathFigura() {
        return pathfigura;
    }

    public void setPathFigura(String pathfigura) {
        this.pathfigura = pathfigura;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
     public void addFuncao(Funcao funcao) {
        if (funcao != null) {
            this.funcao.add(funcao);
        }
    }

    public void setFuncoes(Collection funcao) {
        this.funcao = funcao;
    }

    public Collection listFuncoes() {
        return funcao;
    }

    public Funcao findFuncao(String codigo) {
        Iterator it = this.funcao.iterator();
        while (it.hasNext()) {
            Funcao funcao = (Funcao) it.next();
            if (funcao.getCodigo().equalsIgnoreCase(codigo)) {
                return funcao;
            }
        }
        return null;
    }
    
}
