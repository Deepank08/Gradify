package com.example.gradify;

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


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;


public class Result extends StudentInfo {

    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private MobileServiceClient mClient;
    private MobileServiceSyncTable<StudentResult> mTable;



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
	
        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://gradify.azure-mobile.net/",
                    "QrVvKsjBuUyfqngNGZaIpcvYszKEDT58",
                    this).withFilter(new ProgressFilter());

            // Get the Mobile Service Table instance to use

            // Offline Sync
            mTable = mClient.getSyncTable("StudentResult", StudentResult.class);

            //Init local storage
            initLocalStore().get();


            // Create an adapter to bind the items with the view
            mAdapter = new StudentInfoAdapter(this, R.layout.student_list);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewToDo1);
            listViewToDo.setAdapter(mAdapter);

            if(isNetworkAvailable()) {// Load the items from the Mobile Service
                refreshItemsFromTable();
            }
            else
                initLocalStore().get();

        } catch (MalformedURLException e) {
            createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        } catch (Exception e){
            createAndShowDialog(e, "Error");
        }


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        /*
        TabLayout.newTab() method creates a tab view, Now a Tab view is not the view
        which is below the tabs, its the tab itself.
         */

        final TabLayout.Tab sem1 = tabLayout.newTab();
        final TabLayout.Tab sem2 = tabLayout.newTab();
        sem1.setText("Sem 1");
        sem2.setText("Sem 2");
        tabLayout.addTab(sem1, 0);
        tabLayout.addTab(sem2, 1);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

	private List<StudentResult> refreshItemsFromMobileServiceTableSyncTable() throws ExecutionException, InterruptedException {
        //sync the data
        sync().get();
        Query query = QueryOperations.field("rollno").
                eq(val(st_rollno));
        return mTable.read(query).get();
    }

	 private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }

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

	 @Override
    public void onBackPressed() {
        super.onBackPressed();

        intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
 }

