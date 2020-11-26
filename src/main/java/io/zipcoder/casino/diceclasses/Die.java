package io.zipcoder.casino.diceclasses;

import java.util.Random;


public class Die {
    private Integer numberOfSides;
    private Random random;

    public Die(Integer numberOfSides){
        this.numberOfSides = numberOfSides;
        this.random = new Random();
    }

    public Integer getNumberOfSides(Integer numberOfSides) {
        return numberOfSides;
    }

    public Integer rollDie() {
        Integer dieValue = random.nextInt(numberOfSides) + 1;

        return dieValue;
    }
}