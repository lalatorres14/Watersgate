package edu.gatech.cs2340.SpaceTrader.entity;

/**
 * The Ship class
 *
 */
public class Ship {
    private ShipType ship;
    private Cargo cargo;
    public Ship(ShipType s) {
        this.ship = s;
        this.cargo = new Cargo(s.getcargoSize());
    }

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


    public void buyGood(GoodType good, int quantity) {
        cargo.addGood(good, quantity);
    }

    public void sellGood(GoodType good, int quantity) {
        cargo.removeGood(good, quantity);
    }

    @Override public String toString(){return ship.toString(); }

}
