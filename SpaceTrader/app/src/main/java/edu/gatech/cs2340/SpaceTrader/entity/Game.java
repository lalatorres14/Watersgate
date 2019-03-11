package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.List;

public final class Game {

    Player player;
    List<SolarSystem> universe;

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
}
