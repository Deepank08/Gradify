package com.example.arshdeep.gradify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Result extends StudentInfo {

    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

        final TabLayout.Tab sem1 = tabLayout.newTab();
        final TabLayout.Tab sem2 = tabLayout.newTab();

        sem1.setText("Sem 1");
        sem2.setText("Sem 2");

        tabLayout.addTab(sem1, 0);
        tabLayout.addTab(sem2, 1);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    public static class TabFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.tabs, container, false);
        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new TabFragment();    // Which Fragment should be dislpayed by the viewpager for the given position
            // In my case we are showing up only one fragment in all the three tabs so we are
            // not worrying about the position and just returning the TabFragment
        }

        @Override
        public int getCount() {

            return 2;           // As there are only 3 Tabs
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //implemented back button
    @Override
    public void onBackPressed() {
        finish();
        return ;
    }

}
