package edu.gatech.cs2340.SpaceTrader.entity;

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
    public void planetTravel(Planet current, Planet destination) {
        if (canPlanetTravel(current, destination)) {
            Game.getInstance().setCurrentPlanet(destination);
            ship.setFuel(ship.getFuel() - planetDistance(current, destination));
        }
    }
    public int planetDistance(Planet current, Planet destination) {
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX, 2)
                + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    /**
     * @param current the current Planet
     * @param destination the destination Planet
     * @return if the destination is within travel distance from the current planet
     */
    public boolean canPlanetTravel(Planet current, Planet destination) {
        return (planetDistance(current, destination) <= ship.getFuel());
    }

    public void systemTravel(SolarSystem current, SolarSystem destination) {
        if (canSystemTravel(current, destination)) {
            Game.getInstance().setCurrentSS(destination);
            ship.setFuel(ship.getFuel() - systemDistance(current, destination));
        }
    }
    public int systemDistance(SolarSystem current, SolarSystem destination) {
        return ( (int) Math.ceil(Math.sqrt(Math.pow(current.coordinateX - destination.coordinateX, 2)
                + Math.pow(current.coordinateY - destination.coordinateY, 2))));
    }
    /**
     * @param current the current SolarSystem
     * @param destination the destination SolarSystem
     * @return if the destination is within travel distance from the current SolarSystem
     */
    public boolean canSystemTravel(SolarSystem current, SolarSystem destination) {
        return (systemDistance(current, destination) <= ship.getFuel());
    }

    public void refuel(){ ship.setFuel(ship.getMaxFuel()); }

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
