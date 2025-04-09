package br.com.agi.utils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.DialogPane;

public class Alerta {

    private static void aplicarEstilo(Alert alerta) {
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.getStylesheets().add(Alerta.class.getResource("/br/com/agi/stylesheets/alerta.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane");
    }

    public static void mostrarInformacao(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        aplicarEstilo(alerta);
        alerta.showAndWait();
    }

    public static void mostrarAviso(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        aplicarEstilo(alerta);
        alerta.showAndWait();
    }

    public static void mostrarErro(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        aplicarEstilo(alerta);
        alerta.showAndWait();
    }

    public static boolean mostrarConfirmacao(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        aplicarEstilo(alerta);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
}
