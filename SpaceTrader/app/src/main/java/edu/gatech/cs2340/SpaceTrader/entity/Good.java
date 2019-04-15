package edu.gatech.cs2340.SpaceTrader.entity;

/**
 * The Good class
 *
 */
public class Good {
    private final GoodType type;
    /**
     * The Good constructor
     * @param g type of good
     *
     */
    public Good(GoodType g){
        type = g;
    }
    /**
     * gets the specific good
     * @return a good
     *
     */
    public GoodType getGoodType() { return type; }

    /**
     * @return the good type as a formatted string
     */
    public String getGoodTypeString() {return type.toString();}
    /**
     * calculates the price of a good
     * @param p current planet the player is on
     * @return the calculated price
     */

    public int calculatePrice(Planet p){
        return type.calculatePrice(p.getTechLevelInt(), p.getResources());
    }
    /**
     * checks whether the player can buy good or not
     * @param techLevel the current tech level of the planet
     * @return true if player can buy, else return false
     */
    public boolean canBuy(int techLevel){
        return techLevel >= type.getMtlp();
    }
    /**
     * checks whether the player can sell good or not
     * @param techLevel the current tech level of the planet
     * @return true if player can sell, else return false
     */
    public boolean canSell(int techLevel){
        return techLevel >= type.getMtlu();
    }

}
