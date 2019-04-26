package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.SpaceTrader.R;
import edu.gatech.cs2340.SpaceTrader.entity.Game;
import edu.gatech.cs2340.SpaceTrader.entity.Planet;
import edu.gatech.cs2340.SpaceTrader.entity.Player;

public class ThreeChoiceMinigame extends AppCompatActivity {

    private final Game game = Game.getInstance();
    private Planet planet = game.getCurrentPlanet();
    private Player player = game.getPlayer();
    private String stage = "Mayday";
    private int skillThreshold = 8;
    private int profit = 2000;

    private Button choice1;
    private Button choice2;
    private Button choice3;
    private TextView minigameText;
    private TextView minigameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_choice_minigame_screen);

        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        minigameText = findViewById(R.id.minigameText);
        minigameTitle = findViewById(R.id.minigameTitle);

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice1helper();
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice2helper();
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice3helper();
            }
        });
        setChoice();
        update("Help", "Ignore", "Capture", "You have " +
                "received a distress call from an unknown vessel. If you have the food to " +
                "spare, you could probably let the survivors aboard and take them to safety. " +
                "The survivors will be weak prey, so you could easily lock them up to sell later.");
    }
    private void choice1helper() {
        if (stage.equals("Mayday")) {
            help();
        } else if (stage.equals("Hunted")) {
            fight();
        } else if (stage.equals("Deal")) {
            swindle();
        } else {
            end();
        }
    }
    private void choice2helper() {
        if (stage.equals("Mayday")) {
            stage = "Heartless";
            update("", "Continue", "", "You decided not to " +
                    "help those people.");
        } else if (stage.equals("Hunted")) {
            stage = "Coward";
            update("", "Continue", "", "Since your fighting " +
                    "abilities leave much to be desired, You decided to give " +
                    "your hostages away to avoid further conflict");
        } else if (stage.equals("Deal")) {
            help();
        } else if (stage.equals("Not Fooled")) {
            fight();
        } else {
            end();
        }
        setContinue();
    }

    private void choice3helper() {
        if (stage.equals("Mayday")) {
            capture();
        } else if (stage.equals("Hunted")) {
            deal();
        } else if (stage.equals("Deal")) {
            honest();
        } else {
            end();
        }
    }

    private void help() {
        stage = "Help";
        update("","Continue","","You land on "
                + planet.getName() + ". The man thanks you for your aid, and informs you " +
                "that 'If we ever meet again, I'll be happy to give you a discount on my " +
                "wares!'");
        setContinue();
        game.setHelpedMerchant(true);
    }
    private void capture() {
        stage = "Hunted";
        setChoice();
        update("Fight", "Flee", "Deal", "You bundle the " +
                "man up into a sack, and bring him into your ship. After settling into your " +
                "seat, you feel a large shaking of your ship. Through the airlock come two " +
                "heavily armored mercenaries. They tell you that they're taking your prisoner " +
                "to sell for themselves.");
    }
    private void fight() {
        if (player.getFighterSkill() >= skillThreshold) {
            stage = "Victory";
            update("", "Continue", "", "The mercenaries flee," +
                    " leaving you to sell your prisoner. Luckily, you were able to find a slave " +
                    "market on " + planet.getName() + ", and you made off " +
                    profit + " credits richer!");
            player.setCredits(player.getCredits() + profit);
            setContinue();
        } else {
            stage = "Hunted";
            choice2helper();
        }
    }
    private void deal() {
        stage = "Deal";
        setChoice();
        update("Swindle", "Rat", "Split", "" +
                "You convince the mercenaries to split the profits with you to avoid any " +
                "conflict. Their dealer docks with your ship, and greets you. 'Always glad " +
                "to help out someone in a rough situation' she says. 'Such a shame that this " +
                "guy needs to sell himself into slavery just to help out his family.'");
    }
    private void swindle() {
        if (player.getTraderSkill() >= skillThreshold) {
            stage = "Swindled";
            update("","Continue","","You convince the dealer" +
                    " to wire you the funds directly, explaining that you'll pass on the " +
                    "mercenaries' shares to them. Before they can collect from you though, " +
                    "you give them the slip, getting out of there " + profit + " credits richer!");
            player.setCredits(player.getCredits() + profit);
            setContinue();
        } else {
            setContinue();
            stage = "Not Fooled";
            update("","Continue","","The dealer is not " +
                    "fooled by your swindling, and the two mercenaries look at you menacingly...");
        }
    }
    private void honest() {
        setContinue();
        stage = "Split";
        update("","Continue","","The mercenaries and you " +
                "split the money between yourselves. Right before they leave, one turns back and " +
                "says, 'Pleasure doing business'. You've made it out " + (profit/2) +
                " credits richer!");
        player.setCredits(player.getCredits() + (profit/2));
    }
    private void update(String but1Text, String but2Text, String but3Text, String flavorText) {
        choice1.setText(but1Text);
        choice2.setText(but2Text);
        choice3.setText(but3Text);
        minigameTitle.setText(stage);
        minigameText.setText(flavorText);
    }
    private void setContinue() {
        choice1.setVisibility(View.GONE);
        choice3.setVisibility(View.GONE);
    }
    private void setChoice() {
        choice1.setVisibility(View.VISIBLE);
        choice3.setVisibility(View.VISIBLE);
    }
    private void end() {
        Intent intent = new Intent(ThreeChoiceMinigame.this, game.getNextScreen());
        startActivity(intent);
    }
}
