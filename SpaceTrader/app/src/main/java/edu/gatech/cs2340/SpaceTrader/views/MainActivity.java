package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Difficulty;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;
import edu.gatech.cs2340.SpaceTrader.entity.ShipType;
import edu.gatech.cs2340.SpaceTrader.entity.SolarSystem;
/**
 * This class acts as the code behind instantiating the game
 */
public class MainActivity extends AppCompatActivity {
    private Game game = Game.getInstance();
    //SharedPreferences pref = getApplicationContext().getSharedPreferences("data", 0);
    private SharedPreferences pref ;
    //SharedPreferences.Editor editor ;
    private final Gson gson = new Gson();
    //Game game ;
    private Player player;
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
    private void onStartPressed(){
        List<SolarSystem> tmplist = new ArrayList<>();
        pref = getSharedPreferences("spaceTrader", Context.MODE_PRIVATE);
        if (!pref.getBoolean("first", true)) {
            System.out.println("Savy, inside getShared preferences if");
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("first", false);
            edit.apply();
            Intent intent = new Intent(MainActivity.this, CreatePlayer.class);
            startActivity(intent);
        } else {
            System.out.println("Savy, inside getShared preferences else");
            //String json_1 = pref.getString("Current_Game_Player", null);
            String json_2 = pref.getString("Current_Game_CurrentSS", null);
            String json_3 = pref.getString("Current_Game_CurrentPlanet", null);
            //System.out.println("Savy , " + json_1);
            String playerName = pref.getString("Player_name",null);
            player = new Player("Bob", Difficulty.NORMAL);
            game.setPlayer(player);
            game.setPlayerName(playerName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                game.setDifficulty(Difficulty.valueOf(
                        Objects.requireNonNull
                        (pref.getString("Difficulty", null)).toUpperCase()));
            }
            game.setCredits(pref.getInt("Credits",0));
            game.setShip(ShipType.valueOf(Objects.requireNonNull(pref.
                    getString("Ship_Name", null)).toUpperCase()));
            game.setPilotSkill( pref.getInt("Pilot_Skill",0));
            game.setFighterSkill(pref.getInt("Fighter_Skill",0));
            game.setTraderSkill(pref.getInt("Trader_Skill",0));
            game.setEngineerSkill(pref.getInt("Engineer_Skill",0));
            game.setFuel( pref.getInt("Fuel", 0));
            game.setCurrentSS(gson.fromJson(json_2, SolarSystem.class));
            game.setCurrentPlanet(gson.fromJson(json_3, Planet.class));
            game.setFuel(pref.getInt("Fuel", 0));
            game.addGood(GoodType.WATER,pref.getInt(
                    "WATER", 0));
            game.addGood(GoodType.FURS,pref.getInt(
                    "FURS", 0));
            game.addGood(GoodType.FOOD,pref.getInt(
                    "FOOD", 0));
            game.addGood(GoodType.ORE,pref.getInt(
                    "ORE", 0));
            game.addGood(GoodType.FIREARMS,pref.
                    getInt("FIREARMS", 0));
            game.addGood(GoodType.MEDICINE,pref.
                    getInt("MEDICINE", 0));
            game.addGood(GoodType.MACHINES,pref.
                    getInt("MACHINES", 0));
            game.addGood(GoodType.NARCOTICS,pref.
                    getInt("NARCOTICS", 0));
            game.addGood(GoodType.ROBOTS,pref.
                    getInt("ROBOTS", 0));
            for (int i = 0; i < 10; i++) {
                String json_i = pref.getString("universe_elem" + i,null);
                tmplist.add(gson.fromJson(json_i, SolarSystem.class));

            }
            game.setUniverse(tmplist);
            Intent intent = new Intent(MainActivity.this, UniverseMap.class);
            startActivity(intent);
        }

    }

}
