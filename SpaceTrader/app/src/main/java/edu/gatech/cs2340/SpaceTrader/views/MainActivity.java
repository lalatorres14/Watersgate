package edu.gatech.cs2340.SpaceTrader.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.SpaceTrader.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //public static final int ADD_COURSE_REQUEST_ID = 1;
        Button button = findViewById(R.id.ready_player_one);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartPressed();
            }
        });

    }
    public void onStartPressed(){
        Intent intent = new Intent(MainActivity.this, CreatePlayer.class);
        startActivity(intent);
    }

}
