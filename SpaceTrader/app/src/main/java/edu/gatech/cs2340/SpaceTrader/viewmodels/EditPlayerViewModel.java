package edu.gatech.cs2340.SpaceTrader.viewmodels;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.model.Model;
import edu.gatech.cs2340.SpaceTrader.model.PlayerInteractor;

/**
 * View model supporting adding and updating an individual student
 */
public class EditPlayerViewModel extends AndroidViewModel {
    private final PlayerInteractor interactor;
    /**
     * allows for the editing of the player
     * @param application is the current app
     */
    public EditPlayerViewModel(@NonNull Application application) {
        super(application);
        Model model = Model.getInstance();
        interactor = model.getPlayerInteractor();
    }
    /**
     * allows for the update of the player
     * @param player the current instance of player
     */
    public void updatePlayer(Player player) {
        interactor.updatePlayer(player);
    }
    /**
     * creates an instance of a player
     * @param player the current instance of player
     */
    public void addPlayer(Player player) {
        interactor.addPlayer(player);
    }
    /**
     * gets an instance of a player
     * @param i the current index
     * @return the current instance of the player
     */
    public Player getPlayer(int i) {return interactor.getPlayer(i); }
}
