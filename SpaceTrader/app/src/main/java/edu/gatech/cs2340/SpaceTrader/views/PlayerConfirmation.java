package edu.gatech.cs2340.SpaceTrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.viewmodels.EditPlayerViewModel;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;


public class PlayerConfirmation extends AppCompatActivity {
    private static Game game = Game.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_confirm);
        Button button = findViewById(R.id.game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginGamePressed();
            }
        });

        EditPlayerViewModel viewModel = ViewModelProviders.of(this).get(EditPlayerViewModel.class);

        final TextView nameTextView = findViewById(R.id.nameText);
        nameTextView.setText("Player name: " + game.getPlayerName());

        final TextView diffTextView = findViewById(R.id.difficultyText);
        diffTextView.setText("Difficulty: " + game.getDifficultyName());

        final TextView creditsTextView = findViewById(R.id.creditsText);
        creditsTextView.setText("Credits: " + game.getCredits());

        final TextView shipTextView = findViewById(R.id.shipText);
        shipTextView.setText("Ship name: " + game.getShipName());

        final TextView pilotTextView = findViewById(R.id.pilotSkillText);
        pilotTextView.setText("Pilot Skill: " + game.getPilotSkill());

        final TextView fighterTextView = findViewById(R.id.fighterSkillText);
        fighterTextView.setText("Fighter Skill: " + game.getFighterSkill());

        final TextView traderTextView = findViewById(R.id.traderSkillText);
        traderTextView.setText("Trader Skill: " + game.getTraderSkill());

        final TextView engineerTextView = findViewById(R.id.engineerSkillText);
        engineerTextView.setText("Engineer Skill: " + game.getEngineerSkill());
        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
    }
    private void beginGamePressed(){

        game.generateUniverse();

        for (int i = 0; i < 10; i++) {
            largeLog("Solar System", Game.getUniverse().get(i).toString());
        }

        Intent intent = new Intent(PlayerConfirmation.this, UniverseMap.class);
        startActivity(intent);
    }
    private static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }
}
