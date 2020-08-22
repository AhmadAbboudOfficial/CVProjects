package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    SeekBar volumeSeekBar;
    SeekBar scrupSeekBar;
    AudioManager audioManager;
    Timer MyTimer;

    public void play(View view){
        mediaPlayer.start();
        //a way to start schedule when audio starts to play
        //and stop it when audio stops playing
        startTimer();
    }

    public void pause(View view){
        mediaPlayer.pause();
        MyTimer.purge();
    }

    public void startTimer(){
        MyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrupSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 300);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //volume seek bar
        volumeSeekBar = findViewById(R.id.seekBar);
        //sound seek bar
        scrupSeekBar = findViewById(R.id.seekBar2);
        //initialing with audio service
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //phone max volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //phone current volume
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //setting the audio file to media player object
        mediaPlayer = MediaPlayer.create(this,R.raw.airplane);
        //if done playing the audio file
        mediaPlayer.setOnCompletionListener(this);
        //initialing the max volume to seek bar 1
        volumeSeekBar.setMax(maxVolume);
        //setting the current volume to seek bar 2
        volumeSeekBar.setProgress(currentVolume);

        MyTimer = new Timer();

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //show progress of audio file WHILE PLAYING.
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //audio file duration, which is time in seconds
        int maxDuration = mediaPlayer.getDuration();
        //initialing the max duration(Time) to seek bar 2
        scrupSeekBar.setMax(maxDuration);

        scrupSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //seek to the audio file current second of the seek bar progress.
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //when user change progress pause audio
                mediaPlayer.pause();
                MyTimer.purge();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //when user stops changing progress play audio
                mediaPlayer.start();
                startTimer();
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(this,"finished",Toast.LENGTH_LONG).show();
    }
}