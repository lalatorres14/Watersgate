package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A singleton class to keep track of the player and universe
 */
public final class Game {

    Player player;
    List<SolarSystem> universe;
    Planet currentPlanet;
    SolarSystem currentSS;
    static HashMap<Integer, Integer> solarCoordinates;

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
    public static HashMap<Integer, Integer> getSolarCoordinates(){return solarCoordinates;}
    public Player getPlayer(){return player; }
    public List<SolarSystem> getUniverse(){ return universe; }

    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
        solarCoordinates = universe.get(0).getCoordinates();
    }
}
