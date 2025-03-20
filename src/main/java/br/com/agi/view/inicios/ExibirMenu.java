package br.com.agi.view.inicios;

import br.com.agi.view.MenuFaturamentoView;
import br.com.agi.view.taxas.TaxaMenuView;

import java.util.Scanner;

public class ExibirMenu {
    Scanner sc = new Scanner(System.in);
    public void ExibirMenuInicial() {
        char opcao;

        while (true) {
            System.out.print("\n================================\n");
            System.out.println("1 - Menu Relatorio de Faturamento");
            System.out.println("2 - Ajustar taxas");
            System.out.println("3 - Listar cobrancas por cliente");
            System.out.println("4 - Sair");

            System.out.print(":");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("==================================\n");


            switch (opcao) {
                case '1':
                    MenuFaturamentoView menu = new MenuFaturamentoView(); //Alterado por Lilian
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
                    System.out.println("Saindo do sistema... Ate logo!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

        }
    }

}
