package br.com.agi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Cria um componente de texto
        Label helloLabel = new Label("Hello, World!");

        // Cria um layout e adiciona o texto
        StackPane root = new StackPane();
        root.getChildren().add(helloLabel);

        // Cria uma cena contendo o layout e define seu tamanho
        Scene scene = new Scene(root, 300, 200);

        // Configura a janela (stage)
        primaryStage.setTitle("Hello World App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Inicia a aplicação JavaFX
    }
}
