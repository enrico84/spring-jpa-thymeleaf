package com.capone.thymeleaf.service;

import java.util.List;

import com.capone.thymeleaf.entity.Player;

public interface IPlayerService {
    
      List<Player> players();
 
      Player getPlayer(Long id);
       
      void addPlayer(Player player);
       
      void updatePlayer(Player player);

      void deletePlayer(Long id);
      
      List<Player> searchPlayer(String keyword);

}
