package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.SpaceTrader.R;

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

    }
    public void onStartPressed(){
        Intent intent = new Intent(PlanetScreen.this, InMarket.class);
        startActivity(intent);
        //startActivity(new Intent(MainActivity.this, CreatePlayer.class));
        //setContentView(R.layout.activity_create_player);
    }

}
