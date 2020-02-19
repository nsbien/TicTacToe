package com.example.tictactoe;
/**
 * This program is the welcome screen for the tic tac toe application.
 * CPSC 312-01, Fall 2019
 * Programming Assignment #5
 * No sources to cite.
 *
 * @author Nicole Bien
 * @version v1.0 10/22/2019
 */
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    static final int LOGIN_REQUEST_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String result1 = data.getStringExtra("player1");
            String result2 = data.getStringExtra("player2");
            TextView text = (TextView) findViewById(R.id.results);
            text.setText("\n\nPlayer 1 Results: " + result1 + "\nPlayer 2 Results: " + result2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText player1 = (EditText) findViewById(R.id.player1NameInput);
                EditText player2 = (EditText) findViewById(R.id.player2NameInput);

                if (!player1.getText().toString().equals(null) && !player1.getText().toString().equals("")
                && !player2.getText().toString().equals(null) && !player2.getText().toString().equals("")) {
                    // takes user input and puts it into String variables
                    String one = player1.getText().toString();
                    String two = player2.getText().toString();
                    // changes activity to GameActivity
                    Intent intent = new Intent(WelcomeActivity.this, GameActivity.class);
                    intent.putExtra("player1", one);
                    intent.putExtra("player2", two);
                    startActivityForResult(intent, LOGIN_REQUEST_CODE);
                } else {
                    Toast.makeText(WelcomeActivity.this, "Please enter names for both players.", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}
