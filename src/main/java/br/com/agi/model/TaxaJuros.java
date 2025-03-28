package br.com.agi.model;

import java.util.Date;

public class TaxaJuros {
    private int jurosId, addJurosId;
    private double percentualJurosDiario, addpercentualJurosDiario;
    private Date dataCriacao, adddataCriacao;

    public TaxaJuros(double percentualJurosDiario, int jurosId, Date dataCriacao){
        this.jurosId = jurosId;
        this.percentualJurosDiario = percentualJurosDiario;
        this.dataCriacao = dataCriacao;
    }


    public TaxaJuros(int addJurosId, double addpercentualJurosDiario, Date adddataCriacao){
        this.addJurosId = addJurosId;
        this.addpercentualJurosDiario = addpercentualJurosDiario;
        this.adddataCriacao = adddataCriacao;
    }

    public TaxaJuros(int jurosId, double percentualJurosDiario, java.sql.Date dataCriacao) {     }

    public int getJurosId() { return jurosId; }

    public double getPercentualJurosDiario() {
        return percentualJurosDiario;
    }

    public Date getDataCriacao() { return dataCriacao;  }

    public int isertJurosId() { return addJurosId;}

    public double inserPercentualDiario() { return addpercentualJurosDiario;}

    public  Date intertDataCriacao() { return  adddataCriacao; }

}
