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
    private String difficulty;

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
    private String ship;

    /** this player's current credits */
    private int credits;

    /**
     * Lookup a difficulty based on its code.  Returns the position of that
     * level in the array.
     *
     * @param code the major to find
     *
     * @return the index of the array that corresponds to the submitted major
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalDifficulty.size()) {
            if (code.equals(legalDifficulty.get(i))) return i;
            ++i;
        }
        return 0;
    }

    /**
     * Constructor required for making the new object
     *
     * @param nam     name of the player
     * @param diff    difficulty of game
     */
    public Player(String nam, String diff) {
        name = nam;
        difficulty = diff;
        skillPoints = 16;
        pilotSkill = 0;
        fighterSkill = 0;
        traderSkill = 0;
        engineerSkill = 0;
        ship = "GNAT";
        credits = 1000;
    }

    //Getters and setters are required for accessing the fields from the database

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getSkillPoints() { return skillPoints; }

    public int getPilotSkill() { return  pilotSkill; }

    public int getFighterSkill() { return  fighterSkill; }

    public int getTraderSkill() { return  traderSkill; }

    public int getEngineerSkill() { return  engineerSkill; }

    public String getShip() {return ship; }

    public int getCredits() {return credits; }


    public void setName(String nam) { name = nam; }

    public void setDifficulty(String diff) {difficulty = diff; }

    public void setSkillPoints(int skills) {skillPoints = skills; }

    public void setPilotSkill(int pS) {pilotSkill = pS; }

    public void setFighterSkill(int fS) {fighterSkill = fS; }

    public void setTraderSkill(int tS) {traderSkill = tS; }

    public void setEngineerSkill(int eS) {engineerSkill = eS;}

    public void setShip(String shipName) {ship = shipName; }

    public void setCredits(int c) {credits = c; }

    //Need a way to set the id from the database
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Pilot: %s, Difficulty: %s, id: %d, Skill Points: %d", name, difficulty, id, skillPoints);
    }
}
