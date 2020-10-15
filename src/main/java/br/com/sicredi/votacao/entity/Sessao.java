package br.com.sicredi.votacao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Sessao {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;

    @Column
    private Integer duracao;  // Minutos

    @Column
    private Timestamp dataAbertura;

    @Column
    private Timestamp dataFechamento;

    public Sessao() {
    }

    public Sessao(Integer id, String descricao, Pauta pauta, Integer duracao, Timestamp dataAbertura, Timestamp dataFechamento) {
        this.id = id;
        this.descricao = descricao;
        this.pauta = pauta;
        this.duracao = duracao;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
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

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Timestamp dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Timestamp dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
