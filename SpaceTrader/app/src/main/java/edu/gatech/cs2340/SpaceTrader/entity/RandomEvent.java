package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;
/**
 * The RandomEvent class
 *
 */
public class RandomEvent {

    private static final Game game = Game.getInstance();
    /**
     * @param type type of random event that shows up
     * @return returns a random event
     *
     */
    public static String doRandomEvent(RandomEventType type){
        String message = "";
        int money;
        Random rand = new Random();
        switch (type) {
            case POLICE:
                //for testing, obviously breaks it
                // Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.NARCOTICS,
                // 1);df7652de1d77a147afb0e6f03277b06ec82fcd95
                //check for narcotics
                if(game.getQuantityOfGood(GoodType.NARCOTICS) != 0){
                    //pay a fine
                    int fine = game.adjustPrice(200);
                    //making sure the fine doesn't make the player's funds negative
                    if (game.getCredits() < fine) {
                        fine = game.getCredits();
                    } 
                    game.setCredits(game.getCredits() - fine);
                    //lose narcotics stash
                    game.removeGood(GoodType.NARCOTICS,
                            game.getQuantityOfGood(GoodType.NARCOTICS));
                    //update message
                    message = String.format("You were stopped by police! They found your stash" +
                            " of narcotics: " + "the drugs were confiscated and you were " +
                            "fined %d credits.",fine);
                } else { //update message
                    message = "You were stopped by police! Fortunately, you didn't have any " +
                            "narcotics on board, so you weren't fined.";
                }
                break;
            case LEAK:
                int damage = rand.nextInt(4);
                //small leak, med leak, and big leak
                if (damage == 1) {
                    message += "There was a small fuel leak, ";
                } else if (damage == 2) {
                    message += "There was a fuel leak, ";
                } else {
                    message += "There was a dangerous fuel leak, ";
                }
                //Engineer containing the damage
                if (game.getEngineerSkill() >= 8) {
                    damage -= 1;
                    message += "but you were able to address the situation properly, only ";
                } else {
                    message += "and you were able to fix it, but not before ";
                }
                //0 = no fuel lost, 1 = 25% lost, 2 = 50%, 3 = 75%
                int fuelLost = (damage * game.getFuel()) / 4;
                game.setFuel(game.getFuel() - fuelLost);
                message += "losing " + String.valueOf(fuelLost) + " fuel.";
                break;
            case PIRATES:
                //Fight pirates, either win and take their money, fend off, or lose and lose money
                money = (game.getCredits() * 2) / 10;
                message += "You were boarded by pirates! ";
                if (game.getFighterSkill() >= 12) {
                    game.setCredits(game.getCredits() + money);
                    message += "You managed to defeat them all, gaining "
                            + String.valueOf(money) + " credits!";
                } else if (game.getFighterSkill() >= 6) {
                    message += "You managed to fend them off, and they fled. " +
                            "That was a close one!";
                } else {
                    game.setCredits(game.getCredits() - money);
                    message += "They managed to defeat you, and stole "
                            + String.valueOf(money) + " credits!";
                }
                break;
            case DEALER:
                //Meet a narcotics dealer offering a sweet price, or tyring to scam you.
                boolean scam = rand.nextBoolean();
                Market dealer = new Market();
                money = game.getCredits() / 10;
                message += "You meet a shady figure who offers you some narcotics. ";
                if (scam) {
                    if (game.getTraderSkill() >= 12) {
                        message += "You notice the scam. However, he doesn't notice your hand " +
                                "in his pocket, leaving you " + String.valueOf(money) +
                                " credits richer.";
                        game.setCredits(game.getCredits() + money);
                    } else if(game.getTraderSkill() >= 6) {
                        message += "His merchandise isn't the most appealing, so you send him" +
                                "out without buying anything.";
                    } else {
                        message += "You eagerly purchase, amazed at the opportunity. " +
                                "Unfortunately, the scam left you " +String.valueOf(money) +
                                " credits poorer.";
                    }
                } else {
                    //only purchase if space
                    if (game.hasShipSpace()) {
                        if (money > 1000) { money = 1000; } //Reign in price in late game
                        message += "Only " + String.valueOf(money) + " credits? With such a " +
                                "great deal, how could you refuse!";
                        dealer.buyItem(GoodType.NARCOTICS, 1, money);
                    } else {
                        message += "Oh how you wish you had space for these premium goods!" +
                                " Unfortunately, your ship's cargo is full.";
                    }
                }
                break;
        }
        return message;
    }
}
