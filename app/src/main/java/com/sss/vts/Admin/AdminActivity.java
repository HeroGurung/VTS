package com.sss.vts.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sss.vts.R;
import com.sss.vts.Session_handler.SessionManager;

import java.util.HashMap;


public class AdminActivity extends AppCompatActivity {

    private TextView name, username;
    private Button btn_logout;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sessionManager = new SessionManager(this);
        sessionManager.checkadminlogin();


        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        btn_logout = findViewById(R.id.btn_logout);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mUsername = user.get(sessionManager.USERNAME);

        name.setText(mName);
        username.setText(mUsername);

        btn_logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                sessionManager.adminlogout();
            }
        });

    }
}
