package edu.gatech.cs2340.SpaceTrader.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.cs2340.SpaceTrader.R;

import edu.gatech.cs2340.SpaceTrader.entity.Difficulty;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.viewmodels.EditPlayerViewModel;

/**
 * This class acts as the code behind for editing a player or creating a new player
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
    private final int pointMax = 16;

    /* ***********************
       Data for Player being edited.
     */
    private static Player player;

    private Game game = Game.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }

        nameField = findViewById(R.id.player_name_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        Button button = findViewById(R.id.add_button);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Player.legalDifficulty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        player = new Player("Bob", Difficulty.NORMAL);
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

        ViewModelProvider viewModelProvider = ViewModelProviders.of(this);
        viewModel = viewModelProvider.get(EditPlayerViewModel.class);
    }

    /**
     * Button handler for the add new student button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add/Update Student Pressed");
        if(((player.getEngineerSkill() + player.getFighterSkill() + player.getPilotSkill() +
                player.getTraderSkill()) == pointMax) && (player.getSkillPoints() == 0)) {
            Editable name = nameField.getText();
            player.setName(name.toString());

            if(difficultySpinner.getSelectedItemPosition() == 0) {
                player.setDifficulty(Difficulty.BEGINNER);
                player.setCredits(Difficulty.BEGINNER.getStarterCredits());
            } if(difficultySpinner.getSelectedItemPosition() == 1) {
                player.setDifficulty(Difficulty.EASY);
                player.setCredits(Difficulty.EASY.getStarterCredits());
            } if(difficultySpinner.getSelectedItemPosition() == 2) {
                player.setDifficulty(Difficulty.NORMAL);
                player.setCredits(Difficulty.NORMAL.getStarterCredits());
            } if(difficultySpinner.getSelectedItemPosition() == 3) {
                player.setDifficulty(Difficulty.HARD);
                player.setCredits(Difficulty.HARD.getStarterCredits());
            } if(difficultySpinner.getSelectedItemPosition() == 4) {
                player.setDifficulty(Difficulty.IMPOSSIBLE);
                player.setCredits(Difficulty.IMPOSSIBLE.getStarterCredits());
            }



            Log.d("Edit", "Got new player data: " + player);

            //create player
            viewModel.addPlayer(player);
            game.setPlayer(player);

            Intent intent = new Intent(this, PlayerConfirmation.class);
            startActivity(intent);

            finish();

        } else {
            CharSequence text = "Invalid Player Creation";
            Toast toast = Toast.makeText(CreatePlayer.this, text, Toast.LENGTH_SHORT);
            toast.show();
        }
        //finish();
    }

    /**
     * Button handler for cancel - just call back pressed
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        onBackPressed();
    }

    /**
     * increments the pilot skill
     * @param view up button
     */
    public void onPilotUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setPilotSkill(player.getPilotSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.pilot_points);
            idField.setText(String.valueOf(player.getPilotSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "No points left", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * decrements the pilot skill
     * @param view down button
     */
    public void onPilotDownPressed(View view) {
        if (player.getPilotSkill() > 0) {
            player.setPilotSkill(player.getPilotSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.pilot_points);
            idField.setText(String.valueOf(player.getPilotSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "Can't go below 0", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * increments the fighter skill
     * @param view up button
     */
    public void onFighterUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setFighterSkill(player.getFighterSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.fighter_points);
            idField.setText(String.valueOf(player.getFighterSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "No points left", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * decrements the fighter skill
     * @param view down button
     */
    public void onFighterDownPressed(View view) {
        if (player.getFighterSkill() > 0) {
            player.setFighterSkill(player.getFighterSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.fighter_points);
            idField.setText(String.valueOf(player.getFighterSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "Can't go below 0", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * increments the trader skill
     * @param view up button
     */
    public void onTraderUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setTraderSkill(player.getTraderSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.trader_points);
            idField.setText(String.valueOf(player.getTraderSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "No points left", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * decrements the trader skill
     * @param view down button
     */
    public void onTraderDownPressed(View view) {
        if (player.getTraderSkill() > 0) {
            player.setTraderSkill(player.getTraderSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.trader_points);
            idField.setText(String.valueOf(player.getTraderSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "Can't go below 0", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * increments the engineer skill
     * @param view up button
     */
    public void onEngineerUpPressed(View view) {
        if (player.getSkillPoints() > 0) {
            player.setEngineerSkill(player.getEngineerSkill() + 1);
            player.setSkillPoints(player.getSkillPoints() - 1);
            idField = findViewById(R.id.engineer_points);
            idField.setText(String.valueOf(player.getEngineerSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "No points left", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * decrements the engineer skill
     * @param view down button
     */
    public void onEngineerDownPressed(View view) {
        if (player.getEngineerSkill() > 0) {
            player.setEngineerSkill(player.getEngineerSkill() - 1);
            player.setSkillPoints(player.getSkillPoints() + 1);
            idField = findViewById(R.id.engineer_points);
            idField.setText(String.valueOf(player.getEngineerSkill()));
            idField = findViewById(R.id.remaining);
            idField.setText(String.valueOf(player.getSkillPoints()));
        }
        else {
            Toast toast = Toast.makeText(CreatePlayer.this, "Can't go below 0", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
