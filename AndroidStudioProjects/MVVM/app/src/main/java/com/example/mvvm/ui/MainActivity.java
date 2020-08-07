package com.example.mvvm.ui;
/**
 * this project illustrates MVVM(Model View Viewmodel) design pattern
 * Viewmodel is what manages and store UI
 * data binding is used in this project, its a way to handle project from both java file or xml
 * first should be enabled in build.gradle(app)
 * second in view each should have an id, then pressing ALT+ENTER and covert to data binding layout, a data tag appeared
 * then rebuild to auto-generate data binding class
 * thirdly is to use the generated class file by data binding, its same name of java file reversed + Binding.
 *
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MovieViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //listening to data change
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);


        /*
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getMovieName();
            }
        });*/

    }

}