package br.com.agi.model;

import br.com.agi.utils.FormatoMonetarioFX;

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

    public String getStatus() {
        return status;
    }

    public String getVencimento() {
        return vencimento;
    }

    public double getValorCobranca() {
        return valorCobranca;
    }

    public int getIdCobranca() {
        return idCobranca;
    }

    public String getValorTotalFormatado() {
        return FormatoMonetarioFX.formatar(valorCobranca);
    }

}
