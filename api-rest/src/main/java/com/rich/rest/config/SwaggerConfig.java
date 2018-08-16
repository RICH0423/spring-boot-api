package com.rich.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author rich
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.rich.rest.controller"))
          .paths(PathSelectors.any())       
          .build()
          .apiInfo(apiInfo());
//          .securitySchemes(Arrays.asList(apiKey()));
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("D-Bee REST API")
                .description("The REST API for demo").termsOfServiceUrl("")
                .contact(new Contact("RICH LEE", "", "rich04230@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .build();
    }
    
//    private ApiKey apiKey() {
//        return new ApiKey("authkey", "Authorization", "header");
//      }
}
