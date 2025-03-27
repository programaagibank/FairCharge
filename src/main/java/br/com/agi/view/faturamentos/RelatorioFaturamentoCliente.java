package br.com.agi.view.faturamentos;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.FaturamentoCliente;

import java.util.List;
import java.util.Scanner;

public class RelatorioFaturamentoCliente {
    private Scanner sc;

    public RelatorioFaturamentoCliente() {
        this.sc = new Scanner(System.in);
    }

    public void exibirRelatorioCliente(int mes, int ano, String cpf) {
        FaturamentoController faturamentoController = new FaturamentoController();
        FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente(cpf, mes, ano);

        if (cliente == null) {
            System.out.println("\n**Erro ao gerar o relatório de faturamento do cliente. Tente novamente mais tarde.**");
            return;
        }

        System.out.println("\n==============================");
        System.out.printf(" RELATÓRIO DE FATURAMENTO CLIENTE - %02d/%d%n", mes, ano);
        System.out.println("==============================\n");
        System.out.printf("Cliente: %s%n", cliente.getCliente());
        System.out.printf("Total de cobranças registradas: %d%n", cliente.getTotalCobrancas());
        System.out.printf("Total recebido: R$ %.2f%n", cliente.getTotalRecebido());
        System.out.printf("Total pendente: R$ %.2f%n", cliente.getTotalPendente());
        System.out.printf("Total inadimplente: R$ %.2f%n", cliente.getTotalInadimplente());

        System.out.println("\nDETALHAMENTO POR COBRANÇAS:");
        List<CobrancasFaturamento> cobrancas = cliente.getCobrancas();

        if (cobrancas.isEmpty()) {
            System.out.println("Nenhuma cobrança encontrada.");
        } else {
            for (CobrancasFaturamento cobranca : cobrancas) {
                System.out.printf("ID: %5s | Valor: R$ %10.2f | Vencimento: %s | Status: %-10s%n",
                        cobranca.getIdCobranca(),
                        cobranca.getValorCobranca(),
                        cobranca.getVencimento(),
                        cobranca.getStatus());
            }
        }

        System.out.println("\n==============================");
        System.out.println(" FIM DO RELATÓRIO ");
        System.out.println("==============================\n");

        System.out.println("\nPressione ENTER para voltar ao menu anterior...");
        sc.nextLine();
    }
}
