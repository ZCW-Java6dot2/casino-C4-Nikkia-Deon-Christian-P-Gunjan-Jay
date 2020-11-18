package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Interfaces.HighRollEntrant;
import io.zipcoder.casino.Player;

public class HighRollerNpc implements HighRollEntrant {
   private Player player;
    private Integer currentRoll;
    private Boolean activeRoller;


    public HighRollerNpc(Player player) {
        this.player = player;
    }

    public Double getWallet() {
        return this.player.getBalance();
    }

    public void setWallet(Double wallet) {
        this.player.setBalance(wallet);
    }

    public Player getPlayer() {
        return this.player;
    }

    public Integer getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(Integer currentRoll) {
        this.currentRoll = currentRoll;
    }

    public void setActiveRoller(Boolean yn) {
        this.activeRoller = yn;

    }

    @Override
    public Boolean getActiveRoller() {
        return null;
    }
}
