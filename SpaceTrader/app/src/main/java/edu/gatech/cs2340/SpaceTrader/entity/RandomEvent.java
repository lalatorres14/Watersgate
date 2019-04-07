package edu.gatech.cs2340.SpaceTrader.entity;

public class RandomEvent {

    private static final Player player = Game.getInstance().getPlayer();

    public static String doRandomEvent(RandomEventType type){
        String message = "";
        switch (type) {
            case POLICE:
                //for testing, obviously breaks it
                // Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.NARCOTICS,
                // 1);

                //check for narcotics
                if(player.getShip().getCargo().getQuantityOfGood(GoodType.NARCOTICS) != 0){
                    //pay a fine
                    int fine = Game.getInstance().getPlayer().getDifficulty().adjustPrice(200);
                    //making sure the fine doesn't make the player's funds negative
                    if (Game.getInstance().getPlayer().getCredits() < fine) {
                        fine = Game.getInstance().getPlayer().getCredits();
                    } 
                    Game.getInstance().getPlayer().setCredits(
                            Game.getInstance().getPlayer().getCredits() - fine);

                    //lose narcotics stash
                    Game.getInstance().getPlayer().getShip().getCargo().removeGood(
                            GoodType.NARCOTICS, Game.getInstance().getPlayer().getShip().getCargo()
                                    .getQuantityOfGood(GoodType.NARCOTICS));
                    //update message
                    message = String.format("You were stopped by police! They found your stash" +
                            " of narcotics: " + "the drugs were confiscated and you were " +
                            "fined %d credits.",fine);
                } else { //update message
                    message = "You were stopped by police! Fortunately, you didn't have any " +
                            "narcotics on board, so you weren't fined.";
                }


        }
        return message;
    }
}
