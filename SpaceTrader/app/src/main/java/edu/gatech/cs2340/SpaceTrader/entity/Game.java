package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class to keep track of the player and universe
 */
public final class Game {

    private Player player;
    private List<SolarSystem> universe;
    private Planet currentPlanet;
    private SolarSystem currentSS;

    /*
    a list of things we might also want to keep track of here:
    - current planet and solar system
     */

    private static final Game INSTANCE = new Game();

    private Game() {}

    public static Game getInstance() {
        return INSTANCE;
    }

    public void setPlayer(Player p){
        player = p;
    }

    public void setUniverse(List<SolarSystem> u){
        universe = u;
    }

    public void setCurrentPlanet(Planet p) {currentPlanet = p; }

    public void setCurrentSS(SolarSystem s) {currentSS = s; }

    public Planet getCurrentPlanet() {return currentPlanet; }

    public SolarSystem getCurrentSS() {return currentSS; }

    public Player getPlayer(){return player; }
    public List<SolarSystem> getUniverse(){ return universe; }

    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
    }
}
