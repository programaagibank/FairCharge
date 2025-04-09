package br.com.agi.dao;
import br.com.agi.model.Cobranca;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CobrancaDAO {
    private Connection connection;

    public CobrancaDAO(Connection connection) {
        this.connection = connection;
    }

    public void statusPagamento(int id, String status) throws SQLException {
        String sql = "UPDATE Cobranca SET status = ? WHERE cobranca_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Cobranca> listarCobrancasVencidas() {
        List<Cobranca> cobrancas = new ArrayList<>();
        String sql = """
                SELECT 
                    c.cobranca_id, 
                    c.fatura_id, 
                    cli.nome AS nome_cliente, 
                    f.data_vencimento, 
                    f.valor_fatura,
                    (f.valor_fatura * (1 + IFNULL(m.percentual_multa, 0) / 100.0) * 
                    POWER(1 + IFNULL(j.percentual_juros_diario, 0) / 100.0, DATEDIFF(CURDATE(), f.data_vencimento))) 
                    AS valor_atualizado,
                    c.status
                FROM Cobranca c
                JOIN Fatura f ON c.fatura_id = f.fatura_id
                JOIN Cliente cli ON f.cliente_id = cli.cliente_id
                LEFT JOIN Multa m ON c.multa_atraso_id = m.multa_id
                LEFT JOIN Juros j ON c.juros_diario_id = j.juros_id
                WHERE f.data_vencimento < CURDATE()
                AND c.pagamento_id IS NULL
                AND c.status <> 'Pago';
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cobranca cobranca = new Cobranca(
                        rs.getInt("cobranca_id"),
                        rs.getInt("fatura_id"),
                        rs.getString("nome_cliente"),
                        rs.getDouble("valor_fatura"),
                        rs.getDouble("valor_atualizado"),
                        rs.getDate("data_vencimento").toLocalDate(),
                        rs.getString("status")
                );
                cobrancas.add(cobranca);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return cobrancas;
    }

    public boolean atualizarCobrancasVencidas() {
        String sql = "UPDATE Cobranca c " +
                "JOIN Fatura f ON c.fatura_id = f.fatura_id " +
                "SET " +
                "    c.multa_atraso_id = CASE " +
                "        WHEN c.multa_atraso_id IS NULL THEN (SELECT MAX(multa_id) FROM Multa) " +
                "        ELSE c.multa_atraso_id " +
                "    END, " +
                "    c.juros_diario_id = CASE " +
                "        WHEN c.juros_diario_id IS NULL THEN (SELECT MAX(juros_id) FROM Juros) " +
                "        ELSE c.juros_diario_id " +
                "    END, " +
                "    c.status = 'Atrasado' " +
                "WHERE f.data_vencimento < CURDATE() " +
                "AND c.pagamento_id IS NULL " +
                "AND (c.multa_atraso_id IS NULL OR c.juros_diario_id IS NULL);";


        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int rs = stmt.executeUpdate();
            return rs > 0;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cobranças: " + e.getMessage());
        }
        return false;
    }

    public List<Cobranca> gerarRelatorioCobrancasVencidas() {
        List<Cobranca> cobrancas = new ArrayList<>();
            String sql = "SELECT " +
                "    c.cobranca_id, " +
                "    c.fatura_id, " +
                "    cli.nome AS nome_cliente, " +
                "    f.valor_fatura, " +
                "    f.data_vencimento, " +
                "    c.status, " +
                "    IFNULL(m.percentual_multa, 0) AS percentual_multa, " +
                "    IFNULL(j.percentual_juros_diario, 0) AS percentual_juros_diario, " +
                "    CASE " +
                "        WHEN IFNULL(m.percentual_multa, 0) = 0 AND IFNULL(j.percentual_juros_diario, 0) = 0 THEN f.valor_fatura " +
                "        ELSE (f.valor_fatura + (f.valor_fatura * IFNULL(m.percentual_multa, 0) / 100)) * " +
                "             POW(1 + (IFNULL(j.percentual_juros_diario, 0) / 100), DATEDIFF(CURDATE(), f.data_vencimento)) " +
                "    END AS valor_atualizado " +
                "FROM Cobranca c " +
                "JOIN Fatura f ON c.fatura_id = f.fatura_id " +
                "JOIN Cliente cli ON f.cliente_id = cli.cliente_id " +
                "LEFT JOIN Multa m ON c.multa_atraso_id = m.multa_id " +
                "LEFT JOIN Juros j ON c.juros_diario_id = j.juros_id;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cobrancaId = rs.getInt("cobranca_id");
                int faturaId = rs.getInt("fatura_id");
                String nomeCliente = rs.getString("nome_cliente");
                double valorFatura = rs.getDouble("valor_fatura");
                LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
                String status = rs.getString("status");
                double valorAtualizado = rs.getDouble("valor_atualizado");


                Cobranca cobranca = new Cobranca(cobrancaId, faturaId, nomeCliente, status, valorAtualizado, dataVencimento);
                cobrancas.add(cobranca);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar relatório de cobranças vencidas: " + e.getMessage());
            return null;
        }

        return cobrancas;
    }

    public int buscaCobrancasVencidas() {
        String sql = "SELECT COUNT(*) AS TOTAL FROM Cobranca WHERE Status = 'Atrasado'";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                        return rs.getInt("TOTAL");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return 0;
    }

    public boolean gerarCobranca(Integer inteiro) {
        String sql = "CALL GerarFaturasAleatorias(?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inteiro);
            ResultSet rs = stmt.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return false;
    }

    public int gerarCobrancasPagasPorMes(int mes, int ano) {
        String sql = "SELECT Count(*) AS TOTAL  " +
                "FROM Cobranca c " +
                "JOIN Pagamento p ON c.pagamento_id = p.pagamento_id " +
                "WHERE c.Status = 'Pago' " +
                "AND YEAR(p.data_pagamento) = ? " +
                "AND MONTH(p.data_pagamento) = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ano);
            stmt.setInt(2, mes);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("TOTAL");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return 0;
    }

    public int gerarCobrancasPendentesPorMes() {
        String sql = "SELECT COUNT(distinct c.cobranca_id) AS TOTAL " +
                "FROM Cobranca c " +
                "LEFT JOIN Pagamento p ON c.pagamento_id = p.pagamento_id " +
                "INNER JOIN Fatura f ON c.Fatura_id = c.Fatura_id " +
                "WHERE c.Status IN ('Atrasado', 'Aberto')";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("TOTAL");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return 0;
    }

    public double gerarCobrancasValorTotalMes() {
        String sql = "SELECT SUM(f.valor_fatura * (1 + IFNULL(m.percentual_multa, 0) / 100.0) * " +
                "                    POWER(1 + IFNULL(j.percentual_juros_diario, 0) / 100.0, DATEDIFF(CURDATE(), f.data_vencimento))) AS somaTotal " +
                "FROM Cobranca c " +
                "JOIN Fatura f ON c.fatura_id = f.fatura_id " +
                "LEFT JOIN Multa m ON c.multa_atraso_id = m.multa_id " +
                "LEFT JOIN Juros j ON c.juros_diario_id = j.juros_id " +
                "WHERE c.Status IN ('Atrasado', 'Aberto')";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("somaTotal");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar cobranças: " + e.getMessage());
        }
        return 0;
    }
}
