package io.zipcoder.casino;

public class HighRollerPlayer {
    private Integer currentRoll;
    private Player player;
    private Boolean activeRoller;

    public HighRollerPlayer(Player player){
        this.player = player;

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
}
