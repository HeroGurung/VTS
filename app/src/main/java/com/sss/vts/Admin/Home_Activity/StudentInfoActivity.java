package com.sss.vts.Admin.Home_Activity;

import android.content.Intent;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.sss.vts.Admin.AdminRegister;
import com.sss.vts.R;
import com.sss.vts.User.RegisterActivity;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class StudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        getSupportActionBar().setTitle("Student and Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FabSpeedDial fabSpeedDial = findViewById(R.id.fabAdd);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.add_admin) {

                    Intent intent = new Intent(StudentInfoActivity.this,AdminRegister.class);
                    startActivity(intent);
                }
                else if (id == R.id.add_user)
                {
                    Intent intent = new Intent(StudentInfoActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }

                return StudentInfoActivity.super.onOptionsItemSelected(menuItem);
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }
}
