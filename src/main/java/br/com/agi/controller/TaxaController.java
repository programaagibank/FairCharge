package br.com.agi.controller;

import br.com.agi.dao.TaxaDAO;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;
import javafx.collections.ObservableList;

public class TaxaController {
    private TaxaDAO taxaDAO;


    public TaxaController() {this.taxaDAO = new TaxaDAO(); }

    public TaxaJuros getTaxaJurosDiarios() {
        return taxaDAO.buscarTaxaJurosDiarios();
    }

    public TaxaMulta getMultaPorAtraso() {
        return taxaDAO.buscarMultaPorAtraso();
    }

    public boolean addTaxaJuros(double percentualJuros) {
        return taxaDAO.addTaxaJuros(percentualJuros);
    }

    public boolean addTaxaMulta(double multaControler) {
        return taxaDAO.addTaxaMulta(multaControler);
    }

    public ObservableList<TaxaJuros> buscaTodasDiarias() {
        return taxaDAO.buscarTodasTaxasDiarias();
    }

    public ObservableList<TaxaMulta> buscaTodasMultas() {
        return taxaDAO.buscarTodasTaxasMulta();
    }
}
