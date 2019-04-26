package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;
/**
 * The RandomEvent class
 */
public class RandomEvent {

    private static final Game game = Game.getInstance();

    private RandomEventType type;

    private final int fineNumber = 200;
    private final int ptMax = 12;

    /**
     * constructor for a random event
     * @param type the type of event
     */
    public RandomEvent(RandomEventType type){
        this.type = type;
    }

    private final Random rand = new Random();
    /**
     * @return returns a random event
     */
    public String doRandomEvent(){
        String message = "";
        switch (type) {
            case POLICE:
                message += PoliceEvent(message);
                break;
            case LEAK:
                message += FuelEvent(message);
                break;
            case DISCOVERY:
                message += DiscoveryEvent(message);
                break;
            case CREWMUTINY:
                message += Crew_Mutiny_Event(message);
                break;
            case ILLNESS:
                message += IllnessEvent(message);
                break;
            case PIRATES:
                message += PirateEvent(message);
                break;
            case DEALER:
                message += DealerEvent(message);
                break;
        }
        return message;
    }

    /**
     * The police event
     * @param oldMessage the message to be sent to the player so far
     * @return the completed message
     */
    private String PoliceEvent(String oldMessage) {
        String message = oldMessage;
        if(game.getQuantityOfGood(GoodType.NARCOTICS) != 0){
            //pay a fine
            int fine = game.adjustPrice(fineNumber);
            //making sure the fine doesn't make the player's funds negative
            if (game.getCredits() < fine) {
                fine = game.getCredits();
            }
            game.setCredits(game.getCredits() - fine);
            //lose narcotics stash
            game.removeGood(GoodType.NARCOTICS,
                    game.getQuantityOfGood(GoodType.NARCOTICS));
            //update message
            message += "You were stopped by police! They found your stash of narcotics. The drugs" +
                    " were confiscated and you were fined " + String.valueOf(fine) + " credits.";
        } else { //update message
            message += "You were stopped by police! Fortunately, you didn't have any " +
                    "narcotics on board, so you weren't fined.";
        }
        return message;
    }

    /**
     * The Fuel leak event
     * @param oldMessage the message to be sent to the player so far
     * @return the completed message
     */
    private String FuelEvent(String oldMessage) {
        String message = oldMessage;
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
        return message;
    }

    /**
     * The Pirate attack event
     * @param oldMessage the message to be sent to the player so far
     * @return the completed message
     */
    private String PirateEvent(String oldMessage) {
        String message = oldMessage;
        //Fight pirates, either win and take their money, fend off, or lose and lose money
        int money = (game.getCredits() * 2) / 10;
        message += "You were boarded by pirates! ";
        if (game.getFighterSkill() >= ptMax) {
            game.setCredits(game.getCredits() + money);
            message += "You managed to defeat them all, gaining "
                    + String.valueOf(money) + " credits!";
        } else if (game.getFighterSkill() >= (ptMax / 2)) {
            message += "You managed to fend them off, and they fled. " +
                    "That was a close one!";
        } else {
            game.setCredits(game.getCredits() - money);
            message += "They managed to defeat you, and stole "
                    + String.valueOf(money) + " credits!";
        }
        return message;
    }

    private String Crew_Mutiny_Event(String oldMessage) {
        String message = oldMessage;
        //Fight pirates, either win and take their money, fend off, or lose and lose money
        //int money = (game.getCredits() * 2) / 10;
        message += "Your crew has started a riot!";
        if (game.getFighterSkill() >= ptMax) {
            game.setCredits(game.getCredits() + money);
            message += "You managed to defeat them all and subdue them!";
        } else if (game.getFighterSkill() >= (ptMax / 2)) {
            message += "You managed to fend them off, however they fled. " +
                    "That was a close one!";
        } else {
            //game.setCredits(game.getCredits() - money);
            game.getShip().emptyShip();
            message += "They managed to defeat you, and stole all your goods!";
        }
        return message;
    }

    private String DiscoveryEvent(String oldMessage) {
        String message = oldMessage;
        //int money = (game.getCredits() * 2) / 10;
        message += "You Discovered an abandoned Ship! ";
        if (game.hasShipSpace()) {
            message += "Found some rare firearms! They will make a fine edition to your collection!";
            game.buyGood(GoodType.FIREARMS, 1);
            //game.setCredits(game.getCredits() - (money));
        } else {
            message += "Oh how you wish you had space for these premium goods!" +
                    " Unfortunately, your ship's cargo is full.";
        }

        return message;
    }

    private String IllnessEvent(String oldMessage) {
        String message = oldMessage;
        int money = game.getCredits() / 10;
        message += "Your crew has fallen very ill, so you make a stop to buy medicine. ";
        //only purchase if space
        if (game.hasShipSpace() && (game.getCredits() > money)) {
            message += " You were successfull in buying medicine! Your crew has a full recovery!" ;
            game.buyGood(GoodType.NARCOTICS, 1);
            game.setCredits(game.getCredits() - (money));
        } else {
            message += "Unfortunately, you were unable to acquire the medicine and your crew has perished";
        }
        return message;
    }


    /**
     * The Narcotics Dealer event
     * @param oldMessage the message to be sent to the player so far
     * @return the completed message
     */
    private String DealerEvent(String oldMessage) {
        String message = oldMessage;
        //Meet a narcotics dealer offering a sweet price, or tyring to scam you.
        boolean scam = rand.nextBoolean();
        int money = game.getCredits() / 10;
        message += "You meet a shady figure who offers you some narcotics. ";
        if (scam) {
            if (game.getTraderSkill() >= ptMax) {
                message += "You notice the scam. However, he doesn't notice your hand " +
                        "in his pocket, leaving you " + String.valueOf(money) +
                        " credits richer.";
                game.setCredits(game.getCredits() + money);
            } else if(game.getTraderSkill() >= (ptMax / 2)) {
                message += "His merchandise isn't the most appealing, so you send him" +
                        "out without buying anything.";
            } else {
                message += "You eagerly purchase, amazed at the opportunity. " +
                        "Unfortunately, the scam left you " +String.valueOf(money) +
                        " credits poorer.";
                game.setCredits(game.getCredits() - money);
            }
        } else {
            //only purchase if space
            if (game.hasShipSpace()) {
                if (money > 1000) { money = 1000; } //Reign in price in late game
                message += "Only " + String.valueOf(money) + " credits? With such a " +
                        "great deal, how could you refuse!";
                game.buyGood(GoodType.NARCOTICS, 1);
                game.setCredits(game.getCredits() - (money));
            } else {
                message += "Oh how you wish you had space for these premium goods!" +
                        " Unfortunately, your ship's cargo is full.";
            }
        }
        return message;
    }

    /**
     *
     * @param type type of good to be set
     */
    public void setType(RandomEventType type){this.type = type;}
}
