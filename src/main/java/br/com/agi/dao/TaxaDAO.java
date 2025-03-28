package br.com.agi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.com.agi.database.databaseConnection;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;

public class TaxaDAO {


    private databaseConnection DatabaseConnection;

    public TaxaJuros buscarTaxaJurosDiarios() {
        String sql = "SELECT juros_id, percentual_juros_diario, data_criacao FROM Juros ORDER BY data_criacao DESC LIMIT 1";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return new TaxaJuros(
                        rs.getInt("juros_id"),
                        rs.getDouble("percentual_juros_diario"),
                        rs.getDate("data_criacao")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar taxa de juros diários: " + e.getMessage());
        }
        return null; // Retorna null caso não encontre nada
    }

    public TaxaMulta buscarMultaPorAtraso() {
        String sql = "SELECT multa_id, percentual_multa, data_criacao FROM Multa ORDER BY data_criacao DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return new TaxaMulta(
                        rs.getInt("multa_id"),
                        rs.getDouble("percentual_multa"),
                        rs.getDate("data_criacao")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar multa por atraso: " + e.getMessage());
        }
        return null; // Retorna null caso não encontre nada
    }
}
