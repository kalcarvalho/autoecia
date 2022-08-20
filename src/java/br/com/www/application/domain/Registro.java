package br.com.www.application.domain;

import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable {

    private Date dataCriacao = null;
    private Date dataAlteracao = null;
    private Usuario usuarioResponsavel = null;

    public Registro() {}

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
}
