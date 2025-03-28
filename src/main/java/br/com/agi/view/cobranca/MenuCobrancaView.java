package br.com.agi.view.cobranca;

import br.com.agi.controller.CobrancaController;
import br.com.agi.model.Cobranca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuCobrancaView {
    Scanner sc = new Scanner(System.in);
    CobrancaController cobrancaController = new CobrancaController();

    public void telaMenuCobranca() {
        char opcao;
        List<Cobranca> cobrancas = new ArrayList<>();

        while (true) {
            System.out.print("\n========= MENU COBRANÇAS =========\n");
            System.out.println("1 - Listar Cobranças Vencidas");
            System.out.println("2 - Atualizar Cobranças Vencidas");
            System.out.println("3 - Gerar Relatório de Cobranças");
            System.out.println("4 - Voltar ao Menu Principal");

            System.out.print(": ");
            opcao = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("==================================\n");

            switch (opcao) {
                case '1':
                    cobrancas = cobrancaController.listarCobrancasVencidas();
                    if (cobrancas.isEmpty()) {
                        System.out.println("Nenhuma cobrança vencida encontrada.");
                    } else {
                        System.out.printf("%-12s %-15s %-20s %-15s %-15s %-15s %-12s\n",
                                "ID Cobrança", "ID Fatura", "Nome Cliente", "Valor Atual", "Valor Atualizado", "Data Vencimento", "Status");
                        System.out.println("--------------------------------------------------------------------------------------------");

                        for (Cobranca c : cobrancas) {
                            System.out.printf("%-12d %-15d %-20s R$ %-12.2f R$ %-12.2f %-15s %-12s\n",
                                    c.getCobranca_id(),
                                    c.getFaturaId(),
                                    c.getNomeCliente(),
                                    c.getValorTotal(),
                                    c.getValorTotalComMultas(),
                                    c.getDataVencimento(),
                                    c.getStatus());
                        }

                    }
                    break;
                case '2':
                    if(cobrancaController.atualizarPendencias()) System.out.println("Cobranças atualizadas com sucesso, enviado para análise de riscos!");
                    else System.out.println("Sem cobranças vencidas!");
                    break;
                case '3':
                    cobrancas =  cobrancaController.gerarRelatorioCobrancas();
                    for (Cobranca c : cobrancas) {
                        System.out.println("ID Cobrança: " + c.getCobranca_id());
                        System.out.println("ID Fatura: " + c.getFaturaId());
                        System.out.println("Nome Cliente: " + c.getNomeCliente());
                        System.out.println("Valor Atualizado: R$ " + c.getValorTotalComMultas());
                        System.out.println("Data Vencimento: " + c.getDataVencimento());
                        System.out.println("Status: " + c.getStatus());
                        System.out.println("---------------------------------");
                    }
                    break;
                case '4':
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
