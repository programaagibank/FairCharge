package br.com.agi.model;

import java.util.Date;

public class TaxaMulta implements Taxa {
    private int multaId;
    private double percentualMulta;
    private Date dataCriacao;


    public TaxaMulta(int multaId, double percentualMulta, Date dataCriacao) {
        this.multaId = multaId;
        this.percentualMulta = percentualMulta;
        this.dataCriacao = dataCriacao;
    }

    public String getTipo() {
        return "Taxa Multa";
    }
    @Override
    public int getID() {
        return multaId;
    }
    @Override
    public double getPercentual() {
        return percentualMulta;
    }
    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }


}
