package com.example.tictactoe;

import android.app.Application;

public class MyApplication extends Application {
    public static int click1 = 0;
    private static boolean isMusicOn=true;

    public static boolean isMusicOn() {
        return isMusicOn;
    }

    public static void setMusicOn(boolean musicOn) {
        isMusicOn = musicOn;
    }

    public static int click2 = 0;
    public static int player1;
    public static int player2;
    public static int computer;

    public static int getComputer() {
        return computer;
    }

    public static void setComputer(int computer) {
        MyApplication.computer = computer;
    }

    public static int getUser() {
        return user;
    }

    public static void setUser(int user) {
        MyApplication.user = user;
    }

    public static int user;

    public static boolean isCanPlay() {
        return canPlay;
    }

    public static void setCanPlay(boolean canPlay) {
        MyApplication.canPlay = canPlay;
    }

    public static boolean canPlay=true;
    public static int getClick1() {
        return click1;
    }

    public static void setClick1(int click1) {
        MyApplication.click1 = click1;
    }

    public static int getClick2() {
        return click2;
    }

    public static void setClick2(int click2) {
        MyApplication.click2 = click2;
    }

    public static int getPlayer1() {
        return player1;
    }

    public static void setPlayer1(int player1) {
        MyApplication.player1 = player1;
    }

    public static int getPlayer2() {
        return player2;
    }

    public static void setPlayer2(int player2) {
        MyApplication.player2 = player2;
    }
}
