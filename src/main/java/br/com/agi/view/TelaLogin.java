package br.com.agi.view;

import br.com.agi.dao.UsuarioDAO;

import java.util.Scanner;

public class TelaLogin {
    private MenuView menuView = new MenuView();
    private Scanner sc = new Scanner(System.in);

    protected void telaDeLogin() {
        System.out.println("\n===== LOGIN =====");

        System.out.print("Digite seu E-mail: ");
        String email = sc.nextLine().trim();

        String senha;
        do {
            System.out.print("Digite sua senha: ");
            senha = sc.nextLine();

            if (senha.isEmpty()) {
                System.out.println("A senha não pode estar vazia. Tente novamente.");
            }
        } while (senha.isEmpty());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.validarLogin(email, senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo ao sistema.");
            menuView.ExibirMenuInicial();
        } else {
            System.out.println("E-mail ou senha incorretos! Tente novamente.");
        }
    }
}
