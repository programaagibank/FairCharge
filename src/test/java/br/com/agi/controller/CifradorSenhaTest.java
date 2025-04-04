package br.com.agi.controller;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class CifradorSenhaTest {

    private final CifradorSenha cifradorSenha = new CifradorSenha();

    @Test
    void cifrarSenha_DeveGerarHashDiferenteParaMesmaSenha() {
        // Dado
        String senha = "MinhaSenha123";

        // Quando
        String hash1 = cifradorSenha.cifrarSenha(senha);
        String hash2 = cifradorSenha.cifrarSenha(senha);

        // Então
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertNotEquals(hash1, hash2); // O hash deve ser único, mesmo para a mesma senha
    }

    @Test
    void validarSenhaCrifrada_DeveRetornarTrue_QuandoSenhaForValida() {
        // Dado
        String senha = "MinhaSenha123";
        String hash = cifradorSenha.cifrarSenha(senha);

        // Quando
        boolean resultado = cifradorSenha.validarSenhaCrifrada(hash, senha);

        // Então
        assertTrue(resultado); // Senha válida deve retornar true
    }

    @Test
    void validarSenhaCrifrada_DeveRetornarFalse_QuandoSenhaForInvalida() {
        // Dado
        String senha = "MinhaSenha123";
        String senhaIncorreta = "SenhaErrada456";
        String hash = cifradorSenha.cifrarSenha(senha);

        // Quando
        boolean resultado = cifradorSenha.validarSenhaCrifrada(hash, senhaIncorreta);

        // Então
        assertFalse(resultado); // Senha inválida deve retornar false
    }

    @Test
    void cifrarSenha_DeveGerarHashQuePodeSerValidado() {
        // Dado
        String senha = "MinhaSenha123";

        // Quando
        String hash = cifradorSenha.cifrarSenha(senha);

        // Então
        assertTrue(BCrypt.checkpw(senha, hash)); // Garantia que o hash gerado pode ser validado
    }
}
