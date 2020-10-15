package br.com.sicredi.votacao.controller;

import br.com.sicredi.votacao.entity.Pauta;
import br.com.sicredi.votacao.request.PautaRequest;
import br.com.sicredi.votacao.response.PautaResponse;
import br.com.sicredi.votacao.response.ResponseObject;
import br.com.sicredi.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    private static final String PAUTA_CRIACAO_MSG = "Pauta criada com sucesso!";
    private static final String PAUTA_CRIACAO_ERRO = "Erro ao criar pauta! %s";

    @Autowired
    PautaService service;

    @PostMapping(value = {"/",""}, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody
    ResponseEntity<ResponseObject> novaPauta(@RequestBody PautaRequest request, HttpServletRequest servletRequest) throws URISyntaxException {
        ResponseObject responseObject = new ResponseObject();
        try{
            PautaResponse pautaResponse = service.salvar(request);

            responseObject.setStatus(HttpStatus.CREATED.value());
            responseObject.setEntity(pautaResponse);
            responseObject.setMensagem(PAUTA_CRIACAO_MSG);
            return ResponseEntity.created(new URI(servletRequest.getRequestURI())).body(responseObject);
        }catch (Exception e){
            responseObject.setStatus(HttpStatus.BAD_REQUEST.value());
            responseObject.setMensagem(String.format(PAUTA_CRIACAO_ERRO, e.getMessage()));
            return ResponseEntity.badRequest().body(responseObject);
        }
    }

    @GetMapping(value = "/listar")
    public @ResponseBody
    ResponseEntity<List<Pauta>> listar() {
        List<Pauta> pautaResponse = service.listarTodas();
        return ResponseEntity.ok(pautaResponse);
    }

}
