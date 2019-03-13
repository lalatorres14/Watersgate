package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {

    private Planet p;
    private Good item;
    Player player;
    public Market(Planet p){
        player = Game.getInstance().getPlayer();
        this.p = p;
    }

    public String buyItem(GoodType good, int quantity, int unitPrice){
        player.getShip().buyGood(good, quantity);
        player.setCredits(player.getCredits() - (quantity * unitPrice));
        return "Successfully purchased!";
    }
    public String sellItem(GoodType good, int quantity, int unitPrice){
        //There were problems with selling something with 0 quantity, so I tested this and it worked by just not processing them
        if (quantity != 0) {
            player.getShip().sellGood(good, quantity);
            player.setCredits(player.getCredits() + (quantity * unitPrice));
        }
        return "Successfully sold!";
    }
    //Not using this because it is more convenient to use this method on Goodtype instead of Good (for Owen's InMarket at 3 am purposes anyway)
    public int calculateSalePriceOld(Good good, int quantity){
        GoodType g = good.getGoodType();
        return g.calculatePrice(p.getTechLevelInt(), p.getResources()) * quantity;
    }

    public int calculateSalePrice(GoodType good, int quantity){
        return good.calculatePrice(p.getTechLevelInt(), p.getResources()) * quantity;
    }

}
