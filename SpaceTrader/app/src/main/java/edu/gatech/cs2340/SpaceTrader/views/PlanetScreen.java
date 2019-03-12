package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;

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

        final TextView nameTextView = findViewById(R.id.nameText);
        //so this is bad!! once travel is a thing, doing so needs to somehow set this text here and
        // set a general 'current planet' variable
        nameTextView.setText(Game.getInstance().getUniverse().get(0).getPlanetList().get(0).getName());

    }
    public void onStartPressed(){
        Intent intent = new Intent(PlanetScreen.this, InMarket.class);
        startActivity(intent);
        //startActivity(new Intent(MainActivity.this, CreatePlayer.class));
        //setContentView(R.layout.activity_create_player);
    }

}
