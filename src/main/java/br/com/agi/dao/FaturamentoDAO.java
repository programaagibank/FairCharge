package br.com.agi.dao;

import br.com.agi.database.databaseConnection;
import br.com.agi.model.Faturamento;
import br.com.agi.model.CategoriasFaturamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FaturamentoDAO {

    public Faturamento obterRelatorioFaturamento(int mes, int ano) {
        String sqlTotais = """
                SELECT 
                    COUNT(*) AS total_cobrancas,
                    SUM(CASE WHEN status = 'Pago' THEN valor_total ELSE 0 END) AS total_recebido,
                    SUM(CASE WHEN status = 'Aberto' THEN valor_total ELSE 0 END) AS total_pendente,
                    SUM(CASE WHEN status = 'Atrasado' THEN valor_total ELSE 0 END) AS total_inadimplente
                FROM Cobranca
                WHERE YEAR(data_criacao) = ? AND MONTH(data_criacao) = ?
                """;

        String sqlCategorias = """
                SELECT 
                    f.descricao AS categoria,
                    SUM(CASE WHEN c.status = 'Pago' THEN c.valor_total ELSE 0 END) AS total_recebido,
                    SUM(CASE WHEN c.status = 'Aberto' THEN c.valor_total ELSE 0 END) AS total_pendente,
                    SUM(CASE WHEN c.status = 'Atrasado' THEN c.valor_total ELSE 0 END) AS total_inadimplente
                FROM Cobranca c
                JOIN Fatura f ON c.fatura_id = f.fatura_id
                WHERE YEAR(c.data_criacao) = ? AND MONTH(c.data_criacao) = ?
                GROUP BY f.descricao
                """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmtTotais = conn.prepareStatement(sqlTotais);
             PreparedStatement stmtCategorias = conn.prepareStatement(sqlCategorias)) {

            stmtTotais.setInt(1, ano);
            stmtTotais.setInt(2, mes);

            stmtCategorias.setInt(1, ano);
            stmtCategorias.setInt(2, mes);

            ResultSet rsTotais = stmtTotais.executeQuery();
            Faturamento faturamento = new Faturamento(mes, ano);

            if (rsTotais.next()) {
                faturamento.setTotalCobrancas(rsTotais.getInt("total_cobrancas"));
                faturamento.setTotalRecebido(rsTotais.getDouble("total_recebido"));
                faturamento.setTotalPendente(rsTotais.getDouble("total_pendente"));
                faturamento.setTotalInadimplente(rsTotais.getDouble("total_inadimplente"));
            }

            ResultSet rsCategorias = stmtCategorias.executeQuery();
            while (rsCategorias.next()) {
                faturamento.adicionarDetalhamento(new CategoriasFaturamento(
                        rsCategorias.getString("categoria"),
                        rsCategorias.getDouble("total_recebido"),
                        rsCategorias.getDouble("total_pendente"),
                        rsCategorias.getDouble("total_inadimplente")
                ));
            }
            return faturamento;
        } catch (Exception e) {
            System.out.println("Erro ao obter dados do relatório: " + e.getMessage());
            return null;
        }
    }
}
