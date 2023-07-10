package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.matrimonyapp.databinding.FragmentRegister2Binding;
import com.example.matrimonyapp.databinding.FragmentRegister3Binding;
import com.example.matrimonyapp.modelclass.MaritalStatusModel;
import com.example.matrimonyapp.modelclass.NationalityModel;
import com.example.matrimonyapp.modelclass.ProfileForModel;
import com.example.matrimonyapp.modelclass.ReligionDataModel;
import com.example.matrimonyapp.modelclass.SubCategories;
import com.example.matrimonyapp.modelclass.SubCategoriesReligModel;
import com.example.matrimonyapp.modelclass.VisaStatusModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReligionFragment extends Fragment {

    ArrayList<ReligionDataModel> religionDataModels;
    ArrayList<String> nameReligion;
    HashMap<String,String> mapReligion;
    ArrayList<SubCategoriesReligModel> categoriesReligModels;
    ArrayList<String> nameReligion_Cast;
    HashMap<String,String> mapReligion_cast;
    ArrayList<SubCategories> subcategoriesReligModels;
    ArrayList<String> nameReligion_SubCast;
    HashMap<String,String> mapReligion_Subcast;
    FragmentRegister3Binding binding;
    String religionId,religionName,castId,castName,subCastId,subCastName,userID;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_register2,container,false);

        binding = FragmentRegister3Binding.inflate(inflater,container,false);

        masterApiList1();
        sessionManager = new SessionManager(getContext());
        userID = sessionManager.getUSERID();
        binding.spinnerReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                religionName = binding.spinnerReligion.getItemAtPosition(binding.spinnerReligion.getSelectedItemPosition()).toString();

                if (religionName.equalsIgnoreCase("Select Religion")) {

                    religionId = "";

                } else {

                    religionId = mapReligion.get(religionName);

                    masterApiList2(religionId);

                    Log.d("yourreligionId",religionId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerCast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                castName = binding.spinnerCast.getItemAtPosition(binding.spinnerCast.getSelectedItemPosition()).toString();

                if (castName.equalsIgnoreCase("Select Cast")) {

                    castId = "";

                } else {

                    castId = mapReligion_cast.get(castName);

                    Log.d("yourcastId",castId);

                    masterApiList3(castId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerSubcast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subCastName = binding.spinnerSubcast.getItemAtPosition(binding.spinnerSubcast.getSelectedItemPosition()).toString();

                if (subCastName.equalsIgnoreCase("Select SubCaste")) {

                    subCastId = "";

                } else {

                    subCastId = mapReligion_Subcast.get(subCastName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new JatakaDetailsFragment();
                Bundle args = new Bundle();
                args.putString("YourKey", "SchoolUniform");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framLayout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerDetails(userID,religionId,castId,subCastId);
            }
        });

        return binding.getRoot();
    }

    public void masterApiList1(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiList.masterdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String statusArray = jsonObject_messages.getString("status");
                        JSONObject jsonObject_satues = new JSONObject(statusArray);
                        String nationality = jsonObject_satues.getString("nationality");
                        String visa_status = jsonObject_satues.getString("visa_status");
                        String profile_for = jsonObject_satues.getString("profile_for");
                        String marital_status = jsonObject_satues.getString("marital_status");
                        String religion_data = jsonObject_satues.getString("religion_data");
                        String rashi = jsonObject_satues.getString("rashi");
                        String gotra = jsonObject_satues.getString("gotra");
                        String star = jsonObject_satues.getString("star");
                        String lagna = jsonObject_satues.getString("lagna");
                        String education = jsonObject_satues.getString("education");
                        String profession = jsonObject_satues.getString("profession");
                        String income = jsonObject_satues.getString("income");
                        String location_data = jsonObject_satues.getString("location_data");
                        String family_type = jsonObject_satues.getString("family_type");
                        String bodytype = jsonObject_satues.getString("bodytype");
                        String ethnicity = jsonObject_satues.getString("ethnicity");
                        String complexion = jsonObject_satues.getString("complexion");
                        String language = jsonObject_satues.getString("language");

                        religionDataModels = new ArrayList<>();
                        nameReligion = new ArrayList<>();
                        mapReligion = new HashMap<>();

                        religionDataModels.clear();
                        nameReligion.clear();
                        mapReligion.clear();

                        JSONArray jsonArray_religion_data = new JSONArray(religion_data);

                        if (jsonArray_religion_data.length() != 0){

                            for (int i=0;i<jsonArray_religion_data.length();i++){

                                JSONObject jsonObject1 = jsonArray_religion_data.getJSONObject(i);

                                String cat_id = jsonObject1.getString("cat_id");
                                String cat_name = jsonObject1.getString("cat_name");
                                String parent_id = jsonObject1.getString("parent_id");
                                String sub_categories = jsonObject1.getString("sub_categories");

                                nameReligion.add(cat_name);
                                mapReligion.put(cat_name,cat_id);

                                ReligionDataModel religionDataModel = new ReligionDataModel(cat_id,cat_name,parent_id);
                                religionDataModels.add(religionDataModel);
                            }

                            nameReligion.add(0, "Select Religion");

                            ArrayAdapter<String> nameReligionAdapter = new ArrayAdapter<String>(getContext(),
                                    R.layout.spinnerfront2, nameReligion);
                            nameReligionAdapter.setDropDownViewResource(R.layout.spinneritem);
                            binding.spinnerReligion.setAdapter(nameReligionAdapter);

                        }else{

                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }

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
    public void masterApiList2(String parentid){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiList.masterdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String statusArray = jsonObject_messages.getString("status");
                        JSONObject jsonObject_satues = new JSONObject(statusArray);
                        String nationality = jsonObject_satues.getString("nationality");
                        String visa_status = jsonObject_satues.getString("visa_status");
                        String profile_for = jsonObject_satues.getString("profile_for");
                        String marital_status = jsonObject_satues.getString("marital_status");
                        String religion_data = jsonObject_satues.getString("religion_data");
                        String rashi = jsonObject_satues.getString("rashi");
                        String gotra = jsonObject_satues.getString("gotra");
                        String star = jsonObject_satues.getString("star");
                        String lagna = jsonObject_satues.getString("lagna");
                        String education = jsonObject_satues.getString("education");
                        String profession = jsonObject_satues.getString("profession");
                        String income = jsonObject_satues.getString("income");
                        String location_data = jsonObject_satues.getString("location_data");
                        String family_type = jsonObject_satues.getString("family_type");
                        String bodytype = jsonObject_satues.getString("bodytype");
                        String ethnicity = jsonObject_satues.getString("ethnicity");
                        String complexion = jsonObject_satues.getString("complexion");
                        String language = jsonObject_satues.getString("language");

                        categoriesReligModels = new ArrayList<>();
                        nameReligion_Cast = new ArrayList<>();
                        mapReligion_cast = new HashMap<>();

                        categoriesReligModels.clear();
                        nameReligion_Cast.clear();
                        mapReligion_cast.clear();


                        JSONArray jsonArray_religion_data = new JSONArray(religion_data);

                        if (jsonArray_religion_data.length() != 0){

                            for (int i=0;i<jsonArray_religion_data.length();i++){

                                JSONObject jsonObject1 = jsonArray_religion_data.getJSONObject(i);

                                String cat_id = jsonObject1.getString("cat_id");
                                String cat_name = jsonObject1.getString("cat_name");
                                String parent_id = jsonObject1.getString("parent_id");
                                String sub_categories = jsonObject1.getString("sub_categories");

                                JSONArray jsonArray_religion_Cast = new JSONArray(sub_categories);

                                if (jsonArray_religion_Cast.length() != 0){

                                    for (int j = 0;j<jsonArray_religion_Cast.length();j++){

                                        JSONObject jsonObject11_Cast = jsonArray_religion_Cast.getJSONObject(j);

                                        String cat_id1 = jsonObject11_Cast.getString("cat_id");
                                        String cat_name1 = jsonObject11_Cast.getString("cat_name");
                                        String parent_id1 = jsonObject11_Cast.getString("parent_id");
                                        String sub_categories1 = jsonObject11_Cast.getString("sub_categories");

                                        if (parentid.equals(parent_id1)){

                                            SubCategoriesReligModel subCategoriesReligModel = new SubCategoriesReligModel(
                                                    cat_id1,cat_name1,parent_id1
                                            );

                                            categoriesReligModels.add(subCategoriesReligModel);

                                            nameReligion_Cast.add(cat_name1);
                                            mapReligion_cast.put(cat_name1,cat_id1);
                                        }
                                    }

                                }else{

                                    Toast.makeText(getContext(), "Religion_Cat Not Found", Toast.LENGTH_SHORT).show();
                                }

                            }

                            nameReligion_Cast.add(0, "Select Cast");

                            ArrayAdapter<String> nameReligion_CastAdapter = new ArrayAdapter<String>(getContext(),
                                    R.layout.spinnerfront2, nameReligion_Cast);
                            nameReligion_CastAdapter.setDropDownViewResource(R.layout.spinneritem);
                            binding.spinnerCast.setAdapter(nameReligion_CastAdapter);

                            Log.d("nameReligion_Cast",nameReligion_Cast.toString());

                        }else{

                            Toast.makeText(getContext(), "Religion Not Found", Toast.LENGTH_SHORT).show();
                        }

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
    public void masterApiList3(String parentid){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiList.masterdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")) {

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_messages = new JSONObject(messages);
                        String responsecode = jsonObject_messages.getString("responsecode");
                        String statusArray = jsonObject_messages.getString("status");
                        JSONObject jsonObject_satues = new JSONObject(statusArray);
                        String nationality = jsonObject_satues.getString("nationality");
                        String visa_status = jsonObject_satues.getString("visa_status");
                        String profile_for = jsonObject_satues.getString("profile_for");
                        String marital_status = jsonObject_satues.getString("marital_status");
                        String religion_data = jsonObject_satues.getString("religion_data");
                        String rashi = jsonObject_satues.getString("rashi");
                        String gotra = jsonObject_satues.getString("gotra");
                        String star = jsonObject_satues.getString("star");
                        String lagna = jsonObject_satues.getString("lagna");
                        String education = jsonObject_satues.getString("education");
                        String profession = jsonObject_satues.getString("profession");
                        String income = jsonObject_satues.getString("income");
                        String location_data = jsonObject_satues.getString("location_data");
                        String family_type = jsonObject_satues.getString("family_type");
                        String bodytype = jsonObject_satues.getString("bodytype");
                        String ethnicity = jsonObject_satues.getString("ethnicity");
                        String complexion = jsonObject_satues.getString("complexion");
                        String language = jsonObject_satues.getString("language");

                        JSONArray jsonArray_religion_data = new JSONArray(religion_data);

                        subcategoriesReligModels = new ArrayList<>();
                        nameReligion_SubCast = new ArrayList<>();
                        mapReligion_Subcast = new HashMap<>();

                        subcategoriesReligModels.clear();
                        nameReligion_SubCast.clear();
                        mapReligion_Subcast.clear();

                        if (jsonArray_religion_data.length() != 0){

                            for (int i=0;i<jsonArray_religion_data.length();i++){

                                JSONObject jsonObject1 = jsonArray_religion_data.getJSONObject(i);

                                String cat_id = jsonObject1.getString("cat_id");
                                String cat_name = jsonObject1.getString("cat_name");
                                String parent_id = jsonObject1.getString("parent_id");
                                String sub_categories = jsonObject1.getString("sub_categories");

                                JSONArray jsonArray_religion_Cast = new JSONArray(sub_categories);

                                if (jsonArray_religion_Cast.length() != 0){

                                    for (int j = 0;j<jsonArray_religion_Cast.length();j++){

                                        JSONObject jsonObject11_Cast = jsonArray_religion_Cast.getJSONObject(j);

                                        String cat_id1 = jsonObject11_Cast.getString("cat_id");
                                        String cat_name1 = jsonObject11_Cast.getString("cat_name");
                                        String parent_id1 = jsonObject11_Cast.getString("parent_id");
                                        String sub_categories1 = jsonObject11_Cast.getString("sub_categories");

                                        JSONArray jsonArray_subcast = new JSONArray(sub_categories1);

                                        if (jsonArray_subcast.length() != 0){

                                            for (int k=0;k<jsonArray_subcast.length();k++){

                                                JSONObject jsonObject11_SubCast = jsonArray_subcast.getJSONObject(k);

                                                String cat_id2 = jsonObject11_SubCast.getString("cat_id");
                                                String cat_name2 = jsonObject11_SubCast.getString("cat_name");
                                                String parent_id2 = jsonObject11_SubCast.getString("parent_id");
                                                String sub_categories2 = jsonObject11_SubCast.getString("sub_categories");

                                                if (parentid.equals(parent_id2)){

                                                    SubCategories subCategories = new SubCategories(
                                                            cat_id2,cat_name2,parent_id2
                                                    );

                                                    subcategoriesReligModels.add(subCategories);

                                                    nameReligion_SubCast.add(cat_name2);
                                                    mapReligion_Subcast.put(cat_name2,cat_id2);

                                                }
                                            }
                                        }else{

                                           // Toast.makeText(getContext(), "Religion_SubCate Not Found", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }else{

                                   // Toast.makeText(getContext(), "Religion_Cate Not Found", Toast.LENGTH_SHORT).show();
                                }

                            }

                            nameReligion_SubCast.add(0, "Select SubCaste");

                            ArrayAdapter<String> nameReligion_SubCastAdapter = new ArrayAdapter<String>(getContext(),
                                    R.layout.spinnerfront2, nameReligion_SubCast);
                            nameReligion_SubCastAdapter.setDropDownViewResource(R.layout.spinneritem);
                            binding.spinnerSubcast.setAdapter(nameReligion_SubCastAdapter);

                        }else{

                            Toast.makeText(getContext(), "Religion Not Found", Toast.LENGTH_SHORT).show();
                        }

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

    public void registerDetails(String userID,String religion, String caste, String subcaste){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Login Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.updateprofile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");

                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    }else{

                        String error = jsonObject.getString("error");
                        String message = jsonObject.getString("message");

                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("user_id",userID);
                params.put("candidate_name","");
                params.put("dob","");
                params.put("profile_for","");
                params.put("marital_status","");
                params.put("gender","");
                params.put("living_since","");
                params.put("pob","");
                params.put("nationality","");
                params.put("visa_status","");
                params.put("religion",religion);
                params.put("caste",caste);
                params.put("subcaste",subcaste);
                params.put("rashi","");
                params.put("gotra","");
                params.put("star","");
                params.put("lagna","");
                params.put("mangalik","");
                params.put("astrology","");
                params.put("education","");
                params.put("profession","");
                params.put("income","");
                params.put("country","");
                params.put("state","");
                params.put("district","");
                params.put("city","");
                params.put("living_with","");
                params.put("fathername","");
                params.put("fatheroccupation","");
                params.put("mothername","");
                params.put("motheroccupation","");
                params.put("familytype","");
                params.put("family_status","");
                params.put("brother","");
                params.put("sister","");
                params.put("height","");
                params.put("weight","");
                params.put("body_type","");
                params.put("ethnicity","");
                params.put("complexion","");
                params.put("diet","");
                params.put("drink","");
                params.put("smoke","");
                params.put("disability","");
                params.put("disease","");
                params.put("language","");
                params.put("skills","");
                params.put("hobbies","");
                params.put("description","");

                Log.d("registerdetails",userID+", "+religion+", "+caste+", "+subcaste);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
