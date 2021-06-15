package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.transition.Explode;
import android.transition.Transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
        Transition ts = new Explode();
        getWindow().setEnterTransition(ts);
        getWindow().setExitTransition(ts);
        setContentView(R.layout.activity_menu);


        Button settings = (Button) findViewById(R.id.setting);
        Button players_2 = (Button) findViewById(R.id.player_2);
        Button player_1 =(Button) findViewById(R.id.player_1);


        settings.setOnClickListener(this::openSettings);
        players_2.setOnClickListener(this::Players2);
        player_1.setOnClickListener(this::Players1);


    }
    public void openSettings(View view){
        Intent i= new Intent(this,Settings.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void Players2(View view){
        Intent i = new Intent(this, Players_2_Name_activity.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void Players1(View view){
        Intent i = new Intent(this,Players_1_Name_Activity.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

}
