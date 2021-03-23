package com.capone.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Front-end sviluppato con "Thymeleaf". 
 * Le risorse statiche come file html, css o js sono cercate da Springboot nelle le cartelle "/static" o "/public".
 * Il file "index.html" viene considerato dal framework come una welcome-page di default che viene servita al path root "localhost:8080/"
 * 
 */
@Slf4j
@Controller
public class PlayerController {

    /**
     * Se la api viene chiamata con "http://localhost:8081/hello?name=enrico" stampa "Hello enrico"
     * Se la api viene chiamata con "http://localhost:8081/hello" oppure "http://localhost:8081/" stampa "Hello World"
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
	    Model model) {

	log.info("Home method");
	model.addAttribute("name", name);
	return "hello";
    }

}
