package com.capone.thymeleaf.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.capone.thymeleaf.entity.Player;
import com.capone.thymeleaf.repository.PlayerRepository;

@Component 	//Scommentare per attivare il Database in-memory H2
@Order(value = 1)
public class H2DataLoader implements CommandLineRunner {
    
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
	playerRepository.deleteAll();
	playerRepository.save(new Player(new Long(1), "Ciccio", "Caputo", "Sassuolo", "A"));
	playerRepository.save(new Player(new Long(2), "Gigio", "Donnarumma", "Milan", "P"));
	playerRepository.save(new Player(new Long(3), "Cristiano", "Ronaldo", "Juventus", "A"));
	
    }

}
