package br.com.agi.controller;

import br.com.agi.dao.FaturamentoDAO;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FaturamentoControllerTest {
    private FaturamentoController controller;
    private FaturamentoDAO faturamentoDAOMock;

    @BeforeEach
    void setUp() {
        // Mock do FaturamentoDAO
        faturamentoDAOMock = Mockito.mock(FaturamentoDAO.class);
        controller = new FaturamentoController();

        // Substituindo o DAO da classe controller por um mock
        controller.faturamentoDAO = faturamentoDAOMock;
    }

    @Test
    void gerarRelatorioBanco_DeveRetornarFaturamento_QuandoDAOForBemSucedido() {
        // Dado
        int mes = 3;
        int ano = 2025;
        Faturamento mockFaturamento = new Faturamento();
        when(faturamentoDAOMock.obterRelatorioFaturamentoBanco(mes, ano)).thenReturn(mockFaturamento);

        // Quando
        Faturamento resultado = controller.gerarRelatorio(mes, ano);

        // Então
        assertNotNull(resultado);
        assertEquals(mockFaturamento, resultado);
        verify(faturamentoDAOMock, times(1)).obterRelatorioFaturamentoBanco(mes, ano);
    }

    @Test
    void gerarRelatorioBanco_DeveRetornarNull_QuandoDAOFalhar() {
        // Dado
        int mes = 3;
        int ano = 2025;
        when(faturamentoDAOMock.obterRelatorioFaturamentoBanco(mes, ano)).thenReturn(null);

        // Quando
        Faturamento resultado = controller.gerarRelatorio(mes, ano);

        // Então
        assertNull(resultado);
        verify(faturamentoDAOMock, times(1)).obterRelatorioFaturamentoBanco(mes, ano);
    }

    @Test
    void gerarRelatorioCliente_DeveRetornarFaturamentoCliente_QuandoDAOForBemSucedido() {
        // Dado
        String cpf = "123.456.789-00";
        int mes = 3;
        int ano = 2025;
        FaturamentoCliente mockCliente = new FaturamentoCliente();
        when(faturamentoDAOMock.obterRelatorioFaturamentoCliente(cpf, mes, ano)).thenReturn(mockCliente);

        // Quando
        FaturamentoCliente resultado = controller.gerarRelatorioCliente(cpf, mes, ano);

        // Então
        assertNotNull(resultado);
        assertEquals(mockCliente, resultado);
        verify(faturamentoDAOMock, times(1)).obterRelatorioFaturamentoCliente(cpf, mes, ano);
    }

    @Test
    void gerarRelatorioCliente_DeveRetornarNull_QuandoDAOFalhar() {
        // Dado
        String cpf = "123.456.789-00";
        int mes = 3;
        int ano = 2025;
        when(faturamentoDAOMock.obterRelatorioFaturamentoCliente(cpf, mes, ano)).thenReturn(null);

        // Quando
        FaturamentoCliente resultado = controller.gerarRelatorioCliente(cpf, mes, ano);

        // Então
        assertNull(resultado);
        verify(faturamentoDAOMock, times(1)).obterRelatorioFaturamentoCliente(cpf, mes, ano);
    }
}
