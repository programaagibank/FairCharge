package br.com.agi;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = databaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}