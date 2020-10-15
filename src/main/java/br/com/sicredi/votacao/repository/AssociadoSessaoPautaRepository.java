package br.com.sicredi.votacao.repository;

import br.com.sicredi.votacao.entity.AssociadoSessaoPauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoSessaoPautaRepository extends CrudRepository<AssociadoSessaoPauta, Integer>,QueryByExampleExecutor<AssociadoSessaoPauta> {
}
