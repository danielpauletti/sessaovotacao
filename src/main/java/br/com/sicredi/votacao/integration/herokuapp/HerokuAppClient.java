package br.com.sicredi.votacao.integration.herokuapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "herokuapp", url = "https://user-info.herokuapp.com")
public interface HerokuAppClient {

    @GetMapping("users/{cpf}")
    HerokuAppResponse consultaCpf(@PathVariable("cpf") Integer cpf);
}
