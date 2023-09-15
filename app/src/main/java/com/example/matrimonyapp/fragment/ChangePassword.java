package com.example.matrimonyapp.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.ForgotPassword;
import com.example.matrimonyapp.LoginActivity;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.databinding.FragmentChangepasswordBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword extends Fragment {

    FragmentChangepasswordBinding binding;
    boolean passwordVisiable;
    SessionManager sessionManager;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentChangepasswordBinding.inflate(inflater,container,false);

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();

        binding.editNewPassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= binding.editNewPassword.getRight() - binding.editNewPassword.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = binding.editNewPassword.getSelectionEnd();
                        if (passwordVisiable) {

                            //set Drawable Image here
                            binding.editNewPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off, 0);
                            // for show Password
                            binding.editNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;

                        } else {

                            //set Drawable Image here
                            binding.editNewPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility, 0);
                            // for show Password
                            binding.editNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }

                        binding.editNewPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.editConfirmPassword.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (event.getRawX() >= binding.editConfirmPassword.getRight() - binding.editConfirmPassword.getCompoundDrawables()[Right].getBounds().width()) {

                        int selection = binding.editConfirmPassword.getSelectionEnd();
                        if (passwordVisiable) {

                            //set Drawable Image here
                            binding.editConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off, 0);
                            // for show Password
                            binding.editConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisiable = false;

                        } else {

                            //set Drawable Image here
                            binding.editConfirmPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility, 0);
                            // for show Password
                            binding.editConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisiable = true;
                        }

                        binding.editConfirmPassword.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.editNewPassword.getText().toString().equals("")){

                    Toast.makeText(getActivity(), "Fill The New Password", Toast.LENGTH_SHORT).show();

                } else if (binding.editConfirmPassword.getText().toString().equals("")) {

                    Toast.makeText(getActivity(), "Fill The ConfirmPassword", Toast.LENGTH_SHORT).show();

                }else{

                    if (binding.editNewPassword.getText().toString().trim().
                            equals(binding.editConfirmPassword.getText().toString().trim())){

                        changepassword(userId,binding.editNewPassword.getText().toString().trim());
                    }else{

                        Toast.makeText(getActivity(), "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return binding.getRoot();
    }

    public void changepassword(String user_id, String newpassword){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Change Password Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.Updatepassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("400")){
                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusmess = jsonObject_message.getString("status");
                        Toast.makeText(getActivity(), statusmess, Toast.LENGTH_SHORT).show();
                    }else{

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusmess = jsonObject_message.getString("status");
                        Toast.makeText(getActivity(), statusmess, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("newpassword",newpassword);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
