package edu.gatech.cs2340.SpaceTrader.entity;


/**
 * The Ship class
 *
 */
public class Ship {
    private final ShipType ship;
    private final Cargo cargo;
    /**
     * ship constructor
     * @param s the type of ship
     */
    public Ship(ShipType s) {
        this.ship = s;
        this.cargo = new Cargo(s.getcargoSize());
    }
    /**
     * gets the amount of space available in the ship
     *
     * @return amount of space available in the ship
     */
    public int getSpace(){
        return cargo.getMaxCapacity() - cargo.TotalQuantity();
    }
    /**
     * @return Whether or not the cargo has space for more items
     */
    public boolean hasSpace() {
        return cargo.TotalQuantity() < cargo.getMaxCapacity();
    }

    /**
     * @param good The good being checked
     * @return Whether or not the cargo has a good
     */
    public boolean hasGood(GoodType good) {
        return cargo.getQuantityOfGood(good) != 0;
    }

    /**
     * @param good The good being checked
     * @return get quantity of the particular good
     */
    public int getGoodQuantity(GoodType good){
        return cargo.getQuantityOfGood(good);
    }
    /**
     * updates the cargo after item is bought
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     */
    public void buyGood(GoodType good, int quantity) {
        cargo.addGood(good, quantity);
    }
    /**
     * updates the cargo after item is sold
     *
     * @param good The good being queried
     * @param quantity the number of goods being bought
     */
    public void sellGood(GoodType good, int quantity) {
        cargo.removeGood(good, quantity);
    }
    /**
     * gives the name of the ship
     *
     * @return the name of the ship
     */
    @SuppressWarnings("NullableProblems")
    @Override public String toString(){return ship.toString(); }
    /**
     * gets the amount of fuel available in the ship
     *
     * @return amount of fuel available in the ship
     */
    public int getFuel(){
        return ship.getTankSize();
    }
    /**
     * sets the amount of fuel available in the ship
     * @param x amount of fuel
     */
    public void setFuel(int x) {
        ship.setTankSize(x);
    }
    /**
     * gets the max amount of fuel available in the ship
     *
     * @return  max amount of fuel available in the ship
     */
    public int getMaxFuel(){
        return ship.getMaxTankSize();
    }
    /**
     * gets the cargo
     *
     * @return the cargo from the ship
     */
    public Cargo getCargo() { return cargo;}
    /**
     * gets the name of the ship
     *
     * @return name of the ship
     */
    public String getName() {return toString();}

    public void emptyShip(){cargo.clearCargo();}

}
