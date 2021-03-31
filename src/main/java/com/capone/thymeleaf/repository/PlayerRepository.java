package com.capone.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capone.thymeleaf.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
    @Query(value = "SELECT p FROM Player p WHERE p.name LIKE '%' || :keyword || '%'"
	    + " OR p.lastName LIKE '%' || :keyword || '%' OR p.team LIKE '%' || :keyword || '%'"
	    + " OR p.position LIKE '%' || :keyword || '%'")
    public List<Player> search(@Param("keyword") String keyword);

//    @Query(value = "SELECT p.name, p.last_name, p.team, p.position FROM Player p WHERE p.name LIKE '%':keyword'%'"
//	    + " OR p.last_name LIKE '%':keyword'%' OR p.team LIKE '%':keyword'%'"
//	    + " OR p.position LIKE '%':keyword'%'", nativeQuery = true)
//    public List<Player> search(@Param("keyword") String keyword);

    

}
