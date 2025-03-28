package br.com.agi.model;
import java.util.Date;

public class Pagamento {
        private int pagamento_id;
        private int forma_pagamento_id;
        private double valor_pago;
        private Date data_pagamento;
        private String cod_transacao;
        private String detalhes;
        private String status;

        public Pagamento(){}

        public Pagamento(int pagamento_id, int forma_pagamento_id, double valor_pago, Date data_pagamento, String cod_transacao, String detalhes, String status){
            this.pagamento_id = pagamento_id;
            this.forma_pagamento_id = forma_pagamento_id;
            this.valor_pago = valor_pago;
            this.data_pagamento = data_pagamento;
            this.cod_transacao = cod_transacao;
            this.detalhes = detalhes;
            this.status = status;
        }

        public int getPagamento_id() {return pagamento_id;}
        public void setPagamento_id(int pagamento_id) {this.pagamento_id = pagamento_id;}

        public int getForma_pagamento_id() {return forma_pagamento_id;}
        public void setForma_pagamento_id(int forma_pagamento_id) {this.forma_pagamento_id = forma_pagamento_id;}

        public double getValor_pago() {return valor_pago;}
        public void setValor_pago(double valorPago) {this.valor_pago = valorPago;}

        public Date getData_pagamento() {return data_pagamento;}
        public void setData_pagamento(Date dataVencimento) {this.data_pagamento = dataVencimento;}

        public String getDetalhes() {return detalhes;}
        public void setDetalhes(String detalhes) {this.detalhes = detalhes;}

        public String getCod_transacao() {return cod_transacao;}
        public void setCod_transacao(String codTransacao) {this.cod_transacao = codTransacao;}

        public String getStatus() {return status;}
        public void setStatus(String status) {this.status = status;}
}

