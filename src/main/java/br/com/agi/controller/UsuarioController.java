package br.com.agi.controller;
import br.com.agi.dao.UsuarioDAO;
import br.com.agi.utils.Parametros;

public class UsuarioController {
    Parametros param = new Parametros();
    UsuarioDAO dao = new UsuarioDAO();

    public boolean cadastroUsuario(String nome, String email, String senha, String permissao) {
        boolean vlrs = param.verificaCadastroVazio(nome, email, senha, permissao);
        if (vlrs) {
            dao.cadastrarUsuario(nome, email, senha, permissao);
            return true;
        } else {
            return false;
        }
    }
}
