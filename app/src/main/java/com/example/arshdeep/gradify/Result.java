package com.example.arshdeep.gradify;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Result extends StudentInfo {

    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public static String[] coursecode_str= {"code11","code12","code13","code14","code15","code16"};
    public static String[] coursename_str= {"subject11","subject12","subject13","subject14","subject15","subject16"};
    public static String[] grade_str= {"11A","11B","11C","11D","11E","11F"};
    public static String[] credit_str= {"11-4","11-3","11-2","11-4","11-3.5","11-4"};



    public static List<String> coursecode=new ArrayList<String>(Arrays.asList(coursecode_str));
    public static List<String> coursename=new ArrayList<String>(Arrays.asList(coursename_str));
    public static List<String> grade=new ArrayList<String>(Arrays.asList(grade_str));
    public static List<String> credit=new ArrayList<String>(Arrays.asList(credit_str));
    public static ArrayAdapter<String> adpcourse;
    public static ArrayAdapter<String> adpname;
    public static ArrayAdapter<String> adpcredit;
    public static ArrayAdapter<String> adpgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp((DrawerLayout)findViewById(R.id.drawer_layout),toolbar);*/

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        /*
        Creating Adapter and setting that adapter to the viewPager
        setSupportActionBar method takes the toolbar and sets it as
        the default action bar thus making the toolbar work like a normal
        action bar.
         */
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

       /* viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });*/

        /*
        TabLayout.newTab() method creates a tab view, Now a Tab view is not the view
        which is below the tabs, its the tab itself.
         */

        final TabLayout.Tab sem1 = tabLayout.newTab();
        final TabLayout.Tab sem2 = tabLayout.newTab();


        /*
        Setting Title text for our tabs respectively
         */

        sem1.setText("Sem 1");
        sem2.setText("Sem 2");


        /*
        Adding the tab view to our tablayout at appropriate positions
        As I want home at first position I am passing home and 0 as argument to
        the tablayout and like wise for other tabs as well
         */
        tabLayout.addTab(sem1, 0);
        tabLayout.addTab(sem2, 1);


        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indiactor below the tabs
         */

        //tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        //tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

   /* public static class TabFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            //code for fragments
            View view= inflater.inflate(R.layout.tabs, container, false);
            adpcourse=new ArrayAdapter<>(getActivity(),R.layout.student_list_result,
                    R.id.student_list_items,coursecode);
            adpname=new ArrayAdapter<>(getActivity(),R.layout.student_list_result,
                    R.id.student_list_items,coursename);
            adpcredit=new ArrayAdapter<>(getActivity(),R.layout.student_list_result,
                    R.id.student_list_items,credit);
            adpgrade=new ArrayAdapter<>(getActivity(),R.layout.student_list_result,
                    R.id.student_list_items,grade);

            ListView course_codeList= (ListView)view.findViewById(R.id.listCourseCode);
            course_codeList.setAdapter(adpcourse);

            ListView course_nameList= (ListView)view.findViewById(R.id.listCourseName);
            course_nameList.setAdapter(adpname);

            ListView course_creditList= (ListView)view.findViewById(R.id.listCredit);
            course_creditList.setAdapter(adpcredit);

            ListView course_gradeList= (ListView)view.findViewById(R.id.listGrade);
            course_gradeList.setAdapter(adpgrade);

            return view;



        }
     */



    }



     class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position)
            {
                case 0 : return new TabFragment();
                case 1 : return new TabFragment2();
                //case 2 : return new UpdatesFragment();
            }
            return null;
               // Which Fragment should be dislpayed by the viewpager for the given position
            // In my case we are showing up only one fragment in all the three tabs so we are
            // not worrying about the position and just returning the TabFragment
        }

        @Override
        public int getCount() {
            return 2;           // As there are only 3 Tabs
        }



 /*   @Override
    public void onFragmentInteraction(Uri uri) {

    }
 */


 }

