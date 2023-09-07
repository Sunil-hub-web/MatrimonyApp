package com.example.matrimonyapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.adapter.PackageHistoryAdapter;
import com.example.matrimonyapp.adapter.PackagePriceAdapter;
import com.example.matrimonyapp.modelclass.PackageHistoryModel;
import com.example.matrimonyapp.modelclass.PackagePriceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PackageHistoryFragment extends Fragment {

    RecyclerView packageHistoryRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<PackageHistoryModel> packageHistoryModels = new ArrayList<>();
    SessionManager sessionManager;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.packagehistory,container,false);

        packageHistoryRecycler = view.findViewById(R.id.packageHistoryRecycler);

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();
        getPackageDetails(userId);

        return view;
    }

    public void getPackageDetails(String customer_id){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.packagehistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String statusArray = jsonObject_messages.getString("status");
                        JSONObject jsonObject_satues = new JSONObject(statusArray);
                        String str_package = jsonObject_satues.getString("packagehistory");

                        packageHistoryModels = new ArrayList<>();
                        packageHistoryModels.clear();

                        JSONArray jsonArray_nationality = new JSONArray(str_package);

                        for (int i=0;i<jsonArray_nationality.length();i++){

                            JSONObject jsonObject_nationality = jsonArray_nationality.getJSONObject(i);
                            String packagehistory_id = jsonObject_nationality.getString("packagehistory_id");
                            String packagee_id = jsonObject_nationality.getString("packagee_id");
                            String user_id = jsonObject_nationality.getString("user_id");
                            String pack_name = jsonObject_nationality.getString("pack_name");
                            String pack_description = jsonObject_nationality.getString("pack_description");
                            String pack_activeday = jsonObject_nationality.getString("pack_activeday");
                            String pack_price = jsonObject_nationality.getString("pack_price");
                            String no_of_profile = jsonObject_nationality.getString("no_of_profile");
                            String packageactive_date = jsonObject_nationality.getString("packageactive_date");
                            String packageexpiry_date = jsonObject_nationality.getString("packageexpiry_date");
                            String candidate_name = jsonObject_nationality.getString("candidate_name");
                            String candidate_id = jsonObject_nationality.getString("candidate_id");

                            PackageHistoryModel packagehisModel = new PackageHistoryModel
                                    (packagehistory_id,packagee_id,user_id,pack_name,pack_description,pack_activeday,
                                            pack_price,no_of_profile,packageactive_date,packageexpiry_date,candidate_name,candidate_id);
                            packageHistoryModels.add(packagehisModel);
                        }

                        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                        PackageHistoryAdapter packagePriceAdapter = new PackageHistoryAdapter(packageHistoryModels,getActivity());
                        packageHistoryRecycler.setLayoutManager(linearLayoutManager);
                        packageHistoryRecycler.setHasFixedSize(true);
                        packageHistoryRecycler.setAdapter(packagePriceAdapter);

                        Log.d("nationalityModelsdat",packageHistoryModels.toString());
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();

                Log.d("errordata",error.toString());
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("customer_id",customer_id);

                Log.d("params123",params.toString());

                return params;
            }
        };

        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
}
