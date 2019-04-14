package edu.gatech.cs2340.SpaceTrader.entity;

import java.util.Random;

public enum GoodType {
    WATER (0,0,2,30,3,4,"Lots of Water","Desert"),
    FURS (0,0,0,250,10,10,"Rich Fauna","LIFELESS"),
    FOOD (1,0,1,100,5,5,"Rich Soil","POORSOIL"),
    ORE (2,2,3,350,20,10,"Mineral Rich","MINERALPOOR"),
    GAMES (3,1,6,250,-10,5,"Artistic",""),
    FIREARMS (3,1,5,1250,-75,100,"Warlike",""),
    MEDICINE (4,1,6,650,-20,10,"Lots of Herbs",""),
    MACHINES (4,3,5,900,-30,5,"",""),
    NARCOTICS (5,0,5,3500,-125,150,"Weird Mushrooms",""),
    ROBOTS (6,4,7,5000,-150,100,"","")
    ;

    private final int mtlp;
    private final int mtlu;
    private final int ttp;
    private final int basePrice;
    private final int ipl;
    private final int var;
    private final String cr;
    private final String er;
    private final Random rand = new Random();
    //Game game = Game.getInstance();
    /**
     *
     * @param mtlp - Minimum Tech Level to Produce this resource (You can't buy on planets below
     *             this level)
     * @param mtlu - Minimum Tech Level to Use this resource (You can't sell on planets below
     *            this level)
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

    public int calculatePrice(int techLevel, Resource condition){
        //calculate random amount that is +/- var
        int variance = rand.nextInt(var * 2) - var;
        int price = basePrice + (ipl * (techLevel - mtlp)) + variance;
        String conditionStr = condition.toString();
        if(conditionStr.equals(cr)) { price = price / 2; }
        else if(conditionStr.equals(er)) { price = price * 2; }

        //TODO: These make cyclic dependencies
        //Game.getInstance()adjustPrice(price);
        //Trader skill points adjustment
        //price += ((Game.getInstance().getTraderSkill() / 100) * price);
        return price;
    }


    public int getMtlp(){return mtlp; }
    public int getMtlu(){return mtlu; }
    public int getTtp(){return ttp; }
    public int getBasePrice(){return basePrice; }
    public int getIpl(){return ipl; }
    public int getVar(){return var; }
    public String getCr() {return cr;}
    public String getEr() {return er; }
}
