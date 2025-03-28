package br.com.agi;
<<<<<<< HEAD
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
=======
import br.com.agi.database.databaseConnection;
import br.com.agi.view.inicios.TelaInicial;
import br.com.agi.view.inicios.WelcomeView;


import java.sql.Connection;
>>>>>>> 502a96afa09800089534da836499c7ef0999fabb

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
<<<<<<< HEAD
=======

/*
package br.com.agi;

import br.com.agi.database.databaseConnection;
import br.com.agi.utils.Navegador;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {



    public static void main(String[] args) {
        try (Connection connection = databaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão com o banco de dados bem-sucedida!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Navegador.setPrimaryStage(primaryStage); // Define o Stage principal
        Navegador.getLogin(); // Exibe a tela inicial
    }
}
*/
>>>>>>> 502a96afa09800089534da836499c7ef0999fabb
