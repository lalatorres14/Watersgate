package edu.gatech.cs2340.SpaceTrader.model;

/**
 * Interface for our concrete interactors
 */
abstract class Interactor {

    private final Repository myRepository;

    Interactor(Repository repo) {
        myRepository = repo;
    }

    Repository getRepository() {
        return myRepository;
    }
}
