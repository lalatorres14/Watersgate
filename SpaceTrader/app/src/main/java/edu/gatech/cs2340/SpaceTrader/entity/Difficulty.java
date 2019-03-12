package edu.gatech.cs2340.SpaceTrader.entity;

public enum Difficulty {
    BEGINNER (0.5, 2000),
    EASY (1, 2000),
    NORMAL (1, 1000),
    HARD (1.25, 1000),
    IMPOSSIBLE (2, 500);

    private double priceModifier;
    private int starterCredits;

    /**
     *
     * @param priceModifier - number by which to change all prices in the game
     * @param starterCredits - number of credits player starts with
     */
    Difficulty(double priceModifier, int starterCredits){
        this.priceModifier = priceModifier;
        this.starterCredits = starterCredits;
    }

    public int adjustPrice(int basePrice){
        return (int) (basePrice * priceModifier);
    }

    public int getStarterCredits(){return starterCredits;}
}
