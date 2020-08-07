package com.example.mvc.pojo;

public class Model {

    private String name;
    private String data;
    private int id;

    public Model(String name, String data, int id) {
        this.name = name;
        this.data = data;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }
}
