package br.com.sicredi.votacao.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("br.com.sicredi.votacao"))
//                .paths(PathSelectors.regex("/.*"))
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Demo Service")
//                .description("Service demonstration...")
//                .version("1.0-SNAPSHOT")
//                .contact(contact())
//                .build();
//    }
//
//    private Contact contact() {
//        return new Contact(
//                "Safetech Informática Ltda",
//                "https://safetech.inf.br",
//                "thebestdevelopers@sft.inf.br");
//    }
//
//}