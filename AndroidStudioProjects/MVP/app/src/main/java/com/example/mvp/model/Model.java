package com.example.mvp.model;

public class Model {
    private int id;
    private String name;
    private String date;

    public Model(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
