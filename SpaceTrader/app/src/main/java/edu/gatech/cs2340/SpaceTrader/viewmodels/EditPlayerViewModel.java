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
    @SuppressWarnings("SpellCheckingInspection")
    private PlayerInteractor interactor;

    public EditPlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void updatePlayer(Player player) {
        interactor.updatePlayer(player);
    }

    public void addPlayer(Player player) {
        interactor.addPlayer(player);
    }

    public Player getPlayer(int i) {return interactor.getPlayer(i); }
}
