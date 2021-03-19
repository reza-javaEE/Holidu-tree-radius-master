package com.holidu.interview.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@EnableSwagger2
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Find tree service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.holidu.interview.assignment"))
                .paths(any())
                .build().apiInfo(new ApiInfo(" services",
                        " A service for searching plants in the circle.",
                        "1.0.0",
                        null,
                        new Contact("Holidu co.", "Holidu.com", "info@Holidu.com"),
                        null,
                        null));
    }


}
