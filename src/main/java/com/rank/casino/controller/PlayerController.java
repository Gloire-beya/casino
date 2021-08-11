package com.rank.casino.controller;

import com.rank.casino.model.PlayerTransaction;
import com.rank.casino.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/balances/{id}")
    public double getBalanceOfPlayer(@PathVariable Long id) {
        return playerService.getBalanceOfPlayer(id);
    }

    @PostMapping(value = "/wagers/{wager}/{id}")
    public ResponseEntity<PlayerTransaction> wagering(@PathVariable("id") Long id, @PathVariable double wager) {
        return ResponseEntity.ok().body(playerService.wagering(id, wager));
    }

    @PostMapping(value = "/wins/{wager}/{id}")
    public ResponseEntity<PlayerTransaction> winning(@PathVariable Long id, @PathVariable double wager) {
        return ResponseEntity.ok().body(playerService.winning(id, wager));
    }

    @PostMapping(value = "/playertx/{username}")
    public List<PlayerTransaction> getTenLastPlayerTx(@PathVariable String username) {
        return playerService.getPlayerTransaction(username, 10);
    }
}
