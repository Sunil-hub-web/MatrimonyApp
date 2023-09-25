package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.LoginActivity;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.adapter.ViewProfileSelectedAdapter;
import com.example.matrimonyapp.databinding.FragmentViewprofileBinding;
import com.example.matrimonyapp.modelclass.ProfileSelected_ModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileViewFragment extends Fragment {

    FragmentViewprofileBinding binding;
    ArrayList<ProfileSelected_ModelClass> profileSelectedMode = new ArrayList<>();
    SessionManager sessionManager;
    String userId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentViewprofileBinding.inflate(inflater,container,false);

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();
        getViewProfile(userId);

        return binding.getRoot();
    }

    public void getViewProfile(String user_id){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Change Password Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.viewandselectprofile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode = jsonObject_message.getString("responsecode");
                        String statusmess = jsonObject_message.getString("status");
                        JSONObject jsonObject_statues = new JSONObject(statusmess);
                        String profileviews = jsonObject_statues.getString("profileviews");
                        String selectedprofile = jsonObject_statues.getString("selectedprofile");

                        JSONArray jsonArray_profile = new JSONArray(profileviews);

                        for (int i=0;i<jsonArray_profile.length();i++){

                            JSONObject jsonObject_select = jsonArray_profile.getJSONObject(i);
                            String candidate_name = jsonObject_select.getString("candidate_name");
                            String gender = jsonObject_select.getString("gender");
                            String profile_image = jsonObject_select.getString("profile_image");
                            String profile_id = jsonObject_select.getString("profile_id");
                            String user_id = jsonObject_select.getString("user_id");
                            String view_id = jsonObject_select.getString("view_id");
                            String prpackage_id = jsonObject_select.getString("prpackage_id");
                            String select_profile = jsonObject_select.getString("select_profile");
                            String created_date = jsonObject_select.getString("created_date");
                            String updated_date = jsonObject_select.getString("updated_date");
                            String city_name = jsonObject_select.getString("city_name");
                            String state_name = jsonObject_select.getString("state_name");
                            String country_name = jsonObject_select.getString("country_name");

                            ProfileSelected_ModelClass profileSelected_model = new ProfileSelected_ModelClass(
                                    candidate_name,gender,profile_image,profile_id,user_id,view_id,prpackage_id,select_profile,
                                    created_date,updated_date,city_name,state_name,country_name
                            );

                            profileSelectedMode.add(profileSelected_model);
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                        //GridLayoutManager gridLayoutManager = new GridLayoutManager
                                //(getActivity(),2, GridLayoutManager.VERTICAL,false);

                        ViewProfileSelectedAdapter viewProfileSelectedAdapter = new
                                ViewProfileSelectedAdapter(getActivity(),profileSelectedMode);

                        binding.viewprofileRecycler.setLayoutManager(linearLayoutManager);
                        binding.viewprofileRecycler.setHasFixedSize(true);
                        binding.viewprofileRecycler.setAdapter(viewProfileSelectedAdapter);


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
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
