package br.com.agi.utils;

import br.com.agi.SceneLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navegador {

    public static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void getLogin() {
        SceneLoader.loadScene(primaryStage, "login/login");
    }

    public static void getHome() {
        SceneLoader.loadScene(primaryStage, "main/home");
    }

    public static void getGerenciadorUsuario() {
        SceneLoader.loadScene(primaryStage, "usuarios/gerenciadorUsuarios");
    }
}
