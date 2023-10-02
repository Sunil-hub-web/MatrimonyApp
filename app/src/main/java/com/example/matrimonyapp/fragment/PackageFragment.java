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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.adapter.PackagePriceAdapter;
import com.example.matrimonyapp.databinding.PackageFragmentBinding;
import com.example.matrimonyapp.modelclass.NationalityModel;
import com.example.matrimonyapp.modelclass.PackagePriceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PackageFragment extends Fragment {

    RecyclerView packagePriceRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<PackagePriceModel> packagePriceModels = new ArrayList<>();

    PackageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.package_fragment,container,false);

        packagePriceRecycler = view.findViewById(R.id.packagePriceRecycler);

        getPackageDetails();

        return view;
    }

    public void getPackageDetails(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.Packageprice, new Response.Listener<String>() {
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
                        String str_package = jsonObject_satues.getString("package");

                        packagePriceModels = new ArrayList<>();
                        packagePriceModels.clear();

                        JSONArray jsonArray_nationality = new JSONArray(str_package);

                        for (int i=0;i<jsonArray_nationality.length();i++){

                            JSONObject jsonObject_nationality = jsonArray_nationality.getJSONObject(i);
                            String package_id = jsonObject_nationality.getString("package_id");
                            String package_name = jsonObject_nationality.getString("package_name");
                            String package_description = jsonObject_nationality.getString("package_description");
                            String active_day = jsonObject_nationality.getString("active_day");
                            String package_price = jsonObject_nationality.getString("package_price");
                            String no_of_profile = jsonObject_nationality.getString("no_of_profile");
                            String package_status = jsonObject_nationality.getString("package_status");

                            PackagePriceModel packagePriceModel = new PackagePriceModel
                                    (package_id,package_name,package_description,active_day,package_price,
                                            no_of_profile,package_status);
                            packagePriceModels.add(packagePriceModel);
                        }

                        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
                        PackagePriceAdapter packagePriceAdapter = new PackagePriceAdapter(packagePriceModels,getActivity());
                        packagePriceRecycler.setLayoutManager(gridLayoutManager);
                        packagePriceRecycler.setHasFixedSize(true);
                        packagePriceRecycler.setAdapter(packagePriceAdapter);

                        Log.d("nationalityModelsdat",packagePriceModels.toString());
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
        });

        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }
}
