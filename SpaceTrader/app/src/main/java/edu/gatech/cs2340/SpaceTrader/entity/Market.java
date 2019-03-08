package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {

    private Planet p;
    private Player player;
    private Good item;
    public Market(Planet p){
        this.p = p;
    }
    public void buyItem(GoodType good){
        this.item = new Good(good);
        if (!player.getShip().hasSpace()) {
            String cargoFull;
            cargoFull = "don't have enough cargo space";
        } else if ((player.getCredits() < item.calculatePrice(p))){
            String credits;
            credits = "don't have enough credits";
        } else{
            player.getShip().buyGood(good);
        }
    }
    public void sellItem(GoodType good){
        if(!player.getShip().hasGood(good)) {
            String sellText; // if player can't sell good
            sellText = "Don't have good in cargo";
        } else {
            player.getShip().sellGood(good);
        }
    }

    public int calculateSalePrice(Good good, int quantity){
        GoodType g = good.getGoodType();
        return g.calculatePrice(p.getTechLevelInt(), p.getResources()) * quantity;
    }

}
