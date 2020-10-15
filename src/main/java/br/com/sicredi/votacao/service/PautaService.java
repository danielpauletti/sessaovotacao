package br.com.sicredi.votacao.service;

import br.com.sicredi.votacao.entity.Pauta;
import br.com.sicredi.votacao.repository.PautaRepository;
import br.com.sicredi.votacao.request.PautaRequest;
import br.com.sicredi.votacao.response.PautaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Efetua validações e persiste dados relacionados a pauta
 */
@Service
public class PautaService {

    private static final String PAUTA_NAO_ENCONTRADA_MSG = "Pauta não encontrada (%d) !";
    private static final String PAUTA_ERRO_GENERICO_MSG = "Erro ao salvar pauta ('%s'). %s";

    @Autowired
    PautaRepository repository;

    /**
     * Cria uma nova pauta.
     * @param request
     * @return
     * @throws Exception
     */
    public PautaResponse salvar(PautaRequest request) throws Exception{
        Pauta pauta = new Pauta();
        try{
            pauta.setDescricao(request.getDescricao());
            repository.save(pauta);
        }catch(Exception e){
            throw new Exception(String.format(PAUTA_ERRO_GENERICO_MSG,request.getDescricao(), e.getMessage()));
        }

        return new PautaResponse(pauta.getId(), pauta.getDescricao());
    }

    /**
     * Lista as pautas existentes
     * @return
     */
    public List<Pauta> listarTodas(){
        return StreamSupport
                .stream(repository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    /**
     * Tenta buscar uma pauta por id, se não encontrar retorna uma exceção
     * @param id
     * @return
     * @throws Exception
     */
    public Pauta getById(Integer id) throws Exception{
        return repository.findById(id).orElseThrow(()->
                new Exception(String.format(PAUTA_NAO_ENCONTRADA_MSG, id)));
    }
}
