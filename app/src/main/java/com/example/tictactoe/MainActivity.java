package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


public class MainActivity extends AppCompatActivity {
    //0:red 1:yellow 2:empty
    int[] state= {2,2,2,2,2,2,2,2,2};
    int[][] winning = {{0,4,8},{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6}};
    int player=0;
    boolean gameActive=true;
    public void fadein(View view) {

        ImageView counter = (ImageView) view;
        String winner = "";
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (state[tappedCounter] == 2 && gameActive) {
            state[tappedCounter] = player;
            counter.setTranslationY(-1500);

            if (player == 0) {
                counter.setImageResource(R.drawable.zero);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                player = 0;
            }
            counter.animate().translationYBy(1500).rotation(720).setDuration(300);

            for (int[] winningposition : winning) {

                if (state[winningposition[0]] == state[winningposition[1]] && state[winningposition[1]] == state[winningposition[2]] && state[winningposition[0]] != 2) {
                    gameActive = false;


                    if (player == 0) {
                        winner = "Player 2 Has Won The Game";
                    }
                    if(player==1){
                        winner= "Player 1 Has Won The Game";
                    }

                    Button playagain = (Button) findViewById(R.id.button);
                    TextView result = (TextView) findViewById((R.id.textView2));
                    result.setText(winner);
                    playagain.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                }


            }


        }
    }
    public void playAgain(View view) {

        Button playagain = (Button) findViewById(R.id.button);

        TextView result = (TextView) findViewById(R.id.textView2);

        playagain.setVisibility(View.INVISIBLE);

        result.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayoutTic);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<9; i++) {

            state[i] = 2;

        }

        player = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}