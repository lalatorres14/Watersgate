package edu.gatech.cs2340.SpaceTrader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.SpaceTrader.entity.Player;
/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would pass through to our ROOM (database) objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class Repository {

    /***
     * This provides a mechanism to generate simple unique numbers to be used as
     * keys in the application
     */
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /**************************************************************/

    /** all the players known in the application */
    private final List<Player> allPlayers;


    /**
     * Make a new Repository object
     */
    public Repository() {
        allPlayers = new ArrayList<>();
    }


    /** add a new player to the system
     *
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        player.setId(Repository.getNextUniqueID());
        allPlayers.add(0, player);
    }

    /**
     * Updates the values stored in a Player
     * @param p the player to update
     */
    public void updatePlayer(Player p) {
        for (Player player: allPlayers) {
            if (player.getId() == p.getId()) {
                player.setDifficulty(p.getDifficulty());
                player.setName(p.getName());
                return;
            }
        }
        Log.d("APP", "Player not found with id = " + p.getId());
    }

    public Player getPlayer(int i){
        return allPlayers.get(i);
    }
}

