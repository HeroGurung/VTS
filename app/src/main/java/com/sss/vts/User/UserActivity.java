package com.sss.vts.User;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


public class UserActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

//    private TextView name, username;
    SessionManager sessionManager;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sessionManager = new SessionManager(this);
        sessionManager.checklogin();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


//        name = findViewById(R.id.name);
//        username = findViewById(R.id.username);
//
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String mName = user.get(sessionManager.NAME);
//        String mUsername = user.get(sessionManager.USERNAME);
//
//        name.setText(mName);
//        username.setText(mUsername);
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            sessionManager.logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_attendance)
        {
            Toast.makeText(getApplicationContext(), "Attendance Clicked", Toast.LENGTH_SHORT).show();
//                    return true;
            // Handles the action
        } else if (id == R.id.nav_profile)
        {
            Toast.makeText(getApplicationContext(), "Profile Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_weekly_test)
        {
            Toast.makeText(getApplicationContext(), "Weekly Test Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_performance)
        {
            Toast.makeText(getApplicationContext(), "Performance Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_settings)
        {
            Toast.makeText(getApplicationContext(), "Settings Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_accounts)
        {
            Toast.makeText(getApplicationContext(), "Accounts Clicked", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

