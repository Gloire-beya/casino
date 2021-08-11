package com.rank.casino.repo;

import com.rank.casino.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<Player, Long> {

    @Query("SELECT p.balance FROM Player p WHERE p.id =:id")
    double findBalanceOfPlayer(Long id);
}
