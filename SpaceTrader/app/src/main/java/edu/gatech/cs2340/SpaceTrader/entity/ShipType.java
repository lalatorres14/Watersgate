package edu.gatech.cs2340.SpaceTrader.entity;


public enum ShipType {
    FLEA (8,1000,20),
    GNAT (15,9000,14),
    FIREFLY (20,3000,17),
    MOSQUITO (15,5000,13),
    BUMBLEBEE (20,7000,15),
    BEETLE (50,11000,14),
    HORNET (20,8000,16),
    GRASSHOPPER (30,9000,15),
    TERMITE (60,15000,13),
    WASP (35,17000,14)
    ;


    private int cargoSize, price, tankSize;
    /**
     *
     * @param cSize - cargo size of the ship
     * @param p - price of the ship
     * @param t - tank of the ship. How far the ship can travel on one tank in parsects
     *
     */
    ShipType(int cSize, int p, int t){
        this.cargoSize = cSize;
        this.price = p;
        this.tankSize = t;
    }

    public int getcargoSize(){return cargoSize; }
    public int getprice(){return price; }
    public int gettankSize(){return tankSize; }
}

