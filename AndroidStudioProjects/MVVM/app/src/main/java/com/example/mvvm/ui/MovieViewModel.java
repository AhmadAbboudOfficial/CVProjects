package com.example.mvvm.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.pojo.Model;

public class MovieViewModel extends ViewModel {

    public static MutableLiveData<String> MovieNameMutableLiveData = new MutableLiveData<>();

    private Model getMovieFromDatabase(){
        return new Model(1,"name","14-06-1996");
    }

    public void getMovieName(){
        String movie_name = getMovieFromDatabase().getName();
        MovieNameMutableLiveData.setValue(movie_name);
    }

}
