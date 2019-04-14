package edu.gatech.cs2340.SpaceTrader.entity;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * An item entry in the market table
 */
public class MarketItem {
    private static final Game game = Game.getInstance();

    public TableRow row1;
    private TextView itemName;
    private TextView totalHeader;
    private TextView totalView;
    public EditText input;

    public TableRow row2;
    private TextView holdHeader;
    public TextView hold;
    private TextView unitHeader;
    private TextView unit;

    public TableRow row3;
    private TextView blank;

    public int amount;
    public Good type;
    public int unitPrice;

    /**
     * Making an item in the market
     * @param typeIndex the type of good being made
     * @param current the current planet
     * @param marketTable the table to put this entry into
     * @param params the layout for the item's rows
     * @param context the place to put the rows
     */
    public MarketItem(int typeIndex, Planet current, TableLayout marketTable,
                      TableRow.LayoutParams params, Context context) {

        type = new Good(GoodType.values()[typeIndex % GoodType.values().length]);
        unitPrice = type.calculatePrice(current);
        amount = 0;

        makeRow1(typeIndex, context, params);
        makeRow2(context, params);
        makeRow3(context, params);

        row1.addView(itemName);
        row1.addView(totalHeader);
        row1.addView(totalView);
        row1.addView(input);
        marketTable.addView(row1);
        row2.addView(holdHeader);
        row2.addView(hold);
        row2.addView(unitHeader);
        row2.addView(unit);
        marketTable.addView(row2);
        row3.addView(blank);
        marketTable.addView(row3);
    }

    /**
     * helper method, makes row 1
     * @param typeIndex the current good type
     * @param context the place to put the row
     * @param params the layout for the row
     */
    private void makeRow1(int typeIndex, Context context, TableRow.LayoutParams params){
        row1 = new TableRow(context);
        row1.setLayoutParams(params);
        row1.setGravity(Gravity.CENTER_HORIZONTAL);
        itemName = new TextView(context);
        itemName.setGravity(Gravity.CENTER);
        itemName.setText(GoodType.values()[typeIndex].toString());
        totalHeader = new TextView(context);
        totalHeader.setGravity(Gravity.CENTER);
        totalHeader.setText("Total: ");
        totalView = new TextView(context);
        totalView.setGravity(Gravity.CENTER);
        totalView.setText("0$");
        input = new EditText(context);
        input.setGravity(Gravity.CENTER);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        input.addTextChangedListener(new TextWatcher() {
            //first two Overrides not needed
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(input.getText())) { input.setText("0"); }
                amount = Integer.parseInt(input.getText().toString());
                totalView.setText(String.valueOf(amount * unitPrice));
            }
        });
    }

    /**
     * helper method, makes row 2
     * @param context the place to put the row
     * @param params the layout for the row
     */
    private void makeRow2(Context context, TableRow.LayoutParams params) {
        row2 = new TableRow(context);
        row2.setLayoutParams(params);
        row2.setGravity(Gravity.CENTER_HORIZONTAL);
        holdHeader = new TextView(context);
        holdHeader.setGravity(Gravity.CENTER);
        holdHeader.setText("In hold: ");
        hold = new TextView(context);
        hold.setGravity(Gravity.CENTER);
        hold.setText(String.valueOf(game.getQuantityOfGood(type.getGoodType())));
        unitHeader = new TextView(context);
        unitHeader.setGravity(Gravity.CENTER);
        unitHeader.setText("$ Per: ");
        unit = new TextView(context);
        unit.setGravity(Gravity.CENTER);
        unit.setText(String.valueOf(unitPrice));
    }

    /**
     * helper method, makes row3
     * @param context the place to put the row
     * @param params the layout for the row
     */
    private void makeRow3(Context context, TableRow.LayoutParams params) {
        row3 = new TableRow(context);
        row3.setLayoutParams(params);
        row3.setGravity(Gravity.CENTER_HORIZONTAL);
        blank = new TextView(context);
        blank.setGravity(Gravity.CENTER);
        blank.setText("- - - - - - - - - - - - - - - - - - - -");
    }
}
