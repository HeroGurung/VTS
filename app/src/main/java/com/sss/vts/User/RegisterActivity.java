package com.sss.vts.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sss.vts.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.sss.vts.Helper.utils.baseURL_register;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, username, password, c_password;
    private Button btn_register;
//    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("User Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate()
    {
        final String password = this.password.getText().toString().trim();
        final String c_password = this.c_password.getText().toString().trim();

        if (!password.equals(c_password))
        {
            Toast.makeText(RegisterActivity.this,"Confirm Password Do not match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Register();
        }
    }

    private void Register()
    {
//        loading.setVisibility(View.VISIBLE);
//        btn_register.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseURL_register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Register success !", Toast.LENGTH_SHORT).show();
//                                loading.setVisibility(View.GONE);
//                                btn_register.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register error !" + e.toString(), Toast.LENGTH_SHORT).show();
//                            loading.setVisibility(View.GONE);
//                            btn_register.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register error !"+error.toString(),Toast.LENGTH_SHORT).show();
//                        loading.setVisibility(View.GONE);
//                        btn_register.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
