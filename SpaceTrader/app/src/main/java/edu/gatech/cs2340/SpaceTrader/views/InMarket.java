package edu.gatech.cs2340.SpaceTrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.GoodType;
import edu.gatech.cs2340.SpaceTrader.entity.Market;
import edu.gatech.cs2340.SpaceTrader.entity.MarketItem;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;

public class InMarket extends AppCompatActivity {
    //Game-based variables (as opposed to UI-based)
    private Planet current;
    private Game game;
    private Market market;
    private MarketItem[] items;
    private int buying; //0 = neither, 1 = buy, -1 = sell.
    ScrollView scrollView;
    TableLayout marketTable;
    TableRow.LayoutParams params;
    //all the buttons for the market
    private Button buyMode;
    private Button sellMode;
    //input values
    private TextView holdSpaceView;
    private TextView playerCredits;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_screen);

        game = Game.getInstance();

        //storing planet and player info
        current = game.getCurrentPlanet();
        market = current.getMarket();
        items = new MarketItem[GoodType.values().length];
        buying = 0;

        //Initializing marketTable with MarketItems
        marketTable = findViewById(R.id.marketTable);
        marketTable.setGravity(Gravity.CENTER_HORIZONTAL);
        params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < GoodType.values().length; i++) {
            items[i] = new MarketItem(i, current, marketTable, params, this);
        }

        //linking variables to actual views
        scrollView = findViewById(R.id.scrollView);
        buyMode = findViewById(R.id.buyMode);
        sellMode = findViewById(R.id.sellMode);

        //Putting the table in the scrollview
        //scrollView.addView(marketTable);

        //printing planet name
        final TextView nameTextView = findViewById(R.id.marketHeader);
        nameTextView.setText((game.getCurrentPlanetName() + "'s Bazaar"));
        holdSpaceView = findViewById(R.id.holdSpace);
        holdSpaceView.setText(String.valueOf(game.getSpace()));

        //Printing credits
        playerCredits = findViewById(R.id.currentMoney);
        playerCredits.setText(String.valueOf(game.getCredits()));

        setVisibility();
        resetInputs();
        updateHoldQuantity();
    }

    /**
     * Sets any goods that can be bought on the planet as visible,
     * and sets the complete transaction to buy mode
     * @param view the button pressed
     */
    public void onBuyModePressed(View view){
        buying = 1;
        resetInputs();
        setVisibility();
        view.setSelected(true);
        sellMode.setSelected(false);
    }

    /**
     * sets all sellable goods as visible and changes the complete transaction function to sell mode
     * @param view the button pressed
     */
    public void onSellModePressed(View view){
        buying = -1;
        resetInputs();
        setVisibility();
        view.setSelected(true);
        buyMode.setSelected(false);
    }

    /**
     * Gets user inputs, and does the transactions
     * @param view the button
     */
    public void onCompleteTransactionPressed(View view){
        int totalPrice = 0;
        if (buying == 0) {
            shortToast("Buying or Selling?");
        } else if (buying == 1) {
            int totalAmount = 0;
            for (MarketItem marketItem: items) {
                totalPrice += marketItem.amount * marketItem.unitPrice;
                totalAmount += marketItem.amount;
                if (totalAmount > game.getSpace()) {
                    shortToast("Not Enough Space");
                    return;
                }
            }
            if (totalPrice > game.getCredits()) {
                shortToast("Not enough credits");
            } else {
                for (MarketItem marketItem: items) {
                    market.buyItem(marketItem.type.getGoodType(),
                            marketItem.amount, marketItem.unitPrice);
                }
                playerCredits.setText(String.valueOf(game.getCredits()));
                shortToast("Transaction Complete");
                resetInputs();
                updateHoldQuantity();
            }
        } else if (buying == -1) {
            for (MarketItem marketItem: items) {
                if (game.getQuantityOfGood(marketItem.type.getGoodType()) < marketItem.amount) {
                    shortToast("Not enough " + marketItem.type.getGoodType().toString());
                    return;
                }
            }
            for (MarketItem marketItem: items) {
                market.sellItem(marketItem.type.getGoodType(), marketItem.amount,
                        marketItem.unitPrice);
            }
            playerCredits.setText(String.valueOf(game.getCredits()));
            shortToast("Transaction Complete");
            resetInputs();
            updateHoldQuantity();
        } else {
            shortToast("What? How?");
        }
    }

    /**
     * helper method that sets all of the market's good to the proper visibility
     */
    private void setVisibility(){
        for (MarketItem marketItem: items) {
            marketItem.row1.setVisibility(View.GONE);
            marketItem.row2.setVisibility(View.GONE);
            marketItem.row3.setVisibility(View.GONE);
        }
        if (buying == 1) {
            for (MarketItem marketItem: items) {
                if (marketItem.type.canBuy(current.getTechLevelInt())) {
                    marketItem.row1.setVisibility(View.VISIBLE);
                    marketItem.row2.setVisibility(View.VISIBLE);
                    marketItem.row3.setVisibility(View.VISIBLE);
                }
            }
        } else if (buying == -1) {
            for (MarketItem marketItem: items) {
                if (marketItem.type.canBuy(current.getTechLevelInt())) {
                    marketItem.row1.setVisibility(View.VISIBLE);
                    marketItem.row2.setVisibility(View.VISIBLE);
                    marketItem.row3.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * resets all player inputs to 0
     */
    private void resetInputs(){
        for (MarketItem marketItem: items) {
            marketItem.input.setText("0");
            marketItem.amount = 0;
        }
    }

    /**
     * updates the display of each item inHold
     */
    private void updateHoldQuantity(){
        for (MarketItem marketItem: items) {
            marketItem.hold.setText(String.valueOf(game.getQuantityOfGood(
                    marketItem.type.getGoodType())));
        }
        holdSpaceView.setText(String.valueOf(game.getSpace()));
    }

    /**
     * Displays a short message to the user
     * @param message the message to be displayed
     */
    private void shortToast(String message){
        Toast toast = Toast.makeText(InMarket.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}