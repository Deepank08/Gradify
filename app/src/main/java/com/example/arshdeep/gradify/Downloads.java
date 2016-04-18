package com.example.arshdeep.gradify;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.arshdeep.gradify.adapters.ItemClickListener;
import com.example.arshdeep.gradify.adapters.Section;
import com.example.arshdeep.gradify.adapters.SectionedExpandableLayoutHelper;
import com.example.arshdeep.gradify.models.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Downloads extends AppCompatActivity implements ItemClickListener {

    RecyclerView mRecyclerView;
    SessionManager session;
    String shared_pref_data ;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        arrayList.add(new Item("CSE", 0, "http://gradify3.azurewebsites.net/Syllabus/" + shared_pref_data + "btechcse.pdf"));
        arrayList.add(new Item("iPad", 1, ""));
        arrayList.add(new Item("iPod", 2, ""));
        arrayList.add(new Item("iMac", 3, ""));
        sectionedExpandableLayoutHelper.addSection("Syllabus", arrayList);
        arrayList = new ArrayList<>();
        arrayList.add(new Item("LG", 0, ""));
        arrayList.add(new Item("Apple", 1, ""));
        arrayList.add(new Item("Samsung", 2, ""));
        arrayList.add(new Item("Motorola", 3, ""));
        arrayList.add(new Item("Sony", 4, ""));
        arrayList.add(new Item("Nokia", 5, ""));
        sectionedExpandableLayoutHelper.addSection("Companies", arrayList);
        arrayList = new ArrayList<>();
        arrayList.add(new Item("Chocolate", 0, ""));
        arrayList.add(new Item("Strawberry", 1, ""));
        arrayList.add(new Item("Vanilla", 2, ""));
        arrayList.add(new Item("Butterscotch", 3, ""));
        arrayList.add(new Item("Grape", 4, ""));
        sectionedExpandableLayoutHelper.addSection("Ice cream", arrayList);

        sectionedExpandableLayoutHelper.notifyDataSetChanged();

        //checking if adding single item works
        sectionedExpandableLayoutHelper.addItem("Ice cream", new Item("Tutti frutti",5, ""));
        sectionedExpandableLayoutHelper.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void itemClicked(Item item) {
        //Item clicked - download the pdf and open it via default PDF Application
        //now we have two cases 1. either file already exists 2. first time download

        //if file already exists then just open it
        String data = item.getData();
        String name = item.getName();
        name = name + ".pdf";
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Gradify/" + name);
        if (pdfFile.exists())
        {
            //PDF file found display it
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //Downloading the PDF file
            Toast.makeText(this, "Downloading ..." , Toast.LENGTH_SHORT).show();


            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "This is Awesome", Snackbar.LENGTH_LONG);

            snackbar.show();




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


            Toast.makeText(getApplicationContext(), "Download Completed!" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemClicked(Section section) {
        Toast.makeText(this, "Section: " + section.getName() + " clicked", Toast.LENGTH_SHORT).show();
    }
}
