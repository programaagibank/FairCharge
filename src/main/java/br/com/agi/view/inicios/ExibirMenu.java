package br.com.agi.view.inicios;

import br.com.agi.dao.PagamentoDAO;
import br.com.agi.view.GerenciadorUsuarioView;
import br.com.agi.view.faturamentos.MenuFaturamentoView;
import br.com.agi.view.taxas.TaxaMenuView;

import java.util.Scanner;

public class ExibirMenu {
    Scanner sc = new Scanner(System.in);
    GerenciadorUsuarioView gerenciadorUsuario = new GerenciadorUsuarioView();
    PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public void ExibirMenuInicial() {
        char opcao;

        while (true) {
            System.out.print("\n================================\n");
            System.out.println("1 - Menu Relatorio de Faturamento");
            System.out.println("2 - Ajustar taxas");
            System.out.println("3 - Listar cobrancas por cliente");
            System.out.println("4 - Gerenciar Usuário");
            System.out.println("5 - Sair");
            System.out.println("6 - Listar Pagamentos"); // Nova opção adicionada

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("==================================\n");

            switch (opcao) {
                case '1':
                    MenuFaturamentoView menu = new MenuFaturamentoView();
                    menu.TelaMenu();
                    break;
                case '2':
                    TaxaMenuView taxaMenuView = new TaxaMenuView();
                    taxaMenuView.telaInicial();
                    break;
                case '3':
                    System.out.println("🚧 Lista de Cobrancas - Em desenvolvimento... ");
                    break;
                case '4':
                    gerenciadorUsuario.GerenciadorDeUsuario();
                    break;
                case '5':
                    System.out.println("Saindo do sistema... Até logo!");
                    return;
                case '6':
                    System.out.println("Listar Pagamentos");
                    PagamentoDAO listaPagamentos = new PagamentoDAO();
                    listaPagamentos.listarPagamentos();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
