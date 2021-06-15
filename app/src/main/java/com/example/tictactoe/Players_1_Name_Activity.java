package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Players_1_Name_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public static int player1, player2;
    private static Switch choice;
    private static boolean choiceMade;
    private static EditText name;
    private Button startGame;
    private FloatingActionButton X, O;

    public static boolean getChoiceMade() {
        return choice.isChecked();
    }

    public static String getPlayerName() {
        return name.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_1_name);
        startGame = (Button) findViewById(R.id.startGame);
        choice = (Switch) findViewById(R.id.choice);
        X = (FloatingActionButton) findViewById(R.id.x);
        O = (FloatingActionButton) findViewById(R.id.o);
        name = (EditText) findViewById(R.id.playerName);
        player1 = MyApplication.getComputer();
        player2 = MyApplication.getUser();
        MyApplication.setCanPlay(false);
        X.setBackgroundTintList(getColorStateList(R.color.black));
        O.setBackgroundTintList(getColorStateList(R.color.black));


        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X.setBackgroundTintList(getColorStateList(R.color.white));
                O.setBackgroundTintList(getColorStateList(R.color.black));
                MyApplication.setComputer(2);
                MyApplication.setUser(1);
                MyApplication.setCanPlay(true);
            }
        });
        O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                O.setBackgroundTintList(getColorStateList(R.color.white));
                X.setBackgroundTintList(getColorStateList(R.color.black));
                MyApplication.setComputer(1);
                MyApplication.setUser(2);
                MyApplication.setCanPlay(true);
            }
        });
        System.out.println("Can game be played = " + Players_1_Name_Activity.getChoiceMade());
        choice.setOnCheckedChangeListener(this);
        startGame.setOnClickListener(this::setStartGame);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        choiceMade = choice.isChecked();
        System.out.println(choiceMade);
    }

    public void setStartGame(View view) {
        Intent i = new Intent(this, Players_1.class);
        if (MyApplication.isCanPlay() && isNameFilled())
            startActivity(i);
        else if (!MyApplication.isCanPlay())
            Toast.makeText(this, "Please Choose Proper Choice", Toast.LENGTH_SHORT).show();
        else if (!isNameFilled())
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();

    }

    public boolean isNameFilled() {
        if (name.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}
