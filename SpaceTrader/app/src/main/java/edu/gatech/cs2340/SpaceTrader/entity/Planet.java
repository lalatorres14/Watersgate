package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.Random;
/** This class represents a single Planet*/
public class Planet {
    private String name;
    private int techLevel;
    private int resources;
    private Market market;

    public Planet(){
        Random rand = new Random();
        setName("default");
        setResources(rand.nextInt(13));
        setTechLevel(rand.nextInt(8));
        setMarket(new Market());
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTechLevel(int techLevel){
        this.techLevel = techLevel;
    }
    public void setResources(int resources){
        this.resources = resources;
    }
    public void setMarket(Market market){
        this.market = market;
    }

    public String getName(){
        return name;
    }
    public int getTechLevel(){
        return techLevel;
    }
    public int getResources(){
        return resources;
    }
    public Market getMarket() {
        return market;
    }
}
