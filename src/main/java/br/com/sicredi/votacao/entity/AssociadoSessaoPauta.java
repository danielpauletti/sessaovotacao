package br.com.sicredi.votacao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidade que persiste o voto de cada associado na pauta da sessão
 */
@Entity
public class AssociadoSessaoPauta implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private Pauta pauta;

    @OneToOne
    @JoinColumn(name = "id_sessao", referencedColumnName = "id")
    private Sessao sessao;

    @Column
    private String cpfCnpjAssociado;

    /**
     * Voto do associado definido por S ou N
     */
    @Column
    private String voto;

    public AssociadoSessaoPauta() {
    }

    public AssociadoSessaoPauta(Pauta pauta, Sessao sessao, String voto, String cpfCnpjAssociado) {
        this.pauta = pauta;
        this.sessao = sessao;
        this.voto = voto;
        this.cpfCnpjAssociado = cpfCnpjAssociado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public String getCpfCnpjAssociado() {
        return cpfCnpjAssociado;
    }

    public void setCpfCnpjAssociado(String cpfCnpjAssociado) {
        this.cpfCnpjAssociado = cpfCnpjAssociado;
    }
}
