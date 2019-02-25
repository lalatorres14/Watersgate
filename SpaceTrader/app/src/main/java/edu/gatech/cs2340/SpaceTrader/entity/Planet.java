package edu.gatech.cs2340.SpaceTrader.entity;
/** This class represents a single Planet*/
public class Planet {
    private String name;
    private int techLevel;

    public void setName(String name){
        this.name = name;
    }
    public void setTechLevel(int techLevel){
        this.techLevel = techLevel;
    }

    public String getName(){
        return name;
    }
    public int getTechLevel(){
        return techLevel;
    }
}
