package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public enum RandomEventType {
    POLICE,
    LEAK,
    PIRATES
    ;

    public static RandomEventType getRandomEventType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
