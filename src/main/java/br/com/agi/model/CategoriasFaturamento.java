package br.com.agi.model;

import br.com.agi.utils.FormatoMonetarioFX;

public class CategoriasFaturamento {
    private String categoria;
    private double recebidos;
    private double pendentes;
    private double inadimplentes;
    private int total_cobrancas;

    public CategoriasFaturamento(String categoria, double recebidos, double pendentes, double inadimplentes, int total_cobrancas) {
        this.categoria = categoria;
        this.recebidos = recebidos;
        this.pendentes = pendentes;
        this.inadimplentes = inadimplentes;
        this.total_cobrancas = total_cobrancas;
    }

    public int getTotal_cobrancas() {
        return total_cobrancas;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getRecebidos() {
        return recebidos;
    }

    public double getPendentes() {
        return pendentes;
    }

    public double getInadimplentes() {
        return inadimplentes;
    }

    public String getRecebidosFormatado() {
        return FormatoMonetarioFX.formatar(recebidos);
    }

    public String getPendentesFormatado() {
        return FormatoMonetarioFX.formatar(pendentes);
    }

    public String getInadimplentesFormatado() {
        return FormatoMonetarioFX.formatar(inadimplentes);
    }


}
