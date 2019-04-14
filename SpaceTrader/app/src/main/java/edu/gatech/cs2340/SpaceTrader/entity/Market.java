package edu.gatech.cs2340.SpaceTrader.entity;
/**
 * The Market class
 *
 */
public class Market {
    private static final Game game = Game.getInstance();
    /**
     * player buys item from market
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     * @param unitPrice price of the good
     */
    public void buyItem(GoodType good, int quantity, int unitPrice){
        game.buyGood(good, quantity);
        game.setCredits(game.getCredits() - (quantity * unitPrice));
    }
    /**
     * player sells item from market
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     * @param unitPrice price of the good
     */
    public void sellItem(GoodType good, int quantity, int unitPrice){
        //There were problems with selling something with 0 quantity, so I tested this and it
        // worked by just not processing them
        if (quantity != 0) {
            game.sellGood(good, quantity);
            game.setCredits(game.getCredits() + (quantity * unitPrice));
        }
    }
}