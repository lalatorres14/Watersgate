package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;

public class SolarSystemMap extends AppCompatActivity {
    private Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solarsystem_map);


        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        RadioGroup radioGroup = new RadioGroup(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // adding Radio Group
        layout.addView(radioGroup, p);
        final ArrayList<Planet> planetList = Game.getInstance().getCurrentSS().getPlanetList();

        for(int i =0; i<planetList.size();i++)
        {
            RadioButton radioButtonView = new RadioButton(this);
            radioButtonView.setText(planetList.get(i).getName());
            radioGroup.addView(radioButtonView, p);
            //((ViewGroup)layout.getParent()).removeView(layout);
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

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                int planetIndex = 0;
                if (isChecked) {
                    for(int i =0; i<planetList.size();i++) {
                        if (checkedRadioButton.getId() == group.getChildAt(i).getId()) {
                            planetIndex = i;
                        }
                    }
                    Game.getInstance().setCurrentPlanet(Game.getInstance().getCurrentSS().getPlanetList().get(planetIndex));
                }
            }
        });

    }

    public void onStartPressed(){
        //travel here
        Intent intent = new Intent(SolarSystemMap.this, PlanetScreen.class);
        startActivity(intent);
    }
}
