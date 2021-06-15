package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Players_2_Name_activity extends AppCompatActivity {
    static FloatingActionButton player1_o, player1_x, player2_o, player2_x;
    Button startGame;
    static EditText player1_name, player2_name;
    MyApplication myApplication = (MyApplication) this.getApplication();
    boolean canPlay = MyApplication.isCanPlay();
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_2_name);

        final int[] click1 = {MyApplication.getClick1()};

        final int[] click2 = {MyApplication.getClick2()};

        MyApplication.setClick1(0);
        MyApplication.setClick2(0);
        click1[0]=0;
        click2[0]=0;
        player1_o = (FloatingActionButton) findViewById(R.id.player1o);
        player2_o = (FloatingActionButton) findViewById(R.id.player2o);
        player1_x = (FloatingActionButton) findViewById(R.id.player1x);
        player2_x = (FloatingActionButton) findViewById(R.id.player2x);
        player1_name = (EditText) findViewById(R.id.Player1_name);
        player2_name = (EditText) findViewById(R.id.Player2_name);
        startGame = (Button) findViewById(R.id.startGame);
        player1_o.setBackgroundTintList(getColorStateList(R.color.black));
        player1_x.setBackgroundTintList(getColorStateList(R.color.black));
        player2_x.setBackgroundTintList(getColorStateList(R.color.black));
        player2_o.setBackgroundTintList(getColorStateList(R.color.black));
        player1_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1[0]++;
                MyApplication.setClick1(click1[0]);
                setColor(player1_o, player2_x, click1[0]);
                playerChoice(click1[0], click2[0]);
            }
        });
        player2_o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2[0]++;
                MyApplication.setClick2(click2[0]);
                setColor(player2_o, player1_x, click2[0]);
                playerChoice(click1[0], click2[0]);
            }
        });
        player1_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2[0]++;
                MyApplication.setClick2(click2[0]);
                setColor(player1_x, player2_o, click2[0]);
                playerChoice(click1[0], click2[0]);
            }
        });
        player2_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1[0]++;
                MyApplication.setClick1(click1[0]);
                setColor(player2_x, player1_o, click1[0]);
                playerChoice(click1[0], click2[0]);
            }
        });
        playerChoice(click1[0], click2[0]);
        System.out.println(MyApplication.isCanPlay());

        startGame.setOnClickListener(this::setStartGame);


    }

    public void setColor(View v1, View v2, int click) {
        if (click % 2 == 1) {
            v1.setBackgroundTintList(getColorStateList(R.color.white));
            v2.setBackgroundTintList(getColorStateList(R.color.white));
        } else {
            v1.setBackgroundTintList(getColorStateList(R.color.black));
            v2.setBackgroundTintList(getColorStateList(R.color.black));
        }
    }

    public void playerChoice(int click1, int click2) {
        if (click1 % 2 == 1) {
            if (click2 % 2 != 1 ) {
                MyApplication.setPlayer1(2);
                MyApplication.setPlayer2(1);
                MyApplication.setCanPlay(true);
            } else {
                MyApplication.setCanPlay(false);
            }
        }
        if (click2 % 2 == 1) {
            if (click1 % 2 != 1 ) {
                MyApplication.setPlayer1(1);
                MyApplication.setPlayer2(2);
                MyApplication.setCanPlay(true);
            } else {
                MyApplication.setCanPlay(false);
            }
        }
        if(click2%2==0 && click1%2==0) {
            System.out.println("No choice made");
            MyApplication.setCanPlay(false);
        }
        System.out.println(MyApplication.getPlayer1());
        System.out.println(MyApplication.getPlayer2());
    }

    public static String getPlayer1_Name() {
        return player1_name.getText().toString();
    }

    public static String getPlayer2_Name() {

        return player2_name.getText().toString();
    }

    public void setStartGame(View view) {
        boolean nameFilled = isNameFilled();
        Intent i = new Intent(this, Players_2.class);
        if (MyApplication.isCanPlay() && nameFilled) {
            startActivity(i);
            finish();
        }
        else if (!MyApplication.isCanPlay())
            Toast.makeText(this, "Please Choose Proper Choices", Toast.LENGTH_SHORT).show();
        else if (!nameFilled)
            Toast.makeText(this, "Please Enter Your Names", Toast.LENGTH_SHORT).show();
    }

    public boolean isNameFilled() {
        if (player1_name.getText().toString().equals("") || player2_name.getText().toString().equals(""))
            return false;
        else
            return true;
    }

}
