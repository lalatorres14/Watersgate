package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Good;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Market;
import edu.gatech.cs2340.SpaceTrader.entity.MarketItem;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

import static edu.gatech.cs2340.SpaceTrader.entity.GoodType.*;

public class CopyInMarket extends AppCompatActivity {
    //Game-based variables (as opposed to UI-based)
    private Planet current;
    private static final Game game = Game.getInstance();
    //The market table
    private ScrollView scrollView;
    private TableLayout marketTable;
    private TableRow.LayoutParams params;
    //all the buttons for the market
    private Button buyMode;
    private Button sellMode;
    private Button completeTransaction;
    //input values
    private TextView holdSpaceView;
    private TextView playerCredits;
    private Market market;
    private int buying; //0 = neither, 1 = buy, -1 = sell. It's an int rather than a boolean so
    // that there can be a "null" state, where you are neither in buy or sell mode.

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_market_screen);

        //storing planet and player info
        current = game.getCurrentPlanet();
        market = current.getMarket();
        buying = 0;

        //Initializing marketTable with MarketItems
        marketTable = findViewById(R.id.marketTable);
        marketTable.setGravity(Gravity.CENTER_HORIZONTAL);
        params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < GoodType.values().length; i++) {
            new MarketItem(i, current, marketTable, params, this);
        }

        //linking variables to actual buttons
        scrollView = findViewById(R.id.scrollView);
        buyMode = findViewById(R.id.buyMode);
        sellMode = findViewById(R.id.sellMode);
        completeTransaction = findViewById(R.id.completeTransaction);
        //Putting the table in the scrollview
        scrollView.addView(marketTable);

        //printing planet name
        final TextView nameTextView = findViewById(R.id.marketHeader);
        nameTextView.setText(game.getCurrentPlanetName() + "'s Bazaar");
        holdSpaceView = findViewById(R.id.holdSpace);
        holdSpaceView.setText(String.valueOf(game.getSpace()));

        //Printing credits
        playerCredits = findViewById(R.id.currentMoney);
        playerCredits.setText(String.valueOf(game.getCredits()));

        //initializing number of each good
        numWater = 0;

        material1Row1 = findViewById(R.id.material1Row1);

        TextView unitPriceView = findViewById(R.id.item1Unit);
        Good good = new Good(WATER);
        material1UnitPrice = good.calculatePrice(current);
        unitPriceView.setText(String.valueOf(material1UnitPrice));
        material1Row2 = findViewById(R.id.material1Row2);
        spaceRow1 = findViewById(R.id.spaceRow1);

        quantity1Input = findViewById(R.id.quantity1);

        total1View = findViewById(R.id.item1TotalAmount);
        setInvisible();
        resetInputs();
        updateHoldQuantity();
        //Setting up the TextListeners
        quantity1Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //n/a
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //n/a
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(quantity1Input.getText())) {
                    quantity1Input.setText("0");
                }
                total1View.setText(String.valueOf(Integer.parseInt(
                        quantity1Input.getText().toString()) * material1UnitPrice));
            }
        });
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
        view.setSelected(true);
        sellMode.setSelected(false);
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
        view.setSelected(true);
        buyMode.setSelected(false);
    }

    /**
     * Gets user inputs, and does the transactions
     * @param view the button
     */
    public void onCompleteTransactionPressed(View view){
        //Gets all the user-inputted values
        numWater = Integer.parseInt(quantity1Input.getText().toString());
        //Checks to see if a Mode was selected
        if (buying == 0) {
            shortToast("Buying or Selling?");
            //buyMode
        } else if (buying == 1) {
            //Checks if the player can afford
            if ((material1UnitPrice * numWater) > game.getCredits()) {
                shortToast("Cannot Afford");
                //Checks if player has space
            } else if ((numWater) > game.getSpace()) {
                shortToast("Not enough Space");
                //Buys Items, reprints amount in hold, and reprints player's money
            } else {
                market.buyItem(WATER, numWater, material1UnitPrice);
                playerCredits.setText(String.valueOf(game.getCredits()));
                holdSpaceView.setText(String.valueOf(game.getSpace()));
                shortToast("Transaction Complete");
                resetInputs();
                updateHoldQuantity();
            }
            //sellMode
        } else if (buying == -1) {
            //Checks each quantity individually to give detailed feedback to user
            if (game.getQuantityOfGood(WATER) < numWater) {
                shortToast("Not enough water");
            } else {
                market.sellItem(WATER, numWater, material1UnitPrice);
                playerCredits.setText(String.valueOf(game.getCredits()));
                holdSpaceView.setText(String.valueOf(game.getSpace()));
                shortToast("Transaction Complete");
                resetInputs();
                updateHoldQuantity();
            }
            //This can't happen, but if it does...
        } else {
            shortToast("What? How?");
        }
    }

    /**
     * helper method that sets all of the market to gone before each mode change and at the end
     * of onCreate()
     */
    private void setInvisible(){
        material1Row1.setVisibility(View.GONE);
        material1Row2.setVisibility(View.GONE);
        spaceRow1.setVisibility(View.GONE);
    }

    /**
     * resets all player inputs to 0, used in same places as setInvisible()
     */
    private void resetInputs(){
        quantity1Input.setText("0");
    }

    /**
     * updates the amount of each item in the player's ship in the display of the market
     */
    private void updateHoldQuantity(){
        TextView holdQuantityView = findViewById(R.id.holdQuantity1);
        holdQuantityView.setText(String.valueOf(game.getQuantityOfGood(WATER)));
    }

    /**
     * Displays a short message to the user
     * @param message the message to be displayed
     */
    private void shortToast(String message){
        Toast toast = Toast.makeText(CopyInMarket.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}