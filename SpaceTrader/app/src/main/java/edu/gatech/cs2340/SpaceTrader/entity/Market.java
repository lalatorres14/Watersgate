package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {

    private Planet p;
    private Player player;
    private Good item;
    public Market(Planet p){
        this.p = p;
    }
    public String buyItem(GoodType good, int quantity){
        this.item = new Good(good);
        if (!player.getShip().hasSpace()) {
            String cargoFull;
            cargoFull = "don't have enough cargo space";
            return cargoFull;
        } else if ((player.getCredits() < item.calculatePrice(p))){
            String credits;
            credits = "don't have enough credits";
            return credits;
        } else{
            player.getShip().buyGood(good, quantity);
            player.setCredits(player.getCredits() - item.calculatePrice(p));
            String success = "Successfully purchased!";
            return success;
        }
    }
    public String sellItem(GoodType good, int quantity){
        if(!player.getShip().hasGood(good)) {
            String sellText; // if player can't sell good
            sellText = "Don't have good in cargo";
            return sellText;
        } else {
            player.getShip().sellGood(good, quantity);
            player.setCredits(player.getCredits() + item.calculatePrice(p));
            String success = "Successfully sold!";
            return success;
        }
    }

    public int calculateSalePrice(Good good, int quantity){
        GoodType g = good.getGoodType();
        return g.calculatePrice(p.getTechLevelInt(), p.getResources()) * quantity;
    }

}
