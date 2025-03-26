package br.com.agi.dao;

import br.com.agi.database.databaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PagamentoDAO {
    public void listarPagamentos() {
        String sql = "SELECT pagamento_id, forma_pagamento_id, valor_pago, data_pagamento, cod_transacao, detalhes, status FROM Pagamento";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== LISTA DE PAGAMENTOS =====");
            while (rs.next()) {
                int pagamentoId = rs.getInt("pagamento_id");
                int formaPagamentoId = rs.getInt("forma_pagamento_id");
                double valorPago = rs.getDouble("valor_pago");
                String dataPagamento = rs.getString("data_pagamento");
                String codTransacao = rs.getString("cod_transacao");
                String detalhes = rs.getString("detalhes");
                String status = rs.getString("status");

                System.out.println("ID: " + pagamentoId + " | Forma Pagamento: " + formaPagamentoId + " | Valor: R$" + valorPago +
                        " | Data: " + dataPagamento + " | Código Transação: " + codTransacao +
                        " | Detalhes: " + detalhes + " | Status: " + status);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }
    }
}
