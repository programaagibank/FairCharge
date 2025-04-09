package br.com.agi.controller.fx;

import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Pagamento;
import br.com.agi.model.Usuario;
import br.com.agi.utils.FormatoMonetarioFX;
import br.com.agi.utils.Navegador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuPagamentoController {

    @FXML
    private TableColumn<Pagamento, Date> DataPagamentoColumn;

    @FXML
    private TableColumn<Pagamento, Integer> IDCobrancaColumn;

    @FXML
    private TableColumn<Pagamento, Integer> IDFaturaColumn;

    @FXML
    private TableColumn<Pagamento, String> NomeClienteColumn;

    @FXML
    private TableColumn<Pagamento, Double> ValorFaturaColumn;

    @FXML
    private TableColumn<Pagamento, Double> ValorPagoColumn;

    @FXML
    private TableView<Pagamento> tabelaPagamentos;

    @FXML
    private Label faturasPagas;

    @FXML
    private Label faturasPendentes;

    @FXML
    private Label totalPago;

    @FXML
    private void botaoGrafico() {
        Navegador.getGraficoPagamento();
    }

    PagamentoController controller = new PagamentoController();

    @FXML
    void initialize() {
        LocalDate dataAtual = LocalDate.now();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();

        faturasPagas.setText(String.valueOf(controller.obterQuantidadeFaturasPagas(mesAtual, anoAtual)));
        faturasPendentes.setText(String.valueOf(controller.obterQuantidadeFaturasPendentes(mesAtual, anoAtual)));
        totalPago.setText(FormatoMonetarioFX.formatar(controller.obterValorPagoPorMes(mesAtual, anoAtual)));
        preencherTabelaPagamento();

    }



    public void preencherTabelaPagamento() {
        IDCobrancaColumn.setCellValueFactory(new PropertyValueFactory<>("cobrancaId"));
        IDFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("faturaId"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        ValorFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("ValorFaturaFormatado"));
        ValorPagoColumn.setCellValueFactory(new PropertyValueFactory<>("ValorPagoFormatado"));
        DataPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
        ObservableList<Pagamento> listaPagamentos = FXCollections.observableArrayList(controller.listarCobrancasPagas());
        tabelaPagamentos.setItems(listaPagamentos);
    }
}
