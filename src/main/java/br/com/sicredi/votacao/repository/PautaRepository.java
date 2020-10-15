package br.com.sicredi.votacao.repository;

import br.com.sicredi.votacao.entity.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Integer> {
}
