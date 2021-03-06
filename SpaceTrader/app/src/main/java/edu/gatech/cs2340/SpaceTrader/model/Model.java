package edu.gatech.cs2340.SpaceTrader.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the interface to the domain/business classes from anywhere else in the application
 *
 *
 * This class provides all the interactors for the application.
 *
 * It is a Singleton so that it can be accessed from anywhere in the application
 */

public final class Model {

    /** the data repository */
    private final Repository myRepository;

    private final Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static final Model instance = new Model();

    /**
     * gets the current Model instance
     * @return the instance
     */
    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    /* end Singleton Pattern */

    /**
     * Create a set of interactors to be used by the application
     */
    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
    }

    /**
     * gets player interactors to be used by the application
     * @return player interactors
     */
    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }
}

