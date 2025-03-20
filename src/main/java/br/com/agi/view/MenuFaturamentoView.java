package br.com.agi.view;

import br.com.agi.view.faturamentos.RelatorioFaturamentoUsuario;
import br.com.agi.view.faturamentos.RelatorioFaturamentoUsuario;

import java.util.Scanner;

public class MenuFaturamentoView {
    private Scanner sc;

    public MenuFaturamentoView() {
        this.sc = new Scanner(System.in);
    }

    public void TelaMenu() {
        byte opcao = 0;

        // Opções do menu Faturamento
        while (true) {
            String textoMenu = """
                    Escolha uma das opcoes para gerar Relatorio de Faturamento:
                    1 - Faturamento Usuario
                    2 - Faturamento do Banco
                    3 - Voltar ao Menu Principal""";

            System.out.println(textoMenu);
            opcao = sc.nextByte();
            System.out.println();

            switch (opcao) {
                case 1:
                    System.out.println("🚧 Relatório de Cliente - Em desenvolvimento... ");
                    break;
                    /*RelatorioFaturamentoCliente relatorio = new RelatorioFaturamentoUsuarioView();
                    relatorio.ExibirRelatorioCliente();*/
                case 2:
                    RelatorioFaturamentoUsuario relatorio = new RelatorioFaturamentoUsuario();
                    relatorio.ExibirRelatorioUsuario();
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal!");
                    return;

                default:
                    System.out.println("Opção invalida! Tente novamente.");

            }
            }
        }


}
