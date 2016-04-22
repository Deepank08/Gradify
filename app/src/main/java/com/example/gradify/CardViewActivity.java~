package com.example.arshdeep.gradify;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity {

    TextView personName;
    TextView personPhoneNo;
    TextView personEmail;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = (TextView)findViewById(R.id.person_name);
        personPhoneNo = (TextView)findViewById(R.id.person_phoneNo);
        personEmail = (TextView)findViewById(R.id.person_email);
        personPhoto = (ImageView)findViewById(R.id.person_photo);

        personName.setText("Arshdeep Kaur Gulati");
        personPhoneNo.setText("+91 7310640817");
        personEmail.setText("arshdeepkaurgulati@gmail.com");
        personPhoto.setImageResource(R.drawable.arshdeep);
    }
}
