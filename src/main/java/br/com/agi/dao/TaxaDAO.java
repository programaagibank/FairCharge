package br.com.agi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import br.com.agi.database.databaseConnection;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;

public class TaxaDAO {

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
        return null;
    }

    public void addTaxaJuros(int jurosId, double percentualJurosDiario, Date dataCriacao) {
        String sql = "INSERT INTO Juros (juros_id, percentual_juros_diario, data_criacao) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jurosId);
            stmt.setDouble(2, percentualJurosDiario);
            stmt.setDate(3, dataCriacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar taxa de juros: " + e.getMessage(), e);
        }
    }

    public TaxaMulta buscarMultaPorAtraso() {
        String sql = "SELECT multa_id, percentual_multa, data_criacao FROM Multa ORDER BY data_criacao DESC LIMIT 1";

        try (Connection conn = databaseConnection.getConnection();
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
        return null;
    }

    public void addTaxaMulta(int multaId, double percentualMulta, Date dataCriacao) {
        String sql = "INSERT INTO Multa (multa_id, percentual_multa, data_criacao) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, multaId);
            stmt.setDouble(2, percentualMulta);
            stmt.setDate(3, dataCriacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar taxa de multa: " + e.getMessage(), e);
        }
    }

}
