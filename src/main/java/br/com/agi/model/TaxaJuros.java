package br.com.agi.model;

import java.util.Date;

public class TaxaJuros {
    private int jurosId;
    private double percentualJurosDiario;
    private Date dataCriacao;

    public TaxaJuros(int jurosId, double percentualJurosDiario, Date dataCriacao){
        this.jurosId = jurosId;
        this.percentualJurosDiario = percentualJurosDiario;
        this.dataCriacao = dataCriacao;
    }


    public int getJurosId() { return jurosId; }

    public double getPercentualJurosDiario() {
        return percentualJurosDiario;
    }

    public Date getDataCriacao() { return dataCriacao;  }


}
