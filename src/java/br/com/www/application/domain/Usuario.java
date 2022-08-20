package br.com.www.application.domain;

public class Usuario extends Registro {

    private String chave = "";
    private String password = "";
    private String nome = "";
    private String email = "";
    private String ddd = "";
    private String telefone = "";
    private String ramal = "";
    private Boolean ativado = new Boolean(false);
    private String cidade = "";
    private String uf = "";

    private Perfil perfil = null;

    public Usuario() {}

    public Boolean isAtivado() {
        return ativado;
    }

    public String getChave() {
        return chave;
    }

    public String getDdd() {
        return ddd;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getRamal() {
        return ramal;
    }

    public String getTelefone() {
        return telefone;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
