package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;
/**
 * The Enum class for Random Events
 *
 */
public enum RandomEventType {
    POLICE,
    CREWMUTINY,
    DISCOVERY,
    ILLNESS,
    LEAK,
    PIRATES,
    DEALER
    ;
    /**
     * @return a random event
     *
     */
    public static RandomEventType getRandomEventType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
