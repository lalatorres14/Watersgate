package edu.gatech.cs2340.SpaceTrader;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.SpaceTrader.entity.Difficulty;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.entity.RandomEvent;
import edu.gatech.cs2340.SpaceTrader.entity.RandomEventType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for RandomEvents
 */
public class RandomEventTest {

    private Player player;
    private RandomEvent randomEvent;
    private Game game;
    /**
     * Setup
     *
     */
    @Before
    public void setUp() {
        game = Game.getInstance();
        game.generateUniverse();

        player = new Player("Tester", Difficulty.NORMAL);
        Game.getInstance().setPlayer(player);
        randomEvent = new RandomEvent();
    }
    /**
     * unit test 1, testing the police random event to make sure it follows the proper path
     * when the player has no narcotics.
     */
    @Test
    public void testPoliceNoNarcoticsString() {
        assertEquals(randomEvent.doRandomEvent(RandomEventType.POLICE), "You were " +
                "stopped by police! Fortunately, you didn't have any narcotics on board, " +
                "so you weren't fined.");

    }
    /**
     * unit test 2, testing the police random event for consistency in player credits
     * if the player did not get fined
     */
    @Test
    public void testPoliceNoNarcoticsMoney() {
        int creditsBefore = player.getCredits();
        randomEvent.doRandomEvent(RandomEventType.POLICE);
        assertEquals(creditsBefore, player.getCredits());
    }
    /**
     * unit test 3, testing the police random event to make sure it follows the proper path
     * when the player has narcotics
     */
    @Test
    public void testPoliceWithNarcoticsString() {
        player.buyGood(GoodType.NARCOTICS, 1);
        assertNotEquals(randomEvent.doRandomEvent(RandomEventType.POLICE), "You were " +
                "stopped by police! Fortunately, you didn't have any narcotics on board, " +
                "so you weren't fined");
    }
    /**
     * unit test 4, testing the police random event for a decrease in player credits if the
     * player got fined
     */
    @Test
    public void testPoliceWithNarcoticsMoney() {
        player.buyGood(GoodType.NARCOTICS, 1);
        int creditsBefore = player.getCredits();
        randomEvent.doRandomEvent(RandomEventType.POLICE);
        assertTrue(creditsBefore >= player.getCredits());
    }
    /**
     * unit test 5, testing the police random event for a removal of narcotics in the player's
     * inventory if the player had any.
     */
    @Test
    public void testPoliceWithNarcoticsAmount() {
        player.buyGood(GoodType.NARCOTICS, 1);
        randomEvent.doRandomEvent(RandomEventType.POLICE);
        assertEquals(game.getQuantityOfGood(GoodType.NARCOTICS), 0);
    }
}