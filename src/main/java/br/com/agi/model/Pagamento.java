package br.com.agi.model;

import br.com.agi.utils.FormatoMonetarioFX;

import java.time.LocalDate;

public class Pagamento {
    private int cobrancaId;
    private int faturaId;
    private String nomeCliente;
    private double valorPago;
    private LocalDate dataPagamento;
    private String status;
    private double valorFaturaCorrigido;

    public Pagamento(int cobrancaId, int faturaId, String nomeCliente, double valorPago, LocalDate dataPagamento, String status, double valorFaturaCorrigido) {
        this.cobrancaId = cobrancaId;
        this.faturaId = faturaId;
        this.nomeCliente = nomeCliente;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.valorFaturaCorrigido = valorFaturaCorrigido;
    }

    public int getCobrancaId() {
        return cobrancaId;
    }

    public void setCobrancaId(int cobrancaId) {
        this.cobrancaId = cobrancaId;
    }

    public int getFaturaId() {
        return faturaId;
    }

    public void setFaturaId(int faturaId) {
        this.faturaId = faturaId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorFaturaCorrigido() {
        return valorFaturaCorrigido;
    }

    public void setValorFaturaCorrigido(double valorFaturaCorrigido) {
        this.valorFaturaCorrigido = valorFaturaCorrigido;
    }

    public String getValorFaturaFormatado(){
        return FormatoMonetarioFX.formatar(valorFaturaCorrigido);
    }

    public String getValorPagoFormatado(){
        return FormatoMonetarioFX.formatar(valorPago);
    }

}
