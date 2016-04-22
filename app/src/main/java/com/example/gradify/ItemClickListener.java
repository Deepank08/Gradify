package com.example.gradify.adapters;

import com.example.arshdeep.gradify.models.Item;

/**
 * Written by team Gradify.
 */

public interface ItemClickListener {

    void itemClicked(Item item);

    void itemClicked(Section section);

}
