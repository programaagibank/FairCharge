package br.com.agi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.agi.database.databaseConnection;

public class UsuarioDAO {

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
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

    public boolean updateUsuario(String nome, String senha,String email, String permissao){
        String sql = " UPDATE Usuario SET (nome, senha, email, permissao) VALUES (?, ?, ?, ?) ";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, permissao);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar uma ou mais informações : " + e.getMessage());
            return false;
        }
    }

    public boolean deletarUsuario(String email) {
        String sql = "DELETE FROM Usuario WHERE email = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }

    public void listarUsuarios() {
        String sql = "SELECT * FROM Usuario";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== LISTA DE USUÁRIOS =====");
            while (rs.next()) {
                int id = rs.getInt("usuario_id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                System.out.println("ID: " + id + " | Nome: " + nome + " | Email: " + email);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }
}
