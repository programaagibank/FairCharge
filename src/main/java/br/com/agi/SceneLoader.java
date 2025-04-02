package br.com.agi;

import br.com.agi.controller.fx.RelatorioFaturamentoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    private static String titulo = "FairCharge - Sistema de Cobranças";

    public static void loadScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("/br/com/agi/view/" + fxmlFile + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSceneRelatorios(Stage stage, String fxmlFile, String relatorio, int mes, int ano, String CPFCNPJ) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("/br/com/agi/view/" + fxmlFile + ".fxml"));

            Parent root = loader.load();
            RelatorioFaturamentoController controller = loader.getController();
            controller.selecionaRelatorio(relatorio, mes, ano, CPFCNPJ);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
