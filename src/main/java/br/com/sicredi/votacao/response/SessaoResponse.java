package br.com.sicredi.votacao.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Date;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SessaoResponse implements Serializable {
    private Integer id;
    private Integer tempoTotal;
    private String descricao;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
    private Date dataAbertura;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
    private Date dataFechamento;

    public SessaoResponse(Integer id, Integer tempoTotal, String descricao, Date dataAbertura, Date dataFechamento) {
        this.id = id;
        this.tempoTotal = tempoTotal;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(Integer tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
