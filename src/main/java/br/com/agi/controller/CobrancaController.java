package br.com.agi.controller;
import br.com.agi.dao.CobrancaDAO;
import br.com.agi.database.databaseConnection;
import br.com.agi.model.Cobranca;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CobrancaController {
    private final CobrancaDAO cobrancaDAO;

    public CobrancaController() {
        Connection connection = databaseConnection.getConnection();
        this.cobrancaDAO = new CobrancaDAO(connection);
    }

    public boolean atualizarPendencias() {
        return cobrancaDAO.atualizarCobrancasVencidas();
    }

    public List<Cobranca> listarCobrancasVencidas() {
        return cobrancaDAO.listarCobrancasVencidas();
    }

    public List<Cobranca> gerarRelatorioCobrancas() {
        return cobrancaDAO.gerarRelatorioCobrancasVencidas();
    }

    public int quantidadeCobrancasVencidas() {
        return cobrancaDAO.buscaCobrancasVencidas();
    }

    public boolean gerarCobranca(Integer integer) {
        return cobrancaDAO.gerarCobranca(integer);
    }

    public int cobrancasPagasMes(int mes, int ano) {
        return cobrancaDAO.gerarCobrancasPagasPorMes(mes, ano);
    }

    public int cobrancasPendentesMes(int mes, int ano) {
        return cobrancaDAO.gerarCobrancasPendentesPorMes(mes, ano);
    }

    public double cobrancasValorTotalMes(int mes, int ano) {
        return cobrancaDAO.gerarCobrancasValorTotalMes(mes, ano);
    }
}