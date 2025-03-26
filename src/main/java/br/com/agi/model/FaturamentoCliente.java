package br.com.agi.model;

import java.util.ArrayList;
import java.util.List;

public class FaturamentoCliente {

    private int mes;
    private int ano;
    private List<CobrancasFaturamento> cobrancas;
    private String cliente;
    private int totalCobrancas;
    private double totalRecebido;
    private double totalPendente;
    private double totalInadimplente;

    public FaturamentoCliente(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        this.cobrancas = new ArrayList<>();
    }

    public FaturamentoCliente(String cliente, int totalCobrancas, double totalRecebido, double totalPendente, double totalInadimplente) {
        this.cliente = cliente;
        this.totalCobrancas = totalCobrancas;
        this.totalRecebido = totalRecebido;
        this.totalPendente = totalPendente;
        this.totalInadimplente = totalInadimplente;
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

    public List<CobrancasFaturamento> getCobrancas() {
        return cobrancas;
    }

    public void adicionarCobrancas(CobrancasFaturamento cobranca) {
        cobrancas.add(cobranca);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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


}
