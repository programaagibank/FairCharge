package br.com.agi.controller.fx;

import br.com.agi.controller.UsuarioController;
import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;
//    @FXML private AnchorPane backgroundMain;
    @FXML private Label lblErro;
//    @FXML private VBox loginBackground;

    private final UsuarioController usuario = new UsuarioController();

    @FXML
    private void handleLogin() {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (usuario.acessarLogin(email, senha)) {
            System.out.println("Login bem-sucedido!");
            Navegador.getHome();
        } else {
            lblErro.setText("Credenciais inválidas!");
            lblErro.setVisible(true);
        }
    }
}
