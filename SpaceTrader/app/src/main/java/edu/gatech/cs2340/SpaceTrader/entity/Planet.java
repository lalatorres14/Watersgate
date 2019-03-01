package edu.gatech.cs2340.SpaceTrader.entity;
import android.content.res.Resources;
import java.util.Random;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.SpaceTrader.R;

/** This class represents a single Planet*/
public class Planet {
    private String name;
    ArrayList<String> techLevel = new ArrayList<String>();
    HashMap<Integer, String> pNames = new HashMap<>();
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
    ArrayList<String> resources = new ArrayList<String>();
    private String tLevel;
    private String res;
    private Market market;

    public Planet(){
        Random rand = new Random();
        for(int i = 0; i < planetNames.size(); i++) {
            pNames.put(i, planetNames.get(i));
        }
        setName(pNames.get(rand.nextInt(planetNames.size())));
        initResources(resources);
        initTechLevel(techLevel);
        setResources(resources.get(rand.nextInt(13)));
        setTechLevel(techLevel.get(rand.nextInt(8)));
        setMarket(new Market(this));
    }

    public ArrayList<String> initTechLevel (ArrayList<String> tech) {
        tech.add("Pre-Agriculture");
        tech.add("Agriculture");
        tech.add("Medieval");
        tech.add("Renaissance");
        tech.add("Early Industrial");
        tech.add("Industrial");
        tech.add("Post-Industrial");
        tech.add("Hi-Tech");
        return tech;
    }

    public ArrayList<String> initResources (ArrayList<String> res) {
        res.add("NOSPECIALRESOURCES ");
        res.add("MINERALRICH");
        res.add("MINERALPOOR");
        res.add("DESERT");
        res.add("LOTSOFWATER");
        res.add("RICHSOIL");
        res.add("POORSOIL");
        res.add("RICHFAUNA");
        res.add("LIFELESS");
        res.add("WEIRDMUSHROOMS");
        res.add("LOTSOFHERBS");
        res.add("ARTISTIC");
        res.add("WARLIKE");
        return res;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTechLevel(String techLevel){
        this.tLevel = techLevel;
    }

    public void setResources(String resources){
        this.res = resources;
    }
    public void setMarket(Market market){
        this.market = market;
    }

    public String getName(){
        return name;
    }
    public String getTechLevel(){
        return tLevel;
    }
    public int getTechLevelInt() {return techLevel.indexOf(tLevel); }
    public String getResources(){
        return res;
    }
    public Market getMarket() {
        return market;
    }
    @Override
    public String toString() {
        return String.format("Name: " + getName() + "\nTech Level: " + getTechLevel() + "\nResources: " + getResources());
    }

}