package br.com.agi.view.taxas;

import java.util.Scanner;
import br.com.agi.controller.TaxaController;
import br.com.agi.dao.TaxaDAO;

public class TaxaMenuView{
    private Scanner sc;
    private TaxaController taxaController;

    public TaxaMenuView() {

        this.sc = new Scanner(System.in);
        this.taxaController = new TaxaController();
    }

    public void telaInicial() {
        byte opcao = 0;

        while (true) {
            String textoMenu = """
                    =============================================
                    Escolha uma das opçoes para ajustar as taxas:
                    1 - Taxa de Juros Diarios
                    2 - Multa por Atraso
                    3 - Voltar ao Menu Principal""";

            System.out.println(textoMenu);
            opcao = sc.nextByte();
            System.out.println();

            switch (opcao) {
                case 1:
                    taxaController.ajustarTaxaJuros();
                    break;
                case 2:
                    taxaController.ajustarTaxaMulta();
                    break;
                case 3:
                    System.out.println("Voltando ao Menu Principal!");
                    return;
                default:
                    System.out.println("Opçao invalida! Tente novamente");
            }
        }
    }


}
