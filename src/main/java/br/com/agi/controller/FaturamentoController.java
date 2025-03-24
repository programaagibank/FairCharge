package br.com.agi.controller;

import br.com.agi.dao.FaturamentoDAO;
import br.com.agi.model.Faturamento;

public class FaturamentoController {
    private FaturamentoDAO faturamentoDAO;

    public FaturamentoController() {
        this.faturamentoDAO = new FaturamentoDAO();
    }

    public Faturamento gerarRelatorio(int mes, int ano) {
        Faturamento faturamento = faturamentoDAO.obterRelatorioFaturamento(mes, ano);
        if (faturamento != null) {
            return faturamento;
        } else {
            System.out.println("Erro ao gerar relatório de faturamento.");
            return null;
        }
    }
}