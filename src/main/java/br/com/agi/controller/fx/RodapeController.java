package br.com.agi.controller.fx;

import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;

public class RodapeController {

    @FXML
    void handleSair() {
        Navegador.getMenuRelatorio();
    }

}
