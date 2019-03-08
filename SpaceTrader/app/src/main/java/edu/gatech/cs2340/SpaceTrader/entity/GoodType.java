package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public enum GoodType {
    WATER (0,0,2,30,3,4,"LOTSOFWATER","DESERT"),
    FURS (0,0,0,250,10,10,"RICHFAUNA","LIFELESS"),
    FOOD (1,0,1,100,5,5,"RICHSOIL","POORSOIL"),
    ORE (2,2,3,350,20,10,"MINERALRICH","MINERALPOOR"),
    GAMES (3,1,6,250,-10,5,"ARTISTIC",""),
    FIREARMS (3,1,5,1250,-75,100,"WARLIKE",""),
    MEDICINE (4,1,6,650,-20,10,"LOTSOFHERBS",""),
    MACHINES (4,3,5,900,-30,5,"",""),
    NARCOTICS (5,0,5,3500,-125,150,"WEIRDMUSHROOMS",""),
    ROBOTS (6,4,7,5000,-150,100,"","")
    ;

    private int mtlp, mtlu, ttp, basePrice, ipl, var;
    private String cr, er;
    private Random rand = new Random();
    /**
     *
     * @param mtlp - Minimum Tech Level to Produce this resource (You can't buy on planets below this level)
     * @param mtlu - Minimum Tech Level to Use this resource (You can't sell on planets below this level)
     * @param ttp - Tech Level which produces the most of this item
     * @param basePrice - base price
     * @param ipl - Price increase per tech level
     * @param var - variance
     * @param cr - When this condition is present, the price of this resource is unusually low
     * @param er - When this condition is present, the resource is expensive
     *
     * CR and ER, if not present, are empty strings ""
     */
    GoodType(int mtlp, int mtlu, int ttp, int basePrice, int ipl, int var, String cr, String er){
        this.mtlp = mtlp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.basePrice = basePrice;
        this.ipl = ipl;
        this.var = var;
        this.cr = cr;
        this.er = er;
    }

    public int calculatePrice(int techLevel, String condition){
        //calculate random amount that is +/- var
        int variance = rand.nextInt(var * 2) - var;
        int price = basePrice + (ipl * (techLevel - mtlp)) + variance;

        if(condition.equals(cr)) { price = price / 2; }
        if(condition.equals(er)) { price = price * 2; }

        return price;
    }


    public int getMtlp(){return mtlp; }
    public int getMtlu(){return mtlu; }
    public int getTtp(){return ttp; }
    public int getBasePrice(){return basePrice; }
    public int getIpl(){return ipl; }
    public int getVar(){return var; } //shouldn't need this one probably lol
    public String getCr() {return cr;}
    public String getEr() {return er; }
}
