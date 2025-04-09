package br.com.agi.controller.fx;

import br.com.agi.controller.TaxaController;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;
import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.util.Date;

public class HistoricoTaxaControllerFX {

    @FXML
    private TableColumn<TaxaJuros, Date> dataTaxaDiariaColumn;

    @FXML
    private TableColumn<TaxaMulta, Date> dataTaxaMultaColumn;

    @FXML
    private TableColumn<TaxaJuros, Double> percentualTaxaDiariaColumn;

    @FXML
    private TableColumn<TaxaMulta, Double> percentualTaxaMultaColumn;

    @FXML
    private TableView<TaxaJuros> tableTaxasDiarias;

    @FXML
    private TableView<TaxaMulta> tableTaxasMultas;

    TaxaController controller = new TaxaController();

    public void initialize() {
        preencherTabelaDiaria();
        preencherTabelaMulta();
    }

    public void preencherTabelaDiaria() {
        percentualTaxaDiariaColumn.setCellValueFactory(new PropertyValueFactory<>("Percentual"));
        dataTaxaDiariaColumn.setCellValueFactory(new PropertyValueFactory<>("DataCriacao"));
        percentualTaxaDiariaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                return String.format("%.2f", object);
            }

            @Override
            public Double fromString(String string) {
                return Double.valueOf(string);
            }
        }));

        tableTaxasDiarias.setItems(controller.buscaTodasDiarias());
    }

    public void preencherTabelaMulta() {
        percentualTaxaMultaColumn.setCellValueFactory(new PropertyValueFactory<>("Percentual"));
        dataTaxaMultaColumn.setCellValueFactory(new PropertyValueFactory<>("DataCriacao"));
        percentualTaxaMultaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                return String.format("%.2f", object);
            }

            @Override
            public Double fromString(String string) {
                return Double.valueOf(string);
            }
        }));

        tableTaxasMultas.setItems(controller.buscaTodasMultas());
    }
}
