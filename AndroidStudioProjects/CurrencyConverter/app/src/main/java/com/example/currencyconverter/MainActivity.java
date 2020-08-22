package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_input_value;
    Button btn_convert_action;
    float input_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_value = 0.0f;

        edt_input_value = (EditText)findViewById(R.id.edt_value);


        btn_convert_action = (Button)findViewById(R.id.btn_convert);
        btn_convert_action.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String AmountInPounds = edt_input_value.getText().toString();
        input_value = Float.parseFloat(AmountInPounds);
        float converted_value = input_value * 1.3f;
        String new_value = String.format("%.2f",converted_value);
        //£20 is $26
        Toast.makeText(this,"£"+ input_value +" is $"+new_value,Toast.LENGTH_LONG).show();
    }
}