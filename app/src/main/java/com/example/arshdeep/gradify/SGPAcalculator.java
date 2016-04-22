package com.example.arshdeep.gradify;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
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


public class SGPAcalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Toolbar toolbar;
    Spinner spinner;
    LinearLayout llsub1, llsub2, llsub3, llsub4, llsub5, llsub6, llsub7, llsub8,llsgpa,llerror;
    EditText etsub1, etsub2, etsub3, etsub4, etsub5, etsub6, etsub7, etsub8, etgrade1,etgrade2, etgrade3, etgrade4, etgrade5, etgrade6, etgrade7, etgrade8;
    String grades[] = {"A+", "A", "B", "C", "D+", "D", "E", "F"};
    Integer grade_points[] = {10, 9, 8, 6 , 5 , 4 , 2, 0 };
    Button button, buttonClear;

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
        llsub7 = (LinearLayout)findViewById(R.id.llsub7);
        llsub8 = (LinearLayout)findViewById(R.id.llsub8);
        llsgpa = (LinearLayout)findViewById(R.id.llsgpa);
        llerror = (LinearLayout)findViewById(R.id.llerror);

        llsub1.setVisibility(View.GONE);
        llsub2.setVisibility(View.GONE);
        llsub3.setVisibility(View.GONE);
        llsub4.setVisibility(View.GONE);
        llsub5.setVisibility(View.GONE);
        llsub6.setVisibility(View.GONE);
        llsub7.setVisibility(View.GONE);
        llsub8.setVisibility(View.GONE);
        llsgpa.setVisibility(View.GONE);
        llerror.setVisibility(View.GONE);

        etsub1 = (EditText) findViewById(R.id.etsub1);
        etsub2 = (EditText) findViewById(R.id.etsub2);
        etsub3 = (EditText) findViewById(R.id.etsub3);
        etsub4 = (EditText) findViewById(R.id.etsub4);
        etsub5 = (EditText) findViewById(R.id.etsub5);
        etsub6 = (EditText) findViewById(R.id.etsub6);
        etsub7 = (EditText) findViewById(R.id.etsub7);
        etsub8 = (EditText) findViewById(R.id.etsub8);

        etgrade1 = (EditText) findViewById(R.id.etgrade1);
        etgrade2 = (EditText) findViewById(R.id.etgrade2);
        etgrade3 = (EditText) findViewById(R.id.etgrade3);
        etgrade4 = (EditText) findViewById(R.id.etgrade4);
        etgrade5 = (EditText) findViewById(R.id.etgrade5);
        etgrade6 = (EditText) findViewById(R.id.etgrade6);
        etgrade7 = (EditText) findViewById(R.id.etgrade7);
        etgrade8 = (EditText) findViewById(R.id.etgrade8);

        button = (Button) findViewById(R.id.buttonSGPA);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( checkETfB(etsub1) ||  checkETfB(etsub2) ||  checkETfB(etsub3) ||  checkETfB(etsub4) ||  checkETfB(etsub5) ||  checkETfB(etsub6) ||  checkETfB(etsub7) ||  checkETfB(etsub8) ||
                        checkETfB(etgrade1) ||  checkETfB(etgrade2) ||  checkETfB(etgrade3) ||  checkETfB(etgrade4) ||  checkETfB(etgrade5) ||  checkETfB(etgrade6) ||  checkETfB(etgrade7) ||  checkETfB(etgrade8) ||
                            checkCaN(etsub1) || checkCaN(etsub2) || checkCaN(etsub3) || checkCaN(etsub4) || checkCaN(etsub5) || checkCaN(etsub6) || checkCaN(etsub7) || checkCaN(etsub8) ||
                                checkGrade(etgrade1) || checkGrade(etgrade3) || checkGrade(etgrade3) || checkGrade(etgrade4) || checkGrade(etgrade5) || checkGrade(etgrade6) || checkGrade(etgrade7) || checkGrade(etgrade8))
                                    {

                                        double sgpaAns = calcSGPA();
                                        String sgpa = String.valueOf(sgpaAns);
                                        sgpa = sgpa.substring(0,3);

                                        llsgpa.setVisibility(View.VISIBLE);
                                        TextView sgpaAnswer = (TextView) findViewById(R.id.sgpaAnswer);
                                        sgpaAnswer.setText(sgpa);

                }
                else
                {
                    llerror.setVisibility(View.VISIBLE);
                }

            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etsub1.setText("");
                etsub2.setText("");
                etsub3.setText("");
                etsub4.setText("");
                etsub5.setText("");
                etsub6.setText("");
                etsub7.setText("");
                etsub8.setText("");
                etgrade2.setText("");
                etgrade3.setText("");
                etgrade4.setText("");
                etgrade5.setText("");
                etgrade6.setText("");
                etgrade7.setText("");
                etgrade8.setText("");
                etgrade1.setText("");
                llerror.setVisibility(View.GONE);
            }
        });
    }

    //Check Edit Texts for Blanks
    public boolean checkETfB(EditText et)
    {
        if (et.getText().toString().equals(""))
            return false;
        else
            return true;
    }

    //Check Credits are numeric
    public boolean checkCaN(EditText et)
    {
        if(et.getText().toString().equals("1") || et.getText().toString().equals("2") || et.getText().toString().equals("3") || et.getText().toString().equals("3.5") || et.getText().toString().equals("4") || et.getText().toString().equals("4.5"))
            return true;
        else
            return false;

    }

    //Check Grades
    public boolean checkGrade(EditText et)
    {
        if(et.getText().toString().equals("A+") || et.getText().toString().equals("A") ||  et.getText().toString().equals("B") || et.getText().toString().equals("C") || et.getText().toString().equals("D+") || et.getText().toString().equals("D") || et.getText().toString().equals("E") || et.getText().toString().equals("F") )
            return true;
        else
            return false;
    }


    @Override
    public void onBackPressed() {

        finish();
        return ;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i==0){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.GONE);
            llsub6.setVisibility(View.GONE);
            llsub7.setVisibility(View.GONE);
            llsub8.setVisibility(View.GONE);
        }

        if(i==1){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
            llsub6.setVisibility(View.GONE);
            llsub7.setVisibility(View.GONE);
            llsub8.setVisibility(View.GONE);
        }

        if(i==2){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
            llsub6.setVisibility(View.VISIBLE);
            llsub7.setVisibility(View.GONE);
            llsub8.setVisibility(View.GONE);
        }

        if(i==3){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
            llsub6.setVisibility(View.VISIBLE);
            llsub7.setVisibility(View.VISIBLE);
            llsub8.setVisibility(View.GONE);
        }

        if(i==4){
            llsub1.setVisibility(View.VISIBLE);
            llsub2.setVisibility(View.VISIBLE);
            llsub3.setVisibility(View.VISIBLE);
            llsub4.setVisibility(View.VISIBLE);
            llsub5.setVisibility(View.VISIBLE);
            llsub6.setVisibility(View.VISIBLE);
            llsub7.setVisibility(View.VISIBLE);
            llsub8.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public double calcSGPA(){

        Double c1=0.0,c2=0.0,c3=0.0,c4=0.0,c5=0.0,c6=0.0,c7=0.0,c8=0.0;
        String g1, g2, g3, g4, g5, g6, g7, g8;
        try {
            c1 = Double.parseDouble(etsub1.getText().toString());
            c2 = Double.parseDouble(etsub2.getText().toString());
            c3 = Double.parseDouble(etsub3.getText().toString());
            c4 = Double.parseDouble(etsub4.getText().toString());
            c5 = Double.parseDouble(etsub5.getText().toString());
            c6 = Double.parseDouble(etsub6.getText().toString());
            c7 = Double.parseDouble(etsub7.getText().toString());
            c8 = Double.parseDouble(etsub8.getText().toString());
        }
        catch (Exception e){

        }

        g1 = etgrade1.getText().toString();
        g2 = etgrade2.getText().toString();
        g3 = etgrade3.getText().toString();
        g4 = etgrade4.getText().toString();
        g5 = etgrade5.getText().toString();
        g6 = etgrade6.getText().toString();
        g7 = etgrade7.getText().toString();
        g8 = etgrade8.getText().toString();

        int gp1 = calgp(g1);
        int gp2 = calgp(g2);
        int gp3 = calgp(g3);
        int gp4 = calgp(g4);
        int gp5 = calgp(g5);
        int gp6 = calgp(g6);
        int gp7 = calgp(g7);
        int gp8 = calgp(g8);



        Double sgpa = 0.0;

        sgpa = (c1*gp1+c2*gp2+c3*gp3+c4*gp4+c5*gp5+c6*gp6+c7*gp7+c8*gp8)/(c1+c2+c3+c4+c5+c6+c7+c8);

        return sgpa;


    }

    public int calgp(String grade)
    {

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
