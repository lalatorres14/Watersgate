package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.EnumMap;
public class Cargo {
    private int maxCapacity;
    private  EnumMap<GoodType, Integer> cargo;
    /**
     * Class constructor
     *
     * @param cargoSize The capacity of the cargo.
     */
    public Cargo(int cargoSize){
        maxCapacity = cargoSize;
        cargo = new EnumMap<GoodType, Integer>(GoodType.class);
    }

    /**
     * @return The quantity of all items in the Cargo.
     */
    public int TotalQuantity() {
        int quantity = 0;
        for(GoodType good : cargo.keySet()) {
            quantity += getQuantityOfGood(good);
        }
        return quantity;
    }

    /**
     * Get the quantity of a particular good currently held in cargo.
     *
     * @param good The good being queried
     * @return The quantity of the good currently held
     */
    public int getQuantityOfGood(GoodType good) {
        if(cargo.containsKey(good)) {
            return cargo.get(good);
        }
        return 0;
    }

    /**
     * Add many of the same goods to the cargo at once.
     *
     * @param good   The good to be added
     */
    public void addGood(GoodType good) {
        boolean isNew = !cargo.keySet().contains(good);
        if(isNew) {
            cargo.put(good, 0);
        } else {
            cargo.put(good, cargo.get(good) + 1);
        }
    }


    /**
     * Remove good from cargo.
     *
     * @param good The item to be removed
     * @return whether or not the item was successfully removed
     */
    public boolean removeGood(GoodType good) {
        if(cargo.keySet().contains(good)) {
            if(cargo.get(good) == 1) {
                cargo.remove(good);
            } else {
                cargo.put(good, cargo.get(good) - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }


}

