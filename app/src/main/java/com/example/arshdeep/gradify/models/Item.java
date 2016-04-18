package com.example.arshdeep.gradify.models;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Item {

    private final String name;
    private final int id;
    private final String data;

    public Item(String name, int id, String data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getData() { return data;}
}
