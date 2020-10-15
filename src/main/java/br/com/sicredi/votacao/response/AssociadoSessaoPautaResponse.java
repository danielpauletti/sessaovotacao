package br.com.sicredi.votacao.response;

import br.com.sicredi.votacao.entity.Pauta;
import br.com.sicredi.votacao.entity.Sessao;

public class AssociadoSessaoPautaResponse {
    private Integer id;
    private Pauta pauta;
    private Sessao sessao;
    private String voto;
    private String cpfCnpjAssociado;

    public AssociadoSessaoPautaResponse() {
    }

    public AssociadoSessaoPautaResponse(Integer id, Pauta pauta, Sessao sessao, String cpfCnpjAssociado,String voto) {
        this.id = id;
        this.pauta = pauta;
        this.sessao = sessao;
        this.cpfCnpjAssociado = cpfCnpjAssociado;
        this.voto = voto;
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
