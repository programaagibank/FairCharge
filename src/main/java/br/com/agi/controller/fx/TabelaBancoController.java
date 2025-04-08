package br.com.agi.controller.fx;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.Faturamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TabelaBancoController {

    private int mes;
    private int ano;

    @FXML
    private TableColumn<CategoriasFaturamento, String> CategoriaColumn;

    @FXML
    private TableColumn<CategoriasFaturamento, String> InadimplentesColumn;

    @FXML
    private TableColumn<CategoriasFaturamento, String> PendentesColumn;

    @FXML
    private TableColumn<CategoriasFaturamento, String> RecebidosColumn;

    @FXML
    private TableColumn<CategoriasFaturamento, Integer> qtd_cobrancas;

    @FXML
    private TableView<CategoriasFaturamento> Tabela;

    private final FaturamentoController controller = new FaturamentoController();

    public void setMesAno(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        carregarDados();
    }

    public void initialize() {
        configurarColunas();
    }

    private void configurarColunas() {
        CategoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        InadimplentesColumn.setCellValueFactory(new PropertyValueFactory<>("inadimplentesFormatado"));
        PendentesColumn.setCellValueFactory(new PropertyValueFactory<>("pendentesFormatado"));
        RecebidosColumn.setCellValueFactory(new PropertyValueFactory<>("recebidosFormatado"));
        qtd_cobrancas.setCellValueFactory(new PropertyValueFactory<>("total_cobrancas"));
    }

    private void carregarDados() {
        if (mes > 0 && ano > 0) {
            Faturamento faturamento = controller.gerarRelatorio(mes, ano);
            List<CategoriasFaturamento> categorias = faturamento.getDetalhamentos();

            ObservableList<CategoriasFaturamento> listaCategorias = FXCollections.observableArrayList(categorias);
            Tabela.setItems(listaCategorias);
        } else {
            System.err.println("Os valores de mes e ano não foram definidos corretamente!");
        }
    }
}
