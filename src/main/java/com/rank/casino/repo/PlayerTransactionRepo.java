package com.rank.casino.repo;

import com.rank.casino.model.PlayerTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerTransactionRepo extends PagingAndSortingRepository<PlayerTransaction,Long> {

    @Query("SELECT ptx " +
            "FROM PlayerTransaction ptx " +
            "WHERE ptx.player.username=:username")
    List<PlayerTransaction> findTenLastPlayerTransactionByUsername(String username, Pageable pageable);
}
