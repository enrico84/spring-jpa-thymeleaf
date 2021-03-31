package com.capone.thymeleaf.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.capone.demo.controllers"))
            .paths(PathSelectors.any())
            .build();
    }
    
    private ApiInfo apiInfo() {
	return new ApiInfo(
		 "SpringMVC Thymeleaf Players REST API",
	         "SpringMVC Thymeleaf Players REST API Documentation",
	         "1.0",
	         "Terms of service",
	         new Contact("Enrico", "Capone", "enrico8484@gmail.com"),
	         "GPL v2 Licence",
	         "http://www.gnu.org/licenses/old-licenses/gpl-2.0.html",
	         new ArrayList<VendorExtension>()
		);
    }

}
