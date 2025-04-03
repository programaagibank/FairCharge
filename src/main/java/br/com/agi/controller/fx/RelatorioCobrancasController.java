package br.com.agi.controller.fx;

import br.com.agi.controller.CobrancaController;
import br.com.agi.model.Cobranca;
import br.com.agi.utils.Navegador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class RelatorioCobrancasController {

    @FXML
    private TableColumn<Cobranca, Date> colunaDataVencimento;

    @FXML
    private TableColumn<Cobranca, Integer> colunaIdCobranca;

    @FXML
    private TableColumn<Cobranca, Integer> colunaIdFatura;

    @FXML
    private TableColumn<Cobranca, String> colunaNomeCliente;

    @FXML
    private TableColumn<Cobranca, String> colunaStatus;

    @FXML
    private TableColumn<Cobranca, Double> colunaValor;

    @FXML
    private TableColumn<Cobranca, Double> colunaValorMulta;

    @FXML
    private TableView<Cobranca> tabelaCobrancas;

    @FXML
    void handleSair() {
        Navegador.getMenuCobranca();
    }

    CobrancaController controller = new CobrancaController();

    public void initialize() {
        preencherTabelaCobrancas();
    }

    public void preencherTabelaCobrancas() {
        colunaIdCobranca.setCellValueFactory(new PropertyValueFactory<>("cobranca_id"));
        colunaIdFatura.setCellValueFactory(new PropertyValueFactory<>("faturaId"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valorTotalFormatado"));
        colunaValorMulta.setCellValueFactory(new PropertyValueFactory<>("valorTotalComMultasFormatado"));
        colunaDataVencimento.setCellValueFactory(new PropertyValueFactory<>("dataVencimento"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<Cobranca> listaCobrancas =FXCollections.observableArrayList(controller.gerarRelatorioCobrancas());
        tabelaCobrancas.setItems(listaCobrancas);


    }

}
