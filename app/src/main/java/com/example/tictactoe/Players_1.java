package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Players_1 extends AppCompatActivity implements View.OnClickListener {
    private int playerOneCount, playerTwoCount, round = 0, activePlayer, score;
    private Button[] buttons = new Button[9];
    private TextView playerOneScore, playerTwoScore, playerOne, playerTwo;
    private Button resetGame;
    public MediaPlayer music;

    static int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    static int player1=MyApplication.getComputer(), player2=MyApplication.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players2);
        resetGame = (Button) findViewById(R.id.reset);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerOne = (TextView) findViewById(R.id.playerOne);
        player1=MyApplication.getComputer();
        player2=MyApplication.getUser();
        playerOne.setText("Computer");
        playerTwo.setText(Players_1_Name_Activity.getPlayerName());
        for (int i = 0; i < buttons.length; i++) {
            String buttonId = "index" + i;
            int resource = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = (Button) findViewById(resource);
            buttons[i].setOnClickListener(this);
        }
        music = MediaPlayer.create(Players_1.this,R.raw.background);
        if(MyApplication.isMusicOn()) {
            music.start();
            music.setLooping(true);
        }
        System.out.println(player1+"\n"+player2);
        round = 0;
        playerOneCount = 0;
        playerTwoCount = 0;
        gameState= new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        activePlayer = player2;
        if(Players_1_Name_Activity.getChoiceMade()){
            player2set();
        }
    }

    public static boolean isMovesLeft(int[] gameState) {
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 0)
                return true;
        }
        return false;
    }

    public static int evaluate(int[] gameState, int[][] winningPositions) {
        for (int[] win : winningPositions) {
            if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] == player1) {
                return 10;
            } else if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] == player2) {
                return -10;
            }
        }
        return 0;
    }

    public static int minimax(int[] gameState, boolean isMax) {
        int score = evaluate(gameState, winningPosition);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (isMovesLeft(gameState) == false)
            return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < gameState.length; i++) {
                if (gameState[i] == 0) {
                    gameState[i] = player1;
                    best = Math.max(best, minimax(gameState, !isMax));
                    gameState[i] = 0;
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < gameState.length; i++) {
                if (gameState[i] == 0) {
                    gameState[i] = player2;
                    best = Math.min(best, minimax(gameState, !isMax));
                    gameState[i] = 0;
                }
            }
            return best;
        }
    }

    static int findBestMove(int[] gameState) {
        int bestVal = -1000;
        int move = -1;
        for (int i = 0; i < gameState.length; i++) {
            if (gameState[i] == 0) {
                gameState[i] = player1;
                int moveVal = minimax(gameState, false);
                gameState[i] = 0;
                if (moveVal > bestVal) {
                    bestVal = moveVal;
                    move = i;
                }
            }

        }
        return move;
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
        round++;
        int res_Id = Integer.parseInt(buttonID.replaceAll("[\\D]", ""));

            if (activePlayer == player2) {
                if(player2==1)
                    ((Button) v).setBackground(getDrawable(R.drawable.x));
                else if(player2==2)
                    ((Button) v).setBackground(getDrawable(R.drawable.o));
                v.setVisibility(View.VISIBLE);
                gameState[res_Id] = player2;

                Log.i("round", Integer.toString(round));
                if (round != 5)
                    player2set();
                checkWinner(gameState);
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

    public void player2set() {
        int move = (int) findBestMove(gameState);
        if(player1==1)
            buttons[move].setBackground(getDrawable(R.drawable.x));
        else if(player1==2)
            buttons[move].setBackground(getDrawable(R.drawable.o));

        buttons[move].setVisibility(View.VISIBLE);
        gameState[move] = player1;
    }

    public void reset() {

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(getDrawable(R.drawable.rounded_corners));
            buttons[i].setEnabled(true);
        }
        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 0;
        activePlayer = player2;
        round = 0;
        if(Players_1_Name_Activity.getChoiceMade()) {
            final Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    player2set();
                }
            },1500);

        }

    }

    public void resetEverything() {
        reset();
        playerOneCount = 0;
        playerTwoCount = 0;
        playerOneScore.setText(Integer.toString(playerOneCount));
        playerTwoScore.setText(Integer.toString(playerTwoCount));
        Intent i = new Intent(this,Players_1_Name_Activity.class);
        startActivity(i);
        finish();
    }

    public void checkWinner(int[] gameState) {
        int score = evaluate(gameState, winningPosition);
        if (score == 10) {
            for (int i = 0; i < buttons.length; i++)
                buttons[i].setEnabled(false);
            playerOneCount++;
            playerOneScore.setText(Integer.toString(playerOneCount));
            Toast.makeText(this, "Computer has won this round!!", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            }, 1500);
        } else if (score == -10) {
            for (int i = 0; i < buttons.length; i++)
                buttons[i].setEnabled(false);
            playerTwoCount++;
            playerTwoScore.setText(Integer.toString(playerTwoCount));
            Toast.makeText(this, "PLAYER 2 has won this round!!", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            }, 1500);
        } else if (score == 0 && !isMovesLeft(gameState)) {
            for (int i = 0; i < buttons.length; i++)
                buttons[i].setEnabled(false);
            Toast.makeText(this, "The round was a draw!!", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();
                }
            }, 1500);
        }
    }
}

