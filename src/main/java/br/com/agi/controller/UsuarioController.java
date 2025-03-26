package br.com.agi.controller;
import br.com.agi.dao.UsuarioDAO;
import br.com.agi.utils.Parametros;

public class UsuarioController {
    Parametros param = new Parametros();
    UsuarioDAO dao = new UsuarioDAO();

    public boolean cadastroUsuario(String nome, String email, String senha, String permissao) {
        boolean vlrsCadastro = param.verificaCadastroVazio(nome, email, senha, permissao);
        if (vlrsCadastro) {
            dao.cadastrarUsuario(nome, email, senha, permissao);
            return true;
        } else {
            return false;
        }
    }

    public boolean acessarLogin(String email, String senha){
        boolean vlrsLogin = param.verificaLogin(email, senha);
        if (vlrsLogin){
            return dao.validarLogin(email, senha);
        }
        return false;
    }


    public boolean atualizarNome(String email, String novoNome) {
        return dao.updateNome(novoNome, email);
    }

    public boolean atualizarSenha(String email, String novaSenha) {
        return dao.updateSenha(novaSenha, email);
    }

    public boolean atualizarPermissao(String email, int novaPermissao) {
        return dao.updatePermissao(novaPermissao, email);
    }

    public boolean atualizarEmail(String emailAtual, String novoEmail) {
        return dao.updateEmail(novoEmail, emailAtual);
    }

    public boolean deletarUsuario(String email) {
        return dao.deletarUsuario(email);
    }

}
