package edu.gatech.cs2340.SpaceTrader;

import org.junit.Test;
import org.junit.Before;

import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Good;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Resource;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Unit tests for GoodType
 *
 */
public class GoodTypeTest {

private GoodType testGoodType;
private int testTechLevel;
    /**
     * Setup for unit test
     *
     */
    @Before
    public void setUp() {
        Game.getInstance().generateUniverse();

 
        testGoodType = GoodType.WATER;
        testTechLevel = 1;

    }
    /**
     * unit test 1
     *
     */
    @Test
    public void calculatePriceCheapResourceTest() {
        //if(condition.toString().equals(cr)) { price = price / 2; } -> true
        int price = testGoodType.getBasePrice() + (testGoodType.getIpl() *
                (testTechLevel - testGoodType.getMtlp()));
        //assume resource creates cheap type
        price = price / 2;
        if(price == 0) {
            price = 1;
        }
        Game.getInstance().getPlayer().getDifficulty().adjustPrice(price);
        //Trader skill points adjustment
        price += ((Game.getInstance().getPlayer().getTraderSkill() / 100) * price);

        //see if manually halved price is within variance
        assertTrue(Math.abs(testGoodType.getVar()) >=
                Math.abs(testGoodType.calculatePrice(testTechLevel,
                        Resource.LOTSOFWATER) - price));
    }
    /**
     * unit test 2
     *
     */
    @Test
    public void calculatePriceNotCheapOrExpResourceTest() {
        //if(condition.toString().equals(cr)) { price = price / 2; } -> false
        int price = testGoodType.getBasePrice() + (testGoodType.getIpl() *
                (testTechLevel - testGoodType.getMtlp()));
        if(price == 0) {
            price = 1;
        }
        Game.getInstance().getPlayer().getDifficulty().adjustPrice(price);
        //Trader skill points adjustment
        price += ((Game.getInstance().getPlayer().getTraderSkill() / 100) * price);

        //compare price to no resource price
        assertTrue(Math.abs(testGoodType.getVar()) >=
                Math.abs(testGoodType.calculatePrice(testTechLevel,
                        Resource.NOSPECIALRESOURCES) - price));
    }
    /**
     * unit test 3
     *
     */
    @Test
    public void calculatePriceExpResourceTest() {
        //if(condition.toString().equals(er)) { price = price * 2; } -> true
        int price = testGoodType.getBasePrice() + (testGoodType.getIpl() *
                (testTechLevel - testGoodType.getMtlp()));
        //assume resource creates cheap type
        price = price * 2;
        if(price == 0) {
            price = 1;
        }
        Game.getInstance().getPlayer().getDifficulty().adjustPrice(price);
        //Trader skill points adjustment
        price += ((Game.getInstance().getPlayer().getTraderSkill() / 100) * price);

        //see if manually doubled price is within variance
        assertTrue(Math.abs(testGoodType.getVar()) >=
                Math.abs(testGoodType.calculatePrice(testTechLevel,
                        Resource.DESERT) - price));
    }

}
