package com.alyndroid.architecturepatternstutorialshomework.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alyndroid.architecturepatternstutorialshomework.R;
import com.alyndroid.architecturepatternstutorialshomework.pojo.DataBase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IDiv {

    DataBase db;
    Button btn_plus;
    TextView txt_sum;

    Presenter presenter;
    TextView txt_div;
    Button btn_div;

    Button btn_mul;
    TextView txt_mul;
    MulViewModel mulViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MVC on + operation
        btn_plus = findViewById(R.id.plus_button);
        txt_sum = findViewById(R.id.plus_result_textView);
        btn_plus.setOnClickListener(this);
        db = new DataBase();
        //MVP on / operation
        presenter = new Presenter(this);
        btn_div = findViewById(R.id.div_button);
        btn_div.setOnClickListener(this);
        txt_div = findViewById(R.id.div_result_textView);
        //MVVM on * operation
        btn_mul = findViewById(R.id.mul_button);
        btn_mul.setOnClickListener(this);
        txt_mul = findViewById(R.id.mul_result_textView);
        mulViewModel = ViewModelProviders.of(this).get(MulViewModel.class);
        mulViewModel.ResultMutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txt_mul.setText(""+integer);
            }
        });

    }

    public int sumButtonAction(){
        int sum = db.getNumbers().getFirstNum() + db.getNumbers().getSecondNum();
        return sum;
    }

    @Override
    public void onClick(View v) {
        //MVC
        if (v.getId() == R.id.plus_button){
            txt_sum.setText(""+sumButtonAction());
        }
        //MVP
        if (v.getId() == R.id.div_button){
            presenter.getDiv();
        }

        if (v.getId() == R.id.mul_button){
            mulViewModel.getResult();
        }

    }

    @Override
    public void onGetDiv(int result) {
        txt_div.setText(""+result);
    }
}
