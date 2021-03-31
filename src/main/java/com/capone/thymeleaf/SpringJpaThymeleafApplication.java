package com.capone.thymeleaf;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringJpaThymeleafApplication {

    public static void main(String[] args) {
	SpringApplication.run(SpringJpaThymeleafApplication.class, args);
    }
    
    @PostConstruct
    public void printPostConstruct() {
	log.info("Stampa in un metodo annotato con @PostConstruct eseguita nella classe di start SpringJpaThymeleafApplication.");
    }

}
