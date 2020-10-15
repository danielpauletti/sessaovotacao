package br.com.sicredi.votacao.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SessaoRequest implements Serializable {

    private Integer id;
    private String descricao;
    private Integer tempo;     //Minutos
    private Integer idPauta;

    public SessaoRequest() {
    }

    public SessaoRequest(Integer id, String descricao, Integer tempo, Integer idPauta) {
        this.id = id;
        this.descricao = descricao;
        this.tempo = tempo;
        this.idPauta = idPauta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
