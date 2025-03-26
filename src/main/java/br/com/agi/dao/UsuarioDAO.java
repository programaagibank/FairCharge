package br.com.agi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public boolean cadastrarUsuario(String nome, String email, String senha, String permissao) {
        String sql = "INSERT INTO Usuario (nome, email, senha, permissao) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setString(4, permissao);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    // Updade Usuário
    public boolean UpdateNome(){
        String sql = "UPDATE Usuario SET nome = ? WHERE email = ?";
        String nome="",email="";
        return executeUpdate(sql, nome, email);
    }

    public boolean updateSenha() {
        String sql = "UPDATE Usuario SET senha = ? WHERE email = ?";
        String senha = "", email = "";
        return executeUpdate(sql, senha, email);
    }

    public boolean updatePermissao() {
        String sql = "UPDATE Usuario SET permissao = ? WHERE email = ?";
        String permissao = "", email = "";
        return executeUpdate(sql, permissao, email);
    }

    public boolean updateEmail() {
        String sql = "UPDATE Usuario SET email = ? WHERE permissao = ?";
        String permissao = "",email = "";
        return executeUpdate(sql,email, permissao);
    }

    private boolean executeUpdate(String sql, String valor, String email) {
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, valor);
            stmt.setString(2, email);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar campo: " + e.getMessage());
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
        String sql = "SELECT usuario_id, nome, email, " +
                "CASE " +
                "    WHEN permissao = 1 THEN 'Administrador' " +
                "    WHEN permissao = 2 THEN 'Cliente' " +
                "    ELSE 'Desconhecido' " +
                "END AS permissao " +
                "FROM Usuario";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== LISTA DE USUÁRIOS =====");
            while (rs.next()) {
                int id = rs.getInt("usuario_id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String permissao = rs.getString("permissao");

                System.out.println("ID: " + id + " | Nome: " + nome + " | Email: " + email + " | Permissão: " + permissao);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
    }
}