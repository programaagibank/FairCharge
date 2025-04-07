package br.com.agi.controller.fx;

import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;

public class headerController {

    @FXML
    void AbrirCasa() {
        Navegador.getHome();
    }

    @FXML
    void AbrirPerfil() {
        Navegador.getGerenciadorUsuario();
    }

}
