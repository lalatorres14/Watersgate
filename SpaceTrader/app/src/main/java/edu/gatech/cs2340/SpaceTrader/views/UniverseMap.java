package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.HashMap;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;

public class UniverseMap extends AppCompatActivity {
    private Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.universe_map);
        GraphView uniGraph = (GraphView) findViewById(R.id.universe_graph);
        DataPoint[] data = new DataPoint[9];
        int j = 0;
        HashMap<Integer, Integer> solarCoordinates = Game.getSolarCoordinates();
        //goes through solar coordinates and makes a data point for each entry
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
        button = findViewById(R.id.toSSMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

        final TextView solarSystemTextView = findViewById(R.id.solarSystemText);
        for(int i = 0; i < Game.getInstance().getUniverse().size(); i++) {
            solarSystemTextView.append(String.format("Solar System %s \n", Game.getInstance().getUniverse().get(i).getName()));
        }
    }
    public void onStartPressed(){
        //travel here
        Intent intent = new Intent(UniverseMap.this, SolarSystemMap.class);
        startActivity(intent);
    }
}
