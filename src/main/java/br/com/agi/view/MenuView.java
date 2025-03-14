package br.com.agi.view;
import java.util.Scanner;
import br.com.agi.dao.UsuarioDAO;

public class MenuView {
    private Scanner sc;

    public MenuView() {
        this.sc = new Scanner(System.in);
    }

    public void ExibirMenu() {
        char opcao;

        while (true) {
            System.out.print("\n==============================\n");
            System.out.println("1 - Acessar conta");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();

            switch (opcao) {
                case '1':
                    ExibirTelaLogin();
                    break;
                case '2':
                    TelaCadastro();
                    break;
                case '3':
                    System.out.println("Saindo do sistema... Até logo!");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

        }
    }

    public void ExibirMenuInicial() {
        char opcao;

        while (true) {
            System.out.print("\n==============================\n");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Acessar conta");
            System.out.println("3 - Relatório de faturamento"); // Alterado por Lilian
            System.out.println("4 - Ajustar taxas");
            System.out.println("5 - Listar cobranças por cliente");
            System.out.println("6 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();

            switch (opcao) {
                case '1':
                    System.out.println("🚧 Criar Conta - Em desenvolvimento...");
                    break;
                case '2':
                    ExibirTelaLogin();
                    break;
                case '3':
                    RelatorioFaturamentoView relatorio = new RelatorioFaturamentoView();  // Alterado por Lilian
                    relatorio.ExibirRelatorio();
                    break;
                case '4':
                    TaxaMenuView taxaMenuView = new TaxaMenuView();  //alterado por Tulio
                    taxaMenuView.telaInicial();
                    break;
                case '5':
                    System.out.println("🚧 Lista de Cobranças - Em desenvolvimento... ");
                    break;
                case '6':
                    System.out.println("Saindo do sistema... Até logo!");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

        }
    }

    private void ExibirTelaLogin() {
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
        } else {
            System.out.println("E-mail ou senha incorretos! Tente novamente.");
        }
    }

    private void TelaCadastro() {
        System.out.println("\n===== CADASTRO DE USUÁRIO =====");

        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.cadastrarUsuario(nome, email, senha)) {
            System.out.println(" Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar usuário! Tente novamente.");
        }
    }
}

