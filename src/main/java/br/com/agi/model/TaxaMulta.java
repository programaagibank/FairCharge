package br.com.agi.model;

import java.util.Date;

public class TaxaMulta {
    private int multaId, addMultaId;
    private double percentualMulta, addPercentualMulta;
    private Date dataCriacao, addDataCriacao;


    public TaxaMulta(int multaId, double percentualMulta, Date dataCriacao) {
        this.multaId = multaId;
        this.percentualMulta = percentualMulta;
        this.dataCriacao = dataCriacao;
    }

    public TaxaMulta( double addpercentualMulta,int addMultaId, Date addDataCriacao){
        this.addMultaId = addMultaId;
        this.addPercentualMulta = addpercentualMulta;
        this.addDataCriacao = addDataCriacao;
    }


    public TaxaMulta(int multaI , double percentualMulta, java.sql.Date dataCriacao) {   }


    public int getMultaId() {
        return multaId;
    }

    public double getPercentualMulta() {
        return percentualMulta;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public int insertMulta() { return addMultaId;}

    public double inserPercentualMulta() { return addPercentualMulta;}

    public  Date intertDataCriacaoMulta() { return  addDataCriacao; }

}
