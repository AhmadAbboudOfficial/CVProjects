package com.example.agecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edt_year_of_birth;
    TextView AgeResult_txt;
    Button btn_AgeCalculate;
    int AgeResult;
    int CurrentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_year_of_birth = (EditText)findViewById(R.id.edt_year_of_birth);
        AgeResult_txt = (TextView)findViewById(R.id.AgeResult_txt);
        /*
            saving the instance variable value if rotating
         */
        if(savedInstanceState != null){
            AgeResult = savedInstanceState.getInt("AGE");
            AgeResult_txt.setText(""+AgeResult);
        }
        btn_AgeCalculate = (Button)findViewById(R.id.btn_calculate);

        btn_AgeCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgeResult = Integer.parseInt(edt_year_of_birth.getText().toString());
                CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
                if(AgeResult >= CurrentYear){
                    Toast.makeText(MainActivity.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                AgeResult = CurrentYear - AgeResult;
                AgeResult_txt.setText(""+ AgeResult);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("AGE",AgeResult);
    }
}
