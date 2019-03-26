package edu.gatech.cs2340.SpaceTrader.entity;


public enum ShipType {
    FLEA (8,1000,20,20),
    GNAT (15,9000,14,14),
    FIREFLY (20,3000,17,17),
    MOSQUITO (15,5000,13,13),
    BUMBLEBEE (20,7000,15,15),
    BEETLE (50,11000,14,14),
    HORNET (20,8000,16,16),
    GRASSHOPPER (30,9000,15,15),
    TERMITE (60,15000,13,13),
    WASP (35,17000,14,14)
    ;


    private int cargoSize, price, tankSize, maxTankSize;
    /**
     *
     * @param cSize - cargo size of the ship
     * @param p - price of the ship
     * @param t - tank of the ship. How far the ship can travel on one tank in parsects
     * @param m - max fuel capacity
     *
     */
    ShipType(int cSize, int p, int t, int m){
        this.cargoSize = cSize;
        this.price = p;
        this.tankSize = t;
        this.maxTankSize = m;
    }

    public int getcargoSize(){return cargoSize; }
    public int getprice(){return price; }
    public int getTankSize(){return tankSize; }
    public void setTankSize(int size){tankSize = size;}
    public int getMaxTankSize(){return maxTankSize; }
}

