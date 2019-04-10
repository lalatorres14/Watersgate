package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class to keep track of the player and universe
 */
public final class Game {


    private Player player = new Player("Bob", Difficulty.BEGINNER);
    private static List<SolarSystem> universe;
    private Planet currentPlanet;
    private static SolarSystem currentSS;
    private static HashMap<Integer, Integer> solarCoordinates;
    private Class nextScreen;

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
    public static Map<Integer, Integer> getSolarCoordinates() {
        return Collections.unmodifiableMap(solarCoordinates);
    }
    public Player getPlayer(){return player; }
    public static List<SolarSystem> getUniverse(){ return Collections.unmodifiableList(universe); }
    public void setNextScreen(Class c){nextScreen = c;}
    public Class getNextScreen() {return nextScreen;}

    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
        solarCoordinates = universe.get(0).getCoordinates();
    }
}
