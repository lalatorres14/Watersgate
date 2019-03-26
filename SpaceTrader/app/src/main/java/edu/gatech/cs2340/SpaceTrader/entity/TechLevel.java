package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public enum TechLevel {
    PREAG (0,"Pre-Agriculture"),
    AGRICULTURE (1, "Agriculture"),
    MEDIEVAL (2, "Medieval"),
    RENAISSANCE (3, "Renaissance"),
    EARLYIND (4, "Early Industrial"),
    IND (5, "Industrial"),
    POSTIND (6, "Post-Industrial"),
    HITECH (7, "Hi-Tech");

    private String formattedName;
    private int techLevelInt;

    TechLevel(int i, String f){
        techLevelInt = i;
        formattedName = f;
    }

    public static TechLevel getRandomTechLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    @Override public String toString() {return formattedName;}
    public int getTechLevelInt() {return techLevelInt;}
}

