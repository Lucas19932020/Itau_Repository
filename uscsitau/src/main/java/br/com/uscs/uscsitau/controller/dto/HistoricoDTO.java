package br.com.uscs.uscsitau.controller.dto;

import java.util.Date;

public class HistoricoDTO {

    private String num_conta;
    private String tipo_de_transacao;
    private Date date;
    private Integer status; // Failed = 0, Success = 1

    public String getCpf_cnpj() {
        return num_conta;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.num_conta = cpf_cnpj;
    }

    public String getTipo_de_transacao() {
        return tipo_de_transacao;
    }

    public void setTipo_de_transacao(String tipo_de_transacao) {
        this.tipo_de_transacao = tipo_de_transacao;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
