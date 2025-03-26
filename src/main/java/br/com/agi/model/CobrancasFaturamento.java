package br.com.agi.model;

public class CobrancasFaturamento {
    private int idCobranca;
    private double valorCobranca;
    private String vencimento;
    private String status;

    public CobrancasFaturamento(int id, double valor, String vencimento, String status) {
            this.idCobranca = id;
            this.valorCobranca = valor;
            this.vencimento = vencimento;
            this.status = status;
    }



}
