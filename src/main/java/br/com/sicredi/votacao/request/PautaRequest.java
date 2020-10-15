package br.com.sicredi.votacao.request;

import java.io.Serializable;

public class PautaRequest implements Serializable {
    private String descricao;

    public PautaRequest() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
