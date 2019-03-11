package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.List;

public final class Game {

    Player player;
    List<SolarSystem> universe;

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

    public Player getPlayer(){return player; }
    public List<SolarSystem> getUniverse(){ return universe; }

    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
    }
}
