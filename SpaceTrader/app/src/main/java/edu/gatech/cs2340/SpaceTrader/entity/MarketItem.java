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

public class MarketItem {
    private static final Game game = Game.getInstance();

    public TableRow row1;
    private TextView totalView;
    public EditText input;

    public TableRow row2;
    public TextView hold;

    public TableRow row3;

    public int amount;
    public Good type;
    public int unitPrice;

    public MarketItem(int typeIndex, Planet current, TableLayout marketTable,
                      TableRow.LayoutParams params, Context context) {

        type = new Good(GoodType.values()[typeIndex % GoodType.values().length]);
        unitPrice = type.calculatePrice(current);
        amount = 0;

        row1 = new TableRow(context);
        row1.setLayoutParams(params);
        row1.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView itemName = new TextView(context);
        itemName.setGravity(Gravity.CENTER);
        itemName.setText(GoodType.values()[typeIndex].toString());
        TextView totalHeader = new TextView(context);
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

        row2 = new TableRow(context);
        row2.setLayoutParams(params);
        row2.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView holdHeader = new TextView(context);
        holdHeader.setGravity(Gravity.CENTER);
        holdHeader.setText("In hold: ");
        hold = new TextView(context);
        hold.setGravity(Gravity.CENTER);
        hold.setText(String.valueOf(game.getQuantityOfGood(type.getGoodType())));
        TextView unitHeader = new TextView(context);
        unitHeader.setGravity(Gravity.CENTER);
        unitHeader.setText("$ Per: ");
        TextView unit = new TextView(context);
        unit.setGravity(Gravity.CENTER);
        unit.setText(String.valueOf(unitPrice));

        row3 = new TableRow(context);
        row3.setLayoutParams(params);
        row3.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView blank = new TextView(context);
        blank.setGravity(Gravity.CENTER);
        blank.setText("- - - - - - - - - - - - - - - - - - - -");

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
}
