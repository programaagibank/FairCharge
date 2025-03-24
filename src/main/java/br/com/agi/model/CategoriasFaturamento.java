package br.com.agi.model;

public class CategoriasFaturamento {
    private String categoria;
    private double recebidos;
    private double pendentes;
    private double inadimplentes;

    public CategoriasFaturamento(String categoria, double recebidos, double pendentes, double inadimplentes) {
        this.categoria = categoria;
        this.recebidos = recebidos;
        this.pendentes = pendentes;
        this.inadimplentes = inadimplentes;
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
}
