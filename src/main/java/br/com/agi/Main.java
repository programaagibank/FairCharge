package br.com.agi;

import br.com.agi.view.MenuView;
import br.com.agi.view.TelaInicial;
import br.com.agi.view.WelcomeView;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = databaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexao bem-sucedida!");
            }
        } catch (Exception e) {
            System.out.println("🚧 Erro ao conectar: " + e.getMessage());
        }
        WelcomeView welcome = new WelcomeView();
        welcome.telaBoasVindas();
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.TelaMenu();
        //MenuView menu = new MenuView();
        //menu.ExibirMenuInicial();
    }
}