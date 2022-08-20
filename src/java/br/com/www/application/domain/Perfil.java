package br.com.www.application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Perfil extends Registro implements Serializable {

    private String codigo = "";
    private String descricao = "";
    private Boolean ativado = new Boolean(false);

    private Collection usuarios = new ArrayList();
    private Collection funcoes = new ArrayList();

    public Perfil() {}
    
    public Boolean isAtivado() {
        return ativado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
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

    public void addUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public Collection listUsuarios() {
        return usuarios;
    }

    public Usuario findUsuario(String chave) {
        Iterator it = usuarios.iterator();
        while (it.hasNext()) {
            Usuario usuario = (Usuario)it.next();
            if (usuario.getChave().equalsIgnoreCase(chave)) {
                return usuario;
            }
        }
        return null;
    }

    public void addFuncao(Funcao funcao) {
        if (funcao != null) {
            funcoes.add(funcao);
        }
    }

    public void setFuncoes(Collection funcoes) {
        this.funcoes = funcoes;
    }

    public Collection listFuncoes() {
        return funcoes;
    }

    public Funcao findFuncao(String codigo) {
        Iterator it = funcoes.iterator();
        while (it.hasNext()) {
            Funcao funcao = (Funcao)it.next();
            if (funcao.getCodigo().equalsIgnoreCase(codigo)) {
                return funcao;
            }
        }
        return null;
    }
}
