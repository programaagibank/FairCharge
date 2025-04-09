package br.com.agi.controller.fx;

import br.com.agi.model.Usuario;
import br.com.agi.utils.Navegador;
import br.com.agi.utils.SessaoLogon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    void handleAjustarTaxas() {
        Navegador.getMenuTaxa();
    }

    @FXML
    void handleListarCobrancasCliente() {
        Navegador.getMenuCobranca();
    }

    @FXML
    void handleListarPagamentos() {
        Navegador.getMenuPagamento();
    }

    @FXML
    void handleRelatorioFaturamento() {
        Navegador.getMenuRelatorio();
    }

    @FXML
    void handleSair() {
        Navegador.getLogin();
    }

}
