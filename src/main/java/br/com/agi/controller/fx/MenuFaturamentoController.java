package br.com.agi.controller.fx;

import br.com.agi.utils.DialogHelper;
import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;

import java.util.List;

public class MenuFaturamentoController {
    @FXML
    void handleFaturamentoBanco() {
        String[] mesAno = DialogHelper.solicitarMesAno();
        Navegador.getRelatorioBanco("Banco");
    }

    @FXML
    void handleFaturamentoCliente() {
        String[] mesAno = DialogHelper.solicitarMesAno();
        Navegador.getRelatorioBanco("Cliente");

    }

    @FXML
    void handleSair() {
        Navegador.getHome();
    }

}

