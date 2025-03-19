package br.com.agi.view;

public class WelcomeView {
    public void telaBoasVindas() {
    System.out.println("============== Bem Vindo ao FairCharge =============\n");
        String textoSobreNos = """
                            "Um sistema bancario focado na gestao de cobranca
                             e faturamento. Ele registra, gerencia e acompanha
                             valores devidos, sem processar pagamentos, mas
                             atualiza os status das cobrancas via a Plataforma
                             de Pagamentos Digitais."
                            """;
        System.out.print(textoSobreNos);
    }
}
