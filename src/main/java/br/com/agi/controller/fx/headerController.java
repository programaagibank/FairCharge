package br.com.agi.controller.fx;

import br.com.agi.model.Usuario;
import br.com.agi.utils.Navegador;
import br.com.agi.utils.SessaoLogon;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class headerController {

    @FXML
    void AbrirCasa() {
        Navegador.getHome();
    }

    @FXML
    void AbrirPerfil() {
        Navegador.getGerenciadorUsuario();
    }

    @FXML
    private ImageView botaoUsuario;

    @FXML
    void initialize() {
        Usuario usuario = SessaoLogon.getLoggedUser();
        if (!usuario.isAdmin()) {
            botaoUsuario.setVisible(false);
            botaoUsuario.setManaged(false);
        }
    }

}
