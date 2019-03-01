package edu.gatech.cs2340.SpaceTrader.entity;

/**
 * The Good class
 *
 */
public class Good {
    private GoodType type;

    public Good(GoodType g){
        type = g;
    }

    public GoodType getGoodType() { return type; }

    public int calculatePrice(Planet p){
        return type.calculatePrice(p.getTechLevelInt(), p.getResources());
    }

}
