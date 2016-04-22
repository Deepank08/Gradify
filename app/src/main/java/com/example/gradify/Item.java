package com.example.gradify.models;

/**
 * Written by team Gradify.
 */

public class Item {

    private final String name; //to hold the item name which will be further used in offline sd storage
    private final int id; //to order the elements in UI
    private final String data; //to associate their respective urls with each item

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
