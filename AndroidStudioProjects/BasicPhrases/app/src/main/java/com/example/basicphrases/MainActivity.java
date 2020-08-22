package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saySound(View view){
        Button pressedButton = (Button) view;

        mediaPlayer = MediaPlayer.create(this,getResources().getIdentifier(pressedButton.getTag().toString(),"raw",getPackageName()));

        mediaPlayer.start();
        /*
        int id = view.getId();

        switch (id){
            case R.id.btn_do_you_speak_english:
                mediaPlayer = MediaPlayer.create(this,R.raw.doyouspeakenglish);
                mediaPlayer.start();
                break;
            case R.id.btn_good_evening:
                mediaPlayer = MediaPlayer.create(this,R.raw.goodevening);
                mediaPlayer.start();
                break;
            case R.id.btn_hello:
                mediaPlayer = MediaPlayer.create(this,R.raw.hello);
                mediaPlayer.start();
                break;
            case R.id.btn_how_are_you:
                mediaPlayer = MediaPlayer.create(this,R.raw.howareyou);
                mediaPlayer.start();
                break;
            case R.id.btn_i_live_in:
                mediaPlayer = MediaPlayer.create(this,R.raw.ilivein);
                mediaPlayer.start();
                break;
            case R.id.btn_my_name_is:
                mediaPlayer = MediaPlayer.create(this,R.raw.mynameis);
                mediaPlayer.start();
                break;
            case R.id.btn_please:
                mediaPlayer = MediaPlayer.create(this,R.raw.please);
                mediaPlayer.start();
                break;
            case R.id.btn_welcome:
                mediaPlayer = MediaPlayer.create(this,R.raw.welcome);
                mediaPlayer.start();
                break;
            default:
                Log.v("saySound","invalid id "+id);
        }*/
    }
}