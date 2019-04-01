package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class SolarSystemMap extends AppCompatActivity {
    private Button button;
    Player player = Game.getInstance().getPlayer();
    int planetIndex;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solarsystem_map);

        final TextView locationText = findViewById(R.id.locationText);
        locationText.setText("Current Location: " + Game.getInstance().getCurrentSS().getName() +
                ", " + Game.getInstance().getCurrentPlanet().getName());

        final TextView fuelText = findViewById(R.id.fuelText);
        fuelText.setText("Current Fuel: " + Game.getInstance().getPlayer().getShip().getFuel());

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        // adding Radio Group
        layout.addView(radioGroup, p);
        final ArrayList<Planet> planetList = Game.getInstance().getCurrentSS().getPlanetList();

        //dynamically creates radio buttons for this specific solar system
        for(int i =0; i<planetList.size();i++)
        {
            RadioButton radioButtonView = new RadioButton(this);
            int distance = Game.getInstance().getPlayer().planetDistance(planetList.get(i));
            radioButtonView.setText(planetList.get(i).getName() + " - " + distance +" parsecs");
            radioGroup.addView(radioButtonView, p);
            if (i == 0) {
                radioButtonView.setChecked(true);
            }
            if(!player.canPlanetTravel(planetList.get(i))) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }



        GraphView SSGraph = (GraphView) findViewById(R.id.solarSystem_graph);
        DataPoint[] data = new DataPoint[Game.getInstance().getCurrentSS().getPlanetList().size()];

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        solarSystemTextView.setText(String.format("Solar System %s", Game.getInstance().getCurrentSS().getName()));

        button = findViewById(R.id.toPlanet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

        //checks to see what planet was selected and sets it as the destination
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
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
    public void onStartPressed(){
        //travel here
        if (player.canPlanetTravel(Game.getInstance().getCurrentSS().getPlanetList().get(planetIndex))) {
            Intent intent = new Intent(SolarSystemMap.this, PlanetScreen.class);
            startActivity(intent);
            player.planetTravel(Game.getInstance().getCurrentSS().getPlanetList().get(planetIndex));
        } else {
            Toast toast = Toast.makeText(SolarSystemMap.this, "Not enough fuel", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
