package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Random;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class UniverseMap extends AppCompatActivity {
    private static final Player player = Game.getInstance().getPlayer();
    private CharSequence checkedText;
    private int solarIndex;
    private SharedPreferences pref ;
    private final Gson gson = new Gson();
    //private SolarSystem destination = Game.getUniverse().get(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.universe_map);
        pref = getSharedPreferences("spaceTrader", Context.MODE_PRIVATE);
        final TextView locationText = findViewById(R.id.locationText);
        locationText.setText("Current Location: " + Game.getInstance().getCurrentSS().getName() +
                ", " + Game.getInstance().getCurrentPlanet().getName());

        final TextView fuelText = findViewById(R.id.fuelText);
        fuelText.setText("Current Fuel: " + Game.getInstance().getPlayer().getShip().getFuel());

        GraphView uniGraph = findViewById(R.id.universe_graph);
        DataPoint[] data = new DataPoint[9];
        int j = 0;
        //HashMap<Integer, Integer> solarCoordinates = Game.getSolarCoordinates();
        RadioGroup radioGroup = findViewById(R.id.group);
        RadioButton checkedRadioButton = radioGroup.findViewById(
                radioGroup.getCheckedRadioButtonId());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            String name = Game.getUniverse().get(i).getName();
            int distance = Game.getInstance().getPlayer().systemDistance(Game.getUniverse().get(i));
            ((RadioButton) radioGroup.getChildAt(i)).setText(name + " - " + distance + " parsecs");
            //makes this button grayed out and unclickable if too far away
            if(!player.canSystemTravel(Game.getUniverse().get(i))) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }

        Button button = findViewById(R.id.toSSMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

        //check to see which solar system was selected and sets that as the destination
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = group.findViewById(checkedId);
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
    private void onStartPressed(){
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
        Toast toast = Toast.makeText(UniverseMap.this, "Saved Progress", Toast.LENGTH_SHORT);
        toast.show();
        SharedPreferences.Editor editor = pref.edit();
        //String json_1 = gson.toJson(Game.getInstance().getPlayer());
        String json_2 = gson.toJson(Game.getInstance().getCurrentSS());
        String json_3 = gson.toJson(Game.getInstance().getCurrentPlanet());
        //System.out.println(json);
        //System.out.println("Savy  in UniverseMap, " + json_1);
        //editor.putString("Current_Game_Player", json_1);
        editor.putString("Player_name", Game.getInstance().getPlayer().getName());
        editor.putString("Difficulty", Game.getInstance().getPlayer().getDifficulty().toString());
        editor.putInt("Credits", Game.getInstance().getPlayer().getCredits());
        editor.putString("Ship_Name", Game.getInstance().getPlayer().getShip().toString());
        editor.putInt("Pilot_Skill", Game.getInstance().getPlayer().getPilotSkill());
        editor.putInt("Fighter_Skill", Game.getInstance().getPlayer().getFighterSkill());
        editor.putInt("Trader_Skill", Game.getInstance().getPlayer().getTraderSkill());
        editor.putInt("Engineer_Skill", Game.getInstance().getPlayer().getEngineerSkill());
        editor.putString("Current_Game_CurrentSS", json_2);
        editor.putString("Current_Game_CurrentPlanet", json_3);
        editor.putInt("Fuel", Game.getInstance().getPlayer().getShip().getFuel());
        editor.putInt("WATER", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.WATER));
        editor.putInt("FURS",  Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.FURS));
        editor.putInt("FOOD", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.FOOD));
        editor.putInt("ORE", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.ORE));
        editor.putInt("GAMES",  Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.GAMES));
        editor.putInt("FIREARMS", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.FIREARMS));
        editor.putInt("MEDICINE", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.MEDICINE));
        editor.putInt("MACHINES", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.MACHINES));
        editor.putInt("NARCOTICS", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.NARCOTICS));
        editor.putInt("ROBOTS", Game.getInstance().getPlayer().getShip().getGoodQuantity(
                GoodType.ROBOTS));
        for (int i = 0; i < Game.getUniverse().size(); i++) {
            String json_i = gson.toJson(Game.getUniverse().get(i));
            editor.putString("universe_elem" + i, json_i);
        }
        editor.putInt("Fuel", Game.getInstance().getPlayer().getShip().getFuel());
        editor.apply();
        //System.out.println(pref.getInt("Fuel", 0));
        //System.out.println("Savy  saved file in UniverseMap, ");
    }
}
