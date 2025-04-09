package br.com.agi.controller.fx;

import br.com.agi.controller.CobrancaController;
import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Cobranca;
import br.com.agi.utils.FormatoMonetarioFX;
import br.com.agi.utils.Navegador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
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
    private Label cobrancasPagas;

    @FXML
    private Label cobrancasPendentes;

    @FXML
    private Label valorTotal;

    CobrancaController controller = new CobrancaController();
    PagamentoController pagamentoController = new PagamentoController();

    public void initialize() {
        LocalDate dataAtual = LocalDate.now();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();

        cobrancasPagas.setText(String.valueOf(controller.cobrancasPagasMes(mesAtual, anoAtual)));
        cobrancasPendentes.setText(String.valueOf(controller.cobrancasPendentesMes()));
        valorTotal.setText(FormatoMonetarioFX.formatar(controller.cobrancasValorTotalMes()));

        preencherTabelaCobrancas();

        tabelaCobrancas.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Cobranca cobrancaSelecionada = tabelaCobrancas.getSelectionModel().getSelectedItem();

                if (cobrancaSelecionada != null) {
                    int idCobranca = cobrancaSelecionada.getCobranca_id();
                    double valorComMulta = cobrancaSelecionada.getValorTotalComMultas();
                    boolean sucesso = pagamentoController.pagarCobranca(idCobranca, valorComMulta);

                    if (sucesso) {
                        System.out.println("Cobrança paga com sucesso!");
                        preencherTabelaCobrancas(); // Atualiza a tabela
                    } else {
                        System.out.println("Erro ao pagar cobrança.");
                    }
                }
            }
        });
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
