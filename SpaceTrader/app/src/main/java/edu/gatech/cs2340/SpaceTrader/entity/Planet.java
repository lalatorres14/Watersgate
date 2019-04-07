package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/** This class represents a single PlanetScreen*/
public class Planet {
    private String name;
    ArrayList<String> techLevel = new ArrayList<>();
    private final Random rand = new Random();
    private final HashMap<Integer, Integer> coordinates = new HashMap<>();
    final int coordinateX = rand.nextInt(20);
    final int coordinateY = rand.nextInt(20);
    private final int planetId;
    ArrayList<String> resources = new ArrayList<>();
    private TechLevel tLevel;
    private Resource res;
    private Market market;

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
        HashMap<Integer, String> pNames = new HashMap<>();
        for(int i = 0; i < planetNames.size(); i++) {
            pNames.put(i, planetNames.get(i));
        }
        planetId = rand.nextInt(planetNames.size());
        setName(pNames.get(planetId));
        res = Resource.getRandomResource();
        tLevel = TechLevel.getRandomTechLevel();
        setMarket(new Market());
        coordinates.put(coordinateX,coordinateY);
    }

    private void setName(String name){
        this.name = name;
    }
    public void setTechLevel(TechLevel techLevel){
        this.tLevel = techLevel;
    }

    public void setResources(Resource resources){ this.res = resources; }
    private void setMarket(Market market){
        this.market = market;
    }
    public HashMap<Integer, Integer> getCoordinates() {return coordinates;}
    public String getName(){
        return name;
    }
    public TechLevel getTechLevel(){
        return tLevel;
    }
    public int getTechLevelInt() {return tLevel.getTechLevelInt(); }
    public Resource getResources(){
        return res;
    }
    public Market getMarket() {
        return market;
    }
    public int getPlanetId() { return planetId;}
    public int getCoordinateX() { return coordinateX; }
    public int getCoordinateY() { return coordinateY; }

    @Override
    public String toString() {
        return String.format("Name: " + getName() + "\nTech Level: " + getTechLevel() +
                "\nResources: " + getResources());
    }

}