package br.com.sicredi.votacao.service;

import br.com.sicredi.votacao.entity.AssociadoSessaoPauta;
import br.com.sicredi.votacao.entity.Sessao;
import br.com.sicredi.votacao.repository.AssociadoSessaoPautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Efetua validações e persiste dados relacionados aos votos
 */
@Service
public class AssociadoSessaoPautaService {

    private static final String ASSOCIADO_JA_VOTOU = "Já exsite um voto para este usuário nesta pauta!";

    @Autowired
    private AssociadoSessaoPautaRepository repository;

    /**
     * Salva a entidade que comporta o voto da pauta na sessão
     * @param entity
     * @return
     */
    public AssociadoSessaoPauta salvar(AssociadoSessaoPauta entity){
        return repository.save(entity);
    }

    /**
     * Efetua contagem de votos para "Sim" e "Não" da pauta de uma sessão.
     * Obs.: Para otimização poderia ser feita uma rotina de banco.
     * @param sessao
     * @param voto
     * @return
     */
    public Integer countVotos(Sessao sessao, String voto){
        AssociadoSessaoPauta asp = new AssociadoSessaoPauta();
        asp.setSessao(sessao);
        asp.setPauta(sessao.getPauta());
        asp.setVoto(voto);

        Example<AssociadoSessaoPauta> aspExample = Example.of(asp);
        return new Long(repository.findAll(aspExample).spliterator().estimateSize()).intValue();
    }

    /**
     * Verifica se o associado já realizou a votação da pauta, retornando uma exceção
     * caso este já tenha votado.
     * @param asp
     * @throws Exception
     */
    public void valida(AssociadoSessaoPauta asp) throws Exception {
        Example<AssociadoSessaoPauta> aspExample = Example.of(asp);
        if (repository.exists(aspExample)){
            throw new Exception(ASSOCIADO_JA_VOTOU);
        }
    }
}
