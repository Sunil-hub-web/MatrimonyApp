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
import com.example.matrimonyapp.databinding.FragmentRegister6Binding;
import com.example.matrimonyapp.modelclass.CitiesModel;
import com.example.matrimonyapp.modelclass.DistrictsModel;
import com.example.matrimonyapp.modelclass.GotraModel;
import com.example.matrimonyapp.modelclass.LagnaModel;
import com.example.matrimonyapp.modelclass.LocationDataModel;
import com.example.matrimonyapp.modelclass.RashiModel;
import com.example.matrimonyapp.modelclass.StarModel;
import com.example.matrimonyapp.modelclass.StatesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddressDetailsFragment extends Fragment {

    FragmentRegister6Binding binding;

    ArrayList<LocationDataModel> locationDataModels;
    ArrayList<String> Country_Name;
    Map<String, String> Country_Map;

    ArrayList<StatesModel> statesModels;
    ArrayList<String> State_Name;
    Map<String, String> State_Map;

    ArrayList<DistrictsModel> districtsModels;
    ArrayList<String> District_Name;
    Map<String, String> District_Map;

    ArrayList<CitiesModel> citiesModels;
    ArrayList<String> City_Name;
    Map<String, String> City_Map;
    String CountryName,CountryId,StateName,StateId,DistrictName,DistrictId,CityName,CityId,userId;
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_register6,container,false);

        binding = FragmentRegister6Binding.inflate(inflater, container, false);

        masterApiList1();

        sessionManager = new SessionManager(getContext());
        userId = sessionManager.getUSERID();

        binding.spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CountryName = binding.spinnerCountry.getItemAtPosition(binding.spinnerCountry.getSelectedItemPosition()).toString();

                if (CountryName.equalsIgnoreCase("Select Country")) {

                    CountryId = "";

                } else {

                    CountryId = Country_Map.get(CountryName);

                    Log.d("addCountryId",CountryId);

                    masterApiList2(CountryId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                StateName = binding.spinnerState.getItemAtPosition(binding.spinnerState.getSelectedItemPosition()).toString();

                if (StateName.equalsIgnoreCase("Select State")) {

                    StateId = "";

                } else {

                    StateId = State_Map.get(StateName);

                    Log.d("addCountStateId",CountryId+""+StateId);

                    masterApiList3(CountryId,StateId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DistrictName = binding.spinnerDistrict.getItemAtPosition(binding.spinnerDistrict.getSelectedItemPosition()).toString();

                if (DistrictName.equalsIgnoreCase("Select District")) {

                    DistrictId = "";

                } else {

                    DistrictId = District_Map.get(DistrictName);

                    Log.d("addCountStIdDistrictId",CountryId+""+StateId+""+DistrictId);

                    masterApiList4(CountryId,StateId,DistrictId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CityName = binding.spinnerCity.getItemAtPosition(binding.spinnerCity.getSelectedItemPosition()).toString();

                if (CityName.equalsIgnoreCase("Select City")) {

                    CityId = "";

                } else {

                    CityId = City_Map.get(CityName);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new FamilyDetailsFragment();
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

                registerDetails(userId,CountryId,StateId,DistrictId,CityId);
            }
        });

        return binding.getRoot();
    }

    public void masterApiList1() {

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


                        locationDataModels = new ArrayList<>();
                        Country_Name = new ArrayList<>();
                        Country_Map = new HashMap<>();

                        locationDataModels.clear();
                        Country_Name.clear();
                        Country_Map.clear();

                        JSONArray jsonArray_location_data = new JSONArray(location_data);

                        if (jsonArray_location_data.length() != 0){

                            for (int i = 0; i < jsonArray_location_data.length(); i++) {

                                JSONObject jsonObject1_location_data = jsonArray_location_data.getJSONObject(i);
                                String country_id = jsonObject1_location_data.getString("country_id");
                                String country_name = jsonObject1_location_data.getString("country_name");

                                LocationDataModel locationDataModel = new LocationDataModel(country_id, country_name);

                                locationDataModels.add(locationDataModel);
                                Country_Name.add(country_name);

                                Country_Map.put(country_name, country_id);
                            }

                            Country_Name.add(0, "Select Country");

                            ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                    R.layout.spinnerfront2, Country_Name);
                            nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                            binding.spinnerCountry.setAdapter(nationalityAdapter);
                        }

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
    public void masterApiList2(String countryId) {

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

                        statesModels = new ArrayList<>();
                        State_Name = new ArrayList<>();
                        State_Map = new HashMap<>();

                        statesModels.clear();
                        State_Name.clear();
                        State_Map.clear();


                        JSONArray jsonArray_location_data = new JSONArray(location_data);

                        for (int i = 0; i < jsonArray_location_data.length(); i++) {

                            JSONObject jsonObject1_location_data = jsonArray_location_data.getJSONObject(i);
                            String country_id = jsonObject1_location_data.getString("country_id");
                            String country_name = jsonObject1_location_data.getString("country_name");
                            String states = jsonObject1_location_data.getString("states");

                            if (country_id.equals(countryId)) {

                                JSONArray jsonArray_states = new JSONArray(states);

                                if (jsonArray_states.length() != 0){

                                    for (int j = 0; j < jsonArray_states.length(); j++) {

                                        JSONObject jsonObject1_states = jsonArray_states.getJSONObject(j);
                                        String state_id = jsonObject1_states.getString("state_id");
                                        String state_name = jsonObject1_states.getString("state_name");

                                        StatesModel statesModel = new StatesModel(state_id, state_name);

                                        statesModels.add(statesModel);
                                        State_Name.add(state_name);

                                        State_Map.put(state_name, state_id);
                                    }
                                }

                            } else {

                            }

                        }

                        State_Name.add(0, "Select State");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, State_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerState.setAdapter(nationalityAdapter);

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
    public void masterApiList3(String countryId, String stateId) {

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


                        JSONArray jsonArray_location_data = new JSONArray(location_data);

                        for (int i = 0; i < jsonArray_location_data.length(); i++) {

                            JSONObject jsonObject1_location_data = jsonArray_location_data.getJSONObject(i);
                            String country_id = jsonObject1_location_data.getString("country_id");
                            String country_name = jsonObject1_location_data.getString("country_name");
                            String states = jsonObject1_location_data.getString("states");

                            if (country_id.equals(countryId)) {

                                districtsModels = new ArrayList<>();
                                District_Name = new ArrayList<>();
                                District_Map = new HashMap<>();

                                districtsModels.clear();
                                District_Name.clear();
                                District_Map.clear();

                                JSONArray jsonArray_states = new JSONArray(states);

                                for (int j = 0; j < jsonArray_states.length(); j++) {

                                    JSONObject jsonObject1_states = jsonArray_states.getJSONObject(j);
                                    String state_id = jsonObject1_states.getString("state_id");
                                    String state_name = jsonObject1_states.getString("state_name");
                                    String districts = jsonObject1_states.getString("districts");

                                    if (state_id.equals(stateId)) {

                                        JSONArray jsonArray_districts = new JSONArray(districts);

                                        if (jsonArray_districts.length() !=0){

                                            for (int k = 0; k < jsonArray_districts.length(); k++) {

                                                JSONObject jsonObject1_districts = jsonArray_districts.getJSONObject(k);
                                                String district_id = jsonObject1_districts.getString("district_id");
                                                String district_name = jsonObject1_districts.getString("district_name");

                                                DistrictsModel districtsModel = new DistrictsModel(district_id, district_name);

                                                districtsModels.add(districtsModel);
                                                District_Name.add(district_name);

                                                District_Map.put(district_name, district_id);

                                            }

                                        }

                                    } else {


                                    }

                                }

                            } else {

                            }

                        }

                        District_Name.add(0, "Select District");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, District_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerDistrict.setAdapter(nationalityAdapter);

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
    public void masterApiList4(String countryId, String stateId,String districtId) {
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

                        citiesModels = new ArrayList<>();
                        City_Name = new ArrayList<>();
                        City_Map = new HashMap<>();

                        citiesModels.clear();
                        City_Name.clear();
                        City_Map.clear();


                        JSONArray jsonArray_location_data = new JSONArray(location_data);

                        for (int i = 0; i < jsonArray_location_data.length(); i++) {

                            JSONObject jsonObject1_location_data = jsonArray_location_data.getJSONObject(i);
                            String country_id = jsonObject1_location_data.getString("country_id");
                            String country_name = jsonObject1_location_data.getString("country_name");
                            String states = jsonObject1_location_data.getString("states");

                            if (country_id.equals(countryId)) {

                                JSONArray jsonArray_states = new JSONArray(states);

                                for (int j = 0; j < jsonArray_states.length(); j++) {

                                    JSONObject jsonObject1_states = jsonArray_states.getJSONObject(j);
                                    String state_id = jsonObject1_states.getString("state_id");
                                    String state_name = jsonObject1_states.getString("state_name");
                                    String districts = jsonObject1_states.getString("districts");

                                    if (state_id.equals(stateId)) {

                                        JSONArray jsonArray_districts = new JSONArray(districts);

                                        for (int k = 0; k < jsonArray_districts.length(); k++) {

                                            JSONObject jsonObject1_districts = jsonArray_districts.getJSONObject(k);
                                            String district_id = jsonObject1_districts.getString("district_id");
                                            String district_name = jsonObject1_districts.getString("district_name");
                                            String cities = jsonObject1_districts.getString("cities");

                                            if (district_id.equals(districtId)){

                                                JSONArray jsonArray_cities = new JSONArray(cities);

                                                if (jsonArray_cities.length() != 0){

                                                    for (int l=0;l<jsonArray_cities.length();l++){

                                                        JSONObject jsonObject1_cities = jsonArray_cities.getJSONObject(l);
                                                        String city_id = jsonObject1_cities.getString("city_id");
                                                        String city_name = jsonObject1_cities.getString("city_name");

                                                        CitiesModel citiesModel = new CitiesModel(city_id, city_name);

                                                        citiesModels.add(citiesModel);
                                                        City_Name.add(city_name);

                                                        City_Map.put(city_name, city_id);

                                                    }
                                                }

                                            }else{

                                            }

                                        }

                                    } else {


                                    }

                                }

                            } else {

                            }

                        }

                        City_Name.add(0, "Select City");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, City_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerCity.setAdapter(nationalityAdapter);

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

    public void registerDetails(String userID,String country,String state,String district,String city){

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
                params.put("country",country);
                params.put("state",state);
                params.put("district",district);
                params.put("city",city);
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

                Log.d("registerdet",userID+", "+country+", "+state+", "+district+", "+city);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}
