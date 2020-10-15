package br.com.sicredi.votacao.repository;

import br.com.sicredi.votacao.entity.Sessao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao, Integer> {
    List<Sessao> findAllByDataFechamentoIsNull();
}
