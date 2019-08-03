package com.sss.vts.User.Home_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sss.vts.R;

public class AssignmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        getSupportActionBar().setTitle("Assignments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
