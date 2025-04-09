package br.com.agi.controller.fx;

import br.com.agi.controller.PagamentoController;
import br.com.agi.model.Pagamento;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraficoPagamentoController {
    @FXML
    private LineChart<String, Number> grafico;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    PagamentoController controller = new PagamentoController();

    public void initialize() {
        preencherGrafico();
    }
    public void preencherGrafico() {
        grafico.setTitle("Gastos por Cliente - Mensal/Anual");
        xAxis.setLabel("Meses/Ano");
        yAxis.setLabel("Gastos Totais (R$)");

        List<Pagamento> pagamentos = controller.listarCobrancasPagas();

        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();

        Map<String, List<Pagamento>> pagamentosPorCliente = pagamentos.stream()
                .filter(pagamento -> pagamento.getDataPagamento().getYear() == anoAtual)
                .collect(Collectors.groupingBy(Pagamento::getNomeCliente));

        pagamentosPorCliente.forEach((cliente, listaPagamentos) -> {
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName(cliente);

            Map<String, Double> gastosPorMes = new LinkedHashMap<>();
            for (int mes = 1; mes <= mesAtual; mes++) {
                gastosPorMes.put(String.format("%02d/%d", mes, anoAtual), 0.0);
            }

            listaPagamentos.forEach(pagamento -> {
                String mesAno = String.format("%02d/%d",
                        pagamento.getDataPagamento().getMonthValue(),
                        pagamento.getDataPagamento().getYear());

                gastosPorMes.put(mesAno, gastosPorMes.getOrDefault(mesAno, 0.0) + pagamento.getValorPago());
            });

            gastosPorMes.forEach((mesAno, gasto) -> serie.getData().add(new XYChart.Data<>(mesAno, gasto)));

            grafico.getData().add(serie);
        });
    }
}
