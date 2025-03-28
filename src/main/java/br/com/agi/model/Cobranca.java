package br.com.agi.model;
import java.time.LocalDate;
import java.util.Date;

public class Cobranca {
    private int cobranca_id;
    private int faturaId;
    private Integer cobrancaReferenciadaId; // Pode ser null
    private int clienteId;
    private Integer formaPagamentoId; // Pode ser null
    private double valorTotal;
    private double valorTotalComMultas;
    private Date dataCriacao;
    private LocalDate dataVencimento;
    private String status;
    private String nomeCliente;

    public Cobranca(int cobranca_id, int faturaId, String nomeCliente, double valorTotal, double valorTotalComMultas, LocalDate dataVencimento, String status) {
        this.cobranca_id = cobranca_id;
        this.faturaId = faturaId;
        this.nomeCliente = nomeCliente;
        this.valorTotalComMultas = valorTotalComMultas;
        this.valorTotal = valorTotal;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public Cobranca(int cobranca_id, int faturaId, String nomeCliente, String status, double valorTotalComMultas, LocalDate dataVencimento) {
        this.cobranca_id = cobranca_id;
        this.faturaId = faturaId;
        this.nomeCliente = nomeCliente;
        this.status = status;
        this.valorTotalComMultas = valorTotalComMultas;
        this.dataVencimento = dataVencimento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getCobranca_id() {
        return cobranca_id;
    }

    public int getFaturaId() {
        return faturaId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getValorTotalComMultas() {
        return valorTotalComMultas;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public String getStatus() {
        return status;
    }
}
