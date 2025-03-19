package br.com.agi;

import br.com.agi.view.MenuView;
import br.com.agi.view.TelaInicial;
import br.com.agi.view.WelcomeView;

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
            System.out.println("🚧 Erro ao conectar: " + e.getMessage());
            return;
        }

        welcome.telaBoasVindas();
        telaInicial.TelaMenu();

    }
}