package com.example.gradify.adapters;

/**
 * Written by team Gradify.
 */

public class Section {

    private final String name;

    public boolean isExpanded;

    public Section(String name) {
        this.name = name;
        isExpanded = true;
    }

    public String getName() {
        return name;
    }
}
