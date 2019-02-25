package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;

/** This class represents a single SolarSystem*/
public class SolarSystem {

    /** The list of planets populating this Solar system*/
    private ArrayList<Planet> planetList;

    /**The coordinates for the location of this solar system*/
    private int coordinates;

    /** Total number of planets in the SolarSystem*/
    private int planetTotal;


    /**
     * Constructor to create a SolarSystem
     * @param planetList the list of planets that will be in this solar system
     * @param coordinates the location of this solar system in space
     */
    public SolarSystem(ArrayList<Planet> planetList, int coordinates){
        this.planetList = planetList;
        this.coordinates = coordinates;
        this.planetTotal = planetList.size();
    }



    //Getters and setters for SolarSystem's attributes
    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }

    public int getCoordinates() {
        return coordinates;
    }

    public int getPlanetTotal() {
        return planetTotal;
    }

    public void setPlanetList(ArrayList<Planet> planetList) {
        this.planetList = planetList;
    }

    public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }

    public void setPlanetTotal(int planetTotal) {
        this.planetTotal = planetTotal;
    }

    @Override
    public String toString() {
        return String.format("Coordinates: %d, Number of Planets: %d", coordinates, planetTotal);
    }
}
