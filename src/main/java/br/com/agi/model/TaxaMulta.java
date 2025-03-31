package br.com.agi.model;

import java.util.Date;

public class TaxaMulta {
    private int multaId;
    private double percentualMulta;
    private Date dataCriacao;


    public TaxaMulta(int multaId, double percentualMulta, Date dataCriacao) {
        this.multaId = multaId;
        this.percentualMulta = percentualMulta;
        this.dataCriacao = dataCriacao;
    }


    public int getMultaId() {
        return multaId;
    }

    public double getPercentualMulta() {
        return percentualMulta;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }


}
