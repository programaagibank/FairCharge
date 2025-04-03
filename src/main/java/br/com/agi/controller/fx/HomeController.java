package br.com.agi.controller.fx;

import br.com.agi.model.Usuario;
import br.com.agi.utils.Navegador;
import br.com.agi.utils.SessaoLogon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
    @FXML private Button gerenciarUsuarioButton;

    @FXML
    void handleAjustarTaxas() {
        Navegador.getMenuTaxa();
    }

    @FXML
    void handleGerenciarUsuario() {
        Navegador.getGerenciadorUsuario();
    }

    @FXML
    void handleListarCobrancasCliente() {
        Navegador.getMenuCobranca();
    }

    @FXML
    void handleListarPagamentos() {

    }

    @FXML
    void handleRelatorioFaturamento() {
        Navegador.getMenuRelatorio();
    }

    @FXML
    void handleSair() {
        Navegador.getLogin();
    }



    @FXML
    public void initialize() {
        Usuario usuarioLogado = SessaoLogon.getLoggedUser();

        if (usuarioLogado != null && !usuarioLogado.isAdmin()) {
            gerenciarUsuarioButton.setVisible(false);
            gerenciarUsuarioButton.setManaged(false);
        }
    }

}
