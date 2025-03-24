package br.com.agi.view.faturamentos;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.Faturamento;
import br.com.agi.model.CategoriasFaturamento;

import java.util.List;
import java.util.Scanner;

public class RelatorioFaturamentoBanco {
    private Scanner sc;

    public RelatorioFaturamentoBanco() {
        this.sc = new Scanner(System.in);
    }

    public void exibirRelatorioBanco(int mes, int ano) {
        FaturamentoController faturamentoController = new FaturamentoController();
        Faturamento faturamento = faturamentoController.gerarRelatorio(mes, ano);

        if (faturamento == null) {
            System.out.println("\n**Erro ao gerar o relatório de faturamento. Tente novamente mais tarde.**");
            return;
        }

        System.out.println("\n==============================");
        System.out.printf(" RELATÓRIO DE FATURAMENTO - %02d/%d%n", faturamento.getMes(), faturamento.getAno());
        System.out.println("==============================\n");
        System.out.printf("Total de cobranças registradas: %d%n", faturamento.getTotalCobrancas());
        System.out.printf("Total recebido: R$ %.2f%n", faturamento.getTotalRecebido());
        System.out.printf("Total pendente: R$ %.2f%n", faturamento.getTotalPendente());
        System.out.printf("Total inadimplente: R$ %.2f%n", faturamento.getTotalInadimplente());

        System.out.println("\nDETALHAMENTO POR CATEGORIA:");
        List<CategoriasFaturamento> categorias = faturamento.getDetalhamentos();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
        } else {
            for (CategoriasFaturamento categoria : categorias) {
                System.out.printf("%-25s | Recebidos: R$ %.2f | Pendentes: R$ %.2f | Inadimplentes: R$ %.2f%n",
                        categoria.getCategoria(),
                        categoria.getRecebidos(),
                        categoria.getPendentes(),
                        categoria.getInadimplentes());
            }
        }
        System.out.println("\n==============================");
        System.out.println(" FIM DO RELATÓRIO ");
        System.out.println("==============================\n");

        System.out.println("\nPressione ENTER para voltar ao menu anterior...");
        sc.nextLine();
    }
}
