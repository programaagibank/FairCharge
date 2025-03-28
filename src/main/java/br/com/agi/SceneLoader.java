package br.com.agi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    public static void loadScene(Stage stage, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("/br/com/agi/view/" + fxmlFile + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("FairCharge - Sistema de Cobranças");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
