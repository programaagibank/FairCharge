package br.com.agi.controller;
import br.com.agi.dao.UsuarioDAO;
import br.com.agi.model.Usuario;
import br.com.agi.utils.Parametros;

import java.util.List;

public class UsuarioController {
    Parametros param = new Parametros();
    UsuarioDAO dao = new UsuarioDAO();

    public boolean cadastroUsuario(String nome, String email, String senha, String permissao) {
        boolean vlrsCadastro = param.verificaCadastroVazio(nome, email, senha, permissao);

        if (!vlrsCadastro) {
            return false;
        }

        if (!param.validarTamanhoSenha(senha)) {
            return false;
        }

        dao.cadastrarUsuario(nome, email, senha, permissao);
        return true;
    }

    public boolean acessarLogin(String email, String senha){
        boolean vlrsLogin = param.verificaLogin(email, senha);
        if (vlrsLogin){
            return dao.validarLogin(email, senha);
        }
        return false;
    }


    public boolean atualizarNome(String novoNome, String email) {
        return dao.updateNome(novoNome, email);
    }

    public boolean atualizarSenha(String novaSenha, String email) {

        if (!param.validarTamanhoSenha(novaSenha)) {
            return false;
        }

        return dao.updateSenha(novaSenha, email);
    }

    public boolean atualizarPermissao(String email, int novaPermissao) {
        return dao.updatePermissao(novaPermissao, email);
    }

    public boolean atualizarEmail(String novoEmail, String emailAtual) {
        return dao.updateEmail(novoEmail, emailAtual);
    }

    public boolean deletarUsuario(String email) {
        return dao.deletarUsuario(email);
    }

    public void listarTodosUsuarios(){
        dao.listarUsuarios();
    }

    public List<Usuario> listarTodosUsuariosFX(){
        return dao.listarTodosUsuarios();
    }

    public boolean listarUsuario(String email) {
        return dao.listarUnicoUsuario(email);
    }

    public boolean atualizarUsuarioFX(String emailOriginal, Usuario usuario) {
        return dao.atualizarUsuarioFX(emailOriginal, usuario);
    }
}

