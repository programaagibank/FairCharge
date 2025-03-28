package br.com.agi.utils;

import br.com.agi.model.Usuario;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


import java.util.Optional;

public class DialogHelper {

    public static Optional<Usuario> solicitarInformacoesUsuario() {
        Dialog<Usuario> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Usuário");
        dialog.setHeaderText("Preencha as informações do novo usuário:");

        ButtonType adicionarButtonType = new ButtonType("Adicionar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(adicionarButtonType, ButtonType.CANCEL);

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("Senha");

        ComboBox<String> permissaoComboBox = new ComboBox<>();
        permissaoComboBox.getItems().addAll("Administrador", "Cliente");
        permissaoComboBox.setPromptText("Permissão");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Senha:"), 0, 2);
        grid.add(senhaField, 1, 2);
        grid.add(new Label("Permissão:"), 0, 3);
        grid.add(permissaoComboBox, 1, 3);

        dialog.getDialogPane().setContent(grid);

        Node adicionarButton = dialog.getDialogPane().lookupButton(adicionarButtonType);
        adicionarButton.setDisable(true);

        nomeField.textProperty().addListener((observable, oldValue, newValue) -> {
            adicionarButton.setDisable(newValue.trim().isEmpty() || emailField.getText().trim().isEmpty() ||
                    senhaField.getText().trim().isEmpty() || permissaoComboBox.getValue() == null);
        });

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            adicionarButton.setDisable(newValue.trim().isEmpty() || nomeField.getText().trim().isEmpty() ||
                    senhaField.getText().trim().isEmpty() || permissaoComboBox.getValue() == null);
        });

        senhaField.textProperty().addListener((observable, oldValue, newValue) -> {
            adicionarButton.setDisable(newValue.trim().isEmpty() || nomeField.getText().trim().isEmpty() ||
                    emailField.getText().trim().isEmpty() || permissaoComboBox.getValue() == null);
        });

        permissaoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            adicionarButton.setDisable(newValue == null || nomeField.getText().trim().isEmpty() ||
                    emailField.getText().trim().isEmpty() || senhaField.getText().trim().isEmpty());
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == adicionarButtonType) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = senhaField.getText();
                int permissao = permissaoComboBox.getValue().equals("Administrador") ? 1 : 2;

                return new Usuario(email, senha, nome, permissao);
            }
            return null;
        });

        return dialog.showAndWait();
    }

    public static Optional<Usuario> solicitarEdicaoUsuario(Usuario usuarioAtual) {
        Dialog<Usuario> dialog = new Dialog<>();
        dialog.setTitle("Editar Usuário");
        dialog.setHeaderText("Altere as informações do usuário:");

        ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

        TextField nomeField = new TextField(usuarioAtual.getNome());
        nomeField.setPromptText("Nome");

        TextField emailField = new TextField(usuarioAtual.getEmail());
        emailField.setPromptText("Email");

        PasswordField senhaField = new PasswordField();
        senhaField.setPromptText("Nova Senha (deixe vazio para manter)");

        ComboBox<String> permissaoComboBox = new ComboBox<>();
        permissaoComboBox.getItems().addAll("Administrador", "Cliente");
        permissaoComboBox.setValue(usuarioAtual.getPermissao() == 1 ? "Administrador" : "Cliente");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Nova Senha:"), 0, 2);
        grid.add(senhaField, 1, 2);
        grid.add(new Label("Permissão:"), 0, 3);
        grid.add(permissaoComboBox, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == salvarButtonType) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = senhaField.getText().isEmpty() ? usuarioAtual.getSenha() : senhaField.getText(); // Mantém a senha atual se não for preenchida
                int permissao = permissaoComboBox.getValue().equals("Administrador") ? 1 : 2;

                return new Usuario(email, senha, nome, permissao);
            }
            return null;
        });

        return dialog.showAndWait();
    }

}
