package com.sss.vts.User.Home_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sss.vts.R;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        getSupportActionBar().setTitle("Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
