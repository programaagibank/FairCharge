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
        grafico.setTitle("Valores Pagos - Mensal");
        xAxis.setLabel("Meses/Ano");
        yAxis.setLabel("Valores Pagos (R$)");

        List<Pagamento> pagamentos = controller.listarCobrancasPagas();

        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();

        Map<String, Double> valoresPorMes = new LinkedHashMap<>();
        // Inicializa os meses do ano atual com valor 0
        for (int mes = 1; mes <= mesAtual; mes++) {
            valoresPorMes.put(String.format("%02d/%d", mes, anoAtual), 0.0);
        }

        // Agrega os pagamentos por mês
        pagamentos.stream()
                .filter(pagamento -> pagamento.getDataPagamento().getYear() == anoAtual)
                .forEach(pagamento -> {
                    String mesAno = String.format("%02d/%d",
                            pagamento.getDataPagamento().getMonthValue(),
                            pagamento.getDataPagamento().getYear());
                    valoresPorMes.put(mesAno, valoresPorMes.getOrDefault(mesAno, 0.0) + pagamento.getValorPago());
                });

        // Cria a série para o gráfico
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Valores Pagos");

        valoresPorMes.forEach((mesAno, valor) -> {
            serie.getData().add(new XYChart.Data<>(mesAno, valor));
        });

        grafico.getData().clear(); // Limpa dados existentes antes de adicionar a nova série
        grafico.getData().add(serie);
    }

}
