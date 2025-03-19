package br.com.agi.view;
import java.util.Scanner;
import br.com.agi.dao.UsuarioDAO;

public class MenuView {
    private Scanner sc;


    public MenuView() {
        this.sc = new Scanner(System.in);
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
            ExibirMenuInicial();
        } else {
            System.out.println("E-mail ou senha incorretos! Tente novamente.");
        }
    }

    public void ExibirMenuInicial() {
        char opcao;

        while (true) {
            System.out.print("\n================================\n");
            System.out.println("1 - Relatorio de faturamento"); // Alterado por Lilian
            System.out.println("2 - Ajustar taxas");
            System.out.println("3 - Listar cobrancas por cliente");
            System.out.println("4 - Sobre nos");
            System.out.println("5 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("==================================\n");


            switch (opcao) {
                case '1':
                    RelatorioFaturamentoView relatorio = new RelatorioFaturamentoView();  // Alterado por Lilian
                    relatorio.ExibirRelatorio();
                    break;
                case '2':
                    TaxaMenuView taxaMenuView = new TaxaMenuView();  //alterado por Tulio
                    taxaMenuView.telaInicial();
                    break;
                case '3':
                    System.out.println("🚧 Lista de Cobrancas - Em desenvolvimento... ");
                    break;
                case '4':
                    String textoSobreNos = """
                            
                                                FAIRCHARGE
                            "O FairCharge e um sistema bancario especializado
                            em gestao de cobranca e faturamento, garantindo que os
                            valores devidos ao banco sejam registrados, gerenciados
                            e acompanhados. Ele nao processa pagamentos, mas interage
                            com o sistema de Plataforma de Pagamentos Digitais para
                            atualizacao dos status das cobrancas."
                            """;
                    System.out.print(textoSobreNos);
                    break;
                case '5':
                    System.out.println("Saindo do sistema... Ate logo!");
                    sc.close();

                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

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

