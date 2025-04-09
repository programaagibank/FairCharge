package br.com.agi.controller.fx;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.FaturamentoCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TabelaClienteController {

    @FXML
    private TableView<CobrancasFaturamento> Tabela;
    @FXML
    private TableColumn<CobrancasFaturamento, String> IDColumn;

    @FXML
    private TableColumn<CobrancasFaturamento, String> StatusColumn;

    @FXML
    private TableColumn<CobrancasFaturamento, String> ValorColumn;

    @FXML
    private TableColumn<CobrancasFaturamento, String> VencimentoColumn;

    private final FaturamentoController faturamentoController = new FaturamentoController();

    private int mes;
    private int ano;
    private String CPFCNPJ;

    public void setMesAnoCPFCNPJ(int mes, int ano, String CPFCNPJ) {
        this.mes = mes;
        this.ano = ano;
        this.CPFCNPJ = CPFCNPJ;
        carregarDados();
    }

    private void configurarColunas() {
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("IdCobranca"));
        ValorColumn.setCellValueFactory(new PropertyValueFactory<>("ValorTotalFormatado"));
        VencimentoColumn.setCellValueFactory(new PropertyValueFactory<>("Vencimento"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }


    private void carregarDados() {
        if (mes > 0 && ano > 0) {

            FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente(CPFCNPJ, mes, ano);

            List<CobrancasFaturamento> cobrancas = cliente.getCobrancas();

            ObservableList<CobrancasFaturamento> listaCobrancas = FXCollections.observableArrayList(cobrancas);

            Tabela.setItems(listaCobrancas);
        } else {
            System.err.println("Os valores de mes e ano não foram definidos corretamente!");
        }
    }

    public void initialize() {
        configurarColunas();
    }
}
