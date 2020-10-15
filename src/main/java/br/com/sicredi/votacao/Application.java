package br.com.sicredi.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableFeignClients(basePackages = "br.com.sicredi.votacao.integration")
@EnableJpaRepositories("br.com.sicredi.votacao.repository")
@EnableWebMvc
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
