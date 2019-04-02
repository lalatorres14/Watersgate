package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.HashMap;
import java.util.Random;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;

public class UniverseMap extends AppCompatActivity {
    private static Player player = Game.getInstance().getPlayer();
    private Button button;
    private CharSequence checkedText;
    int solarIndex;
    //private SolarSystem destination = Game.getUniverse().get(0);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.universe_map);

        final TextView locationText = findViewById(R.id.locationText);
        locationText.setText("Current Location: " + Game.getInstance().getCurrentSS().getName() +
                ", " + Game.getInstance().getCurrentPlanet().getName());

        final TextView fuelText = findViewById(R.id.fuelText);
        fuelText.setText("Current Fuel: " + Game.getInstance().getPlayer().getShip().getFuel());

        GraphView uniGraph = (GraphView) findViewById(R.id.universe_graph);
        DataPoint[] data = new DataPoint[9];
        int j = 0;
        //HashMap<Integer, Integer> solarCoordinates = Game.getSolarCoordinates();
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.group);
        RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            String name = Game.getUniverse().get(i).getName();
            int distance = Game.getInstance().getPlayer().systemDistance(Game.getUniverse().get(i));
            ((RadioButton) radioGroup.getChildAt(i)).setText(name + " - " + distance + " parsecs");
            //makes this button grayed out and unclickable if too far away
            if(!player.canSystemTravel(Game.getUniverse().get(i))) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }

        button = findViewById(R.id.toSSMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

        //check to see which solar system was selected and sets that as the destination
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {

                    if (checkedRadioButton.getId() == R.id.solar1){
                        solarIndex = 0;
                    } else if (checkedRadioButton.getId() == R.id.solar2){
                        solarIndex = 1;
                    } else if (checkedRadioButton.getId() == R.id.solar3){
                        solarIndex = 2;
                    } else if (checkedRadioButton.getId() == R.id.solar4){
                        solarIndex = 3;
                    } else if (checkedRadioButton.getId() == R.id.solar5){
                        solarIndex = 4;
                    } else if (checkedRadioButton.getId() == R.id.solar6){
                        solarIndex = 5;
                    } else if (checkedRadioButton.getId() == R.id.solar7){
                        solarIndex = 6;
                    } else if (checkedRadioButton.getId() == R.id.solar8){
                        solarIndex = 7;
                    } else if (checkedRadioButton.getId() == R.id.solar9){
                        solarIndex = 8;
                    } else {
                        solarIndex = 9;
                    }
                }
            }
        });
    }
    //Travel button
    public void onStartPressed(){
        //travel here
        if (player.canSystemTravel(Game.getUniverse().get(solarIndex))) {
            Random random = new Random();
            if(random.nextInt(3) == 0){
                //go to random event screen and tell game to go to solar system screen next
                Game.getInstance().setNextScreen(SolarSystemMap.class);
                Intent intent = new Intent(UniverseMap.this, RandomEventView.class);
                startActivity(intent);
            } else { //travel normally
                Intent intent = new Intent(UniverseMap.this, SolarSystemMap.class);
                startActivity(intent);
            }

            player.systemTravel(Game.getUniverse().get(solarIndex));
        } else {
            Toast toast = Toast.makeText(UniverseMap.this, "Not enough fuel", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
