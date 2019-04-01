package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public enum RandomEventType {
    POLICE
    ;


    public static RandomEventType getRandomEventType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
