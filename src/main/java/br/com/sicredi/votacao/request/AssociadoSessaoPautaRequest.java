package br.com.sicredi.votacao.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AssociadoSessaoPautaRequest implements Serializable {

    private Integer idSessao;
    private String cpfCnpjAssociado;
    private String voto;

    public AssociadoSessaoPautaRequest(Integer idSessao, String cpfCnpjAssociado, String voto) {
        this.idSessao = idSessao;
        this.cpfCnpjAssociado = cpfCnpjAssociado;
        this.voto = voto;
    }

    public AssociadoSessaoPautaRequest() {
    }

    public Integer getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Integer idSessao) {
        this.idSessao = idSessao;
    }

    public String getCpfCnpjAssociado() {
        return cpfCnpjAssociado;
    }

    public void setCpfCnpjAssociado(String cpfCnpjAssociado) {
        this.cpfCnpjAssociado = cpfCnpjAssociado;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }
}
