package com.capone.thymeleaf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource")
public class DemoConfiguration {
    
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    
}
