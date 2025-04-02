package br.com.agi.controller.fx;

import br.com.agi.controller.TaxaController;
import br.com.agi.model.Taxa;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;
import br.com.agi.utils.Navegador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class TaxaControllerFX {

    private TaxaController controller = new TaxaController();

    @FXML
    private TableColumn<Taxa, Double> percentualTaxaColumn;

    @FXML
    private TableView<Taxa> tableTaxas;

    @FXML
    private TableColumn<Taxa, String> tipoTaxaColumn;

    @FXML
    private void handleSair() {
        Navegador.getHome();
    }

    @FXML
    private void handleVisualizarHistorico() {

    }

    public void initialize() {
        Taxa taxaDiaria = controller.getTaxaJurosDiarios();
        Taxa taxaMulta = controller.getMultaPorAtraso();

        percentualTaxaColumn.setCellValueFactory(new PropertyValueFactory<>("Percentual"));
        tipoTaxaColumn.setCellValueFactory(new PropertyValueFactory<>("Tipo"));

        percentualTaxaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                return String.format("%.2f", object);
            }

            @Override
            public Double fromString(String string) {
                return Double.valueOf(string);
            }
        }));

        percentualTaxaColumn.setOnEditCommit(event -> {
            Taxa taxa = event.getRowValue();
            Double newValue = event.getNewValue();
            if (taxa instanceof TaxaJuros) controller.addTaxaJuros(newValue);
            else if (taxa instanceof TaxaMulta) controller.addTaxaMulta(newValue);
        });


        ObservableList<Taxa> listaTaxa = FXCollections.observableArrayList(taxaMulta, taxaDiaria);
        tableTaxas.setItems(listaTaxa);
    }

}
