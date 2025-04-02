package br.com.agi.controller.fx;

import br.com.agi.utils.DialogHelper;
import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;

import java.util.List;

public class MenuFaturamentoController {
    @FXML
    void handleFaturamentoBanco() {
        String[] mesAno = DialogHelper.solicitarMesAno();

        Navegador.getRelatorioBanco("Banco", Integer.parseInt(mesAno[0]), Integer.parseInt(mesAno[1]), null);
    }

    @FXML
    void handleFaturamentoCliente() {
        String[] mesAnoCPFCNPJ = DialogHelper.solicitarMesAnoCPFCNPJ();
        Navegador.getRelatorioBanco("Cliente", Integer.parseInt(mesAnoCPFCNPJ[0]), Integer.parseInt(mesAnoCPFCNPJ[1]), mesAnoCPFCNPJ[2]);

    }

    @FXML
    void handleSair() {
        Navegador.getHome();
    }

}

