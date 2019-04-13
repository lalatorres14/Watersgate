package edu.gatech.cs2340.SpaceTrader.model;

import android.util.Log;

import edu.gatech.cs2340.SpaceTrader.entity.Player;

/**
 * Provide the operations associated with Student Entity
 *
 * Basically an interface between the Model and the UI (view model)
 */
public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }
    Repository repository = getRepository();

    public void addPlayer (Player p) {
        repository.addPlayer(p);
    }

    public void updatePlayer(Player p) {
        repository.updatePlayer(p);
        Log.d("APP", "Interactor: updating player: " + p);
    }

    public Player getPlayer (int i) { return repository.getPlayer(i); }
}