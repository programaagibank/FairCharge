package br.com.agi.dao;

import br.com.agi.database.databaseConnection;
import br.com.agi.model.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    public void  listarPagamentos() {
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

    public int quantidadeFaturasPagas(int mes, int ano) {
        String sql = """
            SELECT 
                COUNT(*) AS total_faturas_pagas
            FROM 
                Cobranca c
            JOIN 
                Pagamento p ON c.pagamento_id = p.pagamento_id
            WHERE 
                p.status = 'Confirmado'
                AND YEAR(p.data_pagamento) = ?
                AND MONTH(p.data_pagamento) = ?;
            """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setInt(1, ano);
             stmt.setInt(2, mes);

             ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_faturas_pagas");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }
        return 0;
    }

    public int quantidadeFaturasPendentes(int mes, int ano) {
        String sql = """
            SELECT 
                COUNT(*) AS total_faturas_pendentes
            FROM 
                Cobranca c
            JOIN 
                Fatura f ON c.fatura_id = f.fatura_id
            WHERE 
                c.status = 'Aberto'
                AND c.pagamento_id IS NULL
                AND YEAR(f.data_criacao) = ?
                AND MONTH(f.data_criacao) = ?;
            """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ano);
            stmt.setInt(2, mes);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_faturas_pendentes");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }
        return 0;
    }

    public List<Pagamento> listaCobrancasPagas() {
        String sql = """
        SELECT
            c.cobranca_id,
            c.fatura_id,
            cl.nome AS cliente,
            CASE
                WHEN m.percentual_multa IS NOT NULL AND j.percentual_juros_diario IS NOT NULL THEN
                    ROUND(
                        (f.valor_fatura + (f.valor_fatura * m.percentual_multa / 100)) *
                        POW(1 + (j.percentual_juros_diario / 100), DATEDIFF(p.data_pagamento, f.data_vencimento)),
                    2)
                ELSE f.valor_fatura
            END AS valor_fatura,
            p.valor_pago,
            p.data_pagamento,
            c.status
        FROM
            Cobranca c
        JOIN
            Fatura f ON c.fatura_id = f.fatura_id
        JOIN
            Pagamento p ON c.pagamento_id = p.pagamento_id
        JOIN
            Cliente cl ON f.cliente_id = cl.cliente_id
        LEFT JOIN
            Multa m ON c.multa_atraso_id = m.multa_id
        LEFT JOIN
            Juros j ON c.juros_diario_id = j.juros_id
        WHERE
            c.pagamento_id IS NOT NULL
        ORDER BY
            p.data_pagamento DESC;
        """;

        List<Pagamento> pagamentos = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pagamento pagamento = new Pagamento(
                        rs.getInt("cobranca_id"),
                        rs.getInt("fatura_id"),
                        rs.getString("cliente"),
                        rs.getDouble("valor_pago"),
                        rs.getDate("data_pagamento").toLocalDate(),
                        rs.getString("status"),
                        rs.getDouble("valor_fatura")
                );
                pagamentos.add(pagamento);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos: " + e.getMessage());
        }

        return pagamentos;
    }

    public List<Pagamento> listarPagamentosPorPeriodo(int mes, int ano) {
        String sql = """
        SELECT
            c.cobranca_id,
            c.fatura_id,
            cl.nome AS cliente,
            CASE
                WHEN m.percentual_multa IS NOT NULL AND j.percentual_juros_diario IS NOT NULL THEN
                    ROUND(
                        (f.valor_fatura + (f.valor_fatura * m.percentual_multa / 100)) *
                        POW(1 + (j.percentual_juros_diario / 100), DATEDIFF(p.data_pagamento, f.data_vencimento)),
                    2)
                ELSE f.valor_fatura
            END AS valor_fatura,
            p.valor_pago,
            p.data_pagamento,
            c.status
        FROM
            Cobranca c
        JOIN
            Fatura f ON c.fatura_id = f.fatura_id
        JOIN
            Pagamento p ON c.pagamento_id = p.pagamento_id
        JOIN
            Cliente cl ON f.cliente_id = cl.cliente_id
        LEFT JOIN
            Multa m ON c.multa_atraso_id = m.multa_id
        LEFT JOIN
            Juros j ON c.juros_diario_id = j.juros_id
        WHERE
            c.pagamento_id IS NOT NULL
            AND MONTH(p.data_pagamento) = ?
            AND YEAR(p.data_pagamento) = ?
        ORDER BY
            p.data_pagamento DESC;
        """;

        List<Pagamento> pagamentos = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mes);
            stmt.setInt(2, ano);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pagamento pagamento = new Pagamento(
                        rs.getInt("cobranca_id"),
                        rs.getInt("fatura_id"),
                        rs.getString("cliente"),
                        rs.getDouble("valor_pago"),
                        rs.getDate("data_pagamento").toLocalDate(),
                        rs.getString("status"),
                        rs.getDouble("valor_fatura")
                );
                pagamentos.add(pagamento);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar pagamentos por período: " + e.getMessage());
        }

        return pagamentos;
    }

    public boolean pagarCobranca(int cobrancaId, double valorPago) {
        String sql = "{CALL pagar_cobranca(?, ?)}";

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, cobrancaId);
            stmt.setDouble(2, valorPago);

            stmt.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao pagar cobrança: " + e.getMessage());
        }
        return false;
    }
}
