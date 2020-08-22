package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtInputValue;
    private Button mNumberTestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumberTestBtn = findViewById(R.id.btn_test_number);
        mNumberTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mEdtInputValue = findViewById(R.id.edt_input_number);
        String input_number_string = mEdtInputValue.getText().toString();
        if (TextUtils.isEmpty(input_number_string)){
            Toast.makeText(this,"Enter a number.",Toast.LENGTH_LONG).show();
            return;
        }
        int input_number_double = Integer.parseInt(input_number_string);

        Number number = new Number();
        number.number = input_number_double;
        String msg = input_number_string;
        if (number.isSquare() && number.isTriangular()){
            msg += " is square and triangular!";
        }else if (number.isTriangular()){
            msg += " is triangular but not square.";
        }else if (number.isSquare()){
            msg += " is square but not triangular.";
        }else{
            msg += " is neither triangular nor square.";
        }

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}