package br.com.agi.model;

import java.util.Date;

public class Fatura {
    private int cliente_id;
    private double valorFatura;
    private Date data_criacao;
    private Date data_vencimento;
    private String statusFatura;
    private String descricao;

    public Fatura(){}

    public Fatura(int cliente_id, double valorFatura, Date data_criacao, Date data_vencimento, String statusFatura, String descricao){
        this.cliente_id = cliente_id;
        this.valorFatura = valorFatura;
        this.data_criacao = data_criacao;
        this.data_vencimento = data_vencimento;
        this.statusFatura = statusFatura;
        this.descricao = descricao;
    }

    public int getCliente_id() {return cliente_id;}
    public void setCliente_id(int clienteId) {this.cliente_id = clienteId;}

    public double getValorFatura() {return valorFatura;}
    public void setValorFatura(double valorFatura) {this.valorFatura = valorFatura;}

    public Date getData_criacao() {return data_criacao;}
    public void setData_criacao(Date data_criacao) {this.data_criacao = data_criacao;}

    public Date getData_vencimento() {return data_vencimento;}
    public void setData_vencimento(Date dataVencimento) {this.data_vencimento = dataVencimento;}

    public String getStatusFatura() {return statusFatura;}
    public void setStatusFatura(String statusFatura) {this.statusFatura = statusFatura;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
}
