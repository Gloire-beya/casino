package com.rank.casino;

import com.rank.casino.model.Player;
import com.rank.casino.repo.PlayerRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class CasinoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext
                = SpringApplication.run(CasinoApplication.class, args);

        PlayerRepo playerRepo = configurableApplicationContext.getBean(PlayerRepo.class);

        var john = new Player("John", 8_000, true);
        var caleb = new Player("Caleb", 2_000, true);
        var jane = new Player("Jane", 200, false);

        playerRepo.saveAll(List.of(john, caleb, jane));

    }
}