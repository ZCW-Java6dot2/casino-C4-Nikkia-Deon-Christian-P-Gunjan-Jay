package io.zipcoder.casino.Interfaces;

import io.zipcoder.casino.Player;

public interface GamblingGameInterface {

    void payout(Double amount, Player player);

    void takeBets(Double amount);
}
