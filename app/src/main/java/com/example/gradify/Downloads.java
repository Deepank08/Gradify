package com.example.gradify;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.arshdeep.gradify.adapters.ItemClickListener;
import com.example.arshdeep.gradify.adapters.Section;
import com.example.arshdeep.gradify.adapters.SectionedExpandableLayoutHelper;
import com.example.arshdeep.gradify.models.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Written by team Gradify.
 */

public class Downloads extends AppCompatActivity implements ItemClickListener {

    Toolbar toolbar;
    RecyclerView mRecyclerView;
    SessionManager session;
    String shared_pref_data ; //to get the shared data
    CoordinatorLayout coordinatorLayout; //for snackbar notification
    String filename=""; //to handle the filename for offline sd storage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());
        HashMap<String,String> user = session.getUserDetails();
        shared_pref_data = user.get(SessionManager.KEY_ROLL);
        shared_pref_data = shared_pref_data.substring(0,2);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        //setting the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(this,
                mRecyclerView, this, 3);

        //random data
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("Computer Science", 0, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechcse.pdf"));
        arrayList.add(new Item("Mechanical", 1, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechme.pdf"));
        arrayList.add(new Item("Civil", 2, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechce.pdf"));
        arrayList.add(new Item("Electronics", 3, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechece.pdf"));
        arrayList.add(new Item("Electrical", 4, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechee.pdf"));
        arrayList.add(new Item("Information Technology", 5, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechit.pdf"));
        sectionedExpandableLayoutHelper.addSection("Syllabus", arrayList);
        arrayList = new ArrayList<>();
        arrayList.add(new Item("Semester Schedule", 0, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btendsem.pdf"));
        arrayList.add(new Item("Mid Term I Schedule", 1, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btmidterm1.pdf"));
        arrayList.add(new Item("Mid Term II Schedule", 2, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btmidterm2.pdf"));
        arrayList.add(new Item("Seating Plan", 3, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btseatingplan.pdf"));
        arrayList.add(new Item("Admit Card", 4, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btadmitcard.pdf"));
        arrayList.add(new Item("Exam Code", 5, "http://gradify3.azurewebsites.net/Exam/" + shared_pref_data + "btexamcode.pdf"));
        sectionedExpandableLayoutHelper.addSection("Exam Corner", arrayList);
        arrayList = new ArrayList<>();
        arrayList.add(new Item("Academic Calender", 0, "http://gradify3.azurewebsites.net/Student/btacademiccal.pdf"));
        arrayList.add(new Item("Monthly NewsLetter", 1, "http://gradify3.azurewebsites.net/Student/btmonth.pdf"));
        arrayList.add(new Item("Quarterly NewsLetter", 2, "http://gradify3.azurewebsites.net/Student/btquarter.pdf"));
        arrayList.add(new Item("Aarohan", 3, "http://gradify3.azurewebsites.net/Student/btaarohan.pdf"));
        arrayList.add(new Item("Fee Plan", 4, "http://gradify3.azurewebsites.net/Student/" + shared_pref_data + "btfeeplan.pdf"));
        arrayList.add(new Item("AXIS Bank Fee Slip", 4, "http://gradify3.azurewebsites.net/Student/" + shared_pref_data + "btfeeslip.pdf"));
        sectionedExpandableLayoutHelper.addSection("Student Corner", arrayList);
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }



    @Override
    public void itemClicked(Item item) {
        //Item clicked - download the pdf and open it via default PDF Application
        //now we have two cases 1. either file already exists 2. first time download
        //if file already exists then just open it
        String data = item.getData(); // to get the attached indexed URL
        String name = item.getName(); //to get the attached Name
        //sub-procedure for naming the files on SD
        name = name + shared_pref_data + ".pdf";
        filename = name;
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Gradify/" + name);
        if (pdfFile.exists())
        {
            //PDF already exists no need to download again, just open it
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "No Application available to view PDF files. Please install one from Play Store", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        else
        {
            //Download the PDF file and display message
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Downloading...", Snackbar.LENGTH_LONG);
            snackbar.show();
            //start the download
            new DownloadFile().execute(data, name);
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];
            String fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "Gradify");
            folder.mkdir();
            File pdfFile = new File(folder, fileName);
            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //show message for Download Completed and option for user to open it
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Download Completed!", Snackbar.LENGTH_LONG)
                    .setAction("Open File", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String name = filename;
                            File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Gradify/" + name);
                            Uri path = Uri.fromFile(pdfFile);
                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                            pdfIntent.setDataAndType(path, "application/pdf");
                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            try {
                                startActivity(pdfIntent);
                            } catch (ActivityNotFoundException e) {
                                Snackbar snackbar = Snackbar
                                        .make(coordinatorLayout, "No Application available to view PDF files. Please install one from Play Store", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    });
            snackbar.show();
        }
    }

    @Override
    public void itemClicked(Section section) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Please Tap/Select an inner option to know more", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
