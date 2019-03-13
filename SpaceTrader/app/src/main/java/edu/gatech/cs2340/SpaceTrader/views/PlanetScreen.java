package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;

public class PlanetScreen extends AppCompatActivity {
    //public static final int ADD_COURSE_REQUEST_ID = 1;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.planet_screen);
        button = findViewById(R.id.toMarket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });
        Planet p = Game.getInstance().getUniverse().get(0).getPlanetList().get(0);
        final TextView nameTextView = findViewById(R.id.planetText);
        //so this is bad!! once travel is a thing, doing so needs to somehow set this text here and
        // set a general 'current planet' variable
        nameTextView.setText(p.getName());

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s",Game.getInstance().getUniverse().get(0).getName()));

        final TextView rssTextView = findViewById(R.id.rssText);
        rssTextView.setText(String.format("Resources: %s",p.getResources().toString()));

        final TextView techLevelTextView = findViewById(R.id.techLevelText);
        techLevelTextView.setText(String.format("Tech Level: %s",p.getTechLevel()));

    }
    public void onStartPressed(){
        Intent intent = new Intent(PlanetScreen.this, InMarket.class);
        startActivity(intent);
        //startActivity(new Intent(MainActivity.this, CreatePlayer.class));
        //setContentView(R.layout.activity_create_player);
    }

}
