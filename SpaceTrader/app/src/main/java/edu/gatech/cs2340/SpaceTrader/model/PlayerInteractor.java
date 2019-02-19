package edu.gatech.cs2340.SpaceTrader.model;

import android.util.Log;

import java.util.List;

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

    public void addPlayer (Player p) {
        getRepository().addPlayer(p);
    }

    public void updatePlayer(Player p) {
        getRepository().updatePlayer(p);
        Log.d("APP", "Interactor: updating player: " + p);
    }


}