package edu.gatech.cs2340.SpaceTrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import edu.gatech.cs2340.SpaceTrader.R;

import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.viewmodels.EditPlayerViewModel;

/**
 * This class acts as the code behind for editing a student or creating a new student
 */
public class CreatePlayer extends AppCompatActivity {

    /** reference to our view model */
    private EditPlayerViewModel viewModel;

    /* ************************
        Widgets we will need for binding and getting information
     */
    private TextView idField;
    private EditText nameField;
    private Spinner difficultySpinner;

    /* ***********************
       Data for Player being edited.
     */
    private Player player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        nameField = findViewById(R.id.player_name_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        Button button = findViewById(R.id.add_button);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Player.legalDifficulty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        player = new Player("Bob", "Normal");
        button.setText("Add");
        setTitle("Creating Player");

        //Initializing display values for skills
        idField = findViewById(R.id.pilot_points);
        idField.setText(String.valueOf(player.getPilotSkill()));
        idField = findViewById(R.id.fighter_points);
        idField.setText(String.valueOf(player.getFighterSkill()));
        idField = findViewById(R.id.trader_points);
        idField.setText(String.valueOf(player.getTraderSkill()));
        idField = findViewById(R.id.engineer_points);
        idField.setText(String.valueOf(player.getEngineerSkill()));
        idField = findViewById(R.id.remaining);
        idField.setText(String.valueOf(player.getSkillPoints()));

        nameField.setText(player.getName());

        viewModel = ViewModelProviders.of(this).get(EditPlayerViewModel.class);
    }

    /**
     * Button handler for the add new student button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add/Update Student Pressed");

        player.setName(nameField.getText().toString());
        player.setDifficulty((String) difficultySpinner.getSelectedItem());

        Log.d("Edit", "Got new player data: " + player);

        viewModel.addPlayer(player);

        finish();
    }

    /**
     * Button handler for cancel - just call back pressed
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        onBackPressed();
    }

    public void onPilotUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setPilotSkill(player.getPilotSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.pilot_points);
            idField.setText(String.valueOf(player.getPilotSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onPilotDownPressed(View view) {
        if (player.getPilotSkill() > 0) {
            player.setPilotSkill(player.getPilotSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.pilot_points);
            idField.setText(String.valueOf(player.getPilotSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onFighterUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setFighterSkill(player.getFighterSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.fighter_points);
            idField.setText(String.valueOf(player.getFighterSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onFighterDownPressed(View view) {
        if (player.getFighterSkill() > 0) {
            player.setFighterSkill(player.getFighterSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.fighter_points);
            idField.setText(String.valueOf(player.getFighterSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onTraderUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setTraderSkill(player.getTraderSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.trader_points);
            idField.setText(String.valueOf(player.getTraderSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onTraderDownPressed(View view) {
        if (player.getTraderSkill() > 0) {
            player.setTraderSkill(player.getTraderSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.trader_points);
            idField.setText(String.valueOf(player.getTraderSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onEngineerUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setEngineerSkill(player.getEngineerSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.engineer_points);
            idField.setText(String.valueOf(player.getEngineerSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }

    public void onEngineerDownPressed(View view) {
        if (player.getEngineerSkill() > 0) {
            player.setEngineerSkill(player.getEngineerSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.engineer_points);
            idField.setText(String.valueOf(player.getEngineerSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
    }
}
