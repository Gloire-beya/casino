package com.rank.casino.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String username;
    private double balance;
    private boolean isPromoCodeOn;
    private int wagerCount;

    public Player(String username, double balance, boolean isPromoCodeOn) {
        this.username = username;
        this.balance = balance;
        this.isPromoCodeOn = isPromoCodeOn;
    }
}
