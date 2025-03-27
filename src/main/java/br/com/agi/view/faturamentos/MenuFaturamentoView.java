package br.com.agi.view.faturamentos;

import br.com.agi.utils.Parametros;

import java.util.Scanner;

public class MenuFaturamentoView {
    private Scanner sc;
    private Parametros parametros;

    public MenuFaturamentoView() {
        this.sc = new Scanner(System.in);
        this.parametros = new Parametros();
    }

    int mes = 0, ano = 0;
    public void TelaMenu() {
        byte opcao = 0;

        // Opções do menu Faturamento
        while (true) {
            String textoMenu = """
                    Escolha uma das opcoes para gerar Relatorio de Faturamento:
                    1 - Faturamento de Cliente
                    2 - Faturamento do Banco
                    3 - Voltar ao Menu Principal""";

            System.out.println(textoMenu);
            opcao = sc.nextByte();
            System.out.println();

            switch (opcao) {
                case 1:
                    String documentoCliente;
                    solicitaMesAno();
                    while (true) {
                        System.out.print("Digite o CPF ou CNPJ do cliente: ");
                        String entrada = sc.next();
                        if (parametros.validaCPFouCNPJ(entrada)) {
                            documentoCliente = entrada.replaceAll("\\D", "");
                            break;
                        } else {
                            System.out.println("Documento inválido! Digite um CPF ou CNPJ válido.");
                        }
                    }

                    RelatorioFaturamentoCliente relatorioCliente = new RelatorioFaturamentoCliente();
                    relatorioCliente.exibirRelatorioCliente(mes, ano, documentoCliente);
                    break;
                case 2:
                    solicitaMesAno();
                    RelatorioFaturamentoBanco relatorio = new RelatorioFaturamentoBanco();
                    relatorio.exibirRelatorioBanco(mes, ano);
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal!");
                    return;

                default:
                    System.out.println("Opção invalida! Tente novamente.");

            }
        }
    }

    private void solicitaMesAno() {
        while (true) {
            System.out.print("Digite o mês para o relatório (1-12): ");
            if (sc.hasNextInt()) {
                mes = sc.nextInt();
                if (parametros.validaMes(mes)) {
                    break;
                } else {
                    System.out.println("Mês inválido! Digite um valor entre 1 e 12.");
                }
            } else {
                System.out.println("Entrada inválida! Digite um número inteiro entre 1 e 12.");
                sc.next();
            }
        }

        while (true) {
            System.out.print("Digite o ano para o relatório: ");
            if (sc.hasNextInt()) {
                ano = sc.nextInt();
                if (parametros.validaAno(ano)) {
                    break;
                } else {
                    System.out.println("Ano inválido! Digite um ano válido.");
                }
            } else {
                System.out.println("Entrada inválida! Digite um número válido para o ano.");
                sc.next();
            }
        }
    }

}
