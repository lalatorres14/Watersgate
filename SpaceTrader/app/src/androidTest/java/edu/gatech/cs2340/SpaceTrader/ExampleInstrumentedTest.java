package edu.gatech.cs2340.SpaceTrader;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("JavaDoc")
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @SuppressWarnings("JavaDoc")
    @Test

/*

  Context of the app under test.

 */
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.gatech.cs2340.SpaceTrader", appContext.getPackageName());
    }
}
