package io.zipcoder.casino.Interfaces;

import io.zipcoder.casino.Player;

public interface GamblingPlayer {

    void playerWager(Double amount, Player player);

    void playerWallet(Double amount);
}
