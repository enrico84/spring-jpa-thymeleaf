package com.capone.thymeleaf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capone.thymeleaf.config.DemoConfiguration;
import com.capone.thymeleaf.entity.Player;
import com.capone.thymeleaf.service.IPlayerService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Front-end sviluppato con "Thymeleaf". 
 * Le risorse statiche come file html, css o js sono cercate da Springboot nelle le cartelle "/static" o "/public".
 * Il file "index.html" viene considerato dal framework come una welcome-page di default che viene servita al path root "host:port/"
 * 
 */
@Slf4j
@Controller
public class PlayerController {
    
    @Autowired
    private Environment environment;

    @Value("${spring.jpa.database-platform}")
    private String databasePlatform;

    @Autowired
    private DemoConfiguration demoConfiguration;
    
    @Autowired
    @Resource(name = "${service.service-player}")
    private IPlayerService playerService;

    /**
     * Chiamare la api http://host:port per avere la lista di players
     * Se la api viene chiamata con "http://host:port/hello?name=enrico" stampa "Hello enrico"
     * Se la api viene chiamata con "http://host:port/hello" stampa "Hello World"
     */
    @GetMapping("/hello")
    @ApiOperation(value = "Hello page")
    public String home(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
	    Model model) {

	log.info("Home method");
	model.addAttribute("name", name);
	return "hello";
    }
    
    /**
     * Recupera la lista dei Player
     */
    @GetMapping("/players")
    @ApiOperation(value = "Lista dei players")
    public String getAllPlayers(Model model) {
	List<Player> players = playerService.players();
	model.addAttribute("players", players);
	return "players"; // Redireziona verso la view players.html con la lista dei players
    }
    
    
    /**
     * Cerca i Player in base ad una stringa passata come parametro
     */
    @RequestMapping("/search")
    @ApiOperation(value = "Cerca i Player in base ad una stringa passata come parametro")
    public String search(@RequestParam String keyword, Model model) {

	List<Player> players = playerService.searchPlayer(keyword);

	model.addAttribute("players", players);
	return "searchpage"; // Redireziona verso la view searchpage.html che contiene il risultato della ricerca tramite keyword
    }
    
    /**
     * Creazione del data-object chiamato "player" (in questo caso -> new Player())
     * che viene passatoview addform.html
     */
    @GetMapping("/addform")
    public ModelAndView showForm(Model model) {
	log.debug("showForm method");
	return new ModelAndView("addform", "player", new Player());
    }

    
    /**
     * Aggiunge un player alla lista dei players
     */
    @PostMapping("/addplayer")
    @ApiOperation(value = "Aggiunta di un player")
    public String addPlayer(@ModelAttribute("player") Player player, BindingResult result) {
	if(result.hasErrors()) {
	    return "addform";
	}
	playerService.addPlayer(player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers" che recupera e visualizza la view players.hmtl
    }

    /**
     * Recupera il  player tramite l'id passandolo poi alla view editform.html
     */
    @RequestMapping(value = "/editplayer/{id}")
    @ApiOperation(value = "Modifica player")
    public ModelAndView editPlayer(@PathVariable Long id, Model model) {
	Player player = playerService.getPlayer(id);
	return new ModelAndView("editform", "player", player); // Redireziona verso la view editform.html per modificare il Player selezionato
    }

    /**
     * Aggiorna il player passato dalla view editform.html
     */
    @PostMapping(value = "/editsave")
    @ApiOperation(value = "Salva modifiche al player")
    public String editSavePlayer(@ModelAttribute("player") Player player, BindingResult result) {
	if(result.hasErrors()) {
	    return "editform";
	}

	playerService.updatePlayer(player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers" che visualizza tutti i players
    }

    /**
     * Elimina un player recuperato tramite il suo id
     */
    @GetMapping(value = "/deleteplayer/{id}")
    @ApiOperation(value = "Elimina player")
    public String deletePlayer(@PathVariable Long id) {
	playerService.deletePlayer(id);
	return "redirect:/players"; // Redireziona verso la Request "getAllPlayers" che visualizza tutti i players
    }

    
    
    @CrossOrigin("*")
    @GetMapping(value = "/is-alive")
    public ResponseEntity<Boolean> isAlive() {

	log.info("############################################################");
	log.info("alive");
	log.info("Profiles: {}", environment.getProperty("spring.profiles"));
	log.info("Database platform: {}", this.databasePlatform);
	log.info("Database url: {}", demoConfiguration.getUrl());
	log.info("Database driver: {}", demoConfiguration.getDriverClassName());
	log.info("Database username: {}", demoConfiguration.getUsername());
	log.info("Database password: {}", demoConfiguration.getPassword());
	System.out.println(environment.getProperty("management.server.address"));
	System.out.println(environment.getProperty("management.server.port"));
	log.info("############################################################");

	return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
