package com.rank.casino.service;

import com.rank.casino.exception.PlayerNotFoundException;
import com.rank.casino.exception.InsufficientBalanceException;
import com.rank.casino.model.Player;
import com.rank.casino.model.PlayerTransaction;
import com.rank.casino.repo.PlayerRepo;
import com.rank.casino.repo.PlayerTransactionRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final PlayerTransactionRepo transactionRepo;

    public PlayerService(PlayerRepo playerRepo, PlayerTransactionRepo transactionRepo) {
        this.playerRepo = playerRepo;
        this.transactionRepo = transactionRepo;
    }

    public double getBalanceOfPlayer(Long id) {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isPresent()) {
            return playerRepo.findBalanceOfPlayer(id);
        } else {
            throw new PlayerNotFoundException();
        }
    }

    private PlayerTransaction saveTransaction(double wager, double winning_amount, Player player) {
        var transaction = new PlayerTransaction(wager, winning_amount, player);
        return transactionRepo.save(transaction);
    }

    public PlayerTransaction wagering(Long id, double wager) {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isPresent() && !optionalPlayer.get().isPromoCodeOn()) {
            Player player = optionalPlayer.get();
            double balance = player.getBalance();

            if (balance < wager) throw new InsufficientBalanceException();

            player.setBalance(balance - wager);

            playerRepo.save(player);
            return saveTransaction(wager, 0.0, player);

        } else if (optionalPlayer.isPresent() && optionalPlayer.get().isPromoCodeOn()) {
            Player player = optionalPlayer.get();
            int wagerCount = player.getWagerCount();
            ++wagerCount;
            if (wagerCount >= 5) player.setPromoCodeOn(false);
            player.setWagerCount(wagerCount);
            playerRepo.save(player);
            return saveTransaction(wager, 0.0, player);

        } else {
            throw new PlayerNotFoundException();
        }

    }

    public PlayerTransaction winning(Long id, double wager) {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            double balance = player.getBalance();
            if (balance < wager) throw new InsufficientBalanceException();

            double wining_amount = wager * 40_000;
            balance = balance - wager + wining_amount;

            player.setBalance(balance);
            playerRepo.save(player);

            return saveTransaction(wager, wining_amount, player);
        } else {
            throw new PlayerNotFoundException();
        }
    }

    public List<PlayerTransaction> getPlayerTransaction(String username, int maxNumber) {
        Pageable sortedByTransactionId = PageRequest.of(0, maxNumber, Sort.by("transactionId").descending());
        return transactionRepo.findTenLastPlayerTransactionByUsername(username, sortedByTransactionId);
    }

}