package edu.gatech.cs2340.SpaceTrader.entity;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/** This class represents a single PlanetScreen*/
@SuppressWarnings("NullableProblems")
public class Planet {
    private String name;
    private final Random rand = new Random();
    private final Map<Integer, Integer> coordinates = new HashMap<>();
    final int coordinateX = rand.nextInt(20);
    final int coordinateY = rand.nextInt(20);
    private final int planetId;
    private TechLevel tLevel;
    private Resource res;
    /**
     * Constructor to create a planet
     */
    public Planet(){
        Random rand = new Random();
        ArrayList<String> planetNames = new ArrayList<String>() {
            {
                add("Ahch-To");
                add("Alderaan");
                add("Anoat");
                add("Bespin");
                add("Cantonica");
                add("Cato Neimoidia");
                add("Christophsis");
                add("Corellia");
                add("Coruscant");
                add("Crait");
                add("Dagobah");
                add("Dantooine");
                add("Dathomir");
                add("Dromund Kaas");
                add("Endor");
                add("Florrum");
                add("Fondor");
                add("Geonosis");
                add("Hoth");
                add("Ilum");
                add("Iridonia");
                add("Jakku");
                add("Jedha");
                add("Kamino");
                add("Kashyyyk ");
                add("Kessel");
                add("Korriban");
                add("Kuat");
                add("Lotho Minor");
                add("Malastare");
                add("Mandalore");
                add("Maridun");
                add("Mon Cala");
                add("Moraband");
                add("Mortis");
                add("Mustafar");
                add("Muunilinst");
                add("Mygeeto");
                add("Naboo");
                add("Nal Hutta");
                add("N'zoth");
                add("Ord Mantell");
                add("Polis Massa");
                add("Rattatak");
                add("Rishi");
                add("Rodia");
                add("Ryloth");
                add("Saleucami");
                add("Scarif");
                add("Shili");
                add("Sullust");
                add("Tatooine");
                add("Toydaria");
                add("Trandosha");
                add("Umbara");
                add("Utapau");
                add("Vandor-1");
                add("Yavin");
                add("Yavin-4");
            }
        };
        //HashMap<Integer, String> pNames = new HashMap<>();
        SparseArray<String> pNames = new SparseArray<>();
        for(int i = 0; i < planetNames.size(); i++) {
            pNames.put(i, planetNames.get(i));
        }
        planetId = rand.nextInt(planetNames.size());
        setName(pNames.get(planetId));
        res = Resource.getRandomResource();
        tLevel = TechLevel.getRandomTechLevel();
        coordinates.put(coordinateX,coordinateY);
    }
    /**
     * set the name of the planet
     * @param name of the planet
     */
    private void setName(String name){
        this.name = name;
    }
    /**
     * sets the tech level on the planet
     * @param techLevel the tech level of the planet
     */
    public void setTechLevel(TechLevel techLevel){
        this.tLevel = techLevel;
    }
    /**
     * sets the resources on the planet
     * @param resources the resources on the planet
     */
    public void setResources(Resource resources){ this.res = resources; }
    /**
     *
     * @return the coordinates of the planet
     */
    public Map<Integer, Integer> getCoordinates()
     {return Collections.unmodifiableMap(coordinates);}
    //public HashMap<Integer, Integer> getCoordinates() {return coordinates;}
    /**
     *
     * @return a string that contains the name of the planet
     */
    public String getName(){
        return name;
    }
    /**
     *
     * @return the tech level of the planet
     */
    public TechLevel getTechLevel(){
        return tLevel;
    }
    /**
     *
     * @return the tech level of the planet
     */
    public int getTechLevelInt() {return tLevel.getTechLevelInt(); }
    /**
     *
     * @return the resources of the planet
     */
    public Resource getResources(){
        return res;
    }
    /**
     *
     * @return the planet id of the planet
     */
    public int getPlanetId() { return planetId;}
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
     * @return a string that contains the attributes of the planet
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return "Name: " + getName() + "\nTech Level: " + getTechLevel() +
                "\nResources: " + getResources();
    }
    /**
     *
     * @return a string that contains the name of the planet
     */
    //Pass-Through Methods
    public String getResourcesName() {return getName();}
}