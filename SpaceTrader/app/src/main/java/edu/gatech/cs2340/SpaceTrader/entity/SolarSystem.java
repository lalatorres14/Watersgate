package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.Random;
import java.util.ArrayList;
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
     */

    public SolarSystem(){
        Random rand = new Random();
        planetTotal = rand.nextInt(5);
        for(int i = 0; i < planetTotal; i++) {
            planetList.add(new Planet());
        }
        coordinates.put(coordinateX,coordinateY);

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
        String planetsToString = "";
        for (Planet planet : planetList) {
            planetsToString += "\t" + planet.toString();
        }
        return String.format("Coordinates: (%d,%d)\nNumber of Planets: %d\nPlanets: " + planetsToString, coordinateX,coordinateY, planetTotal);
    }
}