package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

/** This class represents a single SolarSystem*/
public class SolarSystem {

    /** The list of planets populating this Solar system*/
    private ArrayList<Planet> planetList;
    Random rand = new Random();
    HashMap<Integer, Integer> coordinates = new HashMap<>();

    /**The coordinates for the location of this solar system*/
    int coordinateX = rand.nextInt(20);
    int coordinateY = rand.nextInt(20);
    //private int coordinates;

    /** Total number of planets in the SolarSystem*/
    private int planetTotal;


    /**
     * Constructor to create a SolarSystem
     * @param planetList the list of planets that will be in this solar system
     */
    public SolarSystem(ArrayList<Planet> planetList){
        this.planetList = planetList;
        coordinates.put(coordinateX,coordinateY);
        //this.coordinates = coordinates;
        this.planetTotal = planetList.size();
    }



    //Getters and setters for SolarSystem's attributes
    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }

    /*public int getCoordinates() {
        return coordinates;
    }*/

    public int getPlanetTotal() {
        return planetTotal;
    }

    public void setPlanetList(ArrayList<Planet> planetList) {
        this.planetList = planetList;
    }

    /*public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }*/

    public void setPlanetTotal(int planetTotal) {
        this.planetTotal = planetTotal;
    }

    @Override
    public String toString() {
        return String.format("Coordinates: (%d,%d), Number of Planets: %d", coordinateX,coordinateY, planetTotal);
    }
}
