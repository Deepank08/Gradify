package com.example.arshdeep.gradify.adapters;

import com.example.arshdeep.gradify.models.Item;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(Item item);
    void itemClicked(Section section);
}
