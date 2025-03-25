package br.com.agi.model;
import java.util.Date;

public class Cobranca {
    private int id;
    private int faturaId;
    private Integer cobrancaReferenciadaId; // Pode ser null
    private int clienteId;
    private Integer formaPagamentoId; // Pode ser null
    private double valorTotal;
    private Date dataCriacao;
    private Date dataVencimento;
    private String status;

    public Cobranca() {}

    public Cobranca(int id, int faturaId, Integer cobrancaReferenciadaId, int clienteId,
                    Integer formaPagamentoId, double valorTotal, Date dataCriacao,
                    Date dataVencimento, String status) {
        this.id = id;
        this.faturaId = faturaId;
        this.cobrancaReferenciadaId = cobrancaReferenciadaId;
        this.clienteId = clienteId;
        this.formaPagamentoId = formaPagamentoId;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFaturaId() { return faturaId; }
    public void setFaturaId(int faturaId) { this.faturaId = faturaId; }

    public Integer getCobrancaReferenciadaId() { return cobrancaReferenciadaId; }
    public void setCobrancaReferenciadaId(Integer cobrancaReferenciadaId) {
        this.cobrancaReferenciadaId = cobrancaReferenciadaId;
    }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public Integer getFormaPagamentoId() { return formaPagamentoId; }
    public void setFormaPagamentoId(Integer formaPagamentoId) { this.formaPagamentoId = formaPagamentoId; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public Date getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Date dataCriacao) { this.dataCriacao = dataCriacao; }

    public Date getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
