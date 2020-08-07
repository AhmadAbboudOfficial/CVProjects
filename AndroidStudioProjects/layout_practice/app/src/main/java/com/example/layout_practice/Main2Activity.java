package com.example.layout_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView txtPhone;
    TextView txtMessage;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent inIntent = getIntent();
        txtPhone = (TextView)findViewById(R.id.txtphone);
        txtMessage = (TextView)findViewById(R.id.txtmessage);

        txtPhone.setText(inIntent.getStringExtra(MainActivity.PHONE));
        txtMessage.setText(inIntent.getStringExtra(MainActivity.MESSAGE));

        btnBack = (Button)findViewById(R.id.btnback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
