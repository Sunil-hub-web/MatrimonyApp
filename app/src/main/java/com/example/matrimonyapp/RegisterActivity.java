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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    boolean passwordVisiable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.editFirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editFirstName.getText().toString().trim().equals("")) {

                    binding.editFirstName.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editFirstName.setBackgroundResource(R.drawable.clickedit_back);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editLastName.getText().toString().trim().equals("")) {

                    binding.editLastName.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editLastName.setBackgroundResource(R.drawable.clickedit_back);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editEmail.getText().toString().trim().equals("")) {

                    binding.editEmail.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editEmail.setBackgroundResource(R.drawable.clickedit_back);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.editPhoneNumber.getText().toString().trim().equals("")) {

                    binding.editPhoneNumber.setBackgroundResource(R.drawable.edit_back);

                } else {

                    binding.editPhoneNumber.setBackgroundResource(R.drawable.clickedit_back);
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

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editFirstName.getText().toString().trim().equals("")){

                    Toast.makeText(RegisterActivity.this, "Enter Your FirstName", Toast.LENGTH_SHORT).show();

                } else if (binding.editLastName.getText().toString().trim().equals("")) {

                    Toast.makeText(RegisterActivity.this, "Enter Your LastName", Toast.LENGTH_SHORT).show();

                }else if (binding.editEmail.getText().toString().trim().equals("")) {

                    Toast.makeText(RegisterActivity.this, "Enter Your Email Id", Toast.LENGTH_SHORT).show();

                }else if (binding.editPhoneNumber.getText().toString().trim().equals("")) {

                    Toast.makeText(RegisterActivity.this, "Enter Your Mobile No", Toast.LENGTH_SHORT).show();

                }else if (binding.editPassword.getText().toString().trim().equals("")) {

                    Toast.makeText(RegisterActivity.this, "Enter Your password", Toast.LENGTH_SHORT).show();

                }else{

                    registerActivity(
                            binding.editFirstName.getText().toString().trim(),
                            binding.editLastName.getText().toString().trim(),
                            binding.editEmail.getText().toString().trim(),
                            binding.editPassword.getText().toString().trim(),
                            binding.editPhoneNumber.getText().toString().trim()
                    );

                }
            }
        });


    }

    public void registerActivity(String first_name, String last_name, String email,String password,String contact){

        ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Register Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.registration, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject1 = new JSONObject(response);

                    String status = jsonObject1.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject1.getString("error");
                        String message = jsonObject1.getString("message");

                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterActivity.this, UserInformation.class));

                    }else{

                        String error = jsonObject1.getString("error");
                        String message = jsonObject1.getString("message");

                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("first_name",first_name);
                params.put("last_name",last_name);
                params.put("email",email);
                params.put("password",password);
                params.put("contact",contact);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }


}