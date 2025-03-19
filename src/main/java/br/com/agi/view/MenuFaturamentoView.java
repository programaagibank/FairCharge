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
                    =============================================
                    Escolha uma das opçoes para gerar Relatório de Faturamento:
                    1 - Faturamento Usuário
                    2 - Faturamento Cliente do Banco
                    3 - Voltar ao Menu Principal""";

            System.out.println(textoMenu);
            opcao = sc.nextByte();
            System.out.println();

            switch (opcao) {
                case '1':
                    RelatorioFaturamentoUsuario relatorio = new RelatorioFaturamentoUsuario();
                    relatorio.ExibirRelatorioUsuario();
                    break;
                case '2':
                    System.out.println("🚧 Relatório de Cliente - Em desenvolvimento... ");
                    break;
                    /*RelatorioFaturamentoCliente relatorio = new RelatorioFaturamentoUsuarioView();
                    relatorio.ExibirRelatorioCliente();*/
                case '3':
                    System.out.println("Saindo do sistema... Ate logo!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }
            }
        }


}
