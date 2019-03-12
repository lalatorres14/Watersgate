package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class InMarket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_screen);

        Planet current = Game.getInstance().getUniverse().get(0).getPlanetList().get(0);
        Player player = Game.getInstance().getPlayer();
        final TextView nameTextView = findViewById(R.id.marketHeader);
        nameTextView.setText(current.getName() + "'s Bazaar");
    }

}
