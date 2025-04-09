package br.com.agi.utils;
import br.com.agi.model.Usuario;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;

public class DialogHelper {
    static Parametros param = new Parametros();

    public static Optional<Usuario> solicitarInformacoesUsuario() {
        Dialog<Usuario> dialog = new Dialog<>();
        dialog.setHeaderText("Preencha as informações do novo usuário:");

        dialog.getDialogPane().getStylesheets().add(
                DialogHelper.class.getResource("/br/com/agi/stylesheets/dialogHelp.css").toExternalForm()
        );

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
        dialog.setHeaderText("Altere as informações do usuário:");

        dialog.getDialogPane().getStylesheets().add(
                DialogHelper.class.getResource("/br/com/agi/stylesheets/dialogHelp.css").toExternalForm()
        );

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
                String senha = senhaField.getText().isEmpty() ? usuarioAtual.getSenha() : senhaField.getText();
                int permissao = permissaoComboBox.getValue().equals("Administrador") ? 1 : 2;

                return new Usuario(email, senha, nome, permissao);
            }
            return null;
        });
        return dialog.showAndWait();
    }

    public static String[] solicitarMesAno() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setHeaderText("Informe o mês e o ano:");

        dialog.getDialogPane().getStylesheets().add(
                DialogHelper.class.getResource("/br/com/agi/stylesheets/dialogHelp.css").toExternalForm()
        );

        ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

        ComboBox<String> mesComboBox = new ComboBox<>();
        mesComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        mesComboBox.setValue("1");

        TextField anoField = new TextField();
        anoField.setPromptText("Ano");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Mês:"), 0, 0);
        grid.add(mesComboBox, 1, 0);
        grid.add(new Label("Ano:"), 0, 1);
        grid.add(anoField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == salvarButtonType) {
                String mesSelecionado = mesComboBox.getValue();
                String ano = anoField.getText();
                return new String[]{mesSelecionado, ano};
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

    public static String[] solicitarMesAnoCPFCNPJ() {

        Dialog<String[]> dialog = new Dialog<>();
        dialog.setHeaderText("Informe o mês, o ano e o CPF/CNPJ:");

        dialog.getDialogPane().getStylesheets().add(
                DialogHelper.class.getResource("/br/com/agi/stylesheets/dialogHelp.css").toExternalForm()
        );

        ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

        ComboBox<String> mesComboBox = new ComboBox<>();
        mesComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        mesComboBox.setValue("1");

        TextField anoField = new TextField();
        anoField.setPromptText("Ano");

        TextField documentoField = new TextField();
        documentoField.setPromptText("CPF ou CNPJ");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Mês:"), 0, 0);
        grid.add(mesComboBox, 1, 0);
        grid.add(new Label("Ano:"), 0, 1);
        grid.add(anoField, 1, 1);
        grid.add(new Label("CPF/CNPJ:"), 0, 2);
        grid.add(documentoField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        Button salvarButton = (Button) dialog.getDialogPane().lookupButton(salvarButtonType);
        salvarButton.setDisable(true);

        documentoField.textProperty().addListener((observable, oldValue, newValue) -> {
            salvarButton.setDisable(!param.validaCPFouCNPJ(newValue));
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == salvarButtonType) {
                String mes = mesComboBox.getValue();
                String ano = anoField.getText();
                String documento = documentoField.getText();
                return new String[]{mes, ano, documento};
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

    public static Integer solicitarQuantidade() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setHeaderText("Informe a quantidade de cobranças a serem geradas:");

        dialog.getDialogPane().getStylesheets().add(
                DialogHelper.class.getResource("/br/com/agi/stylesheets/dialogHelp.css").toExternalForm()
        );

        ButtonType salvarButtonType = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(salvarButtonType, ButtonType.CANCEL);

        TextField quantidadeField = new TextField();
        quantidadeField.setPromptText("Quantidade");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Quantidade:"), 0, 0);
        grid.add(quantidadeField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        Button salvarButton = (Button) dialog.getDialogPane().lookupButton(salvarButtonType);
        salvarButton.setDisable(true);

        quantidadeField.textProperty().addListener((observable, oldValue, newValue) -> {
            salvarButton.setDisable(!param.isInteger(newValue) || Integer.parseInt(newValue) <= 0);
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == salvarButtonType) {
                return Integer.parseInt(quantidadeField.getText());
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

}


