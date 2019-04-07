package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/** This class represents a single SolarSystem*/
public class SolarSystem {

    /** The list of planets populating this Solar system*/
    private ArrayList<Planet> planetList = new ArrayList<>();
    private final Random rand = new Random();
    private final HashMap<Integer, Integer> coordinates = new HashMap<>();
    private final HashMap<Integer, Integer> planetCoordinates;
    private static int nameNumber;
    /**The coordinates for the location of this solar system*/
    final int coordinateX = rand.nextInt(20);
    final int coordinateY = rand.nextInt(20);
    //private int coordinates;

    /** Total number of planets in the SolarSystem*/
    private int planetTotal;

    private final String name;

    /**
     * Constructor to create a SolarSystem
     */

    public SolarSystem(){
        Random rand = new Random();
        planetTotal = rand.nextInt(5) + 1;
        for(int i = 0; i < planetTotal; i++) {
            planetList.add(new Planet());
        }
        planetCoordinates = planetList.get(0).getCoordinates();
        coordinates.put(coordinateX,coordinateY);
        String[] names = {"Alpha", "Beta", "Gamma", "Delta", "Zeta", "Theta", "Iota",
                "Nu", "Omicron", "Omega"};
        name = names[nameNumber];
        nameNumber = (nameNumber + 1) % 10;
    }



    //Getters and setters for SolarSystem's attributes
    public ArrayList<Planet> getPlanetList() {
        return planetList;
    }

    public int getCoordinateX() { return coordinateX; }

    public int getCoordinateY() { return coordinateY; }

    public int getPlanetTotal() { return planetTotal; }
    public void setPlanetList(ArrayList<Planet> planetList) { this.planetList = planetList; }
    /*public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }*/
    public HashMap<Integer, Integer> getCoordinates() {return coordinates;}

    public HashMap<Integer, Integer> getPlanetCoordinates() {return planetCoordinates;}


    public void setPlanetTotal(int planetTotal) {
        this.planetTotal = planetTotal;
    }

    @Override
    public String toString() {
        String planetsToString = "";
        for (Planet planet : planetList) {
            planetsToString += "\n" + planet.toString();
        }
        return String.format("Coordinates: (%d,%d)\nNumber of Planets: %d\nPlanets: " +
                planetsToString, coordinateX,coordinateY, planetTotal);
    }
    public String getName() { return name; }
}