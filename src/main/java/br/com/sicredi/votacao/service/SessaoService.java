package br.com.sicredi.votacao.service;

import br.com.sicredi.votacao.entity.AssociadoSessaoPauta;
import br.com.sicredi.votacao.entity.Pauta;
import br.com.sicredi.votacao.entity.Sessao;
import br.com.sicredi.votacao.integration.herokuapp.HerokuAppClient;
import br.com.sicredi.votacao.integration.herokuapp.HerokuAppResponse;
import br.com.sicredi.votacao.repository.SessaoRepository;
import br.com.sicredi.votacao.request.AssociadoSessaoPautaRequest;
import br.com.sicredi.votacao.request.SessaoRequest;
import br.com.sicredi.votacao.response.AssociadoSessaoPautaResponse;
import br.com.sicredi.votacao.response.ContabilizacaoVotosResponse;
import br.com.sicredi.votacao.response.SessaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Efetua as validações prévias, abre e fecha sessão, computa votos
 */
@Service
public class SessaoService {

    private static final String SESSAO_FECHADA_MSG = "A sessão já está fechada!";
    private static final String SESSAO_ERRO_GENERICO_MSG = "Erro ao abrir sessão. %s";
    private static final String SESSAO_ERRO_FECHAR_MSG = "Erro ao fechar sessão. %s";
    private static final String SESSAO_NAO_ENCONTRADA_MSG = "Sessão não encontrada (%d) !.";
    private static final String SESSAO_ASSOCIADO_NAO_INFORMADO_MSG = "Cpf/cnpj não informado!";
    private static final String SESSAO_VOTO_NAO_INFORMADO_MSG = "Voto não informado!";
    private static final String SESSAO_CPF_INVALIDO_MSG = "Cpf inválido!";

//    @Autowired
//    private HerokuAppClient herokuAppClient;

    @Autowired
    private SessaoRepository repository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private AssociadoSessaoPautaService aspService;

    /**
     * Abre uma nova sessão para a pauta informada definindo quanto tempo deverá ficar ativa (Em minutos)
     * @param sessaoRequest
     * @return
     * @throws Exception
     */
    public SessaoResponse abrirSessao(SessaoRequest sessaoRequest) throws Exception {
        Sessao sessao = new Sessao();
        try{
            Calendar cal = Calendar.getInstance();
            sessao.setDataAbertura(new Timestamp(cal.getTimeInMillis()));
            sessao.setDescricao(sessaoRequest.getDescricao());
            sessao.setDuracao(sessaoRequest.getTempo()==null?1:sessaoRequest.getTempo()); //Default 1 min

            Pauta pauta = pautaService.getById(sessaoRequest.getIdPauta());
            sessao.setPauta(pauta);
            repository.save(sessao);
        }catch (Exception e){
            throw new Exception(String.format(SESSAO_ERRO_GENERICO_MSG, e.getMessage()));
        }
        return new SessaoResponse(sessao.getId(), sessao.getDuracao(), sessao.getDescricao(),sessao.getDataAbertura(),null);
    }

    /**
     * Fecha a sessão informada contabilizando e retornando os votos da pauta
     * @param id
     * @return
     * @throws Exception
     */
    public ContabilizacaoVotosResponse fecharSessao(Integer id) throws Exception{
        try{
            Sessao sessao = getById(id);
            fecharSessao(sessao);
            ContabilizacaoVotosResponse response = new ContabilizacaoVotosResponse();
            response.setSessao(sessao);
            response.setVotosSim(aspService.countVotos(sessao,"S"));
            response.setVotosNao(aspService.countVotos(sessao,"N"));
            return response;
        }catch (Exception e){
            throw new Exception(String.format(SESSAO_ERRO_FECHAR_MSG, e.getMessage()));
        }
    }

    /**
     * Tenta fechar a sessão. Caso esta já esteja fechada retorna uma excessão
     * @param sessao
     * @throws Exception
     */
    public void fecharSessao(Sessao sessao) throws Exception{
        if(sessao.getDataFechamento() != null)
            throw new Exception(String.format(SESSAO_ERRO_FECHAR_MSG, "A sessão "+sessao.getId()+" já está fechada!"));

        Calendar cal = Calendar.getInstance();
        sessao.setDataFechamento(new Timestamp(cal.getTimeInMillis()));
        repository.save(sessao);
    }

    /**
     * Registra os votos do associado na pauta da sessão
     * @param request
     * @return
     * @throws Exception
     */
    public AssociadoSessaoPautaResponse computarVoto(AssociadoSessaoPautaRequest request) throws Exception {
        Sessao sessao = getById(request.getIdSessao());
        Pauta pauta = sessao.getPauta(); //pautaService.getById(request.getIdPauta());
        AssociadoSessaoPauta asp = new AssociadoSessaoPauta(pauta, sessao, request.getVoto(), request.getCpfCnpjAssociado());
        valida(asp);
        aspService.salvar(asp);
        return new AssociadoSessaoPautaResponse(asp.getId(), pauta, sessao, request.getCpfCnpjAssociado(), request.getVoto());
    }

    /**
     * Efetua as validações básicas antes de realizar as validações de negócio
     * @param asp
     * @throws Exception
     */
    private void valida(AssociadoSessaoPauta asp) throws Exception {
        StringBuilder erros = new StringBuilder();
        if(sessaoFechada(asp.getSessao())){
            erros.append(SESSAO_FECHADA_MSG);
        }

        if(StringUtils.isEmpty(asp.getCpfCnpjAssociado())){
            erros.append(SESSAO_ASSOCIADO_NAO_INFORMADO_MSG);
        }
//        else{
//            HerokuAppResponse response = herokuAppClient.consultaCpf(Integer.valueOf(asp.getCpfCnpjAssociado()));
//            if(response.getStatus().equals("UNABLE_TO_VOTE")){
//                erros.append(SESSAO_CPF_INVALIDO_MSG);
//            }
//        }

        if(StringUtils.isEmpty(asp.getVoto())){
            erros.append(SESSAO_VOTO_NAO_INFORMADO_MSG);
        }

        if(erros.length() > 0){
            throw new Exception(erros.toString());
        }
        aspService.valida(asp);
    }

    /**
     * Verifica se a sessão está fechada
     * @param sessao
     * @return
     */
    private boolean sessaoFechada(Sessao sessao){
        return sessao.getDataFechamento() != null;
    }

    /**
     * Tenta buscar a sessão por id, caso não exista retorna uma exceção
     * @param id
     * @return
     * @throws Exception
     */
    public Sessao getById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()->
                new Exception(String.format(SESSAO_NAO_ENCONTRADA_MSG, id)));
    }

    public List<Sessao> buscarSessoesExpiradas(){
        return repository.findAllByDataFechamentoIsNull();
    }

    @Scheduled(fixedDelay = 3000)
    public void fecharSessoesExpiradas(){
        System.out.println("Rotina de fechamento de sessão");
        buscarSessoesExpiradas().forEach(sessao -> {
            Calendar cal = Calendar.getInstance();
            Timestamp atual = new Timestamp(cal.getTimeInMillis());
            Timestamp dataAbertura = new Timestamp(sessao.getDataAbertura().getTime());
            cal.setTime(dataAbertura);
            cal.add(Calendar.MINUTE, sessao.getDuracao());

            Timestamp dataLimite = new Timestamp(cal.getTimeInMillis());


            if(atual.after(dataLimite)){
                try{
                    System.out.println("Fechando sessão "+sessao.getId());
                    fecharSessao(sessao);
                }catch(Exception e){
                    System.out.println("Erro ao fechar sessão automaticamente");
                }
            }
        });
    }
}
