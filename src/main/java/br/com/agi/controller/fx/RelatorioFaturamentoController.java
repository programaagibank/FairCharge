package br.com.agi.controller.fx;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class RelatorioFaturamentoController {

    @FXML
    private Label mesAno;

    @FXML
    private ScrollPane scrollPane;

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

    private String relatorio = "";
    public void selecionaRelatorio(String parametro) {
        this.relatorio = parametro;
        if (parametro.equals("Cliente")) relatorioCliente();
        else if (parametro.equals("Banco")) relatorioBanco();
    }


    @FXML
    public void initialize() {
        if (relatorio.equals("Cliente")) relatorioCliente();
        else if (relatorio.equals("Banco")) relatorioBanco();
    }

    public void relatorioBanco() {
        FaturamentoController controller = new FaturamentoController();
        Faturamento faturamento = controller.gerarRelatorio(3, 2025);

        mesAno.setText(faturamento.getMes() + "/" + faturamento.getAno());
        totalCobrancas.setText(String.valueOf(faturamento.getTotalCobrancas()));
        totalRecebido.setText(String.format("%.2f", faturamento.getTotalRecebido()));
        totalPendente.setText(String.format("%.2f", faturamento.getTotalPendente()));
        totalInadimplente.setText(String.format("%.2f", faturamento.getTotalInadimplente()));
        labelScroll.setText("DETALHAMENTO POR CATEGORIAS:");
        List<CategoriasFaturamento> categorias = faturamento.getDetalhamentos();

        VBox categoriasDetalhadas = new VBox(10);
        for (CategoriasFaturamento categoria : categorias) {
            Label label = new Label(String.format("%-25s | Recebidos: R$ %-10.2f | Pendentes: R$ %-10.2f | Inadimplentes: R$ %-10.2f",
                    categoria.getCategoria(),
                    categoria.getRecebidos(),
                    categoria.getPendentes(),
                    categoria.getInadimplentes()));
            categoriasDetalhadas.getChildren().add(label);
        }
        scrollPane.setContent(categoriasDetalhadas);
    }

    public void relatorioCliente() {
        FaturamentoController faturamentoController = new FaturamentoController();
        FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente("12345678000101", 3, 2025);

        mesAno.setText(cliente.getMes() + "/" + cliente.getAno());
        totalCobrancas.setText(String.valueOf(cliente.getTotalCobrancas()));
        totalRecebido.setText(String.format("%.2f", cliente.getTotalRecebido()));
        totalPendente.setText(String.format("%.2f", cliente.getTotalPendente()));
        totalInadimplente.setText(String.format("%.2f", cliente.getTotalInadimplente()));

        labelScroll.setText("DETALHAMENTO POR COBRANÇAS:");
        List<CobrancasFaturamento> cobrancas = cliente.getCobrancas();

        VBox categoriasDetalhadas = new VBox(10);
        for (CobrancasFaturamento cobranca : cobrancas) {
            Label label = new Label(String.format("ID: %-5s | Valor: R$ %-10.2f | Vencimento: %-15s | Status: %-10s",
                    cobranca.getIdCobranca(),
                    cobranca.getValorCobranca(),
                    cobranca.getVencimento(),
                    cobranca.getStatus()));
            categoriasDetalhadas.getChildren().add(label);
        }
        scrollPane.setContent(categoriasDetalhadas);
    }
}
