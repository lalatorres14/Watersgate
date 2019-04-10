package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {

    private static final Player player = Game.getInstance().getPlayer();

    public Market(){}

    public void buyItem(GoodType good, int quantity, int unitPrice){
        player.getShip().buyGood(good, quantity);
        player.setCredits(player.getCredits() - (quantity * unitPrice));
    }
    public void sellItem(GoodType good, int quantity, int unitPrice){
        //There were problems with selling something with 0 quantity, so I tested this and it
        // worked by just not processing them
        if (quantity != 0) {
            player.getShip().sellGood(good, quantity);
            player.setCredits(player.getCredits() + (quantity * unitPrice));
        }
    }
}