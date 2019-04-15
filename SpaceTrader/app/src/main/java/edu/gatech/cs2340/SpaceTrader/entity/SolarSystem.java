package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;


/** This class represents a single SolarSystem*/
public class SolarSystem {

    /** The list of planets populating this Solar system*/
    private List<Planet> planetList = new ArrayList<>();
    private final Random rand = new Random();
    private final Map<Integer, Integer> coordinates = new HashMap<>();
    private final Map<Integer, Integer> planetCoordinates;
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
        //planetCoordinates = (HashMap<Integer, Integer>) planetList.get(0).getCoordinates();
        Planet firstPlanet = planetList.get(0);
        planetCoordinates = firstPlanet.getCoordinates();
        coordinates.put(coordinateX,coordinateY);
        String[] names = {"Alpha", "Beta", "Gamma", "Delta", "Zeta", "Theta", "Iota",
                "Nu", "Omicron", "Omega"};
        name = names[nameNumber];
        nameNumber = (nameNumber + 1) % 10;
    }



    //Getters and setters for SolarSystem's attributes
    /**
     * gets the planets the solar system
     *
     * @return the planets in solar system
     */
    public List<Planet> getPlanetList() {return Collections.unmodifiableList(planetList);}
    //public ArrayList<Planet> getPlanetList() {return planetList;}
    /**
     *
     * @return the x coordinate
     */
    public int getCoordinateX() { return coordinateX; }
    /**
     *
     * @return the y coordinate
     */
    public int getCoordinateY() { return coordinateY; }
    /**
     *
     * @return the number of planets in solar system
     */
    public int getPlanetTotal() { return planetTotal; }
    /**
     * sets the planets in the solar system
     * @param planetList the planets in the solar system
     */
    public void setPlanetList(List<Planet> planetList) {
        this.planetList = Collections.unmodifiableList(planetList);
    }
    //public void setPlanetList(ArrayList<Planet> planetList) {this.planetList = planetList;}
    /**
     *
     * @return the coordinates of the Solar System
     */
    public Map<Integer, Integer> getCoordinates()
     {return Collections.unmodifiableMap(coordinates);}
    //public HashMap<Integer, Integer> getCoordinates() {return coordinates;}
    /**
     *
     * @return the planet coordinates
     */
    //public HashMap<Integer, Integer> getPlanetCoordinates() {return planetCoordinates;}
    public Map<Integer, Integer> getPlanetCoordinates() {
        return Collections.unmodifiableMap(planetCoordinates);
    }

    /**
     * sets the amount of planets in the solar system
     *
     * @param planetTotal the number of planets in the solar system
     */
    public void setPlanetTotal(int planetTotal) {
        this.planetTotal = planetTotal;
    }
    /**
     *
     * @return a string that contains the attributes of the planets in the solar system
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        StringBuilder planetsToString = new StringBuilder();
        for (Planet planet : planetList) {
            planetsToString.append("\n");
            planetsToString.append(planet.toString());
        }
        return String.format("Coordinates: (%d,%d)\nNumber of Planets: %d\nPlanets: " +
                planetsToString, coordinateX,coordinateY, planetTotal);
    }
    /**
     *
     * @return name of the Solar System
     */
    public String getName() { return name; }

    /**
     *
     * @return the first planet in the solar system
     */
    public Planet getFirstPlanet() {return planetList.get(0);}

    /**
     *
     * @param i index of planet in solar system
     * @return ith planet in solar system
     */
    public Planet getPlanet(int i) {return planetList.get(i);}

    /**
     *
     * @return size of current solar system
     */
    public int getCurrentSSSize(){return planetList.size(); }
}