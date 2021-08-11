package com.rank.casino.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PlayerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private double wager;
    private double winning_amount;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public PlayerTransaction(double wager, double winning_amount, Player player) {
        this.wager = wager;
        this.winning_amount = winning_amount;
        this.player = player;
    }
}
