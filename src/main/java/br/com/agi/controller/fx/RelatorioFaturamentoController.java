package br.com.agi.controller.fx;

import br.com.agi.controller.FaturamentoController;
import br.com.agi.model.CategoriasFaturamento;
import br.com.agi.model.CobrancasFaturamento;
import br.com.agi.model.Faturamento;
import br.com.agi.model.FaturamentoCliente;
import br.com.agi.utils.FormatoMonetarioFX;
import br.com.agi.utils.Navegador;
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


    @FXML
    private void HandleVoltarButton(){
        Navegador.getMenuRelatorio();
    }

    @FXML
    private void HandleExportButton(){

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
        if (relatorio.equals("Cliente")) relatorioCliente(mes, ano, CPFCNPJ);
        else if (relatorio.equals("Banco")) relatorioBanco(mes, ano);
    }

    public void relatorioBanco(int mes, int ano) {
        FaturamentoController controller = new FaturamentoController();
        Faturamento faturamento = controller.gerarRelatorio(mes, ano);
        FormatoMonetarioFX formatador = new FormatoMonetarioFX();

        mesAno.setText(faturamento.getMes() + "/" + faturamento.getAno());
        totalCobrancas.setText(String.valueOf(faturamento.getTotalCobrancas()));

        formatador.formatarLabel(totalRecebido, faturamento.getTotalRecebido());
        formatador.formatarLabel(totalPendente, faturamento.getTotalPendente());
        formatador.formatarLabel(totalInadimplente, faturamento.getTotalInadimplente());

        labelScroll.setText("DETALHAMENTO POR CATEGORIAS:");
        List<CategoriasFaturamento> categorias = faturamento.getDetalhamentos();

        VBox categoriasDetalhadas = new VBox(10);
        for (CategoriasFaturamento categoria : categorias) {
            Label label = new Label(String.format("%-25s | Recebidos: %-10s | Pendentes: %-10s | Inadimplentes: %-10s | Cobranças Registradas: %-10d",
                    categoria.getCategoria(),
                    categoria.getRecebidosFormatado(),
                    categoria.getPendentesFormatado(),
                    categoria.getInadimplentesFormatado(),
                    categoria.getTotal_cobrancas()));
            categoriasDetalhadas.getChildren().add(label);
        }
        scrollPane.setContent(categoriasDetalhadas);
    }

    public void relatorioCliente(int mes, int ano, String cpfCnpj) {
        FaturamentoController faturamentoController = new FaturamentoController();
        FaturamentoCliente cliente = faturamentoController.gerarRelatorioCliente(cpfCnpj, mes, ano);
        FormatoMonetarioFX formatador = new FormatoMonetarioFX();

        mesAno.setText(cliente.getMes() + "/" + cliente.getAno());
        totalCobrancas.setText(String.valueOf(cliente.getTotalCobrancas()));
        formatador.formatarLabel(totalRecebido, cliente.getTotalRecebido());
        formatador.formatarLabel(totalPendente, cliente.getTotalPendente());
        formatador.formatarLabel(totalInadimplente, cliente.getTotalInadimplente());

        labelScroll.setText("DETALHAMENTO POR COBRANÇAS:");
        List<CobrancasFaturamento> cobrancas = cliente.getCobrancas();

        VBox categoriasDetalhadas = new VBox(10);
        for (CobrancasFaturamento cobranca : cobrancas) {
            Label label = new Label(String.format("ID: %-5s | Valor: %-10s | Vencimento: %-15s | Status: %-10s",
                    cobranca.getIdCobranca(),
                    cobranca.getValorTotalFormatado(),
                    cobranca.getVencimento(),
                    cobranca.getStatus()));
            categoriasDetalhadas.getChildren().add(label);
        }
        scrollPane.setContent(categoriasDetalhadas);
    }
}
