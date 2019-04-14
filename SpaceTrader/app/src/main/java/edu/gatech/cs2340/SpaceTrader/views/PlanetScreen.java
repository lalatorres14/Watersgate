package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
/**
 * This class acts as the code behind for allowing the player to go to the market on the planet
 * or refuel their ship.
 */
public class PlanetScreen extends AppCompatActivity {
    private static Game game = Game.getInstance();

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
        final TextView nameTextView = findViewById(R.id.planetText);
        nameTextView.setText(game.getCurrentPlanetName());

        ImageView imageView = findViewById(R.id.imageView);
        String planetId = "p" + Integer.toString(game.getCurrentPlanetId());
        Resources res = getResources();
        int resourceId = res.getIdentifier(
                planetId, "drawable", getPackageName() );
        imageView.setImageResource(resourceId);


        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s",
                game.getCurrentSSName()));

        final TextView rssTextView = findViewById(R.id.rssText);
        rssTextView.setText(String.format("Resources: %s", game.getCurrentPlanetRssName()));

        final TextView techLevelTextView = findViewById(R.id.techLevelText);
        techLevelTextView.setText(String.format("Tech Level: %s", game.getTechLevel()));

    }
    private void toMarket() {
        Intent intent = new Intent(PlanetScreen.this, InMarket.class);
        startActivity(intent);
    }
    private void refuel() {
        if (game.adjustPrice(game.getMaxFuel()
                - game.getFuel()) > game.getCredits()) {
            Toast toast = Toast.makeText(PlanetScreen.this, "Cannot afford to refuel",
                    Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(PlanetScreen.this, "Spent " +
                    Integer.toString(game.adjustPrice(
                            game.getMaxFuel() - game.getFuel()))
                    + "$", Toast.LENGTH_SHORT);
            game.refuel();
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
