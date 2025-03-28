package br.com.agi.dao;
import br.com.agi.model.Cobranca;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CobrancaDAO {
    private Connection connection;

    public CobrancaDAO(Connection connection) {
        this.connection = connection;
    }


    public List<Cobranca> BuscarCobrancaCliente(int clienteId) throws SQLException {
        List<Cobranca> cobrancas = new ArrayList<>();
        String sql = "SELECT * FROM Cobranca WHERE cliente_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cobranca cobranca = new Cobranca(
                        rs.getInt("cobranca_id"),
                        rs.getInt("fatura_id"),
                        (Integer) rs.getObject("cobranca_referenciada_id"),
                        rs.getInt("cliente_id"),
                        (Integer) rs.getObject("forma_pagamento_id"),
                        rs.getDouble("valor_total"),
                        rs.getDate("data_criacao"),
                        rs.getDate("data_vencimento"),
                        rs.getString("status")
                );
                cobrancas.add(cobranca);
            }
        }
        return cobrancas;
    }


    public void statusPagamento(int id, String status) throws SQLException {
        String sql = "UPDATE Cobranca SET status = ? WHERE cobranca_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

}
