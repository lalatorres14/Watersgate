package edu.gatech.cs2340.SpaceTrader.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * This class represents a single Player in the database. It is an information holder.
 */
@SuppressWarnings("ALL")
public class Player implements Serializable {
    /** holds difficulty choices. If we decide to have difficulties actually correspond to
     * to something meaningful, we should make this a class instead.
     */
    public static final List<String> legalDifficulty = Arrays.asList(
            "Beginner", "Easy", "Normal", "Hard", "Impossible");

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
     * @param name     name of the player
     * @param diff    difficulty of game
     */
    public Player(String name, Difficulty diff) {
        this.name = name;
        this.diff = diff;
        skillPoints = 16;
        pilotSkill = 0;
        fighterSkill = 0;
        traderSkill = 0;
        engineerSkill = 0;
        ship = new Ship(ShipType.GNAT);
        credits = 1000;
    }
    //Getters and setters are required for accessing the fields from the database
    /**
     * @return the id of the player
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
    /**
     * @return the difficulty of the game
     */
    public Difficulty getDifficulty() {
        return diff;
    }
    /**
     * @return the skill points of the player
     */
    public int getSkillPoints() { return skillPoints; }
    /**
     * @return the pilot skills of the player
     */
    public int getPilotSkill() { return  pilotSkill; }
    /**
     * @return the fighter skills of the player
     */
    public int getFighterSkill() { return  fighterSkill; }
    /**
     * @return the trader skills of the player
     */
    public int getTraderSkill() { return  traderSkill; }
    /**
     * @return the engineering skills of the player
     */
    public int getEngineerSkill() { return  engineerSkill; }

    /**
     * @return the ship object
     */
    public Ship getShip() {return ship; }
    /**
     * @return the credits of the player
     */
    public int getCredits() {return credits; }
    /**
     * sets the name of the player
     * @param  nam the name of player
     */
    public void setName(String nam) { name = nam; }
    /**
     * sets the difficulty of the game
     * @param  diff the difficulty of game
     */
    public void setDifficulty(Difficulty diff) {this.diff = diff; }
    /**
     * sets the skills of the player
     * @param  skills the skills of player
     */
    public void setSkillPoints(int skills) {skillPoints = skills; }
    /**
     * sets the pilot skills of the player
     * @param  pS the pilot skills of player
     */
    public void setPilotSkill(int pS) {pilotSkill = pS; }
    /**
     * sets the fighter skills of the player
     * @param  fS the fighter skills of player
     */
    public void setFighterSkill(int fS) {fighterSkill = fS; }
    /**
     * sets the trader skills of the player
     * @param  tS the trader skills of player
     */
    public void setTraderSkill(int tS) {traderSkill = tS; }
    /**
     * sets the engineer skills of the player
     * @param  eS the engineer skills of player
     */
    public void setEngineerSkill(int eS) {engineerSkill = eS;}
    /**
     * sets the ship of the player
     * @param  shipName the name of the ship of the player
     */
    public void setShip(ShipType shipName) {ship = new Ship(shipName); }
    /**
     * sets the credits of the player
     * @param  c the credits of player
     */
    public void setCredits(int c) {credits = c; }
    /**
     * sets the id of the player
     * @param  id the id of player
     */
    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return a string containing the attributes of the player
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,"Pilot: %s, Difficulty: %s, id: %d, Skill Points: %d",
                name, diff, id, skillPoints);
    }
    /**
     *
     * @param  price the price of the items
     * @return the adjusted price based on the difficulty
     */
    public int adjustPrice(int price) {return diff.adjustPrice(price); }

    //Ship Pass-Through Methods
    /**
     * @return the fuel capacity of the player ship
     */
    public int getFuel(){
        return ship.getFuel();
    }
    /**
     * sets the fuel of the player ship
     * @param  x the fuel to set
     */
    public void setFuel(int x) {
        ship.setFuel(x);
    }
    /**
     * @return the max fuel capacity of the player ship
     */
    public int getMaxFuel(){
        return ship.getMaxFuel();
    }
    /**
     * @return the cargo of the player ship
     */
    public Cargo getCargo() { return ship.getCargo();}
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
    /**
     * @return a string containing the difficulty
     */
    public String getDifficultyName() {return diff.toString();}
    /**
     * @return the name of the player ship
     */
    public String getShipName() {return ship.getName();}
    /**
     * @return return true if the capacity of the player ship
     * is not full, else return false
     */
    public boolean hasShipSpace() {return ship.hasSpace(); }
}
