package edu.gatech.cs2340.SpaceTrader;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for GenerateUniverse
 *
 */
public class GenerateUniverseTest {
    @Before
    public void setUp() {
        Game.getInstance().generateUniverse();

    }

    /**
     * Unit test. Checks that the universe generated has 10 solar systems.
     */
    @Test
    public void UniverseSizeCheck(){
        assertEquals(Game.getInstance().getUniverse().size(), 10);
    }

    /**
     * Unit test. Checks to make sure each solar system has at least one planet, that the planet
     * lists all the planets in the system, and tha the SS has the right name
     */
    @Test
    public void SolarSystemCheck(){
        for (int i = 0; i < 10; i++){
            assertTrue(Game.getInstance().getUniverse().get(i).getPlanetList().size() > 0);
            assertEquals(Game.getInstance().getUniverse().get(i).getPlanetList().size(),
                    Game.getInstance().getUniverse().get(i).getPlanetTotal());
            String[] names = {"Alpha", "Beta", "Gamma", "Delta", "Zeta", "Theta", "Iota",
                    "Nu", "Omicron", "Omega"};
            assertEquals(Game.getInstance().getUniverse().get(i).getName(), names[i]);
        }
    }


    /**
     * Unit test. Checks each planet in each solar system to ensure that it has a market, resource
     * type, tech level, and has a name. Each planet should be in that solar system's planet list.
     */
    @Test
    public void PlanetCheck(){
        SolarSystem ss;
        for (int i = 0; i < 10; i++){
            ss = Game.getInstance().getUniverse().get(i);
            ArrayList<String> actualNames = new ArrayList<>();
            for (int j = 0; j < ss.getPlanetTotal(); j++){
                assertNotNull(ss.getPlanetList().get(j).getName());
                actualNames.add(ss.getPlanetList().get(j).getName());
                assertNotNull(ss.getPlanetList().get(j).getMarket());
                assertNotNull(ss.getPlanetList().get(j).getResources());
                assertNotNull(ss.getPlanetList().get(j).getTechLevel());
            }
            assertEquals(ss.getPlanetTotal(), actualNames.size());
        }

    }
}
