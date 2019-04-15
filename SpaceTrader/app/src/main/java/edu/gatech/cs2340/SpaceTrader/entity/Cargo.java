package edu.gatech.cs2340.SpaceTrader.entity;
import java.util.EnumMap;
import java.util.Set;

/**
 * Class regarding the cargo holdings and size for the player's ship
 */
public class Cargo{
    private final int maxCapacity;
    private final EnumMap<GoodType, Integer> cargo;
    /**
     * Class constructor
     *
     * @param cargoSize The capacity of the cargo.
     */
    public Cargo(int cargoSize){
        maxCapacity = cargoSize;
        cargo = new EnumMap<>(GoodType.class);
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
            if (cargo.get(good) != null) {
                return cargo.get(good);
            }
        }
        return 0;
    }

    /**
     * Add many of the same goods to the cargo at once.
     * @param good   The good to be added
     * @param quantity The number of items
     */
    public void addGood(GoodType good, int quantity) {
        Set<GoodType> keySet = cargo.keySet();
        boolean isNew = !keySet.contains(good);
        if(isNew) {
            cargo.put(good, quantity);
        } else if (cargo.get(good) != null){
            cargo.put(good, cargo.get(good) + quantity);
        }
    }


    /**
     * Remove good from cargo.
     *
     * @param good The item to be removed
     * @param quantity The number of items
     */
    public void removeGood(GoodType good, int quantity) {
        Set<GoodType> keySet = cargo.keySet();
        if(keySet.contains(good)) {
            if(cargo.get(good) == 1) {
                cargo.remove(good);
            } else if (cargo.get(good) != null){
                cargo.put(good, cargo.get(good) - quantity);
            }
        }
    }

    /**
     * tells user the maximum capacity of their ship
     * @return the max capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }


}

