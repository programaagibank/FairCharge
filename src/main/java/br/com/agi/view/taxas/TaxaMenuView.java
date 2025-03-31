package br.com.agi.view.taxas;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import br.com.agi.controller.TaxaController;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;
import br.com.agi.model.TaxaMulta;

public class TaxaMenuView {
    private Scanner sc;
    private TaxaController taxaController;

    public TaxaMenuView() {
        this.sc = new Scanner(System.in);
        this.taxaController = new TaxaController();
    }

    public void telaInicial() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("""
                    =============================================
                            Menu Taxas:
                    1 - Visualização Taxa de Juros Diários e Multa
                    2 - Adição de Taxa de Juros
                    3 - Adiçao de Taxa de Multa
                    4 - Voltar ao Menu Principal
                    =============================================""");

            byte opcao = sc.nextByte();
            System.out.println();

            switch (opcao) {
                case 1:
                    TaxaJuros juros = taxaController.getTaxaJurosDiarios();
                    if (juros != null) {
                        System.out.println("Taxa de Juros Diários");
                        System.out.println("ID: " + juros.getJurosId());
                        System.out.println("Percentual: " + juros.getPercentualJurosDiario() + "% ao dia");
                        System.out.println("Criado em: " + juros.getDataCriacao());
                        System.out.println("--------------------------------------");
                    } else {
                        System.out.println("Nenhuma taxa de juros encontrada.");
                        System.out.println("--------------------------------------");
                    }

                    TaxaMulta multa = taxaController.getMultaPorAtraso();
                    if (multa != null) {
                        System.out.println("Multa por Atraso");
                        System.out.println("ID: " + multa.getMultaId());
                        System.out.println("Percentual: " + multa.getPercentualMulta() + "%");
                        System.out.println("Criado em: " + multa.getDataCriacao());
                    } else {
                        System.out.println("Nenhuma multa encontrada.");
                    }
                    break;
                case 2:
                    System.out.println("Digite um novo percentual de juros diário: ");
                    boolean jurosAdd = taxaController.addTaxaJuros(sc.nextDouble());
                    if (jurosAdd) {
                        TaxaJuros jurosImpressao = taxaController.getTaxaJurosDiarios();
                        System.out.println("Atualização da Taxa de Juros Diários");
                        System.out.println("ID: " + jurosImpressao.getJurosId());
                        System.out.println("Percentual: " + jurosImpressao.getPercentualJurosDiario() + "%");
                        System.out.println("Criado em: " + jurosImpressao.getDataCriacao());
                    } else System.out.println("Não foi possível atualizar o juros diário!");
                    break;
                case 3:
                    System.out.println("Digite o percentual de multa: ");
                    double percentualMulta = sc.nextDouble();
                    TaxaController multaController = new TaxaController();
                    boolean resultadoAddMulta = multaController.addTaxaMulta(percentualMulta);
                    if (resultadoAddMulta) {
                        TaxaMulta imprimirMulta = multaController.getMultaPorAtraso();
                        System.out.println("Percentual adicionado: " +imprimirMulta.getPercentualMulta());
                        System.out.println("Multa foi adicionada com sucesso!");
                    }else System.out.println("Erro ao adicionar a Multa!");

                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        TaxaMenuView menu = new TaxaMenuView();
        menu.telaInicial();
    }
}
