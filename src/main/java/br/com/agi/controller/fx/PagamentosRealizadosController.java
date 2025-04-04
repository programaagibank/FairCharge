package br.com.agi.controller.fx;

import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Cobranca;
import br.com.agi.model.Pagamento;
import br.com.agi.utils.Navegador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class PagamentosRealizadosController {

    @FXML
    private TableColumn<Pagamento, Date> colunaDataVencimento;

    @FXML
    private TableColumn<Pagamento, Integer> colunaIdCobranca;

    @FXML
    private TableColumn<Pagamento, Integer> colunaIdFatura;

    @FXML
    private TableColumn<Pagamento, String> colunaNomeCliente;

    @FXML
    private TableColumn<Pagamento, String> colunaStatus;

    @FXML
    private TableColumn<Pagamento, Double> colunaValorFatura;

    @FXML
    private TableColumn<Pagamento, Double> colunaValorPago;

    @FXML
    private TableView<Pagamento> tabelaCobrancas;

    @FXML
    void handleSair() {
        Navegador.getMenuPagamento();
    }

    PagamentoController controller = new PagamentoController();

    public void initialize() {
        preencherTabelaPagamento();
    }

    public void preencherTabelaPagamento() {
        colunaIdCobranca.setCellValueFactory(new PropertyValueFactory<>("cobrancaId"));
        colunaIdFatura.setCellValueFactory(new PropertyValueFactory<>("faturaId"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colunaValorFatura.setCellValueFactory(new PropertyValueFactory<>("ValorFaturaFormatado"));
        colunaValorPago.setCellValueFactory(new PropertyValueFactory<>("ValorPagoFormatado"));
        colunaDataVencimento.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<Pagamento> listaPagamentos = FXCollections.observableArrayList(controller.listarCobrancasPagas());
        tabelaCobrancas.setItems(listaPagamentos);
    }
}
