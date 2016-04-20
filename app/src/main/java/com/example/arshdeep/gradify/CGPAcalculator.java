package com.example.arshdeep.gradify;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CGPAcalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Toolbar toolbar;
    Spinner spinner;
    LinearLayout llsub1, llsub2, llsub3, llsub4, llsub5, llsub6, llsgpa;
    EditText etsub1, etsub2, etsub3, etsub4, etsub5, etsub6, etgrade1,etgrade2, etgrade3, etgrade4, etgrade5, etgrade6;
    String grades[] = {"A+", "A", "B", "C", "D+", "D", "E", "F"};
    Integer grade_points[] = {10, 9, 8, 6 , 5 , 4 , 2, 0 };
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpacalculator);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.NoOfSubjects,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        llsub1 = (LinearLayout)findViewById(R.id.llsub1);
        llsub2 = (LinearLayout)findViewById(R.id.llsub2);
        llsub3 = (LinearLayout)findViewById(R.id.llsub3);
        llsub4 = (LinearLayout)findViewById(R.id.llsub4);
        llsub5 = (LinearLayout)findViewById(R.id.llsub5);
        llsub6 = (LinearLayout)findViewById(R.id.llsub6);
        llsgpa = (LinearLayout)findViewById(R.id.llsgpa);

        llsub1.setVisibility(View.GONE);
        llsub2.setVisibility(View.GONE);
        llsub3.setVisibility(View.GONE);
        llsub4.setVisibility(View.GONE);
        llsub5.setVisibility(View.GONE);
        llsub6.setVisibility(View.GONE);
        llsgpa.setVisibility(View.GONE);

        etsub1 = (EditText) findViewById(R.id.etsub1);
        etsub2 = (EditText) findViewById(R.id.etsub2);
        etsub3 = (EditText) findViewById(R.id.etsub3);
        etsub4 = (EditText) findViewById(R.id.etsub4);
        etsub5 = (EditText) findViewById(R.id.etsub5);
        etsub6 = (EditText) findViewById(R.id.etsub6);

        etgrade1 = (EditText) findViewById(R.id.etgrade1);
        etgrade2 = (EditText) findViewById(R.id.etgrade2);
        etgrade3 = (EditText) findViewById(R.id.etgrade3);
        etgrade4 = (EditText) findViewById(R.id.etgrade4);
        etgrade5 = (EditText) findViewById(R.id.etgrade5);
        etgrade6 = (EditText) findViewById(R.id.etgrade6);

        button = (Button) findViewById(R.id.buttonSGPA);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sgpaAns = calcSGPA();
                String sgpa = String.valueOf(sgpaAns);
                sgpa = sgpa.substring(0,3);

                llsgpa.setVisibility(View.VISIBLE);
                TextView sgpaAnswer = (TextView) findViewById(R.id.sgpaAnswer);
                sgpaAnswer.setText(sgpa);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cgpacalculator, menu);
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
    public void onBackPressed() {
        //Intent i=new Intent(this, StudentInfo.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(i);
        finish();
        return ;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView number = (TextView) view;
        //Toast.makeText(this, "(Outside if)You selected "+number.getText().toString()+" at "+i,Toast.LENGTH_LONG).show();

        if(i==0){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
           // Toast.makeText(this, "You selected "+number.getText()+" at "+i,Toast.LENGTH_LONG).show();

        }
        if(i==1){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
           // Toast.makeText(this, "You selected "+number.getText()+" at "+i,Toast.LENGTH_LONG).show();

        }
        if(i==2){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
            llsub6.setVisibility(View.VISIBLE);
           // Toast.makeText(this, "You selected "+number.getText()+" at "+i,Toast.LENGTH_LONG).show();

        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public double calcSGPA(){
        //Toast.makeText(this, "In calcSGPA",Toast.LENGTH_LONG).show();
        Double c1=0.0,c2=0.0,c3=0.0,c4=0.0,c5=0.0,c6=0.0;
        String g1, g2, g3, g4, g5, g6;
        try {
            c1 = Double.parseDouble(etsub1.getText().toString()); //Toast.makeText(this,"credit1="+c1,Toast.LENGTH_LONG).show();
            c2 = Double.parseDouble(etsub2.getText().toString()); //Toast.makeText(this,"credit2="+c2,Toast.LENGTH_LONG).show();
            c3 = Double.parseDouble(etsub3.getText().toString()); //Toast.makeText(this,"credit3="+c3,Toast.LENGTH_LONG).show();
            c4 = Double.parseDouble(etsub4.getText().toString()); //Toast.makeText(this,"credit4="+c4,Toast.LENGTH_LONG).show();
            c5 = Double.parseDouble(etsub5.getText().toString()); //Toast.makeText(this,"credit5="+c5,Toast.LENGTH_LONG).show();
            c6 = Double.parseDouble(etsub6.getText().toString()); //Toast.makeText(this,"credit6="+c6,Toast.LENGTH_LONG).show();
        }
        catch (Exception e){

        }

        g1 = etgrade1.getText().toString(); //Toast.makeText(this,"Grade 1="+g1,Toast.LENGTH_LONG).show();
        g2 = etgrade2.getText().toString(); //Toast.makeText(this,"Grade 2="+g2,Toast.LENGTH_LONG).show();
        g3 = etgrade3.getText().toString(); //Toast.makeText(this,"Grade 3="+g3,Toast.LENGTH_LONG).show();
        g4 = etgrade4.getText().toString(); //Toast.makeText(this,"Grade 4="+g4,Toast.LENGTH_LONG).show();
        g5 = etgrade5.getText().toString(); //Toast.makeText(this,"Grade 5="+g5,Toast.LENGTH_LONG).show();
        g6 = etgrade6.getText().toString(); //Toast.makeText(this,"Grade 6="+g6,Toast.LENGTH_LONG).show();

        int gp1 = calgp(g1); //Toast.makeText(this,"Grade point 1="+gp1,Toast.LENGTH_LONG).show();
        int gp2 = calgp(g2); //Toast.makeText(this,"Grade point 2="+gp2,Toast.LENGTH_LONG).show();
        int gp3 = calgp(g3); //Toast.makeText(this,"Grade point 3="+gp3,Toast.LENGTH_LONG).show();
        int gp4 = calgp(g4); //Toast.makeText(this,"Grade point 4="+gp4,Toast.LENGTH_LONG).show();
        int gp5 = calgp(g5); //Toast.makeText(this,"Grade point 5="+gp5,Toast.LENGTH_LONG).show();
        int gp6 = calgp(g6); //Toast.makeText(this,"Grade point 6="+gp6,Toast.LENGTH_LONG).show();



        Double sgpa = 0.0;

        sgpa = (c1*gp1+c2*gp2+c3*gp3+c4*gp4+c5*gp5+c6*gp6)/(c1+c2+c3+c4+c5+c6);

      //  Toast.makeText(this, "SGPA = "+sgpa ,Toast.LENGTH_LONG).show();
        return sgpa;


    }

    public int calgp(String grade)
    {
        //Toast.makeText(this, "In calGradePoint",Toast.LENGTH_LONG).show();
        int gp=0,pos=0;
        for(int i=0;i<8;i++)
        {
            if(grades[i].equals(grade)){
                pos=i;
                break;
                }

        }
        gp = grade_points[pos];
        return gp;
    }
}
