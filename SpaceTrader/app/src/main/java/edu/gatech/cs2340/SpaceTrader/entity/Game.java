package edu.gatech.cs2340.SpaceTrader.entity;

import android.util.Log;

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
    private final Cargo cargo = getCargo();
    private final Ship ship = getShip();

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
    public static List<SolarSystem> getUniverse(){
        return Collections.unmodifiableList(universe);
    }
    public void setNextScreen(Class c){nextScreen = c;}
    public Class getNextScreen() {return nextScreen;}

    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
        currentSS = universe.get(0);
        solarCoordinates = currentSS.getCoordinates();
        currentPlanet = currentSS.getPlanetList().get(0);
    }



    public int adjustPrice(int price) {return player.adjustPrice(price); }


    //Player Pass-through methods
    public int getTraderSkill() {return player.getTraderSkill(); }
    public int getPilotSkill() {return player.getPilotSkill(); }
    public int getFighterSkill() {return player.getFighterSkill(); }
    public int getEngineerSkill() {return player.getEngineerSkill(); }

    public void setTraderSkill(int value) { player.setTraderSkill(value); }
    public void setPilotSkill(int value) { player.setPilotSkill(value); }
    public void setFighterSkill(int value) { player.setFighterSkill(value); }
    public void setEngineerSkill(int value) { player.setEngineerSkill(value); }

    public Ship getShip() {return player.getShip(); }
    public void setShip(ShipType type) {player.setShip(type); }

    public void setDifficulty(Difficulty diff) {player.setDifficulty(diff);}
    public Difficulty getDifficulty() {return player.getDifficulty();}
   public String getDifficultyName() {return player.getDifficultyName();}

    public void setCredits(int credits) {player.setCredits(credits); }
    public int getCredits() {return player.getCredits(); }

    public void setPlayerName(String name) {player.setName(name); }
    public String getPlayerName() {return player.getName(); }

    public void buyGood(GoodType good, int quantity) {ship.buyGood(good, quantity);}
    public void sellGood(GoodType good, int quantity) {ship.sellGood(good, quantity);}
//    public void refuel() {player.refuel(); }
    public void refuel() {
        setCredits(getCredits() - adjustPrice(
                getMaxFuel() - getFuel()));
        setFuel(getMaxFuel());
    }

    //public int planetDistance(Planet destination) {return player.planetDistance(destination); }
    public int planetDistance(Planet destination) {
        Planet current = getCurrentPlanet();
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX,
                2) + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    //public boolean canPlanetTravel(Planet dest) {return player.canPlanetTravel(dest);}
    public boolean canPlanetTravel(Planet destination) { return (planetDistance(destination) <=
            ship.getFuel()); }

    //public void planetTravel(Planet destination) {player.planetTravel(destination);}
    public void planetTravel(Planet destination) {
        if (canPlanetTravel(destination)) {
            ship.setFuel(ship.getFuel() - planetDistance(destination));
            setCurrentPlanet(destination);
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }
//    public int systemDistance(SolarSystem dest) {return player.systemDistance(dest); }
    public int systemDistance(SolarSystem dest) {
        return ((int) Math.ceil(Math.sqrt(Math.pow(currentSS.coordinateX - dest.coordinateX,
                2) + Math.pow(currentSS.coordinateY - dest.coordinateY, 2))));
    }
//    public boolean canSystemTravel(SolarSystem dest) {return player.canSystemTravel(dest); }
    public boolean canSystemTravel(SolarSystem destination) { return (systemDistance(destination)
            <= ship.getFuel()); }
//    public void systemTravel(SolarSystem dest) {player.systemTravel(dest);}
    public void systemTravel(SolarSystem destination) {
        if (canSystemTravel(destination)) {
            ship.setFuel(ship.getFuel() - systemDistance(destination));
            setCurrentSS(destination);
            setCurrentPlanet(destination.getPlanetList().get(0));
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }



    //Ship pass-through methods
    public int getFuel(){
        return player.getFuel();
    }
    public void setFuel(int x) {
        player.setFuel(x);
    }
    public int getMaxFuel(){
        return player.getMaxFuel();
    }

    public Cargo getCargo() { return player.getCargo();}
    public String getShipName() {return player.getShipName();}
    public boolean hasShipSpace() {return player.hasShipSpace(); }

    //Cargo pass-through methods
    public void addGood(GoodType good, int quantity){cargo.addGood(good, quantity); }
    public void removeGood(GoodType good, int quantity) {cargo.removeGood(good,quantity); }
    public int getQuantityOfGood(GoodType good) {return cargo.getQuantityOfGood(good); }
    public int getSpace(){ return ship.getSpace(); }



    //SolarSystem Pass-Through Methods
    public String getCurrentSSName() {return currentSS.getName();}
    public ArrayList<Planet> getCurrentPlanetList() {return currentSS.getPlanetList();}

    //Planet Pass-Through Methods
    public String getCurrentPlanetName() {return currentPlanet.getName(); }
    // getCurrentPlanetResources() {return currentPlanet.getResources(); }
    public String getCurrentPlanetRssName() {return currentPlanet.getResourcesName(); }
    public TechLevel getTechLevel() {return currentPlanet.getTechLevel();}
    public int getCurrentPlanetId() {return currentPlanet.getPlanetId(); }
}
