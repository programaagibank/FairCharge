package br.com.agi.utils;

import br.com.agi.SceneLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navegador {

    public static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void getLogin() {
        SceneLoader.loadScene(primaryStage, "login/login");
    }

    public static void getHome() {
        SceneLoader.loadScene(primaryStage, "main/home2");
    }

    public static void getGerenciadorUsuario() {
        SceneLoader.loadScene(primaryStage, "usuarios/gerenciadorUsuarios");
    }

    public static void getMenuRelatorio() {
        SceneLoader.loadScene(primaryStage, "faturamento/MenuFaturamento");
    }

    public static void getMenuTaxa() {
        SceneLoader.loadScene(primaryStage, "taxa/MenuTaxas");
    }

    public static void getHistoricoTaxas() {
        SceneLoader.loadScene(primaryStage, "taxa/HistoricoTaxas");
    }

    public static void getRelatorioBanco(String parametro, int mes, int ano, String CPFCNPJ) {
        SceneLoader.loadSceneRelatorios(primaryStage, "faturamento/RelatorioFaturamento2", parametro, mes, ano, CPFCNPJ);
    }

    public static void getMenuCobranca() {
        SceneLoader.loadScene(primaryStage, "cobranca/MenuCobrancas");
    }

    public static void getCobrancasVencidas() {
        SceneLoader.loadScene(primaryStage, "cobranca/CobrancasVencidas");
    }

    public static void getRelatorioCobrancas() {
        SceneLoader.loadScene(primaryStage, "cobranca/RelatorioCobrancas");
    }

    public static void getMenuPagamento() {
        SceneLoader.loadScene(primaryStage, "pagamento/MenuPagamento");
    }

    public static void getPagamentosRealizados() {
        SceneLoader.loadScene(primaryStage, "pagamento/PagamentosRealizados");
    }

    public static void rodapeVoltar(String fxml) {
        SceneLoader.loadScene(primaryStage, fxml);
    }

    public static void getGraficoPagamento() {
        SceneLoader.loadScene(primaryStage, "pagamento/graficoPagamento");
    }
}
