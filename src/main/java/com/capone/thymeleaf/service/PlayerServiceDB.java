package com.capone.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capone.thymeleaf.entity.Player;
import com.capone.thymeleaf.repository.PlayerRepository;

import lombok.extern.slf4j.Slf4j;

@Service(value = "serviceListaDB")
@Slf4j
public class PlayerServiceDB implements IPlayerService {
    
    @Autowired
    PlayerRepository playerRepository;
    
    @Override
    public List<Player> players() {
	log.info("Classe serviceListaDB --> players");
	return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
	log.info("Classe serviceListaDB --> singolo player");
	return playerRepository.findById(id).orElse(null);
	
    }

    @Override
    public void addPlayer(Player player) {
	log.info("Classe serviceListaDB --> aggiunta del player {}", player);
	playerRepository.save(player);
	
    }

    @Override
    public void updatePlayer(Player player) {
	log.info("Classe serviceListaDB --> aggiornamento del player con id {}", player.getId());
	Player findPlayer =  this.getPlayer(player.getId());
	if(findPlayer != null) {
	    findPlayer.setName(player.getName());
	    findPlayer.setLastName(player.getLastName());
	    findPlayer.setTeam(player.getTeam());
	    findPlayer.setPosition(player.getPosition());
	    playerRepository.save(findPlayer);
	    log.info("Player con id {} aggiornato.", player.getId()); 
	} else 
	    log.info("Player con id {} non trovato.", player.getId());

    }

    @Override
    public void deletePlayer(Long id) {
	log.info("Classe serviceListaDB --> eliminazione del player con id {}", id);
	playerRepository.deleteById(id);
    }

    @Override
    public List<Player> searchPlayer(String keyword) {
	log.info("Classe serviceListaDB --> ricerca di Player con keyword {}", keyword);
	return playerRepository.search(keyword);
    }

}
