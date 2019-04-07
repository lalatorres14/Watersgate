package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public class RandomEvent {

    private static Player player = Game.getInstance().getPlayer();

    public static String doRandomEvent(RandomEventType type){
        String message = "";
        Random rand = new Random();
        switch (type) {
            case POLICE:
                //check for narcotics
                if(player.getShip().getCargo().getQuantityOfGood(GoodType.NARCOTICS) != 0){
                    //pay a fine
                    int fine = player.getDifficulty().adjustPrice(200);
                    //making sure the fine doesn't make the player's funds negative
                    if (player.getCredits() < fine) {
                        fine = player.getCredits();
                    } 
                    player.setCredits(player.getCredits() - fine);
                    //lose narcotics stash
                    player.getShip().getCargo().removeGood(GoodType.NARCOTICS,
                            player.getShip().getCargo().getQuantityOfGood(GoodType.NARCOTICS));
                    //update message
                    message = String.format("You were stopped by police! They found your stash of narcotics: " +
                            "the drugs were confiscated and you were fined %d credits.",fine);
                } else { //update message
                    message = "You were stopped by police! Fortunately, you didn't have any narcotics " +
                            "on board, so you weren't fined.";
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
                if (player.getEngineerSkill() >= 8) {
                    damage -= 1;
                    message += "but you were able to address the situation properly, only ";
                } else {
                    message += "and you were able to fix it, but not before ";
                }
                //0 = no fuel lost, 1 = 25% lost, 2 = 50%, 3 = 75%
                int fuelLost = damage * player.getShip().getFuel() / 4;
                player.getShip().setFuel(player.getShip().getFuel() - fuelLost);
                message += "losing " + String.valueOf(fuelLost) + " fuel";
                break;
        }
        return message;
    }
}
