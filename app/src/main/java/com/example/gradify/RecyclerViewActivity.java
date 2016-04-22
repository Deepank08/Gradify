package com.example.gradify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Written by team Gradify.
 */

public class RecyclerViewActivity extends Activity {

    private List<Person> persons;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Arshdeep Kaur Gulati", "+91 7310640817", "arshdeepkaurgulati@gmail.com", R.drawable.arshdeep));
        persons.add(new Person("Deepank Srivastava", "+91 9415337178", "deepanksrivastava08@gmail.com",R.drawable.deepank));
        persons.add(new Person("Varun Dhall", "+91 8868838028", "varun.vd1994@gmail.com",R.drawable.varun));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
