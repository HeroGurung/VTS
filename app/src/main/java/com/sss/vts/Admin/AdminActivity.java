package com.sss.vts.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sss.vts.Admin.Home_Activity.AssignmentsActivity;
import com.sss.vts.Admin.Home_Activity.DownloadsActivity;
import com.sss.vts.Admin.Home_Activity.EventActivity;
import com.sss.vts.Admin.Home_Activity.InformationActivity;
import com.sss.vts.Admin.Home_Activity.RoutineActivity;
import com.sss.vts.Admin.Home_Activity.StudentInfoActivity;
import com.sss.vts.Admin.Home_Activity.TrainingActivity;
import com.sss.vts.R;
import com.sss.vts.Session_handler.SessionManager;

import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    public void eventClick(View v) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void downloadClick (View v){
        Intent intent = new Intent(this, DownloadsActivity.class);
        startActivity(intent);
    }
    public void assignmentClick (View v){
        Intent intent = new Intent(this, AssignmentsActivity.class);
        startActivity(intent);
    }
    public void informationClick (View v){
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }
    public void routineClick (View v){
        Intent intent = new Intent(this, RoutineActivity.class);
        startActivity(intent);
    }
    public void trainingClick (View v) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }
    public void studentInfoClick (View v)
    {
        Intent intent = new Intent(this, StudentInfoActivity.class);
        startActivity(intent);
    }
//    private TextView name, username;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(this);
        sessionManager.checkadminlogin();

//        name = findViewById(R.id.name);
//        username = findViewById(R.id.username);
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String mName = user.get(sessionManager.NAME);
//        String mUsername = user.get(sessionManager.USERNAME);
//
//        name.setText(mName);
//        username.setText(mUsername);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the homemenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Logout)
        {
            sessionManager.adminlogout();
        }

        return super.onOptionsItemSelected(item);
    }

}
