package br.com.sicredi.votacao.response;

import br.com.sicredi.votacao.entity.Pauta;
import br.com.sicredi.votacao.entity.Sessao;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContabilizacaoVotosResponse {
    private Sessao sessao;
    private Integer votosSim;
    private Integer votosNao;

    public ContabilizacaoVotosResponse() {
    }

    public ContabilizacaoVotosResponse(Sessao sessao, Integer votosSim, Integer votosNao) {
        this.sessao = sessao;
        this.votosSim = votosSim;
        this.votosNao = votosNao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Integer getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(Integer votosSim) {
        this.votosSim = votosSim;
    }

    public Integer getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(Integer votosNao) {
        this.votosNao = votosNao;
    }
}
