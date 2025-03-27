package br.com.agi.view.faturamentos;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;

import java.util.List;
import java.util.Scanner;

public class RelatorioFaturamentoCliente {
    private Scanner sc;

    public RelatorioFaturamentoCliente() {
        this.sc = new Scanner(System.in);
    }

    public void ExibirRelatorioCliente(int mes, int ano, int cliente_id){
        FaturamentoController faturamentoController = new FaturamentoController();
        FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente(cliente_id, mes, ano);

        //List<CobrancasFaturamento> = cliente.getCobrancas();


        // Variáveis do relatório
        int mesRelatorio = 0, anoRelatorio = 0,cobrancasRegisTotal = 0;
        double recebidosTotal = 0,pendentesTotal = 0,inadimplentesTotal = 0, emprestimosReceb = 0,emprestimosPend = 0,
                tarifasReceb = 0,tarifasPend = 0,bolEmpresReceb = 0,bolEmpresInad = 0;

        // Mensagem Temperária
        System.out.println("\n**Relatorio ainda sem dados, aguardando desenvolvimento...");//todo, Excluir quando os dados forem colocados.

        // Exibir Relatório
        System.out.println("RELATORIO DE FATURAMENTO CLIENTE - Mes: " +mes+ "/" +ano+ "\n");
        System.out.printf("Total de cobrancas registradas: " ,cliente.getTotalCobrancas());
        System.out.printf("Total recebido: %.2f " , cliente.getTotalRecebido());
        System.out.printf("Total pendente: %.2f " , cliente.getTotalPendente() );
        System.out.printf("Total inadimplente: %.2f " ,cliente.getTotalInadimplente());
        System.out.println("\nDETALHAMENTO POR CATEGORIA:");
        List<CobrancasFaturamento> categorias = cliente.getCobrancas();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
        } else {
            for (CobrancasFaturamento categoria : categorias) {
                System.out.printf("%-25s | ID: %.2f | Valor: R$ %.2f | Vencimento: %.2f% | Status: %s",
                        categoria.getIdCobranca(),
                        categoria.getValorCobranca(),
                        categoria.getVencimento(),
                        categoria.getStatus());
            }
        }
        System.out.println("\n==============================");
        System.out.println(" FIM DO RELATÓRIO ");
        System.out.println("==============================\n");

        System.out.println("\nPressione ENTER para voltar ao menu anterior...");
        sc.nextLine();


    }
}