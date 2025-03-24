package br.com.agi.view.faturamentos;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.Faturamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioFaturamentoBancoTest {

    private FaturamentoController faturamentoController;
    private RelatorioFaturamentoBanco relatorioFaturamentoBanco;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        faturamentoController = new FaturamentoController();
        relatorioFaturamentoBanco = new RelatorioFaturamentoBanco();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExibirRelatorioBanco() {
        int mes = 3, ano = 2025;
        Faturamento faturamentoTeste = new Faturamento(mes, ano);
        faturamentoTeste.setTotalCobrancas(10);
        faturamentoTeste.setTotalRecebido(5000);
        faturamentoTeste.setTotalPendente(2000);
        faturamentoTeste.setTotalInadimplente(1000);

        String simulatedInput = "alguma entrada\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        relatorioFaturamentoBanco.exibirRelatorioBanco(mes, ano);

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("RELATORIO DE FATURAMENTO BANCO - Mes: 3/2025"));
        assertTrue(output.contains("Total de cobrancas registradas: 10"));
        assertTrue(output.contains("Total recebido: 5000.0"));
        assertTrue(output.contains("Total pendente: 2000.0"));
        assertTrue(output.contains("Total inadimplente: 1000.0"));
    }
}
