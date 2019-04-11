package edu.gatech.cs2340.SpaceTrader.entity;

/**
 * Difficulty enum that has values that will impact the game
 */
public enum Difficulty {
    BEGINNER (0.5, 2000),
    EASY (1, 2000),
    NORMAL (1, 1000),
    HARD (1.25, 1000),
    IMPOSSIBLE (2, 500);

    private final double priceModifier;
    private final int starterCredits;

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

    @Override public String toString(){
        String name = name();
        String afterFirst = name.substring(1);
        return name.substring(0,1)
            + afterFirst.toLowerCase();}
}
