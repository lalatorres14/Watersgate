package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;

public class UniverseMap extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.universe_map);

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s",Game.getInstance().getCurrentSS().getName()));

    }
}
