package edu.gatech.cs2340.SpaceTrader.entity;

public class Market {
    private static final Game game = Game.getInstance();

    public void buyItem(GoodType good, int quantity, int unitPrice){
        Game.getInstance().buyGood(good, quantity);
        Game.getInstance().setCredits(Game.getInstance().getCredits() - (quantity * unitPrice));
    }
    public void sellItem(GoodType good, int quantity, int unitPrice){
        //There were problems with selling something with 0 quantity, so I tested this and it
        // worked by just not processing them
        if (quantity != 0) {
            game.sellGood(good, quantity);
            game.setCredits(game.getCredits() + (quantity * unitPrice));
        }
    }
}