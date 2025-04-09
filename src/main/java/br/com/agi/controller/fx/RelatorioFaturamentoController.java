package br.com.agi.controller.fx;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;
import br.com.agi.utils.FormatoMonetarioFX;
import br.com.agi.utils.Navegador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class RelatorioFaturamentoController {

    @FXML
    public VBox tabelas;

    @FXML
    private Label mesAno;

    @FXML
    private Label totalCobrancas;

    @FXML
    private Label totalInadimplente;

    @FXML
    private Label totalPendente;

    @FXML
    private Label totalRecebido;

    @FXML
    private Label labelScroll;

    @FXML
    void exportPhoto() {

    }

    @FXML
    void homePhoto() {
        Navegador.getHome();
    }

    private String relatorio = "";
    private int mes;
    private int ano;
    private String CPFCNPJ;

    public void selecionaRelatorio(String relatorio, int mes, int ano, String CPFCNPJ) {
        this.relatorio = relatorio;
        this.mes = mes;
        this.ano = ano;
        this.CPFCNPJ = CPFCNPJ;

        carregarTabela();

        if (relatorio.equals("Cliente")) relatorioCliente(mes, ano, CPFCNPJ);
        else if (relatorio.equals("Banco")) relatorioBanco(mes, ano);
    }

    private void carregarTabela() {
        try {
            String fxmlPath;
            FXMLLoader loader;

            if (relatorio.equals("Banco")) {
                fxmlPath = "/br/com/agi/view/faturamento/TabelaBanco.fxml";
            } else if (relatorio.equals("Cliente")) {
                fxmlPath = "/br/com/agi/view/faturamento/TabelaCliente.fxml";
            } else {
                throw new IllegalArgumentException("Tipo de relatório inválido: " + relatorio);
            }

            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            VBox tabelaPane = loader.load();

            if (relatorio.equals("Banco")) {
                TabelaBancoController tabelaController = loader.getController();
                tabelaController.setMesAno(mes, ano);
            } else if (relatorio.equals("Cliente")) {
                TabelaClienteController tabelaController = loader.getController();
                tabelaController.setMesAnoCPFCNPJ(mes, ano, CPFCNPJ);
            }

            tabelas.getChildren().clear();
            tabelas.getChildren().add(tabelaPane);

            AnchorPane.setTopAnchor(tabelaPane, 0.0);
            AnchorPane.setBottomAnchor(tabelaPane, 0.0);
            AnchorPane.setLeftAnchor(tabelaPane, 0.0);
            AnchorPane.setRightAnchor(tabelaPane, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o FXML: " + relatorio);
        }
    }


    public void relatorioBanco(int mes, int ano) {
        FaturamentoController controller = new FaturamentoController();
        Faturamento faturamento = controller.gerarRelatorio(mes, ano);
        FormatoMonetarioFX formatador = new FormatoMonetarioFX();

        mesAno.setText("Relatório Bancário " + faturamento.getMes() + "/" + faturamento.getAno());
        totalCobrancas.setText(String.valueOf(faturamento.getTotalCobrancas()));

        formatador.formatarLabel(totalRecebido, faturamento.getTotalRecebido());
        formatador.formatarLabel(totalPendente, faturamento.getTotalPendente());
        formatador.formatarLabel(totalInadimplente, faturamento.getTotalInadimplente());

        labelScroll.setText("Detalhamento por categorias:");
    }

    public void relatorioCliente(int mes, int ano, String cpfCnpj) {
        FaturamentoController faturamentoController = new FaturamentoController();
        FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente(cpfCnpj, mes, ano);
        FormatoMonetarioFX formatador = new FormatoMonetarioFX();

        mesAno.setText("Relatório Cliente: " +cliente.getCliente() + " " + cliente.getMes() + "/" + cliente.getAno());
        totalCobrancas.setText(String.valueOf(cliente.getTotalCobrancas()));
        formatador.formatarLabel(totalRecebido, cliente.getTotalRecebido());
        formatador.formatarLabel(totalPendente, cliente.getTotalPendente());
        formatador.formatarLabel(totalInadimplente, cliente.getTotalInadimplente());

        labelScroll.setText("Detalhamento por cobrança:");
    }
}
