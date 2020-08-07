package com.example.layout_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtPhone;
    EditText edtMessage;
    Button btn_next;
    Button btn_close;
    public static final String PHONE = "PHONE";
    public  static final String MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_next = (Button)findViewById(R.id.btn_next);
        edtPhone = (EditText)findViewById(R.id.etdphone);
        edtMessage = (EditText)findViewById(R.id.edtmessage);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(MainActivity.this,Main2Activity.class);
                outIntent.putExtra(PHONE,edtPhone.getText().toString());
                outIntent.putExtra(MESSAGE,edtMessage.getText().toString());
                startActivity(outIntent);
            }
        });


        btn_close = (Button)findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
