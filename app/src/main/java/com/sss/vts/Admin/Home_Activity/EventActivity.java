package com.sss.vts.Admin.Home_Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
