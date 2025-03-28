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
                        System.out.println("Criado em: " + sdf.format(juros.getDataCriacao()));
                    } else {
                        System.out.println("Nenhuma taxa de juros encontrada.");
                    }

                    TaxaMulta multa = taxaController.getMultaPorAtraso();
                    if (multa != null) {
                        System.out.println("Multa por Atraso");
                        System.out.println("ID: " + multa.getMultaId());
                        System.out.println("Percentual: " + multa.getPercentualMulta() + "%");
                        System.out.println("Criado em: " + sdf.format(multa.getDataCriacao()));
                    } else {
                        System.out.println("Nenhuma multa encontrada.");
                    }
                    break;
                case 2:
                    TaxaJuros jurosAdd = taxaController.addTaxaJuros();

                    System.out.println("Atualização da Taxa de Juros Diários");
                    System.out.println("ID: " + jurosAdd.isertJurosId());
                    System.out.println("Percentual: " + jurosAdd.inserPercentualDiario() + "%");
                    System.out.println("Criado em: " + sdf.format(jurosAdd.intertDataCriacao()));


                case 3:
                    TaxaMulta multaAdd = taxaController.addTaxaMulta();
                    if (multaAdd != null) {
                        System.out.println("Multa por Atraso");
                        System.out.println("ID: " + multaAdd.insertMulta());
                        System.out.println("Percentual: " + multaAdd.inserPercentualMulta() + "%");
                        System.out.println("Criado em: " + sdf.format(multaAdd.intertDataCriacaoMulta()));
                    } else {
                        System.out.println("Nenhuma multa encontrada.");
                    }
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
