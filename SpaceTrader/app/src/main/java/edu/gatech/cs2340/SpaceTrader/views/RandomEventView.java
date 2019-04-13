package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.RandomEvent;
import edu.gatech.cs2340.SpaceTrader.entity.RandomEventType;

public class RandomEventView extends AppCompatActivity {
    private static final Game game = Game.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.random_event_screen);

        RandomEventType randomEventGenerated = RandomEventType.getRandomEventType();
        String message = RandomEvent.doRandomEvent(randomEventGenerated);

        final TextView titleText = findViewById(R.id.randomEventTitle);
        titleText.setText(randomEventGenerated.toString());

        final TextView msgText = findViewById(R.id.randomEventText);
        msgText.setText(message);

        Button continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomEventView.this,
                        game.getNextScreen());
                startActivity(intent);
            }
        });
    }

}
