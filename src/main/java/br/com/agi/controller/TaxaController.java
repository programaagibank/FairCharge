package br.com.agi.controller;

import br.com.agi.dao.TaxaDAO;
import br.com.agi.model.TaxaJuros;
import br.com.agi.model.TaxaMulta;

public class TaxaController {
    private TaxaDAO taxaDAO;

    public TaxaController() {this.taxaDAO = new TaxaDAO(); }

    public TaxaJuros getTaxaJurosDiarios() {
        return taxaDAO.buscarTaxaJurosDiarios();
    }

    public TaxaMulta getMultaPorAtraso() {
        return taxaDAO.buscarMultaPorAtraso();
    }

//    public TaxaJuros addTaxaJuros() {return taxaDAO.addTaxaJuros();}
//
//    public TaxaMulta addTaxaMulta() {return taxaDAO.addTaxaMulta();}
}
