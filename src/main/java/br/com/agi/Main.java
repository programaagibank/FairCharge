package br.com.agi;
import br.com.agi.database.databaseConnection;
import br.com.agi.view.inicios.TelaInicial;
import br.com.agi.view.inicios.WelcomeView;


import java.sql.Connection;

public class Main {
    private static WelcomeView welcome = new WelcomeView();
    private static TelaInicial telaInicial = new TelaInicial();

    public static void main(String[] args) {
        try (Connection connection = databaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexao bem-sucedida!");
            }
        } catch (Exception e) {
            System.out.println("🚧telaBoasVindas(); Erro ao conectar: " + e.getMessage());
            return;
        }

        welcome.telaBoasVindas();
        telaInicial.TelaMenu();
    }
}

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