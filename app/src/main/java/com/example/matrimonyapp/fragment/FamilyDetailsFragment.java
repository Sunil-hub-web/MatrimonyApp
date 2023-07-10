package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.matrimonyapp.databinding.FragmentRegister8Binding;
import com.example.matrimonyapp.modelclass.FamilyType;
import com.example.matrimonyapp.modelclass.GotraModel;
import com.example.matrimonyapp.modelclass.LagnaModel;
import com.example.matrimonyapp.modelclass.RashiModel;
import com.example.matrimonyapp.modelclass.StarModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FamilyDetailsFragment extends Fragment {

    FragmentRegister8Binding binding;
    ArrayList<FamilyType> familyTypes;
    ArrayList<String> familyTypes_Name;
    Map<String,String> familyTypes_Map;
    ArrayList<String> name_FamilyStatus = new ArrayList<>();
    HashMap<String, String> FamilyStatus_Map = new HashMap<>();
    RadioButton selectedRadioButton;
    String familyTypesName,familyTypesId,nameFamilyStatus,idFamilyStatus,strWithFamily,userId;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

   //     View view = inflater.inflate(R.layout.fragment_register8,container,false);

        binding = FragmentRegister8Binding.inflate(inflater,container,false);

        masterApiList();

        sessionManager = new SessionManager(getContext());
        userId = sessionManager.getUSERID();

        name_FamilyStatus.add("Family Status");
        name_FamilyStatus.add("Rich");
        name_FamilyStatus.add("Medium");
        name_FamilyStatus.add("Poor");

        FamilyStatus_Map.put("Rich", "2");
        FamilyStatus_Map.put("Medium", "4");
        FamilyStatus_Map.put("Poor", "5");

        ArrayAdapter<String> livingCountryAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, name_FamilyStatus);
        livingCountryAdapter.setDropDownViewResource(R.layout.spinneritem);
        binding.spinnerFamilyStatus.setAdapter(livingCountryAdapter);
        binding.radioWithFamily.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                strWithFamily = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), strWithFamily, Toast.LENGTH_SHORT).show();
            }
        });
        binding.spinnerfamilytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                familyTypesName = binding.spinnerfamilytype.getItemAtPosition(binding.spinnerfamilytype.getSelectedItemPosition()).toString();

                if (familyTypesName.equalsIgnoreCase("Select Family Type")) {

                    familyTypesId = "";

                } else {

                    familyTypesId = familyTypes_Map.get(familyTypesName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerFamilyStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nameFamilyStatus = binding.spinnerFamilyStatus.getItemAtPosition(binding.spinnerFamilyStatus.getSelectedItemPosition()).toString();

                if (nameFamilyStatus.equalsIgnoreCase("Family Status")) {

                    idFamilyStatus = "";

                } else {

                    idFamilyStatus = FamilyStatus_Map.get(nameFamilyStatus);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PhysicalDetailsFragment();
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

                registerDetails(userId,strWithFamily,binding.editFatherName.getText().toString().trim(),
                        binding.editFatherOccupation.getText().toString().trim(),
                        binding.editMotherName.getText().toString().trim(),
                        binding.editMotherOccupation.getText().toString().trim(),familyTypesId,idFamilyStatus,
                        binding.editNoofBrother.getText().toString().trim(),
                        binding.editNoofSister.getText().toString().trim());
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

                        familyTypes = new ArrayList<>();
                        familyTypes_Name = new ArrayList<>();
                        familyTypes_Map = new HashMap<>();

                        familyTypes.clear();
                        familyTypes_Name.clear();
                        familyTypes_Map.clear();

                        JSONArray jsonArray_family_type = new JSONArray(family_type);

                        for (int i=0;i<jsonArray_family_type.length();i++){

                            JSONObject jsonObject1_family_type = jsonArray_family_type.getJSONObject(i);
                            String familystatus_id = jsonObject1_family_type.getString("familystatus_id");
                            String familystatus_name = jsonObject1_family_type.getString("familystatus_name");

                            FamilyType rashiModel = new FamilyType(familystatus_id,familystatus_name);

                            familyTypes.add(rashiModel);
                            familyTypes_Name.add(familystatus_name);

                            familyTypes_Map.put(familystatus_name,familystatus_id);
                        }

                        familyTypes_Name.add(0, "Select Family Type");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, familyTypes_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerfamilytype.setAdapter(nationalityAdapter);

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
    public void registerDetails(String userID,String living_with,String fathername,String fatheroccupation,
                                String mothername,String motheroccupation,String familytype,String family_status,
                                String brother,String sister){

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
                params.put("education","");
                params.put("profession","");
                params.put("income","");
                params.put("country","");
                params.put("state","");
                params.put("district","");
                params.put("city","");
                params.put("living_with",living_with);
                params.put("fathername",fathername);
                params.put("fatheroccupation",fatheroccupation);
                params.put("mothername",mothername);
                params.put("motheroccupation",motheroccupation);
                params.put("familytype",familytype);
                params.put("family_status",family_status);
                params.put("brother",brother);
                params.put("sister",sister);
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

                Log.d("registerdet",userID+", "+living_with+", "+fathername+", "+fatheroccupation
                +", "+mothername+", "+ motheroccupation+", "+ familytype+", "+ family_status+", "+ brother+", "+ sister);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
