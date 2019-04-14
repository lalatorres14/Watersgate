package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

/**
 * TechLevel enum that has values that will impact the prices in the market
 */
public enum TechLevel {
    PREAG (0,"Pre-Agriculture"),
    AGRICULTURE (1, "Agriculture"),
    MEDIEVAL (2, "Medieval"),
    RENAISSANCE (3, "Renaissance"),
    EARLYIND (4, "Early Industrial"),
    IND (5, "Industrial"),
    POSTIND (6, "Post-Industrial"),
    HITECH (7, "Hi-Tech");

    private final String formattedName;
    private final int techLevelInt;
    /**
     * Constructor for TechLevel
     * @param i the techLevel number
     * @param f the techLevel name
     */
    TechLevel(int i, String f){
        techLevelInt = i;
        formattedName = f;
    }
    /**
     * gets random tech level
     * @return techLevel values
     */
    public static TechLevel getRandomTechLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    /**
     * @return techLevel name
     */
    @Override public String toString() {return formattedName;}
    /**
     * @return techLevel number
     */
    public int getTechLevelInt() {return techLevelInt;}
}

