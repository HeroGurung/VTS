package com.sss.vts.User.Home_Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sss.vts.R;

public class TrainingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        getSupportActionBar().setTitle("Training");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
