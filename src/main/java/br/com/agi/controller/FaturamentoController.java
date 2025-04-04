package br.com.agi.controller;

import br.com.agi.dao.FaturamentoDAO;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;

public class FaturamentoController {
    public FaturamentoDAO faturamentoDAO;

    public FaturamentoController() {
        this.faturamentoDAO = new FaturamentoDAO();
    }

    public Faturamento gerarRelatorio(int mes, int ano) {
        Faturamento faturamento = faturamentoDAO.obterRelatorioFaturamentoBanco(mes, ano);
        if (faturamento != null) {
            return faturamento;
        } else {
            System.out.println("Erro ao gerar relatório de faturamento do banco.");
            return null;
        }
    }

    public FaturamentoCliente gerarRelatorioCliente(String cpf, int mes, int ano) {
        FaturamentoCliente cliente = faturamentoDAO.obterRelatorioFaturamentoCliente(cpf, mes, ano);
        if (cliente != null) {
            return cliente;
        } else {
            System.out.println("Erro ao gerar relatório de faturamento do cliente.");
            return null;
        }
    }
}