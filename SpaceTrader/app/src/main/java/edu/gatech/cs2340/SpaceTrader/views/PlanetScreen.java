package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class PlanetScreen extends AppCompatActivity {
    private static Player player = Game.getInstance().getPlayer();
    Planet current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.planet_screen);
        //public static final int ADD_COURSE_REQUEST_ID = 1;
        Button toMarketButton = findViewById(R.id.toMarket);
        toMarketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMarket();
            }
        });
        Button refuelButton = findViewById(R.id.refuel);
        refuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refuel();
            }
        });
        current = Game.getInstance().getCurrentPlanet();
        final TextView nameTextView = findViewById(R.id.planetText);
        nameTextView.setText(current.getName());

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s",Game.getInstance().getCurrentSS().getName()));

        final TextView rssTextView = findViewById(R.id.rssText);
        rssTextView.setText(String.format("Resources: %s",current.getResources().toString()));

        final TextView techLevelTextView = findViewById(R.id.techLevelText);
        techLevelTextView.setText(String.format("Tech Level: %s",current.getTechLevel()));

    }
    public void toMarket() {
        Intent intent = new Intent(PlanetScreen.this, InMarket.class);
        startActivity(intent);
    }
    public void refuel() {
        if (player.getDifficulty().adjustPrice(player.getShip().getMaxFuel() - player.getShip().getFuel()) > player.getCredits()) {
            Toast toast = Toast.makeText(PlanetScreen.this, "Cannot afford to refuel", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(PlanetScreen.this, "Spent " + Integer.toString(player.getDifficulty().adjustPrice(player.getShip().getMaxFuel() - player.getShip().getFuel())) + "$", Toast.LENGTH_SHORT);
            player.refuel();
            toast.show();
        }
    }

    //override triangle back button behaviour to not undo travel
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlanetScreen.this, SolarSystemMap.class);
        startActivity(intent);
    }
}
