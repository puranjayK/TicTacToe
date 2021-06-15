package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Players_2 extends AppCompatActivity implements View.OnClickListener {
    private int playerOneCount, playerTwoCount, round, activePlayer;
    private Button[] buttons = new Button[9];
    private TextView playerOneScore, playerTwoScore, playerOne, playerTwo;
    private Button resetGame;
    private String Player1_name, Player2_name;
    private MediaPlayer music;

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    /*    p1->1
          p2->2
          empty->0
     */
    int[][] winningPattern = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    int player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players2);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerOne = (TextView) findViewById(R.id.playerOne);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerOne.setText(Players_2_Name_activity.getPlayer1_Name());
        playerTwo.setText(Players_2_Name_activity.getPlayer2_Name());
        resetGame = (Button) findViewById(R.id.reset);
        for (int i = 0; i < buttons.length; i++) {
            String buttonId = "index" + i;
            int resource = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = (Button) findViewById(resource);
            buttons[i].setOnClickListener(this);
        }
        music = MediaPlayer.create(Players_2.this,R.raw.background);
        if(MyApplication.isMusicOn()) {
            music.start();
            music.setLooping(true);
        }
        round = 0;
        playerOneCount = 0;
        playerTwoCount = 0;
        player1 = MyApplication.getPlayer1();
        player2 = MyApplication.getPlayer2();
        activePlayer = player1;
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (this.isFinishing()){
            music.stop();
        }
    }
    @Override
    public void onClick(View v) {
        if (((Button) v).getBackground().getConstantState()
                .equals(getResources().getDrawable(R.drawable.o).getConstantState())
                || ((Button) v).getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.x).getConstantState())
        ) {

            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int res_Id = Integer.parseInt(buttonID.replaceAll("[\\D]", ""));
        if (activePlayer == player1) {
            if (player1 == 1) {
                ((Button) v).setBackground(getDrawable(R.drawable.x));
                gameState[res_Id] = player1;
            } else if (player1 == 2) {
                ((Button) v).setBackground(getDrawable(R.drawable.o));
                gameState[res_Id] = player1;
            }
        } else {
            if (player2 == 2) {
                ((Button) v).setBackground(getDrawable(R.drawable.o));
                gameState[res_Id] = player2;
            } else if (player2 == 1) {
                ((Button) v).setBackground(getDrawable(R.drawable.x));
                gameState[res_Id] = player2;
            }
        }
        System.out.println(gameState[res_Id]);
        round++;
        if (checkWinner()) {
            for(int i=0;i< buttons.length;i++)
                buttons[i].setEnabled(false);
            if (activePlayer == player1) {
                playerOneCount++;
                playerOneScore.setText(Integer.toString(playerOneCount));
                Toast.makeText(this, Players_2_Name_activity.getPlayer1_Name() + " has WON this Round!!", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                    }
                }, 1500);
            } else {
                playerTwoCount++;
                playerTwoScore.setText(Integer.toString(playerTwoCount));
                Toast.makeText(this, Players_2_Name_activity.getPlayer2_Name() + " has WON this Round!!", Toast.LENGTH_SHORT).show();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                    }
                }, 1500);
            }
        } else if (round == 9) {
            Toast.makeText(this, "This round is a DRAW", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            }, 1500);
        } else {
            if (activePlayer == player2)
                activePlayer = player1;
            else
                activePlayer = player2;
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        resetGame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resetEverything();
                return false;
            }
        });
    }

    public void reset() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(getDrawable(R.drawable.rounded_corners));
            buttons[i].setEnabled(true);
        }
        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 0;
        activePlayer = player1;
        round = 0;
    }
    public void resetEverything(){
        reset();
        playerOneCount=0;
        playerTwoCount=0;
        playerOneScore.setText(Integer.toString(playerOneCount));
        playerTwoScore.setText(Integer.toString(playerTwoCount));
    }

    public boolean checkWinner() {
        boolean hasWinner = false;
        for (int[] win : winningPattern) {
            if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != 0) {
                hasWinner = true;
            }
        }
        return hasWinner;
    }

}
