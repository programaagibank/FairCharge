package br.com.agi.dao;
import br.com.agi.database.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FaturaDAO {

    public void atualizarStatusFatura(int faturaId) {
        String sqlPagamento = "SELECT status FROM Pagamento WHERE pagamento_id = ?";
        String sqlAtualizaFatura = "UPDATE Fatura SET status_fatura = ? WHERE fatura_id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmtPagamento = conn.prepareStatement(sqlPagamento);
             PreparedStatement stmtAtualizaFatura = conn.prepareStatement(sqlAtualizaFatura)) {

            stmtPagamento.setInt(1, faturaId);
            ResultSet rs = stmtPagamento.executeQuery();

            if (rs.next()) {
                String statusPagamento = rs.getString("status");
                String novoStatusFatura = switch (statusPagamento) {
                    case "Confirmado" -> "Pago";
                    case "Pendente" -> "Pendente";
                    case "Cancelado" -> "Cancelado";
                    default -> "Indefinido";
                };

                stmtAtualizaFatura.setString(1, novoStatusFatura);
                stmtAtualizaFatura.setInt(2, faturaId);
                stmtAtualizaFatura.executeUpdate();
                System.out.println("Status da fatura atualizado para: " + novoStatusFatura);
            } else {
                System.out.println("Pagamento não encontrado.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar status da fatura: " + e.getMessage());
        }
    }
}
