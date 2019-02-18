package edu.gatech.cs2340.SpaceTrader.views;

import android.arch.lifecycle.ViewModelProvider;
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

        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.player_name_input);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        idField = findViewById(R.id.player_id_field);
        Button button = findViewById(R.id.add_button);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Player.legalDifficulty);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        /*
           If a student has been passed in, this was an edit, if not, this is a new add
         */

        //Adding a new student
        player = new Player("Bob", "Normal");
        button.setText("Add");
        setTitle("Creating Player");

        nameField.setText(player.getName());
        idField.setText(String.format("Player ID: %d", player.getId()));

        viewModel = ViewModelProvider.get(EditPlayerViewModel.class);
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

        //do the right thing depending on whether this is a new student or an edit

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
}

