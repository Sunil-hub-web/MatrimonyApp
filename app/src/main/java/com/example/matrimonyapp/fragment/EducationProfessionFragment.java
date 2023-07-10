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
import com.example.matrimonyapp.databinding.FragmentRegister5Binding;
import com.example.matrimonyapp.modelclass.EducationModel;
import com.example.matrimonyapp.modelclass.GotraModel;
import com.example.matrimonyapp.modelclass.IncomeModel;
import com.example.matrimonyapp.modelclass.LagnaModel;
import com.example.matrimonyapp.modelclass.ProfessionModel;
import com.example.matrimonyapp.modelclass.RashiModel;
import com.example.matrimonyapp.modelclass.StarModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EducationProfessionFragment extends Fragment {

    FragmentRegister5Binding binding;
    ArrayList<EducationModel> educationModels;
    ArrayList<String> education_Name;
    Map<String,String> education_Map;
    ArrayList<ProfessionModel> professionModels;
    ArrayList<String> profession_Name;
    Map<String,String> profession_Map;
    ArrayList<IncomeModel> incomeModels;
    ArrayList<String> income_Name;
    Map<String,String> income_Map;
    String educationName,educationId,professionName,professionId,incomeName,incomeId,userId;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_register5,container,false);

        binding = FragmentRegister5Binding.inflate(inflater,container,false);

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();

        masterApiList();
        binding.spinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                educationName = binding.spinnerEducation.getItemAtPosition(binding.spinnerEducation.getSelectedItemPosition()).toString();

                if (educationName.equalsIgnoreCase("Select Eduction")) {

                    educationId = "";

                } else {

                    educationId = education_Map.get(educationName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                professionName = binding.spinnerProfession.getItemAtPosition(binding.spinnerProfession.getSelectedItemPosition()).toString();

                if (professionName.equalsIgnoreCase("Select Profession")) {

                    professionId = "";

                } else {

                    professionId = profession_Map.get(professionName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                incomeName = binding.spinnerIncome.getItemAtPosition(binding.spinnerIncome.getSelectedItemPosition()).toString();

                if (incomeName.equalsIgnoreCase("Select Income")) {

                    incomeId = "";

                } else {

                    incomeId = income_Map.get(incomeName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new AddressDetailsFragment();
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

                registerDetails(userId,educationId,professionId,incomeId);
            }
        });

        return binding.getRoot();
    }

    public void masterApiList() {

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

                        educationModels = new ArrayList<>();
                        education_Name = new ArrayList<>();
                        education_Map = new HashMap<>();

                        educationModels.clear();
                        education_Name.clear();
                        education_Map.clear();

                        JSONArray jsonArray_education = new JSONArray(education);

                        for (int i=0;i<jsonArray_education.length();i++){

                            JSONObject jsonObject1_education = jsonArray_education.getJSONObject(i);
                            String education_id = jsonObject1_education.getString("education_id");
                            String education_name = jsonObject1_education.getString("education_name");

                            EducationModel educationModel = new EducationModel(education_id,education_name);

                            educationModels.add(educationModel);
                            education_Name.add(education_name);

                            education_Map.put(education_name,education_id);
                        }

                        education_Name.add(0, "Select Eduction");

                        ArrayAdapter<String> educationyAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, education_Name);
                        educationyAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerEducation.setAdapter(educationyAdapter);

                        professionModels = new ArrayList<>();
                        profession_Name = new ArrayList<>();
                        profession_Map = new HashMap<>();

                        professionModels.clear();
                        profession_Name.clear();
                        profession_Map.clear();

                        JSONArray jsonArray_profession = new JSONArray(profession);

                        for (int j=0;j<jsonArray_profession.length();j++){

                            JSONObject jsonObject1_profession = jsonArray_profession.getJSONObject(j);
                            String profession_id = jsonObject1_profession.getString("profession_id");
                            String profession_name = jsonObject1_profession.getString("profession_name");

                            ProfessionModel professionModel = new ProfessionModel(profession_id,profession_name);

                            professionModels.add(professionModel);
                            profession_Name.add(profession_name);

                            profession_Map.put(profession_name,profession_id);
                        }

                        profession_Name.add(0, "Select Profession");

                        ArrayAdapter<String> professionAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, profession_Name);
                        professionAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerProfession.setAdapter(professionAdapter);

                        incomeModels = new ArrayList<>();
                        income_Name = new ArrayList<>();
                        income_Map = new HashMap<>();

                        incomeModels.clear();
                        income_Name.clear();
                        income_Map.clear();

                        JSONArray jsonArray_income = new JSONArray(income);

                        for (int j=0;j<jsonArray_income.length();j++){

                            JSONObject jsonObject1_income = jsonArray_income.getJSONObject(j);
                            String income_id = jsonObject1_income.getString("income_id");
                            String income_name = jsonObject1_income.getString("income_name");

                            IncomeModel incomeModel = new IncomeModel(income_id,income_name);

                            incomeModels.add(incomeModel);
                            income_Name.add(income_name);

                            income_Map.put(income_name,income_id);
                        }

                        income_Name.add(0, "Select Income");

                        ArrayAdapter<String> incomenAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, income_Name);
                        incomenAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerIncome.setAdapter(incomenAdapter);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();

                Log.d("errordata", error.toString());
            }
        });

        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

    public void registerDetails(String userID,String education,String profession,String income){

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
                params.put("religion","");
                params.put("caste","");
                params.put("subcaste","");
                params.put("rashi","");
                params.put("gotra","");
                params.put("star","");
                params.put("lagna","");
                params.put("mangalik","");
                params.put("astrology","");
                params.put("education",education);
                params.put("profession",profession);
                params.put("income",income);
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

                Log.d("registerdet",userID+", "+education+", "+profession+", "+income);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
