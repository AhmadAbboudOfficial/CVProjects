package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgBart;
    private ImageView imgHomer;
    private boolean BartIsShown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBart = findViewById(R.id.imgBart);
        imgHomer = findViewById(R.id.imgHomer);
        imgBart.setX(-1000);
        imgBart.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        imgHomer.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
        imgBart.animate().translationXBy(1000).rotation(3600).setDuration(2000);

        //imgBart.animate().scaleX(1.0f).scaleY(1.0f).setDuration(2000);
    }

    public void fade(View view){

        //imgBart.animate().scaleX(1.0f).scaleY(1.0f).setDuration(2000);
        //return;

        if (BartIsShown) {
            BartIsShown = false;
            imgBart.animate().alpha(0).setDuration(2000);
            imgHomer.animate().alpha(1).setDuration(2000);
        }else{
            BartIsShown = true;
            imgBart.animate().alpha(1).setDuration(2000);
            imgHomer.animate().alpha(0).setDuration(2000);
        }
    }

}