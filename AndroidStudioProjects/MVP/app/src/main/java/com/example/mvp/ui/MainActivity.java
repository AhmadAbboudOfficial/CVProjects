package com.example.mvp.ui;
/*
    in MVP
    V denotes View and Activity both are called view and deal with ui only and to get data we use
    P denotes presenter is responsible for getting data from database and handles all the logic
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ModelView {


    Presenter presenter;
    @BindView(R.id.textView)
    TextView text;
    @BindView(R.id.button)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        btn.setOnClickListener(this);
        presenter = new Presenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            presenter.getMovieName();
        }
    }

    @Override
    public void onGetMovieName(String name) {
        text.setText(name);
    }
}