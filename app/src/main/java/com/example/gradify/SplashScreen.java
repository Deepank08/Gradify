package com.example.gradify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

/**
 * Written by team Gradify.
 */

public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_splash_screen);
        session=new SessionManager(getApplicationContext());


        new Handler().postDelayed(new Runnable() {         //Thread for Splash screen with a timer.
            @Override
            public void run() {

                if (session.isLoggedIn()){
                    Intent i=new Intent(SplashScreen.this,StudentInfo.class);
                    startActivity(i);
                }
                else{
                    Intent i=new Intent(SplashScreen.this,Login.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        },3000);
    }


}


