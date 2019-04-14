package edu.gatech.cs2340.SpaceTrader.model;

import android.util.Log;

import edu.gatech.cs2340.SpaceTrader.entity.Player;

/**
 * Provide the operations associated with Student Entity
 *
 * Basically an interface between the Model and the UI (view model)
 */
public class PlayerInteractor extends Interactor {
    /**
     * creates an repository
     * @param repo the repository of the current game
     */
    public PlayerInteractor(Repository repo) {
        super(repo);
    }
    private Repository repository = getRepository();
    /**
     * creates an instance of a player
     * @param p the current instance of player
     */
    public void addPlayer (Player p) {
        repository.addPlayer(p);
    }
    /**
     * allows for the update of the player
     * @param p the current instance of player
     */
    public void updatePlayer(Player p) {
        repository.updatePlayer(p);
        Log.d("APP", "Interactor: updating player: " + p);
    }
    /**
     * gets an instance of a player
     * @param i the current index
     * @return the current instance of the player
     */
    public Player getPlayer (int i) { return repository.getPlayer(i); }
}