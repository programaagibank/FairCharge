package br.com.agi.model;

public class Usuario {
    private String email;
    private String senha;
    private String nome;
    private String permissao;

    public Usuario(){}

    public Usuario(String email, String senha, String nome,String permissao){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.permissao = permissao;
    }


    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getPermissao() {return permissao;}
    public void setPermissao(String permissao) {this.permissao = permissao;}
}
