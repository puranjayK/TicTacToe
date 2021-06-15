package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;


public class Settings extends AppCompatActivity {

    public static ToggleButton music;
    private Button instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        instruction=(Button)findViewById(R.id.instruction);
        music =(ToggleButton) findViewById(R.id.music);
//        music.setTextOn("Music On");
        music.setChecked(MyApplication.isMusicOn());
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(music.isChecked()) {
                    MyApplication.setMusicOn(true);

                }
                else {
                    MyApplication.setMusicOn(false);

                }
            }
        });
        instruction.setOnClickListener(this::openInstructions);
    }
public void openInstructions(View view){
        Intent i =new Intent(this,Instructions_activity.class);
        startActivity(i);
}
}