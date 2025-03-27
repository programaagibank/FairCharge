package br.com.agi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.agi.controller.CifradorSenha;
import br.com.agi.controller.UsuarioController;
import br.com.agi.view.GerenciadorUsuarioView;

import br.com.agi.database.databaseConnection;

public class UsuarioDAO {
    GerenciadorUsuarioView menuGerenciador = new GerenciadorUsuarioView();

    public boolean validarLogin(String email, String senha) {
        String sql = "SELECT senha FROM Usuario WHERE email = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaArmazenada = rs.getString("senha");

                CifradorSenha cifrador = new CifradorSenha();
                return cifrador.validarSenhaCrifrada(senha, senhaArmazenada);
            }

            return false;

        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }


    public boolean cadastrarUsuario(String nome, String email, String senha, String permissao) {
        String sql = "INSERT INTO Usuario (nome, email, senha, permissao) VALUES (?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            CifradorSenha cifrador = new CifradorSenha();
            String senhaCriptografada = cifrador.cifrarSenha(senha);

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

    public boolean listarUnicoUsuario(String email) {
        String sql = "SELECT usuario_id, nome, email, permissao FROM Usuario WHERE email = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("usuario_id");
                String nome = rs.getString("nome");
                int permissao = rs.getInt("permissao");

                System.out.println("\n===== USUARIO ENCONTRADO =====");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Email: " + email);
                System.out.println("Permissao: " + (permissao == 1 ? "Administrador" : permissao == 2 ? "Cliente" : "Desconhecido"));
                return true;
            } else {
                System.out.println("Usuario não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return false;
    }

    public boolean updateNome(String novoNome, String email) {
        String sql = "UPDATE Usuario SET nome = ? WHERE email = ?";
        return executeUpdate(sql, novoNome, email);
    }

    public boolean updateSenha(String novaSenha, String email) {
        String sql = "UPDATE Usuario SET senha = ? WHERE email = ?";
        return executeUpdate(sql, novaSenha, email);
    }

    public boolean updatePermissao(int novaPermissao, String email) {
        String sql = "UPDATE Usuario SET permissao = ? WHERE email = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaPermissao);
            stmt.setString(2, email);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar permissão: " + e.getMessage());
            return false;
        }
    }

    public boolean updateEmail(String novoEmail, String emailAntigo) {
        String sql = "UPDATE Usuario SET email = ? WHERE email = ?";
        return executeUpdate(sql, novoEmail, emailAntigo);
    }

    public boolean deletarUsuario(String email) {
        String sql = "DELETE FROM Usuario WHERE email = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
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

            System.out.println("\n===== LISTA DE USUARIOS =====");
            while (rs.next()) {
                int id = rs.getInt("usuario_id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String permissao = rs.getString("permissao");

                System.out.printf("ID: %-5s Nome: %-12s Email: %-23s Permissao: %-15s%n", id, nome, email, permissao);

            }

        } catch (Exception e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }

    }

}