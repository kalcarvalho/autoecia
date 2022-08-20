/*
 * Carro.java
 *
 * Created on 13 de Fevereiro de 2007, 18:39
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
public class Carro extends Registro {
    
    private int codigo;
    private Boolean novo = new Boolean(false);
    private Cambio cambio = null;
    private Versao versao = null;
    private long quilometragem;
    private Boolean blindado = new Boolean(false);
    private String cor = "";
    private int placa;
    private Boolean deficiente = new Boolean(false);
    private Boolean garantia = new Boolean(false);
    private Boolean unicoDono = new Boolean(false);
    private int portas;
    private String combustivel;
    private String observacoes;
    private String descricao;
    private Anunciante anunciante = null;
    private Collection opcional = new ArrayList();
    private Collection fotos = new ArrayList();
    private Boolean destaque = new Boolean(false);
    private long visitas;
    private long propostas;
    private double valor;
    private Boolean possuiFoto = new Boolean(false);
    
    /** Creates a new instance of Carro */
    public Carro() {
    }
    
    public Carro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Boolean isNovo() {
        return novo;
    }

    public void setNovo(Boolean novo) {
        this.novo = novo;
    }

    public Cambio getCambio() {
        return cambio;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public Versao getVersao() {
        return versao;
    }

    public void setVersao(Versao versao) {
        this.versao = versao;
    }

    public long getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(long quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Boolean isBlindado() {
        return blindado;
    }

    public void setBlindado(Boolean blindado) {
        this.blindado = blindado;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public Boolean isDeficiente() {
        return deficiente;
    }

    public void setDeficiente(Boolean deficiente) {
        this.deficiente = deficiente;
    }

    public Boolean isGarantia() {
        return garantia;
    }

    public void setGarantia(Boolean garantia) {
        this.garantia = garantia;
    }

    public Boolean isUnicoDono() {
        return unicoDono;
    }

    public void setUnicoDono(Boolean unicoDono) {
        this.unicoDono = unicoDono;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public Collection getOpcional() {
        return opcional;
    }

    public void setOpcional(Collection opcional) {
        this.opcional = opcional;
    }

    public long getVisitas() {
        return visitas;
    }

    public void setVisitas(long visitas) {
        this.visitas = visitas;
    }

    public long getPropostas() {
        return propostas;
    }

    public void setPropostas(long propostas) {
        this.propostas = propostas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Boolean getPossuiFoto() {
        return possuiFoto;
    }

    public void setPossuiFoto(Boolean possuiFoto) {
        this.possuiFoto = possuiFoto;
    }

    public Collection getFotos() {
        return fotos;
    }

    public void setFotos(Collection fotos) {
        this.fotos = fotos;
    }
    
}
