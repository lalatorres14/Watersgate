package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Random;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
/**
 * This class acts as the code behind for allowing the player to travel between planets
 */
public class SolarSystemMap extends AppCompatActivity {
    private static Game game = Game.getInstance();
    private int planetIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solarsystem_map);

        final TextView locationText = findViewById(R.id.locationText);
        locationText.setText("Current Location: " + game.getCurrentSSName() +
                ", " + game.getCurrentPlanetName());

        final TextView fuelText = findViewById(R.id.fuelText);
        fuelText.setText("Current Fuel: " + game.getFuel());

        LinearLayout layout = findViewById(R.id.layout);
        RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        // adding Radio Group
        layout.addView(radioGroup, p);
        final ArrayList<Planet> planetList = game.getCurrentPlanetList();

        //dynamically creates radio buttons for this specific solar system
        for(int i =0; i<planetList.size();i++)
        {
            Planet thisPlanet = planetList.get(i);
            RadioButton radioButtonView = new RadioButton(this);
            int distance = game.planetDistance(planetList.get(i));
            radioButtonView.setText(thisPlanet.getName() + " - " + distance +" parsecs");
            radioGroup.addView(radioButtonView, p);
            if (i == 0) {
                radioButtonView.setChecked(true);
            }
            if(!game.canPlanetTravel(planetList.get(i))) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }



        GraphView SSGraph = findViewById(R.id.solarSystem_graph);
        DataPoint[] data = new DataPoint[game.getCurrentPlanetList().size()];

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s",
                game.getCurrentSSName()));

        Button button = findViewById(R.id.toPlanet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

        //checks to see what planet was selected and sets it as the destination
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                planetIndex = 0;
                if (isChecked) {
                    for(int i =0; i<planetList.size();i++) {
                        if (checkedRadioButton.getId() == group.getChildAt(i).getId()) {
                            planetIndex = i;
                        }
                    }
                }
            }
        });

    }

    //travel button
    private void onStartPressed(){
        //travel here
        if (game.canPlanetTravel(game.getCurrentPlanetList()
                .get(planetIndex))) {

            Random random = new Random();
            //this 3 is arbitrary, we should decide how often to get random events
            if(random.nextInt(3) == 0){
                //go to random event screen and tell game to go to planet screen next
                game.setNextScreen(PlanetScreen.class);
                Intent intent = new Intent(SolarSystemMap.this, RandomEventView.class);
                startActivity(intent);
            } else { //travel normally
                Intent intent = new Intent(SolarSystemMap.this, PlanetScreen.class);
                startActivity(intent);
            }

            game.planetTravel(game.getCurrentPlanetList().get(planetIndex));
        } else {
            Toast toast = Toast.makeText(SolarSystemMap.this, "Not enough fuel",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //override triangle back button behaviour to not undo travel
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SolarSystemMap.this, UniverseMap.class);
        startActivity(intent);
    }
}
