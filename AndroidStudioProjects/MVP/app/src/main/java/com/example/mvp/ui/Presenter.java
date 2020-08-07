package com.example.mvp.ui;

import com.example.mvp.model.Model;

public class Presenter {

    ModelView view;

    public Presenter(ModelView view) {
        this.view = view;
    }

    public Model getMovieFromDatabase(){
        return new Model(1,"movie name","14-06-1996");
    }

    public void getMovieName(){
        view.onGetMovieName(getMovieFromDatabase().getName());
    }
}
