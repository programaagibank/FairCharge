package br.com.agi.controller;
import org.mindrot.jbcrypt.BCrypt;

public class CifradorSenha {

        public String cifrarSenha(String senha) {

            String salGerado = BCrypt.gensalt();

            String senhaHasheada = BCrypt.hashpw(senha, salGerado);

            return senhaHasheada;
        }

        public boolean validarSenhaCrifrada(String senhaCifrada, String senha) {
            return BCrypt.checkpw(senha, senhaCifrada);
        }

    }
