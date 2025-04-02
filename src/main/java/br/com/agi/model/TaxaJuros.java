package br.com.agi.model;

import javafx.fxml.FXML;

import java.util.Date;

public class TaxaJuros implements Taxa {
    private int jurosId;
    private double percentualJurosDiario;
    private Date dataCriacao;

    public TaxaJuros(int jurosId, double percentualJurosDiario, Date dataCriacao){
        this.jurosId = jurosId;
        this.percentualJurosDiario = percentualJurosDiario;
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String getTipo() {
        return "Taxa Diária";
    }
    @Override
    public int getID() { return jurosId; }
    @Override
    public double getPercentual() {
        return percentualJurosDiario;
    }
    @Override
    public Date getDataCriacao() { return dataCriacao;  }


}
