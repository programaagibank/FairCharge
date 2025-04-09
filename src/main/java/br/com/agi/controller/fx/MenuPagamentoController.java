package br.com.agi.controller.fx;

import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Pagamento;
import br.com.agi.model.Usuario;
import br.com.agi.utils.FormatoMonetarioFX;
import br.com.agi.utils.Navegador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuPagamentoController {

    @FXML
    private LineChart<String, Number> grafico;

    @FXML
    private CategoryAxis xAxis; // Eixo X

    @FXML
    private NumberAxis yAxis; // Eixo Y

    @FXML
    private TableColumn<Pagamento, Date> DataPagamentoColumn;

    @FXML
    private TableColumn<Pagamento, Integer> IDCobrancaColumn;

    @FXML
    private TableColumn<Pagamento, Integer> IDFaturaColumn;

    @FXML
    private TableColumn<Pagamento, String> NomeClienteColumn;

    @FXML
    private TableColumn<Pagamento, Double> ValorFaturaColumn;

    @FXML
    private TableColumn<Pagamento, Double> ValorPagoColumn;

    @FXML
    private TableView<Pagamento> tabelaPagamentos;

    @FXML
    private Label faturasPagas;

    @FXML
    private Label faturasPendentes;

    @FXML
    private Label totalPago;

    @FXML
    void botaoGrafico() {
        Navegador.getGraficoPagamento();
    }

    PagamentoController controller = new PagamentoController();

    @FXML
    void initialize() {
        LocalDate dataAtual = LocalDate.now();
        int mesAtual = dataAtual.getMonthValue();
        int anoAtual = dataAtual.getYear();

        faturasPagas.setText(String.valueOf(controller.obterQuantidadeFaturasPagas(mesAtual, anoAtual)));
        faturasPendentes.setText(String.valueOf(controller.obterQuantidadeFaturasPendentes(mesAtual, anoAtual)));
        totalPago.setText(FormatoMonetarioFX.formatar(controller.obterValorPagoPorMes(mesAtual, anoAtual)));
        preencherTabelaPagamento();
        preencherGrafico();
    }

    public void preencherGrafico() {
        // Configuração do título e eixos
        grafico.setTitle("Gastos por Cliente - Mensal/Anual");
        xAxis.setLabel("Meses/Ano");
        yAxis.setLabel("Gastos Totais (R$)");

        // Obtém lista de pagamentos
        List<Pagamento> pagamentos = controller.listarCobrancasPagas();

        // Filtra pagamentos para o ano de 2025
        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();

        // Agrupa pagamentos por cliente
        Map<String, List<Pagamento>> pagamentosPorCliente = pagamentos.stream()
                .filter(pagamento -> pagamento.getDataPagamento().getYear() == anoAtual)
                .collect(Collectors.groupingBy(Pagamento::getNomeCliente));

        // Para cada cliente, cria uma série de dados
        pagamentosPorCliente.forEach((cliente, listaPagamentos) -> {
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName(cliente);

            // Cria um mapa para representar os meses do ano de 2025 com valores zerados inicialmente
            Map<String, Double> gastosPorMes = new LinkedHashMap<>();
            for (int mes = 1; mes <= mesAtual; mes++) {
                gastosPorMes.put(String.format("%02d/%d", mes, anoAtual), 0.0);
            }

            // Preenche o mapa com os valores dos pagamentos
            listaPagamentos.forEach(pagamento -> {
                String mesAno = String.format("%02d/%d",
                        pagamento.getDataPagamento().getMonthValue(),
                        pagamento.getDataPagamento().getYear());

                gastosPorMes.put(mesAno, gastosPorMes.getOrDefault(mesAno, 0.0) + pagamento.getValorPago());
            });

            // Adiciona os dados do mapa à série
            gastosPorMes.forEach((mesAno, gasto) -> serie.getData().add(new XYChart.Data<>(mesAno, gasto)));

            // Adiciona a série ao gráfico
            grafico.getData().add(serie);
        });
    }

    public void preencherTabelaPagamento() {
        IDCobrancaColumn.setCellValueFactory(new PropertyValueFactory<>("cobrancaId"));
        IDFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("faturaId"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        ValorFaturaColumn.setCellValueFactory(new PropertyValueFactory<>("ValorFaturaFormatado"));
        ValorPagoColumn.setCellValueFactory(new PropertyValueFactory<>("ValorPagoFormatado"));
        DataPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataPagamento"));
        ObservableList<Pagamento> listaPagamentos = FXCollections.observableArrayList(controller.listarCobrancasPagas());
        tabelaPagamentos.setItems(listaPagamentos);
    }
}
