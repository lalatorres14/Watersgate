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
import edu.gatech.cs2340.SpaceTrader.viewmodels.EditPlayerViewModel;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;


public class PlayerConfirmation extends AppCompatActivity {
    private EditPlayerViewModel viewModel;
    private Button button;
    private ArrayList<SolarSystem> systemslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_confirm);
        button = findViewById(R.id.game_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginGamePressed();
            }
        });

        viewModel = ViewModelProviders.of(this).get(EditPlayerViewModel.class);

        final TextView nameTextView = findViewById(R.id.nameText);
        nameTextView.setText("Player name: " + viewModel.getPlayer(0).getName());

        final TextView diffTextView = findViewById(R.id.difficultyText);
        diffTextView.setText("Difficulty: " + viewModel.getPlayer(0).getDifficulty());

        final TextView creditsTextView = findViewById(R.id.creditsText);
        creditsTextView.setText("Credits: " + viewModel.getPlayer(0).getCredits());

        final TextView shipTextView = findViewById(R.id.shipText);
        shipTextView.setText("Ship name: " + viewModel.getPlayer(0).getShip());

        final TextView pilotTextView = findViewById(R.id.pilotSkillText);
        pilotTextView.setText("Pilot Skill: " + viewModel.getPlayer(0).getPilotSkill());

        final TextView fighterTextView = findViewById(R.id.fighterSkillText);
        fighterTextView.setText("Fighter Skill: " + viewModel.getPlayer(0).getFighterSkill());

        final TextView traderTextView = findViewById(R.id.traderSkillText);
        traderTextView.setText("Trader Skill: " + viewModel.getPlayer(0).getTraderSkill());

        final TextView engineerTextView = findViewById(R.id.engineerSkillText);
        engineerTextView.setText("Engineer Skill: " + viewModel.getPlayer(0).getEngineerSkill());
        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
    }
    public void beginGamePressed(){
        for (int i = 0; i < 10; i++) {
            systemslist.add(new SolarSystem());
            //Log.i(this.systemslist.toString(),systemslist.get(i).toString());
            largeLog(this.systemslist.toString(),systemslist.get(i).toString());
        }
        Intent intent = new Intent(PlayerConfirmation.this, GameStart.class);
        startActivity(intent);
        //startActivity(new Intent(MainActivity.this, CreatePlayer.class));
        //setContentView(R.layout.activity_create_player);
    }
    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }
}
