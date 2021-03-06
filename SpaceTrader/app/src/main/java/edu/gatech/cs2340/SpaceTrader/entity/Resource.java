package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

/**
 * The Resource class
 *
 */
public enum Resource {
    NOSPECIALRESOURCES ("No Special Resources"),
    MINERALRICH ("Mineral Rich"),
    MINERALPOOR ("Mineral Poor"),
    LOTSOFWATER ("Lots of Water"),
    RICHSOIL ("Rich Soil"),
    POORSOIL ("Poor Soil"),
    RICHFAUNA ("Rich Fauna"),
    LIFELESS ("Lifeless"),
    WEIRDMUSHROOMS ("Weird Mushrooms"),
    LOTSOFHERBS ("Lots of Herbs"),
    ARTISTIC ("Artistic"),
    WARLIKE ("Warlike"),
    DESERT ("Desert");

    private final String formattedName;

    Resource(String f){
        formattedName = f;
    }

    @SuppressWarnings("NullableProblems")
    @Override public String toString(){
        return formattedName;
    }
    /**
     *
     * @return returns a random resource
     *
     */
    public static Resource getRandomResource() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
