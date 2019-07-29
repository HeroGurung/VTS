package com.sss.vts.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sss.vts.Admin.AdminActivity;
import com.sss.vts.User.UserActivity;
import com.sss.vts.R;
import com.sss.vts.Session_handler.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.sss.vts.Helper.utils.baseURL_adminLogin;
import static com.sss.vts.Helper.utils.baseURL_login;

public class LoginActivity extends AppCompatActivity {

    String[] userType = {"USER", "ADMIN"};
    private EditText username,password;
//        private ProgressBar loading;
    private Button btn_login;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinner = findViewById(R.id.spinner_userType);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,userType);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Object item = parent.getItemAtPosition(position);
                if (item != null) {

                    switch(item.toString()){
                        case "ADMIN":
                            btn_login.setOnClickListener(new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {
                                    String mUsername = username.getText().toString().trim();
                                    String mPassword = password.getText().toString().trim();

                                    if (!mUsername.isEmpty()||!mPassword.isEmpty())
                                    {
                                        adminLogin(mUsername,mPassword);
                                    }else
                                    {
                                        username.setError("Please insert username");
                                        password.setError("Please insert password");
                                    }
                                }
                            });
                            break;
                        case "USER":
                            btn_login.setOnClickListener(new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {
                                    String mUsername = username.getText().toString().trim();
                                    String mPassword = password.getText().toString().trim();

                                    if (!mUsername.isEmpty()||!mPassword.isEmpty())
                                    {
                                        login(mUsername,mPassword);
                                    }else
                                    {
                                        username.setError("Please insert username");
                                        password.setError("Please insert password");
                                    }
                                }
                            });
                            break;

                        default: break;
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sessionManager = new SessionManager(this);

//        loading = findViewById(R.id.loading);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);


    }

    private void login(final String username, final String password)
    {
//        loading.setVisibility(View.VISIBLE);
//        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseURL_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1"))
                            {
                                for (int i = 0; i< jsonArray.length();i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String username = object.getString("username").trim();

                                    sessionManager.createSession(name,username);

                                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);

                                    intent.putExtra("name",name);
                                    intent.putExtra("username",username);
                                    startActivity(intent);

//                                    loading.setVisibility(View.GONE);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.setVisibility(View.GONE);
//                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this,"Invalid username or Password",Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        loading.setVisibility(View.GONE);
//                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this,"Invalid username or Password",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void adminLogin(final String username, final String password)
    {
//        loading.setVisibility(View.VISIBLE);
//        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseURL_adminLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1"))
                            {
                                for (int i = 0; i< jsonArray.length();i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String username = object.getString("username").trim();

                                    sessionManager.createSession(name,username);

                                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);

                                    intent.putExtra("name",name);
                                    intent.putExtra("username",username);
                                    startActivity(intent);

//                                    loading.setVisibility(View.GONE);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            loading.setVisibility(View.GONE);
//                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this,"Invalid username or Password",Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                        loading.setVisibility(View.GONE);
//                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this,"Invalid username or Password",Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
