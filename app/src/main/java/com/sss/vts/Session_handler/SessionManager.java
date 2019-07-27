package com.sss.vts.Session_handler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.sss.vts.Admin.AdminActivity;
import com.sss.vts.User.UserActivity;
import com.sss.vts.Login.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;


    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String USERNAME = "USERNAME";

    public SessionManager(Context context)
    {
        this.context=context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession (String name, String username){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(USERNAME,username);
        editor.apply();
    }

    public boolean islogin()
    {
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checklogin()
    {
        if (!this.islogin()) {
            Intent i = new Intent(context,LoginActivity.class);
            context.startActivity(i);
            ((UserActivity)context).finish();
        }
    }

    public void checkadminlogin()
    {
        if (!this.islogin()) {
            Intent i = new Intent(context,LoginActivity.class);
            context.startActivity(i);
            ((AdminActivity)context).finish();
        }
    }

    public HashMap<String, String> getUserDetail()
    {
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        return user;
    }

    public void logout()
    {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((UserActivity)context).finish();
    }

    public void adminlogout()
    {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((AdminActivity)context).finish();
    }
}