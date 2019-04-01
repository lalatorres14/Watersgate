package edu.gatech.cs2340.SpaceTrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a single Player in the database. It is an information holder.
 */
public class Player implements Serializable {
    /** holds difficulty choices. If we decide to have difficulties actually correspond to
     * to something meaningful, we should make this a class instead.
     */
    public static List<String> legalDifficulty = Arrays.asList("Beginner", "Easy", "Normal", "Hard", "Impossible");

    /** a globally unique number for this object */
    private int id;

    /** this player's name */
    private String name;

    /** this player's difficulty mode*/
    private Difficulty diff;

    /** this player's piloting skill level */
    private int pilotSkill;

    /** this player's fighting skill level */
    private int fighterSkill;

    /** this player's trading skill level */
    private int traderSkill;

    /** this player's engineering skill level */
    private int engineerSkill;

    /** this player's total possible skill points */
    private int skillPoints;

    /** placeholder until ship class is created */
    private Ship ship;

    /** this player's current credits */
    private int credits;


    /**
     * Constructor required for making the new object
     *
     * @param nam     name of the player
     * @param diff    difficulty of game
     */
    public Player(String nam, Difficulty diff) {
        name = nam;
        this.diff = diff;
        skillPoints = 16;
        pilotSkill = 0;
        fighterSkill = 0;
        traderSkill = 0;
        engineerSkill = 0;
        ship = new Ship(ShipType.GNAT);
        credits = 1000;
    }

    //Travel methods, helper methods, and refuel method
    public void planetTravel(Planet destination) {
        if (canPlanetTravel(destination)) {
            ship.setFuel(ship.getFuel() - planetDistance(destination));
            Game.getInstance().setCurrentPlanet(destination);
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }
    public int planetDistance(Planet destination) {
        Planet current = Game.getInstance().getCurrentPlanet();
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX, 2) + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    public boolean canPlanetTravel(Planet destination) { return (planetDistance(destination) <= ship.getFuel()); }

    public void systemTravel(SolarSystem destination) {
        if (canSystemTravel(destination)) {
            ship.setFuel(ship.getFuel() - systemDistance(destination));
            Game.getInstance().setCurrentSS(destination);
            Game.getInstance().setCurrentPlanet(destination.getPlanetList().get(0));
            Log.i("Travel", "Successfully traveled to " + destination.getName());
        }
    }
    public int systemDistance(SolarSystem destination) {
        SolarSystem current = Game.getInstance().getCurrentSS();
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX, 2) + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    public boolean canSystemTravel(SolarSystem destination) { return (systemDistance(destination) <= ship.getFuel()); }

    public void refuel() {
        setCredits(getCredits() - getDifficulty().adjustPrice(getShip().getMaxFuel() - getShip().getFuel()));
        getShip().setFuel(getShip().getMaxFuel());
    }

    //Getters and setters are required for accessing the fields from the database

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Difficulty getDifficulty() {
        return diff;
    }

    public int getSkillPoints() { return skillPoints; }

    public int getPilotSkill() { return  pilotSkill; }

    public int getFighterSkill() { return  fighterSkill; }

    public int getTraderSkill() { return  traderSkill; }

    public int getEngineerSkill() { return  engineerSkill; }

    public void fuelExpenditure(int distance) {
        ship.setFuel(ship.getFuel() - distance);
    }
    public String refuel(int cost){
        if (getCredits() < cost) {
            return "cannot purchase fuel";
        }
        int fuelToAdd = ship.getMaxFuel() - ship.getFuel();
        ship.setFuel(ship.getFuel() + fuelToAdd);
        return "Successfully refuelled ship";
    }
    public Ship getShip() {return ship; }

    public int getCredits() {return credits; }

    public void setName(String nam) { name = nam; }

    public void setDifficulty(Difficulty diff) {this.diff = diff; }

    public void setSkillPoints(int skills) {skillPoints = skills; }

    public void setPilotSkill(int pS) {pilotSkill = pS; }

    public void setFighterSkill(int fS) {fighterSkill = fS; }

    public void setTraderSkill(int tS) {traderSkill = tS; }

    public void setEngineerSkill(int eS) {engineerSkill = eS;}

    public void setShip(ShipType shipName) {ship = new Ship(shipName); }

    public void setCredits(int c) {credits = c; }

    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Pilot: %s, Difficulty: %s, id: %d, Skill Points: %d", name, diff, id, skillPoints);
    }
}
