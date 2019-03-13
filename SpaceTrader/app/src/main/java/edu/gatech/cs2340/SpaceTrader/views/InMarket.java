package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Good;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class InMarket extends AppCompatActivity {
    //Game-based variables (as opposed to UI-based)
    Planet current;
    Player player;
    //all the buttons for the market
    protected Button buyMode;
    protected Button sellMode;
    protected Button completeTransaction;
    //TableRows for data stuff
    protected TableRow material1Row1;
    protected TableRow material1Row2;
    protected TableRow spaceRow1;
    protected TableRow material2Row1;
    protected TableRow material2Row2;
    protected TableRow spaceRow2;
    protected TableRow material3Row1;
    protected TableRow material3Row2;
    protected TableRow spaceRow3;
    protected TableRow material4Row1;
    protected TableRow material4Row2;
    protected TableRow spaceRow4;
    protected TableRow material5Row1;
    protected TableRow material5Row2;
    protected TableRow spaceRow5;
    protected TableRow material6Row1;
    protected TableRow material6Row2;
    protected TableRow spaceRow6;
    protected TableRow material7Row1;
    protected TableRow material7Row2;
    protected TableRow spaceRow7;
    protected TableRow material8Row1;
    protected TableRow material8Row2;
    protected TableRow spaceRow8;
    protected TableRow material9Row1;
    protected TableRow material9Row2;
    protected TableRow spaceRow9;
    protected TableRow material10Row1;
    protected TableRow material10Row2;
    //unit prices
    protected int material1UnitPrice;
    protected int material2UnitPrice;
    protected int material3UnitPrice;
    protected int material4UnitPrice;
    protected int material5UnitPrice;
    protected int material6UnitPrice;
    protected int material7UnitPrice;
    protected int material8UnitPrice;
    protected int material9UnitPrice;
    protected int material10UnitPrice;
    //other
    protected TextView unitPriceView;
    protected TextView holdQuantityView;
    protected TextView holdSpaceView;
    protected Good good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_screen);
        //linking variables to actual buttons
        buyMode = findViewById(R.id.buyMode);
        sellMode = findViewById(R.id.sellMode);
        completeTransaction = findViewById(R.id.completeTransaction);
        //storing planet and player info
        current = Game.getInstance().getUniverse().get(0).getPlanetList().get(0);
        player = Game.getInstance().getPlayer();
        //printing planet name
        final TextView nameTextView = findViewById(R.id.marketHeader);
        nameTextView.setText(current.getName() + "'s Bazaar");
        holdSpaceView = findViewById(R.id.holdSpace);
        holdSpaceView.setText(String.valueOf(player.getShip().getSpace()));

        //removing all rows from view until button presses and initializing values in each row
        //material*Row1 children (materialName, "Total:", totalAmount, unitAmount)
        //material*Row2 children ("In Hold:", holdTotal, "Quantity:", quantity)
        material1Row1 = findViewById(R.id.material1Row1);
        unitPriceView = findViewById(R.id.item1Unit);
        good = new Good(GoodType.WATER);
        material1UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material1UnitPrice));
        material1Row1.setVisibility(View.GONE);
        material1Row2 = findViewById(R.id.material1Row2);
        holdQuantityView = findViewById(R.id.holdQuantity1);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material1Row2.setVisibility(View.GONE);
        spaceRow1 = findViewById(R.id.spaceRow1);
        spaceRow1.setVisibility(View.GONE);

        material2Row1 = findViewById(R.id.material2Row1);
        material2Row1.setVisibility(View.GONE);
        good = new Good(GoodType.FURS);
        material2UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material2UnitPrice));
        material2Row2 = findViewById(R.id.material2Row2);
        holdQuantityView = findViewById(R.id.holdQuantity2);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material2Row2.setVisibility(View.GONE);
        spaceRow2 = findViewById(R.id.spaceRow2);
        spaceRow2.setVisibility(View.GONE);

        material3Row1 = findViewById(R.id.material3Row1);
        material3Row1.setVisibility(View.GONE);
        good = new Good(GoodType.FOOD);
        material3UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material3UnitPrice));
        material3Row2 = findViewById(R.id.material3Row2);
        holdQuantityView = findViewById(R.id.holdQuantity3);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material3Row2.setVisibility(View.GONE);
        spaceRow3 = findViewById(R.id.spaceRow3);
        spaceRow3.setVisibility(View.GONE);

        material4Row1 = findViewById(R.id.material4Row1);
        material4Row1.setVisibility(View.GONE);
        good = new Good(GoodType.ORE);
        material4UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material4UnitPrice));
        material4Row2 = findViewById(R.id.material4Row2);
        holdQuantityView = findViewById(R.id.holdQuantity4);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material4Row2.setVisibility(View.GONE);
        spaceRow4 = findViewById(R.id.spaceRow4);
        spaceRow4.setVisibility(View.GONE);

        material5Row1 = findViewById(R.id.material5Row1);
        material5Row1.setVisibility(View.GONE);
        good = new Good(GoodType.GAMES);
        material5UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material5UnitPrice));
        material5Row2 = findViewById(R.id.material5Row2);
        holdQuantityView = findViewById(R.id.holdQuantity5);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material5Row2.setVisibility(View.GONE);
        spaceRow5 = findViewById(R.id.spaceRow5);
        spaceRow5.setVisibility(View.GONE);

        material6Row1 = findViewById(R.id.material6Row1);
        material6Row1.setVisibility(View.GONE);
        good = new Good(GoodType.FIREARMS);
        material6UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material6UnitPrice));
        material6Row2 = findViewById(R.id.material6Row2);
        holdQuantityView = findViewById(R.id.holdQuantity6);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material6Row2.setVisibility(View.GONE);
        spaceRow6 = findViewById(R.id.spaceRow6);
        spaceRow6.setVisibility(View.GONE);

        material7Row1 = findViewById(R.id.material7Row1);
        material7Row1.setVisibility(View.GONE);
        good = new Good(GoodType.MEDICINE);
        material7UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material7UnitPrice));
        material7Row2 = findViewById(R.id.material7Row2);
        holdQuantityView = findViewById(R.id.holdQuantity7);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material7Row2.setVisibility(View.GONE);
        spaceRow7 = findViewById(R.id.spaceRow7);
        spaceRow7.setVisibility(View.GONE);

        material8Row1 = findViewById(R.id.material8Row1);
        material8Row1.setVisibility(View.GONE);
        good = new Good(GoodType.MACHINES);
        material8UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material8UnitPrice));
        material8Row2 = findViewById(R.id.material8Row2);
        holdQuantityView = findViewById(R.id.holdQuantity8);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material8Row2.setVisibility(View.GONE);
        spaceRow8 = findViewById(R.id.spaceRow8);
        spaceRow8.setVisibility(View.GONE);

        material9Row1 = findViewById(R.id.material9Row1);
        material9Row1.setVisibility(View.GONE);
        good = new Good(GoodType.NARCOTICS);
        material9UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material9UnitPrice));
        material9Row2 = findViewById(R.id.material9Row2);
        holdQuantityView = findViewById(R.id.holdQuantity9);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material9Row2.setVisibility(View.GONE);
        spaceRow9 = findViewById(R.id.spaceRow9);
        spaceRow9.setVisibility(View.GONE);

        material10Row1 = findViewById(R.id.material10Row1);
        material10Row1.setVisibility(View.GONE);
        good = new Good(GoodType.ROBOTS);
        material10UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material10UnitPrice));
        material10Row2 = findViewById(R.id.material10Row2);
        holdQuantityView = findViewById(R.id.holdQuantity10);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(good.getGoodType())));
        material10Row2.setVisibility(View.GONE);
    }
}
