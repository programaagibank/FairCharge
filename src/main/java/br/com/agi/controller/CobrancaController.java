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

    public List<Cobranca> listarPorCliente(int clienteId) throws SQLException {
        return cobrancaDAO.BuscarCobrancaCliente(clienteId);
    }

    public void atualizarStatusCobranca(int id, String status) throws SQLException {
        cobrancaDAO.statusPagamento(id, status);
    }

}