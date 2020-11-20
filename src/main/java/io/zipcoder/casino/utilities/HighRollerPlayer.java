package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Interfaces.HighRollEntrant;
import io.zipcoder.casino.Player;

public class HighRollerPlayer implements HighRollEntrant {
    private Integer currentRoll;
    private Player player;
    private Boolean activeRoller;

    public HighRollerPlayer(Player player){
        this.player = player;
        this.activeRoller = true;

    }

    public Player getPlayer() {
        return player;
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
        return activeRoller;
    }
}
