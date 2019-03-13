package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Good;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Market;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

import static edu.gatech.cs2340.SpaceTrader.entity.GoodType.*;

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
    //all the different inputs
    protected EditText quantity1Input;
    protected EditText quantity2Input;
    protected EditText quantity3Input;
    protected EditText quantity4Input;
    protected EditText quantity5Input;
    protected EditText quantity6Input;
    protected EditText quantity7Input;
    protected EditText quantity8Input;
    protected EditText quantity9Input;
    protected EditText quantity10Input;
    //input values
    int numWater, numFur, numFood, numOre, numGames, numFirearms, numMedicine, numMachines, numNarcotics, numRobots;
    //other
    protected TextView unitPriceView;
    protected TextView holdQuantityView;
    protected TextView holdSpaceView;
    protected TextView playerCredits;
    protected Good good;
    protected Market market;
    private int buying; //0 = neither, 1 = buy, -1 = sell. It's an int rather than a boolean so that there can be a "null" state, where you are neither in buy or sell mode.

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
        market = current.getMarket();
        player = Game.getInstance().getPlayer();
        buying = 0;

        //printing planet name
        final TextView nameTextView = findViewById(R.id.marketHeader);
        nameTextView.setText(current.getName() + "'s Bazaar");
        holdSpaceView = findViewById(R.id.holdSpace);
        holdSpaceView.setText(String.valueOf(player.getShip().getSpace()));

        //Printing credits
        playerCredits = findViewById(R.id.currentMoney);
        playerCredits.setText(String.valueOf(player.getCredits()));

        //initializing number of each good
        numWater = 0; numFur = 0; numFood = 0; numOre = 0; numGames = 0;
        numFirearms = 0; numMedicine = 0; numMachines = 0; numNarcotics = 0; numRobots = 0;

        //saving each row for visibility purposes and calculating the unitPrices of each material
        //material*Row1 children (materialName, "Total:", totalAmount, unitAmount)
        //material*Row2 children ("In Hold:", holdTotal, "Quantity:", quantity)
        material1Row1 = findViewById(R.id.material1Row1);
        unitPriceView = findViewById(R.id.item1Unit);
        good = new Good(WATER);
        material1UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material1UnitPrice));
        material1Row2 = findViewById(R.id.material1Row2);
        spaceRow1 = findViewById(R.id.spaceRow1);

        material2Row1 = findViewById(R.id.material2Row1);
        unitPriceView = findViewById(R.id.item2Unit);
        good = new Good(GoodType.FURS);
        material2UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material2UnitPrice));
        material2Row2 = findViewById(R.id.material2Row2);
        spaceRow2 = findViewById(R.id.spaceRow2);

        material3Row1 = findViewById(R.id.material3Row1);
        unitPriceView = findViewById(R.id.item3Unit);
        good = new Good(GoodType.FOOD);
        material3UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material3UnitPrice));
        material3Row2 = findViewById(R.id.material3Row2);
        spaceRow3 = findViewById(R.id.spaceRow3);

        material4Row1 = findViewById(R.id.material4Row1);
        unitPriceView = findViewById(R.id.item4Unit);
        good = new Good(GoodType.ORE);
        material4UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material4UnitPrice));
        material4Row2 = findViewById(R.id.material4Row2);
        spaceRow4 = findViewById(R.id.spaceRow4);

        material5Row1 = findViewById(R.id.material5Row1);
        unitPriceView = findViewById(R.id.item5Unit);
        good = new Good(GoodType.GAMES);
        material5UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material5UnitPrice));
        material5Row2 = findViewById(R.id.material5Row2);
        spaceRow5 = findViewById(R.id.spaceRow5);

        material6Row1 = findViewById(R.id.material6Row1);
        unitPriceView = findViewById(R.id.item6Unit);
        good = new Good(GoodType.FIREARMS);
        material6UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material6UnitPrice));
        material6Row2 = findViewById(R.id.material6Row2);
        spaceRow6 = findViewById(R.id.spaceRow6);

        material7Row1 = findViewById(R.id.material7Row1);
        unitPriceView = findViewById(R.id.item7Unit);
        good = new Good(GoodType.MEDICINE);
        material7UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material7UnitPrice));
        material7Row2 = findViewById(R.id.material7Row2);
        spaceRow7 = findViewById(R.id.spaceRow7);

        material8Row1 = findViewById(R.id.material8Row1);
        unitPriceView = findViewById(R.id.item8Unit);
        good = new Good(GoodType.MACHINES);
        material8UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material8UnitPrice));
        material8Row2 = findViewById(R.id.material8Row2);
        spaceRow8 = findViewById(R.id.spaceRow8);

        material9Row1 = findViewById(R.id.material9Row1);
        unitPriceView = findViewById(R.id.item9Unit);
        good = new Good(GoodType.NARCOTICS);
        material9UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material9UnitPrice));
        material9Row2 = findViewById(R.id.material9Row2);
        spaceRow9 = findViewById(R.id.spaceRow9);

        material10Row1 = findViewById(R.id.material10Row1);
        unitPriceView = findViewById(R.id.item10Unit);
        good = new Good(GoodType.ROBOTS);
        material10UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material10UnitPrice));
        material10Row2 = findViewById(R.id.material10Row2);

        //Assigning each EditText
        quantity1Input = findViewById(R.id.quantity1);
        quantity2Input = findViewById(R.id.quantity2);
        quantity3Input = findViewById(R.id.quantity3);
        quantity4Input = findViewById(R.id.quantity4);
        quantity5Input = findViewById(R.id.quantity5);
        quantity6Input = findViewById(R.id.quantity6);
        quantity7Input = findViewById(R.id.quantity7);
        quantity8Input = findViewById(R.id.quantity8);
        quantity9Input = findViewById(R.id.quantity9);
        quantity10Input = findViewById(R.id.quantity10);

        //Hiding all goods until buyMode or sellMode
        setInvisible();
        //setting all input values to 0
        resetInputs();
        //setting the amounts in hold properly
        updateHoldQuantity();
    }

    /**
     * Sets any goods that can be bought on the planet as visible,
     * and sets the complete transaction to buy mode
     * @param view the button pressed
     */
    public void onBuyModePressed(View view){
        setInvisible();
        resetInputs();
        buying = 1;
        if (new Good(WATER).canBuy(current.getTechLevelInt())) {
            material1Row1.setVisibility(View.VISIBLE);
            material1Row2.setVisibility(View.VISIBLE);
            spaceRow1.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FURS).canBuy(current.getTechLevelInt())) {
            material2Row1.setVisibility(View.VISIBLE);
            material2Row2.setVisibility(View.VISIBLE);
            spaceRow2.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FOOD).canBuy(current.getTechLevelInt())) {
            material3Row1.setVisibility(View.VISIBLE);
            material3Row2.setVisibility(View.VISIBLE);
            spaceRow3.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.ORE).canBuy(current.getTechLevelInt())) {
            material4Row1.setVisibility(View.VISIBLE);
            material4Row2.setVisibility(View.VISIBLE);
            spaceRow4.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.GAMES).canBuy(current.getTechLevelInt())) {
            material5Row1.setVisibility(View.VISIBLE);
            material5Row2.setVisibility(View.VISIBLE);
            spaceRow5.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FIREARMS).canBuy(current.getTechLevelInt())) {
            material6Row1.setVisibility(View.VISIBLE);
            material6Row2.setVisibility(View.VISIBLE);
            spaceRow6.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.MEDICINE).canBuy(current.getTechLevelInt())) {
            material7Row1.setVisibility(View.VISIBLE);
            material7Row2.setVisibility(View.VISIBLE);
            spaceRow7.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.MACHINES).canBuy(current.getTechLevelInt())) {
            material8Row1.setVisibility(View.VISIBLE);
            material8Row2.setVisibility(View.VISIBLE);
            spaceRow8.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.NARCOTICS).canBuy(current.getTechLevelInt())) {
            material9Row1.setVisibility(View.VISIBLE);
            material9Row2.setVisibility(View.VISIBLE);
            spaceRow9.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.ROBOTS).canBuy(current.getTechLevelInt())) {
            material10Row1.setVisibility(View.VISIBLE);
            material10Row2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * sets all sellable goods as visible and changes the complete transaction function to sell mode
     * @param view the button pressed
     */
    public void onSellModePressed(View view){
        setInvisible();
        resetInputs();
        buying = -1;
        if (new Good(WATER).canSell(current.getTechLevelInt())) {
            material1Row1.setVisibility(View.VISIBLE);
            material1Row2.setVisibility(View.VISIBLE);
            spaceRow1.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FURS).canSell(current.getTechLevelInt())) {
            material2Row1.setVisibility(View.VISIBLE);
            material2Row2.setVisibility(View.VISIBLE);
            spaceRow2.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FOOD).canSell(current.getTechLevelInt())) {
            material3Row1.setVisibility(View.VISIBLE);
            material3Row2.setVisibility(View.VISIBLE);
            spaceRow3.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.ORE).canSell(current.getTechLevelInt())) {
            material4Row1.setVisibility(View.VISIBLE);
            material4Row2.setVisibility(View.VISIBLE);
            spaceRow4.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.GAMES).canSell(current.getTechLevelInt())) {
            material5Row1.setVisibility(View.VISIBLE);
            material5Row2.setVisibility(View.VISIBLE);
            spaceRow5.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.FIREARMS).canSell(current.getTechLevelInt())) {
            material6Row1.setVisibility(View.VISIBLE);
            material6Row2.setVisibility(View.VISIBLE);
            spaceRow6.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.MEDICINE).canSell(current.getTechLevelInt())) {
            material7Row1.setVisibility(View.VISIBLE);
            material7Row2.setVisibility(View.VISIBLE);
            spaceRow7.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.MACHINES).canSell(current.getTechLevelInt())) {
            material8Row1.setVisibility(View.VISIBLE);
            material8Row2.setVisibility(View.VISIBLE);
            spaceRow8.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.NARCOTICS).canSell(current.getTechLevelInt())) {
            material9Row1.setVisibility(View.VISIBLE);
            material9Row2.setVisibility(View.VISIBLE);
            spaceRow9.setVisibility(View.VISIBLE);
        }
        if (new Good(GoodType.ROBOTS).canSell(current.getTechLevelInt())) {
            material10Row1.setVisibility(View.VISIBLE);
            material10Row2.setVisibility(View.VISIBLE);
        }
    }
    public void onCompleteTransactionPressed(View view){
        //Gets all the user-inputted values
        numWater = Integer.parseInt(quantity1Input.getText().toString());
        numFur = Integer.parseInt(quantity2Input.getText().toString());
        numFood = Integer.parseInt(quantity3Input.getText().toString());
        numOre = Integer.parseInt(quantity4Input.getText().toString());
        numGames = Integer.parseInt(quantity5Input.getText().toString());
        numFirearms = Integer.parseInt(quantity6Input.getText().toString());
        numMedicine = Integer.parseInt(quantity7Input.getText().toString());
        numMachines = Integer.parseInt(quantity8Input.getText().toString());
        numNarcotics = Integer.parseInt(quantity9Input.getText().toString());
        numRobots = Integer.parseInt(quantity10Input.getText().toString());
        //Checks to see if a Mode was selected
        if (buying == 0) {
            Toast toast = Toast.makeText(InMarket.this, "Buying or Selling?", Toast.LENGTH_SHORT);
            toast.show();
        //buyMode
        } else if (buying == 1) {
            //Checks if the player can afford
            if ((material1UnitPrice * numWater) + (material2UnitPrice * numFur) +
                    (material3UnitPrice * numFood) + (material4UnitPrice * numOre) +
                    (material5UnitPrice * numGames) + (material6UnitPrice * numFirearms) +
                    (material7UnitPrice * numMedicine) + (material8UnitPrice * numMachines) +
                    (material9UnitPrice * numNarcotics) + (material10UnitPrice * numRobots) > player.getCredits()) {
                Toast toast = Toast.makeText(InMarket.this, "Cannot Afford", Toast.LENGTH_SHORT);
                toast.show();
            //Checks if player has space
            } else if (numWater + numFur + numFood + numOre + numGames + numFirearms +
                    numMedicine + numMachines + numNarcotics + numRobots > player.getShip().getSpace()) {
                Toast toast = Toast.makeText(InMarket.this, "Not enough Space", Toast.LENGTH_SHORT);
                toast.show();
            //Buys Items, reprints amount in hold, and reprints player's money
            } else {
                market.buyItem(WATER, numWater, material1UnitPrice); market.buyItem(FURS, numFur, material2UnitPrice);
                market.buyItem(FOOD, numFood, material3UnitPrice); market.buyItem(ORE, numOre, material4UnitPrice);
                market.buyItem(GAMES, numGames, material5UnitPrice); market.buyItem(FIREARMS, numFirearms, material6UnitPrice);
                market.buyItem(MEDICINE, numMedicine, material7UnitPrice); market.buyItem(MACHINES, numMachines, material8UnitPrice);
                market.buyItem(NARCOTICS, numNarcotics, material9UnitPrice); market.buyItem(ROBOTS, numRobots, material10UnitPrice);
                playerCredits.setText(String.valueOf(player.getCredits()));
                holdSpaceView.setText(String.valueOf(player.getShip().getSpace()));
                Toast toast = Toast.makeText(InMarket.this, "Transaction Complete", Toast.LENGTH_SHORT);
                toast.show();
                resetInputs();
                updateHoldQuantity();
            }
        //sellMode
        } else if (buying == -1) {

        //This can't happen, but if it does...
        } else {
            Toast toast = Toast.makeText(InMarket.this, "What? How?", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * helper method that sets all of the market to gone before each mode change and at the end
     * of onCreate()
     */
    public void setInvisible(){
        material1Row1.setVisibility(View.GONE);
        material1Row2.setVisibility(View.GONE);
        spaceRow1.setVisibility(View.GONE);
        material2Row1.setVisibility(View.GONE);
        material2Row2.setVisibility(View.GONE);
        spaceRow2.setVisibility(View.GONE);
        material3Row1.setVisibility(View.GONE);
        material3Row2.setVisibility(View.GONE);
        spaceRow3.setVisibility(View.GONE);
        material4Row1.setVisibility(View.GONE);
        material4Row2.setVisibility(View.GONE);
        spaceRow4.setVisibility(View.GONE);
        material5Row1.setVisibility(View.GONE);
        material5Row2.setVisibility(View.GONE);
        spaceRow5.setVisibility(View.GONE);
        material6Row1.setVisibility(View.GONE);
        material6Row2.setVisibility(View.GONE);
        spaceRow6.setVisibility(View.GONE);
        material7Row1.setVisibility(View.GONE);
        material7Row2.setVisibility(View.GONE);
        spaceRow7.setVisibility(View.GONE);
        material8Row1.setVisibility(View.GONE);
        material8Row2.setVisibility(View.GONE);
        spaceRow8.setVisibility(View.GONE);
        material9Row1.setVisibility(View.GONE);
        material9Row2.setVisibility(View.GONE);
        spaceRow9.setVisibility(View.GONE);
        material10Row1.setVisibility(View.GONE);
        material10Row2.setVisibility(View.GONE);
    }

    /**
     * resets all player inputs to 0, used in same places as setInvisible()
     */
    public void resetInputs(){
        quantity1Input.setText("0");
        quantity2Input.setText("0");
        quantity3Input.setText("0");
        quantity4Input.setText("0");
        quantity5Input.setText("0");
        quantity6Input.setText("0");
        quantity7Input.setText("0");
        quantity8Input.setText("0");
        quantity9Input.setText("0");
        quantity10Input.setText("0");
    }

    /**
     * updates the amount of each item in the player's ship in the display of the market
     */
    public void updateHoldQuantity(){
        holdQuantityView = findViewById(R.id.holdQuantity1);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(WATER)));
        holdQuantityView = findViewById(R.id.holdQuantity2);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(FURS)));
        holdQuantityView = findViewById(R.id.holdQuantity3);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(FOOD)));
        holdQuantityView = findViewById(R.id.holdQuantity4);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(ORE)));
        holdQuantityView = findViewById(R.id.holdQuantity5);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(GAMES)));
        holdQuantityView = findViewById(R.id.holdQuantity6);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(FIREARMS)));
        holdQuantityView = findViewById(R.id.holdQuantity7);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(MEDICINE)));
        holdQuantityView = findViewById(R.id.holdQuantity8);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(MACHINES)));
        holdQuantityView = findViewById(R.id.holdQuantity9);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(NARCOTICS)));
        holdQuantityView = findViewById(R.id.holdQuantity10);
        holdQuantityView.setText(String.valueOf(player.getShip().getGoodQuantity(ROBOTS)));
    }
}
