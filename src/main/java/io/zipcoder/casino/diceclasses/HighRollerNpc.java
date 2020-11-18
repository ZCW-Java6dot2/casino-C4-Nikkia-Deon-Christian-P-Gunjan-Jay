package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Interfaces.HighRollEntrant;
import io.zipcoder.casino.Player;

public class HighRollerNpc implements HighRollEntrant {
   private Player player;
    private Double wallet;
    private Integer currentRoll;
    private Boolean activeRoller;


    public HighRollerNpc(Player player,Double startingMoney) {
        this.player = player;
        this.wallet = startingMoney;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
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

    @Override
    public void setActiveRoller(Boolean yn) {

    }

    @Override
    public Boolean getActiveRoller() {
        return null;
    }
}
