package edu.gatech.cs2340.SpaceTrader.model;

/**
 * Interface for our concrete interactors
 */
public abstract class Interactor {

    private Repository myRepository;

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Repository getRepository() {
        return myRepository;
    }
}
