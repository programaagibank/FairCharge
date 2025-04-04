package br.com.agi.controller.fx;

import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Usuario;
import br.com.agi.utils.Navegador;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

import java.time.LocalDate;

public class MenuPagamentoController {

    @FXML
    private Label faturasPagas;

    @FXML
    private Label faturasPendentes;

    @FXML
    void handleGerarRelatorioPagamentos() {

    }

    @FXML
    void handleSair() {
        Navegador.getHome();
    }

    @FXML
    void handleVisualizarPagamentos() {
        Navegador.getPagamentosRealizados();
    }

    PagamentoController controller = new PagamentoController();
    @FXML
    void initialize() {
        LocalDate dataAtual = LocalDate.now();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();

        faturasPagas.setText(String.valueOf(controller.obterQuantidadeFaturasPagas(mesAtual, anoAtual)));
        faturasPendentes.setText(String.valueOf(controller.obterQuantidadeFaturasPendentes(mesAtual, anoAtual)));

    }
}
