package edu.gatech.cs2340.SpaceTrader.entity;

/**
 * ShipType enum class
 */
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
    WASP (35,17000,14,14),
    NONE (0,0,0,0)
    ;


    private final int cargoSize;
    private final int price;
    private int tankSize;
    private final int maxTankSize;
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
    /**
     * gets the size of the cargo
     * @return cargo size
     */
    public int getcargoSize(){return cargoSize; }
    /**
     * gets the price of the item
     * @return price
     */
    public int getprice(){return price; }
    /**
     * gets the the fuel capacity
     * @return tank size
     */
    public int getTankSize(){return tankSize; }
    /**
     * changes the amount of fuel left
     * @param size the amount of fuel added or removed from tank
     */
    public void setTankSize(int size){tankSize = size;}
    /**
     * gets the maximum fuel capacity of the ship
     * @return max tank size
     */
    public int getMaxTankSize(){return maxTankSize; }
}

