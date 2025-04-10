package br.com.agi.controller.fx;

import br.com.agi.controller.CobrancaController;
import br.com.agi.model.Usuario;
import br.com.agi.utils.Alerta;
import br.com.agi.utils.DialogHelper;
import br.com.agi.utils.Navegador;
import br.com.agi.utils.SessaoLogon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MenuCobrancasController {

    @FXML
    private Label cobrancasVencidas;

    @FXML
    private Pane paneGerarCobrancas;

    @FXML
    private Button botaoGerarCobrancas;

    @FXML
    void handleVisualizarVencidas() {
        Navegador.getCobrancasVencidas();
    }

    @FXML
    void handleAtualizarVencidas() {
        if(controller.atualizarPendencias()) Alerta.mostrarConfirmacao("Atualização de cobranças pendentes", "Atualização Pendências", "Cobranças Atualizadas com sucesso!");
        else Alerta.mostrarErro("Atualização de cobranças pendentes", "Atualização Pendências", "Não foi possível atualizar cobranças pendentes.");
        cobrancasVencidas.setText(String.valueOf(controller.quantidadeCobrancasVencidas()));
    }

    @FXML
    void handleGerarRelatorioCobrancas() {
        Navegador.getRelatorioCobrancas();
    }

    @FXML
    void handleGerarCobrancas() {
        boolean cobrancas = controller.gerarCobranca(DialogHelper.solicitarQuantidade());
        if(cobrancas) Alerta.mostrarConfirmacao("Geração de cobranças", "Geração de Cobranças", "Cobranças geradas com sucesso!");
        else Alerta.mostrarErro("Geração de cobranças", "Geração de Cobranças", "Não foi possível gerar as cobranças!");
    }

    @FXML
    void handleSair() {
        Navegador.getHome();
    }

    CobrancaController controller = new CobrancaController();

    public void initialize() {
        cobrancasVencidas.setText(String.valueOf(controller.quantidadeCobrancasVencidas()));

        Usuario usuario = SessaoLogon.getLoggedUser();
        if (!usuario.isAdmin()) {
            botaoGerarCobrancas.setVisible(false);
            botaoGerarCobrancas.setManaged(false);
            paneGerarCobrancas.setVisible(false);
            paneGerarCobrancas.setManaged(false);
        }
    }

}
