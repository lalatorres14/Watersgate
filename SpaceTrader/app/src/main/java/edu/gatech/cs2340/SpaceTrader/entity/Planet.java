package edu.gatech.cs2340.SpaceTrader.entity;
import android.content.res.Resources;
import java.util.Random;

import java.util.ArrayList;

import edu.gatech.cs2340.SpaceTrader.R;

/** This class represents a single Planet*/
public class Planet {
    private String name;
    ArrayList<String> techLevel = new ArrayList<String>();
    ArrayList<String> planetNames = new ArrayList<String>() {
        {
            add("Tatooine");
            add("Hoth");
            add("Bespin");
            add("Alderaan");
            add("Coruscant");
            add("Kashyyyk ");
            add("Kessel");
            add("Jakku");
            add("Corellia");
            add("Illum");
            add("Crait");
            add("Dathomir");
            add("Ahch-To");
            add("Endor");
            add("Yavin");
            add("Mustafar");
            add("Kamino");
            add("Endor");
            add("Utapau");
            add("Naboo");
            add("Mygeeto");
            add("Mortis");
            add("Polis Massa");
            add("Korriban");
            add("Geonosis");
        }
    };
    ArrayList<String> resources = new ArrayList<String>();
    private String tLevel;
    private String res;
    private Market market;

    public Planet(){
        Random rand = new Random();
        setName(planetNames.get(rand.nextInt(planetNames.size())));
        initResources(resources);
        initTechLevel(techLevel);
        setResources(resources.get(rand.nextInt(13)));
        setTechLevel(techLevel.get(rand.nextInt(8)));
        setMarket(new Market());
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