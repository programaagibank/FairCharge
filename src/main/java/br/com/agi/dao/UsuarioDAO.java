package br.com.agi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.agi.database.databaseConnection;

public class UsuarioDAO {

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT * FROM Usuario u INNER JOIN Cliente c ON u.cliente_id = c.cliente_id WHERE u.email = ? AND u.senha = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email); //
            stmt.setString(2, senha); //
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        String sql = "INSERT INTO Usuario (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
}
