package br.com.agi.view;

public class RelatorioFaturamentoView{


    public void ExibirRelatorio(){
        int mesRelatorio = 0, anoRelatorio = 0,cobrancasRegisTotal = 0;
        double recebidosTotal = 0,pendentesTotal = 0,inadimplentesTotal = 0, emprestimosReceb = 0,emprestimosPend = 0,
                tarifasReceb = 0,tarifasPend = 0,bolEmpresReceb = 0,bolEmpresInad = 0;


        System.out.println(" Relatório de Faturamento - Mês: " +mesRelatorio+ "/" +anoRelatorio);
        System.out.print("\n==============================\n");
        System.out.println("Total de cobranças registradas: " +cobrancasRegisTotal);
        System.out.println("Total recebido: " +recebidosTotal);
        System.out.println("Total pendente: " +pendentesTotal );
        System.out.println("Total inadimplente: " +inadimplentesTotal);
        System.out.println("");
        System.out.println("Detalhamento por categoria");
        System.out.println("Empréstimo: " +emprestimosReceb+ " recebidos / " +emprestimosPend+ " pendentes");
        System.out.println("Tarifas Bancárias: " +tarifasReceb+ " recebidos / " +tarifasPend+ " pendentes");
        System.out.println("Boletos Empresariais: " +bolEmpresReceb+ " recebidos / " +bolEmpresInad+ " inadimplentes");
        System.out.print("\n==============================\n");
        System.out.println(" Fim do Relatório");
    }
}