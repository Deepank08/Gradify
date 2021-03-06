package com.example.gradify;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Written by team Gradify.
 */

public class NavigationDrawerFragment extends Fragment {


    private ActionBarDrawerToggle mdrawerToggle;
    private DrawerLayout mdrawerLayout;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    public NavigationDrawerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater. inflate(R.layout.navigation_drawer_below_action_bar,container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList); //Inflating the recycler view

        adapter = new MyAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }


    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.ic_number3, R.drawable.ic_number4, R.drawable.ic_action_view_as_grid,R.drawable.ic_action_download,R.drawable.ic_action_help, R.drawable.ic_action_about};
        String[] titles = {"1st year", "2nd year", "3rd year", "4th year","CGPA Calculator", "Downloads", "Help & FAQ", "About Us"};
        for (int i=0; i< titles.length && i<icons.length; i++){
            Information current  = new Information();
            current.iconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {

        mdrawerLayout = drawerLayout;
        mdrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };



        //Listener
        mdrawerLayout.setDrawerListener(mdrawerToggle);

        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mdrawerToggle.syncState();
            }
        });


    }

    public interface OnFragmentInteractionListener {
        void onNavigationDrawerItemSelected(int position);
        public void onFragmentInteraction(Uri uri);
    }
}
