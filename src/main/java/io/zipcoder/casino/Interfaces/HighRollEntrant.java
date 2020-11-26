package io.zipcoder.casino.Interfaces;

import io.zipcoder.casino.Player;

public interface HighRollEntrant {
     Player getPlayer();

    Integer getCurrentRoll();

    void setCurrentRoll(Integer currentRoll);

    void setActiveRoller(Boolean yn);

    Boolean getActiveRoller();


}
