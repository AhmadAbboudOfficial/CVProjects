package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: yellow, 1: red, 2:empty

    private int[] gameState = {2,2,2, 2,2,2, 2,2,2};
    //all possible winning positions, all possibilities which are one of 3 rows, one of 3 columns or 2 diagonals
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8}, //rows
                                {0,3,6},{1,4,7},{2,5,8}, //columns
                                {0,4,8},{2,4,6}}; //diagonals
    private int activePlayer = 0;
    //to stop game after having a winner
    private boolean gameActive = true;
    //if no winner
    private static int taps_count = 0;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        //images are tagged from 0 to 8
        int tappedImage = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedImage] == 2 && gameActive) {
            //tracking tapped images through an array.
            gameState[tappedImage] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            //after tapping
            this.taps_count++;
            //checking all possible positions and not empty
            String winner = "";
            for (int[] winningPositions : winningPositions) {
                //possibility of rows
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2) {
                    //winning player
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "yellow ";
                    }else{
                        winner = "red ";
                    }
                    //Toast.makeText(this, winner + " player has won!", Toast.LENGTH_LONG).show();
                    //after having a winner playAgain button and winner text appears with winning message
                    Button playAgainbtn = findViewById(R.id.btn_playAgain);

                    TextView winningTextView  = findViewById(R.id.txt_winner);

                    winningTextView.setText(winner + getString(R.string.player_has_won));
                    playAgainbtn.setVisibility(View.VISIBLE);
                    winningTextView.setVisibility(View.VISIBLE);
                }else{
                    //no winning player
                    winner = "none";
                }
            }
            if (taps_count == 9 && winner.equals("none")){
                Button playAgainbtn = findViewById(R.id.btn_playAgain);

                TextView winningTextView  = findViewById(R.id.txt_winner);

                winningTextView.setText("No winner yet!");

                playAgainbtn.setVisibility(View.VISIBLE);
                winningTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view){
        Button playAgainbtn = findViewById(R.id.btn_playAgain);

        TextView winningTextView  = findViewById(R.id.txt_winner);

        playAgainbtn.setVisibility(View.INVISIBLE);
        winningTextView.setVisibility(View.INVISIBLE);
        //accidental bug :P 
        androidx.gridlayout.widget.GridLayout gridLayout =  findViewById(R.id.gridLayout);

        for (int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for (int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        //all possible winning positions, all possibilities which are one of 3 rows, one of 3 columns or 2 diagonals
        activePlayer = 0;
        //to stop game after having a winner
        gameActive = true;
        //count again
        taps_count = 0;
    }
}