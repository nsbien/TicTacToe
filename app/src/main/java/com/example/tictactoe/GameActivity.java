package com.example.tictactoe;
/**
 * This program is the main driver program for the tic tac toe board game.
 * CPSC 312-01, Fall 2019
 * Programming Assignment #5
 * No sources to cite.
 *
 * @author Nicole Bien
 * @version v1.0 10/22/2019
 */
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    static char player = 'X';
    static String player1 = "p1";
    static String player2 = "p2";
    static int x = 0;
    static int y = 0;

    TicTacToeBoard game = new TicTacToeBoard();
    GameStats statsX = new GameStats('X');
    GameStats statsY = new GameStats('Y');

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_board);

        game = createGameBoard();

        // gathers user input from WelcomeActivity
        Intent intent = getIntent();
        if (intent != null) {
            player1 = intent.getStringExtra("player1");
            player2 = intent.getStringExtra("player2");
        }

        // closes GameActivity and goes back to WelcomeActivity screen
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("player1", statsX.printMe());
                intent.putExtra("player2", statsY.printMe());
                setResult(Activity.RESULT_OK, intent);
                GameActivity.this.finish();
            }
        });

        // resets the gameboard for a new game
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        // chooses random player, initially prints this status to the screen
        TextView updatePlayerText = (TextView) findViewById(R.id.updateStatusText);
        ImageView playerTurnPreview = (ImageView) findViewById(R.id.playerTurnPreview);
        player = selectRandomly();
        if (player == 'X') {
            updatePlayerText.setText(player1 + "'s turn ;)");
            playerTurnPreview.setImageResource(R.drawable.hippopotamus);
        } else {
            updatePlayerText.setText(player2 + "'s turn (;");
            playerTurnPreview.setImageResource(R.drawable.sheep);
        }
    }

    /**
     * changes the interface when ImageView's are clicked
     */
    public void onClickImageView(View view) {
        ImageView square = (ImageView) view;
        if (square.getDrawable() == null) {
            if (player == 'X') {
                String[] intArray = square.getTag().toString().split(" ");
                x = Integer.parseInt(intArray[0]);
                y = Integer.parseInt(intArray[1]);

                game.makeMove(new Coordinates(x, y), player);
                square.setImageResource(R.drawable.hippopotamus);
                if (determineKeepPlaying(game, statsX, statsY, player)) {
                    player = changePlayer(player);
                }
            } else {
                String[] intArray = square.getTag().toString().split(" ");
                x = Integer.parseInt(intArray[0]);
                y = Integer.parseInt(intArray[1]);

                game.makeMove(new Coordinates(x, y), player);
                square.setImageResource(R.drawable.sheep);
                if (determineKeepPlaying(game, statsX, statsY, player)) {
                    player = changePlayer(player);
                }
            }
        } else {
            Toast.makeText(this, "already clicked. pick a new spot!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * generates a random integer that will be used to determine which player goes first
     * @return character ('X' or 'O') based on the random number generator
     */
    public static char selectRandomly() {
        char player;
        Random random = new Random();
        int randInt = random.nextInt(10);
        if (randInt % 2 == 0) {
            player = 'O';
        } else {
            player = 'X';
        }
        return player;
    }
    /**
     * creates a game board
     * @return TicTacToeBoard of the current game
     */
    public static TicTacToeBoard createGameBoard() {
        TicTacToeBoard game = new TicTacToeBoard();
        return game;
    }

    /**
     * Checks which player was playing, switches turn to other player
     * @return a new player ('X' or 'O')
     */
    public char changePlayer(char player) {
        TextView updatePlayerText = (TextView) findViewById(R.id.updateStatusText);
        ImageView playerTurnPreview = (ImageView) findViewById(R.id.playerTurnPreview);
        if (player == 'X') {
            updatePlayerText.setText(player2 + "'s turn (;");
            playerTurnPreview.setImageResource(R.drawable.sheep);
            return 'O';
        } else {
            updatePlayerText.setText(player1 + "'s turn (;");
            playerTurnPreview.setImageResource(R.drawable.hippopotamus);
            return 'X';
        }
    }

    /**
     * determines if a player has won or if it was a scratch game. Updates Game Stats if so.
     * @param game: the current TicTacToeBoard to be used
     * @param statsX: variable for player 'X' statistics
     * @param statsY: variable for player 'Y' statistics
     * @param player: the player's character symbol ('X' or 'O')
     * @return true if the game(round) should continue, or if it should stop
     */
    public boolean determineKeepPlaying(TicTacToeBoard game, GameStats statsX, GameStats statsY, char player) {
        TextView updatePlayerText = (TextView) findViewById(R.id.updateStatusText);
        ImageView playerTurnPreview = (ImageView) findViewById(R.id.playerTurnPreview);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        if (game.isWinner(player)) {
            if (player == 'X') {
                updatePlayerText.setText("Congratz " + player1 + "! You Won! Play again? ;)");
                playerTurnPreview.setImageResource(R.drawable.hippopotamus);
                statsX.addWin();
                statsY.addLoss();
            } else {
                updatePlayerText.setText("Congratz " + player2 + "! You Won! Play again? ;)");
                playerTurnPreview.setImageResource(R.drawable.sheep);
                statsX.addLoss();
                statsY.addWin();
            }
            playAgainButton.setVisibility(View.VISIBLE);
            return false;
        } else if (game.determineCatScratch()) {
            playerTurnPreview.setImageResource(R.color.colorPrimary);
            statsX.addScratch();
            statsY.addScratch();
            updatePlayerText.setText("Boo. No one won. Play again? ;)");
            playAgainButton.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
}
