package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.HashMap;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;

public class UniverseMap extends AppCompatActivity {
    private Button button;
    private CharSequence checkedText;
    //private SolarSystem destination = Game.getUniverse().get(0);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.universe_map);
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
        }
        //goes through solar coordinates and makes a data point for each entry
        /*HashMap<Integer, Integer> solarCoordinates = Game.getInstance().getSolarCoordinates();
        for (int i = 0; i < 20; i++){
            if (solarCoordinates.get(i) != null){
                int y = solarCoordinates.get(i);
                data[j] = new DataPoint(i+1, y);
                j++;
                //return String.format("Solar system at %d" + i);
            }
        }
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(data);
        uniGraph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        */


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
                int solarIndex;
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
                    Game.getInstance().setCurrentSS(Game.getUniverse().get(solarIndex));
                }
            }
        });

        /*final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        for(int i = 0; i < Game.getInstance().getUniverse().size(); i++) {
            solarSystemTextView.append(String.format("Solar System %s \n", Game.getInstance().getUniverse().get(i).getName()));
        }*/
    }

    //Travel button
    public void onStartPressed(){
        //travel here
        Intent intent = new Intent(UniverseMap.this, SolarSystemMap.class);
        startActivity(intent);
    }


    /*public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        Intent intent;
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
            Game.setCurrentSS(Game.getUniverse().get(solarIndex));
        }
        startActivity(intent);
    }*/
}
