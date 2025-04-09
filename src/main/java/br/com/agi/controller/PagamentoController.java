package br.com.agi.controller;

import br.com.agi.dao.PagamentoDAO;
import br.com.agi.model.Pagamento;

import java.util.List;

public class PagamentoController {

    private PagamentoDAO pagamentoDAO = new PagamentoDAO();

    public int obterQuantidadeFaturasPagas(int mes, int ano) {
        return pagamentoDAO.quantidadeFaturasPagas(mes, ano);
    }

    public int obterQuantidadeFaturasPendentes(int mes, int ano) {
        return pagamentoDAO.quantidadeFaturasPendentes(mes, ano);
    }

    public double obterValorPagoPorMes(int mes, int ano) {
        return pagamentoDAO.valorPagoMes(mes, ano);
    }

    public List<Pagamento> listarCobrancasPagas() {
        return pagamentoDAO.listaCobrancasPagas();
    }

    public List<Pagamento> listarPagamentosPorPeriodo(int mes, int ano) {
        return pagamentoDAO.listarPagamentosPorPeriodo(mes, ano);
    }

    public boolean pagarCobranca(int cobrancaID, double valorPago) {
        return pagamentoDAO.pagarCobranca(cobrancaID, valorPago);
    }
}

