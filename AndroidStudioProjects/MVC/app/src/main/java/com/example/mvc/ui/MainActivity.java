package com.example.mvc.ui;

/*
    architectural patterns
    MVC stands for Model Viewer Controller
    Model is where data is kept and usually in a separate package called pojo, model or data
    Viewer is what user sees on the screen which it is xml files
    controller is the main activity or activity it self. controls between model and viewer.
        and usually kept in a separate package called ui
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.mvc.pojo.Model;

import com.example.mvc.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);//important
    }

    public Model getModel(){
        return new Model("movie name","14-06-1996",1);
    }

    @Override
    public void onClick(View v) {
        btn.setText(getModel().getName());
    }
}