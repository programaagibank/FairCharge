package br.com.agi.view.faturamentos;

public class RelatorioFaturamentoView{


    public void ExibirRelatorio(){
        int mesRelatorio = 0, anoRelatorio = 0,cobrancasRegisTotal = 0;
        double recebidosTotal = 0,pendentesTotal = 0,inadimplentesTotal = 0, emprestimosReceb = 0,emprestimosPend = 0,
                tarifasReceb = 0,tarifasPend = 0,bolEmpresReceb = 0,bolEmpresInad = 0;

        System.out.println("\n**Relatorio ainda sem dados, aguardando desenvolvimento...");//todo, Excluir quando os dados forem colocados.
        System.out.println("RELATORIO DE FATURAMENTO - Mes: " +mesRelatorio+ "/" +anoRelatorio+ "\n");
        System.out.println("Total de cobrancas registradas: " +cobrancasRegisTotal);
        System.out.println("Total recebido: " +recebidosTotal);
        System.out.println("Total pendente: " +pendentesTotal );
        System.out.println("Total inadimplente: " +inadimplentesTotal+ "\n");
        System.out.println("Detalhamento por categoria");
        System.out.println("Emprestimo: " +emprestimosReceb+ " recebidos / " +emprestimosPend+ " pendentes");
        System.out.println("Tarifas Bancarias: " +tarifasReceb+ " recebidos / " +tarifasPend+ " pendentes");
        System.out.println("Boletos Empresariais: " +bolEmpresReceb+ " recebidos / " +bolEmpresInad+ " inadimplentes");
        System.out.println(" Fim do Relatório");
        System.out.print("==============================\n");

    }
}