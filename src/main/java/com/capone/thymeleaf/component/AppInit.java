package com.capone.thymeleaf.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppInit {
    
    @PostConstruct
    public void postConstruct() {
	log.info("Stampa di postConstruct eseguita nella classe AppInit.");
    }

}
