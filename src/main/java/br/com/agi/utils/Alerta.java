package br.com.agi.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerta {

    public static void mostrarInformacao(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }

    public static void mostrarAviso(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }

    public static void mostrarErro(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }

    public static boolean mostrarConfirmacao(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK; // Retorna true se o usuário confirmar
    }
}
