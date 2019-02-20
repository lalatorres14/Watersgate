package edu.gatech.cs2340.SpaceTrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.viewmodels.EditPlayerViewModel;

public class PlayerConfirmation extends AppCompatActivity {
    private EditPlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_confirm);

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
}
