package br.com.sicredi.votacao.response;

import java.io.Serializable;

public class PautaResponse implements Serializable {
    private Integer id;
    private String descricao;

    public PautaResponse() {
    }

    public PautaResponse(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
