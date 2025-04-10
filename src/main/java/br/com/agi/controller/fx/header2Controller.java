package br.com.agi.controller.fx;

import br.com.agi.model.Usuario;
import br.com.agi.utils.Navegador;
import br.com.agi.utils.SessaoLogon;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class header2Controller {

    @FXML
    void AbrirCasa() {
        Navegador.getHome();
    }

}
