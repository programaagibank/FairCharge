package br.com.agi.model;

//todo, ATRIBUTOS USUARIO
public class Usuario {
    private String email;
    private String senha;
    private String nome;
    private int permissao;

    public Usuario(){}

    //todo, CONSTRUTOR DOS ATRIBUTOS
    public Usuario(String email, String nome,int permissao){
        this.email = email;
        this.nome = nome;
        this.permissao = permissao;
    }

    public Usuario(String email, String senha, String nome, int permissao) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.permissao = permissao;
    }

    //todo, GETTER AND SETTER'S
    //todo, controle de acesso os tornando-os publicos
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public int getPermissao() {return permissao;}
    public void setPermissao(int permissao) {this.permissao = permissao;}

    public boolean isAdmin() {
        return this.permissao == 1;
    }

}
