package com.capone.thymeleaf.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.capone.thymeleaf.entity.Player;

import lombok.extern.slf4j.Slf4j;

@Service(value = "serviceLista")
@Slf4j
public class PlayerService implements IPlayerService{
    
    List<Player> players = new ArrayList<>(Arrays.asList(
	    new Player(new Long(1), "Gianluigi", "Buffon", "Juventus", "P"),
	    new Player(new Long(2), "Gigio", "Donnarumma", "Milan", "P"),
	    new Player(new Long(3), "Lorenzo", "Insigne", "Napoli", "A")
	    ));
	
    
    public List<Player> players(){
	return players;
    }
    
    public Player getPlayer(Long id) {
	log.info("Troviamo il player con id {}", id);
	return players.
		stream().
		filter(p -> p.getId().intValue() == id.intValue()).
		findFirst().orElse(null);	
	
    }
    
    public void addPlayer(Player player) {
	log.info("Aggiungiamo il calciatore {}", player);
	if(players.size() == 0) {
	    player.setId(new Long(1));
	 } else {
	     long lastPlayer = players.get(players.size() - 1).getId();
	     player.setId(lastPlayer + 1);
	 }
	players.add(player);
	
    }
    
    public void updatePlayer(Player selectedPlayer) {
	log.info("Update del calciatore con id {}", selectedPlayer.getId());
	for(int i=0; i <= players.size() ; i++) {
	    
	    Player p = players.get(i);    
	    if(p != null && p.getId().intValue() == selectedPlayer.getId().intValue()) {
		players.set(i, selectedPlayer);
		break;
	    }
	}
    }

    public void deletePlayer(Long id) {
	log.info("Eliminiamo il calciatore con id {}", id);
	Player player = players.stream().filter(p -> p.getId().intValue() == id.intValue()).findFirst().orElse(null);
	boolean removed = false;
	
	if(player != null) {
	 removed =  players.remove(player);
	 log.debug("Player {} {} rimosso: {}" , player.getName(), player.getLastName(), removed);
	} else {
	    log.debug("Ops...player non trovato");
	}
	
	
    }
    
    public List<Player> searchPlayer(String keyword) {
	
	log.info("Troviamo il calciatore con keyword {}", keyword);
	
	Predicate<Player> findName = player -> (player.getName().toLowerCase().contains(keyword.toLowerCase()));
	Predicate<Player> findLastName = player -> (player.getLastName().toLowerCase().contains(keyword.toLowerCase()));
	Predicate<Player> findTeam = player -> (player.getTeam().toLowerCase().contains(keyword.toLowerCase()));
	Predicate<Player> findPosition = player -> (player.getPosition().toLowerCase().contains(keyword.toLowerCase()));
	
	return  players.stream().filter(findName.or(findLastName).or(findTeam).or(findPosition)).collect(Collectors.toList());
	
    }

}
