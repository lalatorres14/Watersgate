package edu.gatech.cs2340.SpaceTrader.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
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
    private static Map<Integer, Integer> solarCoordinates;
    private Class nextScreen;
    private final Cargo cargo = getCargo();
    private final Ship ship = getShip();

    /*
    a list of things we might also want to keep track of here:
    - current planet and solar system
     */

    private static final Game INSTANCE = new Game();

    private Game() {}
    /**
     * @return an instance of the game
     */
    public static Game getInstance() {
        return INSTANCE;
    }
    /**
     * sets the player of the game
     * @param  p the player of the game
     */
    public void setPlayer(Player p){
        player = p;
    }
    /**
     * sets the universe of the game
     * @param  u the universe of the game
     */
    public void setUniverse(List<SolarSystem> u){
        universe = Collections.unmodifiableList(u);
    }
    /**
     * sets the current planet of the game
     * @param  p the current planet of the game
     */
    public void setCurrentPlanet(Planet p) {currentPlanet = p; }
    /**
     * sets the current solar system of the game
     * @param  s the current solar system of the game
     */
    public void setCurrentSS(SolarSystem s) {currentSS = s; }
    /**
     * @return the current planet
     */
    public Planet getCurrentPlanet() {return currentPlanet; }
    /**
     * @return the current solar system
     */
    public SolarSystem getCurrentSS() {return currentSS; }
    /**
     * @return the coordinates of the solar system
     */
    public static Map<Integer, Integer> getSolarCoordinates() {
        return Collections.unmodifiableMap(solarCoordinates);
    }
    /**
     * @return the player of the game
     */
    public Player getPlayer(){return player; }
    /**
     * @return the player of the game
     */
    public static List<SolarSystem> getUniverse(){ return Collections.unmodifiableList(universe); }

    /**
     *
     * @param i index of solar system in universe
     * @return the log of a solar system
     */
    public static String getUniverseLog(int i) {return universe.get(i).toString(); }

    /**
     *
     * @param i index of a solar system in the universe
     * @return that solar system's name
     */
    public static String getSolarSystemName(int i) {return universe.get(i).getName(); }

    /**
     * sets the next screen of the game
     * @param  c the next screen of the game
     */
    public void setNextScreen(Class c){nextScreen = c;}
    /**
     * @return the next screen of the game
     */
    public Class getNextScreen() {return nextScreen;}
    /**
     * generates the initial universe
     */
    public void generateUniverse(){
        universe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            universe.add(new SolarSystem());
        }
        currentSS = universe.get(0);
        solarCoordinates = currentSS.getCoordinates();
        currentPlanet = currentSS.getFirstPlanet();
    }


    /**
     *
     * @param  price the price of the items
     * @return the adjusted price based on the difficulty
     */
    public int adjustPrice(int price) {return player.adjustPrice(price); }


    //Player Pass-through methods
    /**
     * @return the trader skills of the player
     */
    public int getTraderSkill() {return player.getTraderSkill(); }
    /**
     * @return the pilot skills of the player
     */
    public int getPilotSkill() {return player.getPilotSkill(); }
    /**
     * @return the fighter skills of the player
     */
    public int getFighterSkill() {return player.getFighterSkill(); }
    /**
     * @return the engineering skills of the player
     */
    public int getEngineerSkill() {return player.getEngineerSkill(); }
    /**
     * sets the trader skills of the player
     * @param  value the trader skills of player
     */
    public void setTraderSkill(int value) { player.setTraderSkill(value); }
    /**
     * sets the pilot skills of the player
     * @param  value the pilot skills of player
     */
    public void setPilotSkill(int value) { player.setPilotSkill(value); }
    /**
     * sets the fighter skills of the player
     * @param  value the fighter skills of player
     */
    public void setFighterSkill(int value) { player.setFighterSkill(value); }
    /**
     * sets the engineer skills of the player
     * @param  value the engineer skills of player
     */
    public void setEngineerSkill(int value) { player.setEngineerSkill(value); }
    /**
     * @return the ship of the player
     */
    public Ship getShip() {return player.getShip(); }
    /**
     * sets the ship of the player
     * @param  type the name of the ship of the player
     */
    public void setShip(ShipType type) {player.setShip(type); }
    /**
     * sets the difficulty of the game
     * @param  diff the difficulty of game
     */
    public void setDifficulty(Difficulty diff) {player.setDifficulty(diff);}
    /**
     * @return a string containing the difficulty
     */
    public Difficulty getDifficulty() {return player.getDifficulty();}
    /**
     * @return a string containing the difficulty
     */
    public String getDifficultyName() {return player.getDifficultyName();}
    /**
     * sets the credits of the player
     * @param  credits the credits of player
     */
    public void setCredits(int credits) {player.setCredits(credits); }
    /**
     * @return the credits of the player
     */
    public int getCredits() {return player.getCredits(); }
    /**
     * sets the name of the player
     * @param  name the name of player
     */
    public void setPlayerName(String name) {player.setName(name); }
    /**
     * @return the name of the player
     */
    public String getPlayerName() {return player.getName(); }
    /**
     * updates the cargo after item is bought
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     */
    public void buyGood(GoodType good, int quantity) {ship.buyGood(good, quantity);}
    /**
     * updates the cargo after item is sold
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     */
    public void sellGood(GoodType good, int quantity) {ship.sellGood(good, quantity);}

//    public void refuel() {player.refuel(); }
    /**
     * refuels the ship
     */
    public void refuel() {
        setCredits(getCredits() - adjustPrice(
                getMaxFuel() - getFuel()));
        setFuel(getMaxFuel());
    }

    //public int planetDistance(Planet destination) {return player.planetDistance(destination); }
    /**
     * gets the distance of the planet to travel to
     *
     * @param destination  planet to travel to
     * @return distance of the planet
     */
    public int planetDistance(Planet destination) {
        Planet current = getCurrentPlanet();
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX,
                2) + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    //public boolean canPlanetTravel(Planet dest) {return player.canPlanetTravel(dest);}
    /**
     * determines whether it is possible to travel to another planet or not
     *
     * @param destination   planet to travel to
     * @return true if player can travel to planet, else return false
     */
    public boolean canPlanetTravel(Planet destination) { return (planetDistance(destination) <=
            ship.getFuel()); }

    //public void planetTravel(Planet destination) {player.planetTravel(destination);}
    /**
     * helper method for traveling to another planet
     *
     * @param destination   planet to travel to
     */
    public void planetTravel(Planet destination) {
        if (canPlanetTravel(destination)) {
            ship.setFuel(ship.getFuel() - planetDistance(destination));
            setCurrentPlanet(destination);
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }
    /**
     * gets the distance of the solar system to travel to
     *
     * @param dest  solar system to travel to
     * @return distance of the solar system
     */
    public int systemDistance(SolarSystem dest) {
        return ((int) Math.ceil(Math.sqrt(Math.pow(currentSS.coordinateX - dest.coordinateX,
                2) + Math.pow(currentSS.coordinateY - dest.coordinateY, 2))));
    }
    /**
     * determines whether it is possible to travel to another solar system or not
     *
     * @param destination   solar system to travel to
     * @return true if player can travel to solar system, else return false
     */
    public boolean canSystemTravel(SolarSystem destination) { return (systemDistance(destination)
            <= ship.getFuel()); }
    /**
     * helper method for traveling to another solar system
     *
     * @param destination   solar system to travel to
     */
    public void systemTravel(SolarSystem destination) {
        if (canSystemTravel(destination)) {
            ship.setFuel(ship.getFuel() - systemDistance(destination));
            setCurrentSS(destination);
            setCurrentPlanet(destination.getFirstPlanet());
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }

    //Ship pass-through methods
    /**
     * @return the fuel capacity of the player ship
     */
    public int getFuel(){
        return player.getFuel();
    }
    /**
     * sets the fuel of the player ship
     * @param  x the fuel to set
     */
    public void setFuel(int x) {
        player.setFuel(x);
    }
    /**
     * @return the max fuel capacity of the player ship
     */
    public int getMaxFuel(){
        return player.getMaxFuel();
    }
    /**
     * @return the cargo of the player ship
     */
    public Cargo getCargo() { return player.getCargo();}
    /**
     * @return the name of the player ship
     */
    public String getShipName() {return player.getShipName();}
    /**
     * @return return true if the capacity of the player ship
     * is not full, else return false
     */
    public boolean hasShipSpace() {return player.hasShipSpace(); }

    //Cargo pass-through methods
    /**
     * Add many of the same goods to the cargo at once.
     * @param good   The good to be added
     * @param quantity The number of items
     */
    public void addGood(GoodType good, int quantity){cargo.addGood(good, quantity); }
    /**
     * Remove good from cargo.
     *
     * @param good The item to be removed
     * @param quantity The number of items
     */
    public void removeGood(GoodType good, int quantity) {cargo.removeGood(good,quantity); }
    /**
     * Get the quantity of a particular good currently held in cargo.
     *
     * @param good The good being queried
     * @return The quantity of the good currently held
     */
    public int getQuantityOfGood(GoodType good) {return cargo.getQuantityOfGood(good); }
    /**
     * @return return true if the capacity of the player ship
     * is not full, else return false
     */
    public int getSpace(){ return ship.getSpace(); }



    //SolarSystem Pass-Through Methods
    /**
     *
     * @return name of the Solar System
     */
    public String getCurrentSSName() {return currentSS.getName();}
    /**
     * gets the planets the solar system
     *
     * @return the planets in solar system
     */
    public List<Planet> getCurrentPlanetList() {
        return currentSS.getPlanetList();
    }

    /**
     *
     * @param i index of a solar system in universe
     * @return that solar system
     */
    public static SolarSystem getSolarSystemAtIndex(int i) {
        return universe.get(i);
    }
    /**
     *
     * @return size of planet list
     */
    public int getCurrentPlanetListSize() {
        return currentSS.getCurrentSSSize();
    }
    /**
     * @return size of universe
     */
    public static int getUniverseSize() {return universe.size();}
    //Planet Pass-Through Methods
    /**
     *
     * @return a string that contains the name of the planet
     */
    public String getCurrentPlanetName() {return currentPlanet.getName(); }

    /**
     *
     * @param i index of planet in current solar system
     * @return planet at index i in current solar system
     */
    public Planet getCurrentPlanetAtIndex(int i) {return currentSS.getPlanet(i);}
    /**
     *
     * @return the resources of the planet
     */
    public Resource getCurrentPlanetResources() {return currentPlanet.getResources(); }
    /**
     *
     * @return the name of resources of the planet
     */
    public String getCurrentPlanetRssName() {return currentPlanet.getResourcesName(); }
    /**
     *
     * @return the tech level of the planet
     */
    public TechLevel getTechLevel() {return currentPlanet.getTechLevel();}
    /**
     *
     * @return the planet id of the planet
     */
    public int getCurrentPlanetId() {return currentPlanet.getPlanetId(); }
}
