package br.com.agi.model;

import java.util.ArrayList;
import java.util.List;

public class Faturamento {
    private int mes;
    private int ano;
    private int totalCobrancas;
    private double totalRecebido;
    private double totalPendente;
    private double totalInadimplente;
    private List<CategoriasFaturamento> detalhamentos;

    public Faturamento(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        this.detalhamentos = new ArrayList<>();
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getTotalCobrancas() {
        return totalCobrancas;
    }

    public void setTotalCobrancas(int totalCobrancas) {
        this.totalCobrancas = totalCobrancas;
    }

    public double getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

    public double getTotalPendente() {
        return totalPendente;
    }

    public void setTotalPendente(double totalPendente) {
        this.totalPendente = totalPendente;
    }

    public double getTotalInadimplente() {
        return totalInadimplente;
    }

    public void setTotalInadimplente(double totalInadimplente) {
        this.totalInadimplente = totalInadimplente;
    }

    public List<CategoriasFaturamento> getDetalhamentos() {
        return detalhamentos;
    }

    public void adicionarDetalhamento(CategoriasFaturamento detalhamento) {
        detalhamentos.add(detalhamento);
    }
}
