package br.com.agi.dao;

import br.com.agi.database.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxaDAO {

    // Método para buscar a taxa de Multa ou Juros no banco de dados
    public double buscarTaxa(String tipo) {
        String sql = "SELECT valor FROM Taxa WHERE tipo = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("valor");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar taxa '" + tipo + "': " + e.getMessage());
        }

        // Valores padrão caso a taxa não esteja no banco
        if (tipo.equalsIgnoreCase("Multa")) return 5.0;  // Multa padrão de 5%
        if (tipo.equalsIgnoreCase("Juros")) return 3.0;  // Juros padrão de 3% ao dia

        return 0.0;
    }

    // Método para atualizar uma taxa existente no banco de dados
    public boolean atualizarTaxa(String tipo, double novoValor) {
        String sql = "UPDATE Taxa SET valor = ? WHERE tipo = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoValor);
            stmt.setString(2, tipo);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Taxa '" + tipo + "' atualizada com sucesso para " + novoValor + "%.");
                return true;
            } else {
                System.out.println("Nenhuma taxa foi atualizada. Verifique se o tipo informado é válido.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar taxa '" + tipo + "': " + e.getMessage());
            return false;
        }
    }

}
