package br.com.agi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.agi.database.databaseConnection;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaxaDAO {

    public TaxaJuros buscarTaxaJurosDiarios() {
        String sql = "SELECT * FROM Juros ORDER BY juros_id DESC LIMIT 1";

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

    public boolean addTaxaJuros(double percentualJurosDiario) {
        String sql = "INSERT INTO Juros (percentual_juros_diario, data_criacao) VALUES (?, CURRENT_DATE)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percentualJurosDiario);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar taxa de juros: " + e.getMessage(), e);
        }
    }

    public TaxaMulta buscarMultaPorAtraso() {
        String sql = "SELECT multa_id, percentual_multa, data_criacao FROM Multa ORDER BY multa_id DESC LIMIT 1";

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

    public boolean addTaxaMulta(double percentualMulta) {
        String sql = "INSERT INTO Multa (percentual_multa, data_criacao) VALUES (?, CURRENT_DATE)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percentualMulta);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar taxa de multa: " + e.getMessage(), e);
        }
    }

    public ObservableList<TaxaJuros> buscarTodasTaxasDiarias() {
        String sql = "SELECT * FROM Juros ORDER BY data_criacao DESC";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<TaxaJuros> lista = FXCollections.observableArrayList();
            while (rs.next()) {
                lista.add(
                        new TaxaJuros(
                            rs.getInt("juros_id"),
                            rs.getDouble("percentual_juros_diario"),
                            rs.getDate("data_criacao")
                        )
                );
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar taxa de juros diários: " + e.getMessage());
        }
        return null;
    }

    public ObservableList<TaxaMulta> buscarTodasTaxasMulta() {
        String sql = "SELECT * FROM Multa ORDER BY data_criacao DESC";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ObservableList<TaxaMulta> lista = FXCollections.observableArrayList();
            while (rs.next()) {
                lista.add(
                        new TaxaMulta(
                                rs.getInt("multa_id"),
                                rs.getDouble("percentual_multa"),
                                rs.getDate("data_criacao")
                        )
                );
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar taxa de juros diários: " + e.getMessage());
        }
        return null;
    }
}
