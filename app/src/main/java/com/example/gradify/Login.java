package com.example.gradify;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Written by team Gradify.
 */

public class Login extends AppCompatActivity implements DialogInterface.OnClickListener {

    EditText Name, RollNo;
    TextView errorName, errorRollNo,aboutUs,help;
    Button login;
    String name, rollno;
    String status;
    Intent TodisplayPage;
    private static final String Name_Pattern="^[a-zA-Z\\s]+$"; //Declaring Regular Expression for a Legal name.
    private Pattern pattern=Pattern.compile(Name_Pattern);  //Using Pattern class to use and compile RE.
    private Matcher matcher; // Complimentary object to match pattern.

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_login);

        Name=(EditText)findViewById(R.id.name);
        RollNo=(EditText)findViewById(R.id.rollno);
        login=(Button)findViewById(R.id.login);
        errorName=(TextView)findViewById(R.id.errorName);
        errorRollNo=(TextView)findViewById(R.id.errorRollNo);
        final SharedPreferences.Editor editor;
        aboutUs=(TextView)findViewById(R.id.aboutUs);
        help=(TextView)findViewById(R.id.help);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_SHORT).show();

        login.setOnClickListener(new View.OnClickListener() {   //OnClick listener on login button.
            @Override
            public void onClick(View v) {

                name=Name.getText().toString();
                rollno=RollNo.getText().toString();


                hideKeyboard(); //Function to hide keyboard.

                // Toast.makeText(Login.this,"Passed roll no "+rollno,Toast.LENGTH_LONG).show();
                // Toast.makeText(Login.this,"Passed name "+name,Toast.LENGTH_LONG).show();
                if(!validaterollnumber(rollno) && !validateName(name))
                {
                    errorName.setText("Not a valid name");
                    errorRollNo.setText("Not a valid Roll number");

                }
                if(validateName(name) && !validaterollnumber(rollno))
                {
                    errorName.setText("");
                    errorRollNo.setText("Not a valid Roll number");
                }
                if(!validateName(name) && validaterollnumber(rollno))
                {
                    errorName.setText("Not a valid name");
                    errorRollNo.setText("");
                }
                if(validateName(name) && validaterollnumber(rollno))
                {
                    errorName.setText("");
                    errorRollNo.setText("");
                    doLogin();

                }

            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Login.this, AboutUs.class);
                startActivity(in);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Login.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Help");
                builder.setMessage("You can only login when you enter both a valid name and a valid roll number. Name should be your first name as in the University Stats. And roll number should be a 10 digit University Roll Number. ");
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }


    public void hideKeyboard()
    {
        View view = getCurrentFocus();
        if(view!=null)
        {
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromInputMethod(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS); //logic to hide keyboard after the button is pressed.
        }
    }


    public boolean validateName(String name)  //Method to validate a legal Name using Regular Expression.
    {
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean validaterollnumber(String rollno) // Method to validate legal rollnumber >10 digits.
    {
        return rollno.length()==10;
    }

    public void doLogin() //Method to perform Login
    {
        Toast.makeText(getApplicationContext(), "Login Successful...", Toast.LENGTH_SHORT).show();


        session.createLoginSession(name, rollno); //creating session details


        TodisplayPage=new Intent(Login.this,StudentInfo.class);
        TodisplayPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TodisplayPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //TodisplayPage.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(TodisplayPage);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Thankyou for using GRADIFY :)", Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if(which==-1) {
            Toast.makeText(Login.this,"You have clicked on yes",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Login.this, "You have clicked on no", Toast.LENGTH_SHORT).show();
        }

    }
}

