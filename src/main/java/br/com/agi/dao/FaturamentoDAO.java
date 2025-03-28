package br.com.agi.dao;

import br.com.agi.database.databaseConnection;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.Faturamento;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.FaturamentoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FaturamentoDAO {

    public Faturamento obterRelatorioFaturamentoBanco(int mes, int ano) {
        String sqlTotais = """
            SELECT 
                COUNT(*) AS total_cobrancas,
                SUM(CASE 
                    WHEN c.status = 'Pago' THEN p.valor_pago 
                    ELSE 0
                END) AS total_recebido,
                SUM(CASE 
                    WHEN c.status = 'Aberto' THEN f.valor_fatura 
                    WHEN c.status = 'Atrasado' THEN 
                        (f.valor_fatura + IFNULL(m.percentual_multa, 0) / 100 * f.valor_fatura) * 
                        POW(1 + IFNULL(j.percentual_juros_diario, 0) / 100, DATEDIFF(CURDATE(), f.data_vencimento)) 
                    ELSE 0
                END) AS total_pendente,
                SUM(CASE 
                    WHEN c.status = 'Atrasado' THEN f.valor_fatura 
                    ELSE 0
                END) AS total_inadimplente
            FROM
                Cobranca c
            JOIN
                Fatura f ON c.fatura_id = f.fatura_id
            LEFT JOIN
                Pagamento p ON c.pagamento_id = p.pagamento_id
            LEFT JOIN
                Multa m ON c.multa_atraso_id = m.multa_id
            LEFT JOIN
                Juros j ON c.juros_diario_id = j.juros_id
            WHERE
                YEAR(f.data_criacao) = ?
                AND MONTH(f.data_criacao) = ?;
        """;


        String sqlCategorias = """
    SELECT 
        f.descricao AS categoria,
        SUM(CASE 
            WHEN c.status = 'Pago' THEN p.valor_pago 
            ELSE 0
        END) AS total_recebido,
        SUM(CASE 
            WHEN c.status = 'Aberto' THEN f.valor_fatura 
            WHEN c.status = 'Atrasado' THEN 
                (f.valor_fatura + IFNULL(m.percentual_multa, 0) / 100 * f.valor_fatura) * 
                POW(1 + IFNULL(j.percentual_juros_diario, 0) / 100, DATEDIFF(CURDATE(), f.data_vencimento)) 
            ELSE 0
        END) AS total_pendente,
        SUM(CASE 
            WHEN c.status = 'Atrasado' THEN f.valor_fatura 
            ELSE 0
        END) AS total_inadimplente
    FROM
        Cobranca c
    JOIN
        Fatura f ON c.fatura_id = f.fatura_id
    LEFT JOIN
        Pagamento p ON c.pagamento_id = p.pagamento_id
    LEFT JOIN
        Multa m ON c.multa_atraso_id = m.multa_id
    LEFT JOIN
        Juros j ON c.juros_diario_id = j.juros_id
    WHERE
        YEAR(f.data_criacao) = ?
        AND MONTH(f.data_criacao) = ?
    GROUP BY
        f.descricao;
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

    public FaturamentoCliente obterRelatorioFaturamentoCliente(String cpf, int mes, int ano) {
        String sqlTotais = """
    SELECT 
        cl.nome AS cliente,
        COUNT(c.cobranca_id) AS total_cobrancas,
        COALESCE(SUM(CASE 
            WHEN c.status = 'Pago' THEN p.valor_pago 
            ELSE 0
        END), 0) AS total_recebido,
        COALESCE(SUM(CASE 
            WHEN c.status = 'Aberto' THEN f.valor_fatura 
            WHEN c.status = 'Atrasado' THEN 
                (f.valor_fatura + IFNULL(m.percentual_multa, 0) / 100 * f.valor_fatura) * 
                POW(1 + IFNULL(j.percentual_juros_diario, 0) / 100, DATEDIFF(CURDATE(), f.data_vencimento)) 
            ELSE 0
        END), 0) AS total_pendente,
        COALESCE(SUM(CASE 
            WHEN c.status = 'Atrasado' THEN f.valor_fatura 
            ELSE 0
        END), 0) AS total_inadimplente
    FROM 
        Cobranca c
    JOIN 
        Fatura f ON c.fatura_id = f.fatura_id
    JOIN 
        Cliente cl ON f.cliente_id = cl.cliente_id
    LEFT JOIN 
        Pagamento p ON c.pagamento_id = p.pagamento_id
    LEFT JOIN 
        Multa m ON c.multa_atraso_id = m.multa_id
    LEFT JOIN 
        Juros j ON c.juros_diario_id = j.juros_id
    WHERE 
        cl.cpf_cnpj = ?  
        AND YEAR(f.data_criacao) = ? 
        AND MONTH(f.data_criacao) = ?
    GROUP BY 
        cl.nome;
""";


        String sqlDetalhes = """
            SELECT 
                c.cobranca_id AS id,
                f.valor_fatura AS valor,
                DATE_FORMAT(f.data_vencimento, '%d/%m/%Y') AS vencimento,
                c.status
            FROM 
                Cobranca c
            JOIN 
                Fatura f ON c.fatura_id = f.fatura_id
            JOIN 
                Cliente cl ON f.cliente_id = cl.cliente_id
            WHERE 
                cl.cpf_cnpj = ?  
                AND YEAR(f.data_criacao) = ? 
                AND MONTH(f.data_criacao) = ?
            ORDER BY 
                f.data_vencimento ASC;
            """;

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmtTotais = conn.prepareStatement(sqlTotais);
             PreparedStatement stmtDetalhes = conn.prepareStatement(sqlDetalhes)) {

            stmtTotais.setString(1, cpf);
            stmtTotais.setInt(2, ano);
            stmtTotais.setInt(3, mes);

            stmtDetalhes.setString(1, cpf);
            stmtDetalhes.setInt(2, ano);
            stmtDetalhes.setInt(3, mes);

            ResultSet rsTotais = stmtTotais.executeQuery();
            FaturamentoCliente faturamentoCliente = new FaturamentoCliente(mes, ano);

            if (rsTotais.next()) {
                faturamentoCliente = new FaturamentoCliente(
                        rsTotais.getString("cliente"),
                        rsTotais.getInt("total_cobrancas"),
                        rsTotais.getDouble("total_recebido"),
                        rsTotais.getDouble("total_pendente"),
                        rsTotais.getDouble("total_inadimplente")
                );
            }

            ResultSet rsDetalhes = stmtDetalhes.executeQuery();
            while (rsDetalhes.next()) {
                faturamentoCliente.adicionarCobrancas(new CobrancasFaturamento(
                        rsDetalhes.getInt("id"),
                        rsDetalhes.getDouble("valor"),
                        rsDetalhes.getString("vencimento"),
                        rsDetalhes.getString("status")
                ));
            }
            return faturamentoCliente;
        } catch (Exception e) {
            System.out.println("Erro ao obter dados do relatório do cliente: " + e.getMessage());
            return null;
        }
    }



}
