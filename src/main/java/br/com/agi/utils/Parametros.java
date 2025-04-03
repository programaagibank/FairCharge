package br.com.agi.utils;
import br.com.agi.dao.UsuarioDAO;

public class Parametros {
    public boolean verificaCadastroVazio(String nome, String email, String senha, String permissao) {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || permissao.isEmpty()) {
            System.out.println("Todos os campos são obrigatórios!");
            return false;
        }
        return true;
    }

    public boolean verificaLogin(String email, String senha) {
        if (email.isEmpty() || senha.isEmpty()) {
            System.out.println("Email e senha são obrigatórios!");
            return false;
        }
        return true;
    }

    public boolean validaMes(int mes) {
        return mes >= 1 && mes <= 12;
    }

    public boolean validaAno(int ano) {
        return ano > 0;
    }

    public boolean validaCPFouCNPJ(String documento) {
        documento = documento.replaceAll("\\D", "");

        return documento.length() == 11 || documento.length() == 14;
    }

    public boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
