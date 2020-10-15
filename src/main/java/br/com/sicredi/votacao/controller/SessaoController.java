package br.com.sicredi.votacao.controller;

import br.com.sicredi.votacao.request.AssociadoSessaoPautaRequest;
import br.com.sicredi.votacao.request.SessaoRequest;
import br.com.sicredi.votacao.response.AssociadoSessaoPautaResponse;
import br.com.sicredi.votacao.response.ContabilizacaoVotosResponse;
import br.com.sicredi.votacao.response.ResponseObject;
import br.com.sicredi.votacao.response.SessaoResponse;
import br.com.sicredi.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/sessao")
//@Api("Endpoint for operations with users.")
public class SessaoController {

    private static final String SESSAO_CRIADA_MSG = "Sessão criada com sucesso!";
    private static final String SESSAO_FECHADA_MSG = "Sessão fechada com sucesso!";
    private static final String VOTO_COMPUTADO_MSG = "Voto computado com sucesso!";

    private static final String SESSAO_CRIADA_ERRO = "Erro ao criar sessão! %s";
    private static final String SESSAO_FECHADA_ERRO = "Erro ao fechar sessão! %s";
    private static final String VOTO_COMPUTADO_ERRO = "Erro ao cmputar voto! %s";

    @Autowired
    private SessaoService sessaoService;

    @PostMapping(value = "/nova-sessao", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody ResponseEntity<ResponseObject> novaSessao(@RequestBody SessaoRequest sessaoRequest, HttpServletRequest servletRequest) throws URISyntaxException {
        ResponseObject responseObject = new ResponseObject();
        try{
            SessaoResponse sessaoResponse = sessaoService.abrirSessao(sessaoRequest);

            responseObject.setStatus(HttpStatus.CREATED.value());
            responseObject.setEntity(sessaoResponse);
            responseObject.setMensagem(SESSAO_CRIADA_MSG);
            return ResponseEntity.created(new URI(servletRequest.getRequestURI())).body(responseObject);
        }catch (Exception e){
            responseObject.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObject.setMensagem(String.format(SESSAO_CRIADA_ERRO, e.getMessage()));
            return ResponseEntity.badRequest().body(responseObject);
        }
    }

    @PutMapping(value = "/fechar-sessao/{id}")
    public @ResponseBody ResponseEntity<ResponseObject> fecharSessao(@PathVariable("id") Integer idSessao, HttpServletRequest servletRequest) throws URISyntaxException {
        ResponseObject responseObject = new ResponseObject();
        try{
            ContabilizacaoVotosResponse response = sessaoService.fecharSessao(idSessao);

            responseObject.setStatus(HttpStatus.OK.value());
            responseObject.setEntity(response);
            responseObject.setMensagem(SESSAO_FECHADA_MSG);
            return ResponseEntity.ok(responseObject);
        }catch (Exception e){
            responseObject.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObject.setMensagem(String.format(SESSAO_FECHADA_ERRO, e.getMessage()));
            return ResponseEntity.badRequest().body(responseObject);
        }
    }

    @PostMapping(value = "/computar-voto", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody ResponseEntity<ResponseObject> computarVoto(@RequestBody AssociadoSessaoPautaRequest request, HttpServletRequest servletRequest) throws URISyntaxException {
        ResponseObject responseObject = new ResponseObject();
        try {
            AssociadoSessaoPautaResponse entity = sessaoService.computarVoto(request);
            responseObject.setEntity(entity);
            responseObject.setStatus(HttpStatus.CREATED.value());
            responseObject.setMensagem(VOTO_COMPUTADO_MSG);
            return ResponseEntity.created(new URI(servletRequest.getRequestURI())).body(responseObject);
        }catch(Exception e){
            responseObject.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObject.setMensagem(String.format(VOTO_COMPUTADO_ERRO,e.getMessage()));
            return ResponseEntity.status(400).body(responseObject);
        }
    }
}
