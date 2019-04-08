package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Difficulty;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.entity.ShipType;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;

public class MainActivity extends AppCompatActivity {
    //SharedPreferences pref = getApplicationContext().getSharedPreferences("data", 0);
    SharedPreferences pref ;
    //SharedPreferences.Editor editor ;
    Boolean firstTime = false;
    Gson gson = new Gson();
    //Game game ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Savy, inside onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("spaceTrader", Context.MODE_PRIVATE);

        Button button = findViewById(R.id.ready_player_one);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });


    }
    public void onStartPressed(){
        List<SolarSystem> tmplist = new ArrayList<>();
        pref = getSharedPreferences("spaceTrader", Context.MODE_PRIVATE);
        if (pref.getBoolean("firstTime", true)) {
            System.out.println("Savy, inside getShared preferences if");
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("firstTime", false);
            edit.commit();
            Intent intent = new Intent(MainActivity.this, CreatePlayer.class);
            startActivity(intent);
        } else {
            System.out.println("Savy, inside getShared preferences else");
            //String json_1 = pref.getString("Current_Game_Player", null);
            String json_2 = pref.getString("Current_Game_CurrentSS", null);
            String json_3 = pref.getString("Current_Game_CurrentPlanet", null);
            //System.out.println("Savy , " + json_1);
            Game.getInstance().getPlayer().setName(pref.getString("Player_name", null));
            Game.getInstance().getPlayer().setDifficulty(Difficulty.valueOf(pref.getString("Difficulty", null).toUpperCase()));
            Game.getInstance().getPlayer().setCredits(pref.getInt("Credits",0));
            Game.getInstance().getPlayer().setShip(ShipType.valueOf(pref.getString("Ship_Name", null).toUpperCase()));
            Game.getInstance().getPlayer().setPilotSkill( pref.getInt("Pilot_Skill",0));
            Game.getInstance().getPlayer().setFighterSkill(pref.getInt("Fighter_Skill",0));
            Game.getInstance().getPlayer().setTraderSkill(pref.getInt("Trader_Skill",0));
            Game.getInstance().getPlayer().setEngineerSkill(pref.getInt("Engineer_Skill",0));
            Game.getInstance().getPlayer().getShip().setFuel( pref.getInt("Fuel", 0));
            Game.getInstance().setCurrentSS(gson.fromJson(json_2, SolarSystem.class));
            Game.getInstance().setCurrentPlanet(gson.fromJson(json_3, Planet.class));
            Game.getInstance().getPlayer().getShip().setFuel(pref.getInt("Fuel", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.WATER,pref.getInt("WATER", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.FURS,pref.getInt("FURS", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.FOOD,pref.getInt("FOOD", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.ORE,pref.getInt("ORE", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.FIREARMS,pref.getInt("FIREARMS", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.MEDICINE,pref.getInt("MEDICINE", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.MACHINES,pref.getInt("MACHINES", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.NARCOTICS,pref.getInt("NARCOTICS", 0));
            Game.getInstance().getPlayer().getShip().getCargo().addGood(GoodType.ROBOTS,pref.getInt("ROBOTS", 0));
            for (int i = 0; i < 10; i++) {
                String json_i = pref.getString("universe_elem" + i,null);
                tmplist.add(gson.fromJson(json_i, SolarSystem.class));

            }
            Game.getInstance().setUniverse(tmplist);
            Intent intent = new Intent(MainActivity.this, UniverseMap.class);
            startActivity(intent);
        }

    }

}
