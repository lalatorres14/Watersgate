package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {

    private Planet p;
    public Market(Planet p){
        this.p = p;
    }
    public void buyItem(){}
    public void sellItem(){}

    public int calculateSalePrice(Good good, int quantity){
        GoodType g = good.getGoodType();
        return g.calculatePrice(p.getTechLevelInt(), p.getResources()) * quantity;
    }

}
