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
    private PlayerInteractor interactor;

    public EditAddStudentViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void updateStudent(Player player) {
        interactor.updatePlayer(player);
    }

    public void addStudent(Player player) {
        interactor.addPlayer(player);
    }
}
