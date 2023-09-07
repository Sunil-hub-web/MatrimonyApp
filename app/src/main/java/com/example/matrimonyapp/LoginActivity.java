package com.example.matrimonyapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.databinding.ActivityLoginBinding;
import com.example.matrimonyapp.fragment.PersonalInformationFrag;
import com.example.matrimonyapp.fragment.ViewProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    boolean passwordVisiable;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(LoginActivity.this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.editEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editEmailId.getText().toString().trim().equals("")) {

                    binding.editEmailId.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editEmailId.setBackgroundResource(R.drawable.clickedit_back);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editPassword.getText().toString().trim().equals("")) {

                    binding.editPassword.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editPassword.setBackgroundResource(R.drawable.clickedit_back);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editPassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= binding.editPassword.getRight() - binding.editPassword.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = binding.editPassword.getSelectionEnd();
                        if (passwordVisiable) {

                            //set Drawable Image here
                            binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off, 0);
                            // for show Password
                            binding.editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;

                        } else {

                            //set Drawable Image here
                            binding.editPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility, 0);
                            // for show Password
                            binding.editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }

                        binding.editPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editEmailId.getText().toString().trim().equals("")){

                    Toast.makeText(LoginActivity.this, "Enter Your EmailId", Toast.LENGTH_SHORT).show();

                } else if (binding.editPassword.getText().toString().trim().equals("")) {

                    Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                }else{

                    userLoginPage(binding.editEmailId.getText().toString().trim(),
                                  binding.editPassword.getText().toString().trim());
                }
            }
        });

    }

    public void userLoginPage(String email,String password) {

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Login Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.Loginauth, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    progressDialog.dismiss();

                    if (status.equals("200")) {

                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");
                        JSONObject jsonObject_statues = new JSONObject(statusArray);

                        String customer_id = jsonObject_statues.getString("customer_id");
                        String fullname = jsonObject_statues.getString("fullname");
                        String email = jsonObject_statues.getString("email");
                        String password = jsonObject_statues.getString("password");

                        sessionManager.setUSERID(customer_id);
                        sessionManager.setUSERNAME(fullname);
                        sessionManager.setUSEREMAIL(email);
                       // sessionManager.setMOBILENO(contact);
                        sessionManager.setpassword(password);

                        sessionManager.setLogin();

                        startActivity(new Intent(LoginActivity.this, DashBordActivity.class));

                      /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ViewProfileActivity viewProfileActivity = new ViewProfileActivity();
                        ft.replace(R.id.framLayout, viewProfileActivity);
                        ft.addToBackStack(null);
                        ft.commit();*/

                    } else {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusArray = jsonObject_message.getString("status");

                        Toast.makeText(LoginActivity.this, statusArray, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();

/*                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();

                } else {

                    Log.d("successresponceVolley", "" + error.networkResponse.statusCode);
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        try {
                            String jError = new String(networkResponse.data);
                            JSONObject jsonError = new JSONObject(jError);

                            String data = jsonError.getString("msg");
                            Toast.makeText(LoginPage.this, data, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("successresponceVolley", "" + e);
                        }


                    }

                }*/
            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sessionManager.isLogin()){

            startActivity(new Intent(LoginActivity.this,DashBordActivity.class));
        }
    }
}