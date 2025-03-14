package br.com.agi.view;

import java.util.Scanner;

public class TaxaMenuView{
    private Scanner sc;

    public TaxaMenuView() {
        this.sc = new Scanner(System.in);
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
                    System.out.println("🚧 Ajuste de taxas diarias - Em desenvolvimento...");
                    break;
                case 2:
                    System.out.println("🚧 Ajuste de multas por Atraso - Em desenvolvimento...");
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
