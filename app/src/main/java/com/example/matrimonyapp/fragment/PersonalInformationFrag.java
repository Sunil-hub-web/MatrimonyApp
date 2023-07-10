package com.example.matrimonyapp.fragment;

import static com.example.matrimonyapp.MasterdataApi.masterApiList;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.example.matrimonyapp.LoginActivity;
import com.example.matrimonyapp.MasterdataApi;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.UserInformation;
import com.example.matrimonyapp.databinding.FragmentRegister2Binding;
import com.example.matrimonyapp.modelclass.CitiesModel;
import com.example.matrimonyapp.modelclass.MaritalStatusModel;
import com.example.matrimonyapp.modelclass.NationalityModel;
import com.example.matrimonyapp.modelclass.ProfileForModel;
import com.example.matrimonyapp.modelclass.VisaStatusModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PersonalInformationFrag extends Fragment {

    FragmentRegister2Binding binding;
    MasterdataApi masterdataApi;
    ArrayList<String> name_nationality = new ArrayList<>();
    HashMap<String, String> nationality_Map = new HashMap<>();
    ArrayList<NationalityModel> nationalityModels;

    ArrayList<String> name_profileFor = new ArrayList<>();
    HashMap<String, String> profileFor_Map = new HashMap<>();
    ArrayList<ProfileForModel> profileForModels = new ArrayList<>();

    ArrayList<String> name_maritalstatus = new ArrayList<>();
    HashMap<String, String> maritalstatus_Map = new HashMap<>();
    ArrayList<MaritalStatusModel> maritalStatusModels = new ArrayList<>();

    ArrayList<String> name_visastatus = new ArrayList<>();
    HashMap<String, String> visa_status_Map = new HashMap<>();
    ArrayList<VisaStatusModel> visaStatusModels = new ArrayList<>();

    ArrayList<String> name_LivingCountrysince = new ArrayList<>();
    HashMap<String, String> livingCountrysince_Map = new HashMap<>();
    String nationalityName, nationalityId, profileForId, profileForName,maritalStatuesName,maritalStatuesId,
            livingCountryId,livingCountryName,visaStatuesId,visaStatuesName,date,time,userId,genderName,
            PlaceBrithName,PlaceBrithId;
    int year, month, day, hour, minute;

    DatePickerDialog.OnDateSetListener setListener;
    SessionManager sessionManager;
    RadioButton selectedRadioButton;
    ArrayList<CitiesModel> citiesModels;
    ArrayList<String> City_Name;
    Map<String, String> City_Map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // View view = inflater.inflate(R.layout.fragment_register2,container,false);

        binding = FragmentRegister2Binding.inflate(inflater, container, false);

        masterApiList();
        masterApiList4();

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        date = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(new Date());
        time = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());

        name_LivingCountrysince.add("Living in this Country since ?");
        name_LivingCountrysince.add("1 year");
        name_LivingCountrysince.add("2 year");
        name_LivingCountrysince.add("3 year");
        name_LivingCountrysince.add("4 year");
        name_LivingCountrysince.add("5 year");
        name_LivingCountrysince.add("6 year");
        name_LivingCountrysince.add("7 year");
        name_LivingCountrysince.add("8 year");
        name_LivingCountrysince.add("9 year");
        name_LivingCountrysince.add("10 year");
        name_LivingCountrysince.add("10 + year");
        name_LivingCountrysince.add("20 + year");
        name_LivingCountrysince.add("30 + year");
        name_LivingCountrysince.add("40 + year");

        livingCountrysince_Map.put("1 year", "1");
        livingCountrysince_Map.put("2 year", "2");
        livingCountrysince_Map.put("3 year", "3");
        livingCountrysince_Map.put("4 year", "4");
        livingCountrysince_Map.put("5 year", "5");
        livingCountrysince_Map.put("6 year", "6");
        livingCountrysince_Map.put("7 year", "7");
        livingCountrysince_Map.put("8 year", "8");
        livingCountrysince_Map.put("9 year", "9");
        livingCountrysince_Map.put("10 year", "10");
        livingCountrysince_Map.put("10 + year", "11");
        livingCountrysince_Map.put("20 + year", "20");
        livingCountrysince_Map.put("30 + year", "30");
        livingCountrysince_Map.put("40 + year", "40");

        ArrayAdapter<String> livingCountryAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, name_LivingCountrysince);
        livingCountryAdapter.setDropDownViewResource(R.layout.spinneritem);
        binding.spinnerLivingCountry.setAdapter(livingCountryAdapter);

        binding.spinnerNatianality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nationalityName = binding.spinnerNatianality.getItemAtPosition(binding.spinnerNatianality.getSelectedItemPosition()).toString();

                if (nationalityName.equalsIgnoreCase("Select Nationality")) {

                    nationalityId = "";

                } else {

                    nationalityId = nationality_Map.get(nationalityName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerProfileFor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profileForName = binding.spinnerProfileFor.getItemAtPosition(binding.spinnerProfileFor.getSelectedItemPosition()).toString();

                if (profileForName.equalsIgnoreCase("You Are Creating Profile For")) {

                    profileForId = "";

                } else {

                    profileForId = profileFor_Map.get(profileForName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerMaritalStatues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                maritalStatuesName = binding.spinnerMaritalStatues.getItemAtPosition(binding.spinnerMaritalStatues.getSelectedItemPosition()).toString();

                if (maritalStatuesName.equalsIgnoreCase("Marital Status")) {

                    maritalStatuesId = "";

                } else {

                    maritalStatuesId = maritalstatus_Map.get(maritalStatuesName);

                    Log.d("mapdetails",maritalstatus_Map.toString());

                    Log.d("statues11",maritalStatuesName+","+maritalStatuesId);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerLivingCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                livingCountryName = binding.spinnerLivingCountry.getItemAtPosition(binding.spinnerLivingCountry.getSelectedItemPosition()).toString();

                if (livingCountryName.equalsIgnoreCase("Living in this Country since ?")) {

                    livingCountryId = "";

                } else {

                    livingCountryId = livingCountrysince_Map.get(livingCountryName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerNatianality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nationalityName = binding.spinnerNatianality.getItemAtPosition(binding.spinnerNatianality.getSelectedItemPosition()).toString();

                if (nationalityName.equalsIgnoreCase("Select Nationality")) {

                    nationalityId = "";

                } else {

                    nationalityId = nationality_Map.get(nationalityName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerVisaStatues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                visaStatuesName = binding.spinnerVisaStatues.getItemAtPosition(binding.spinnerVisaStatues.getSelectedItemPosition()).toString();

                if (visaStatuesName.equalsIgnoreCase("Your visa status")) {

                    visaStatuesId = "";

                } else {

                    visaStatuesId = visa_status_Map.get(visaStatuesName);

                    Log.d("statues12",maritalStatuesName+","+maritalStatuesId);

                    Log.d("mapdetails",visa_status_Map.toString());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerPlaceBrith.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PlaceBrithName = binding.spinnerPlaceBrith.getItemAtPosition(binding.spinnerPlaceBrith.getSelectedItemPosition()).toString();

                if (PlaceBrithName.equalsIgnoreCase("Select City")) {

                    PlaceBrithId = "";

                } else {

                    PlaceBrithId = City_Map.get(PlaceBrithName);

                    Log.d("statues121",PlaceBrithName+","+PlaceBrithId);

                    Log.d("mapdetails",City_Map.toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new ReligionFragment();
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
        binding.editDateBrith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCalender1();
            }
        });
        binding.radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                genderName = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), genderName, Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerDetails(userId,binding.editDateBrith.getText().toString().trim(),profileForId,
                        binding.editCandidateName.getText().toString().trim(),genderName,
                        maritalStatuesId,livingCountryId,PlaceBrithId,nationalityId,visaStatuesId);

                Log.d("registerdetails2",userId+", "+binding.editDateBrith.getText().toString()+", "+profileForId
                        +", "+binding.editCandidateName.getText().toString()+", "+genderName+", "+
                        maritalStatuesId+", "+livingCountryId+", "+nationalityId+", "+visaStatuesId+""+PlaceBrithId);
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

                        nationalityModels = new ArrayList<>();
                        nationalityModels.clear();
                        name_nationality.clear();
                        nationality_Map.clear();

                        JSONArray jsonArray_nationality = new JSONArray(nationality);

                        for (int i = 0; i < jsonArray_nationality.length(); i++) {

                            JSONObject jsonObject_nationality = jsonArray_nationality.getJSONObject(i);
                            String nationality_id = jsonObject_nationality.getString("nationality_id");
                            String nationality_name = jsonObject_nationality.getString("nationality_name");

                            NationalityModel nationalityModel = new NationalityModel(nationality_id, nationality_name);
                            nationalityModels.add(nationalityModel);

                            name_nationality.add(nationality_name);
                            nationality_Map.put(nationality_name, nationality_id);

                        }

                        name_nationality.add(0, "Select Nationality");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, name_nationality);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerNatianality.setAdapter(nationalityAdapter);

                        name_profileFor.clear();
                        profileFor_Map.clear();

                        JSONArray jsonArray_profile_for = new JSONArray(profile_for);
                        for (int j = 0; j < jsonArray_profile_for.length(); j++) {

                            JSONObject jsonObject1_profile_for = jsonArray_profile_for.getJSONObject(j);
                            String profilefor_id = jsonObject1_profile_for.getString("profilefor_id");
                            String profilefor_name = jsonObject1_profile_for.getString("profilefor_name");

                            ProfileForModel profileFor_Model = new ProfileForModel(profilefor_id, profilefor_name);
                            profileForModels.add(profileFor_Model);

                            name_profileFor.add(profilefor_name);
                            profileFor_Map.put(profilefor_name, profilefor_id);

                        }

                        name_profileFor.add(0, "You Are Creating Profile For");

                        ArrayAdapter<String> profileForAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, name_profileFor);
                        profileForAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerProfileFor.setAdapter(profileForAdapter);

                        name_maritalstatus.clear();
                        maritalstatus_Map.clear();
                        maritalStatusModels.clear();

                        JSONArray jsonArray_marital_status = new JSONArray(marital_status);

                        for (int k = 0; k < jsonArray_marital_status.length(); k++) {

                            JSONObject jsonObject1_marital_status = jsonArray_marital_status.getJSONObject(k);
                            String maritalstatus_id = jsonObject1_marital_status.getString("maritalstatus_id");
                            String maritalstatus_name = jsonObject1_marital_status.getString("maritalstatus_name");

                            MaritalStatusModel maritalStatus_Model = new MaritalStatusModel(maritalstatus_id, maritalstatus_name);

                            name_maritalstatus.add(maritalstatus_name);
                            maritalStatusModels.add(maritalStatus_Model);
                            maritalstatus_Map.put(maritalstatus_name,maritalstatus_id);

                        }

                        name_maritalstatus.add(0, "Marital Status");

                        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, name_maritalstatus);
                        maritalStatusAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerMaritalStatues.setAdapter(maritalStatusAdapter);

                        name_visastatus.clear();
                        visa_status_Map.clear();
                        visaStatusModels.clear();

                        JSONArray jsonArray_visa_status = new JSONArray(visa_status);
                        for (int l = 0; l < jsonArray_visa_status.length(); l++) {

                            JSONObject jsonObject1_visa_status = jsonArray_visa_status.getJSONObject(l);
                            String visastatus_id = jsonObject1_visa_status.getString("visastatus_id");
                            String visastatus_name = jsonObject1_visa_status.getString("visastatus_name");

                            VisaStatusModel visaStatusModel = new VisaStatusModel(visastatus_id, visastatus_name);
                            visaStatusModels.add(visaStatusModel);
                            name_visastatus.add(visastatus_name);
                            visa_status_Map.put(visastatus_name, visastatus_id);

                        }

                        name_visastatus.add(0, "Your visa status");

                        ArrayAdapter<String> visastatusAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, name_visastatus);
                        visastatusAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerVisaStatues.setAdapter(visastatusAdapter);

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
    public void showCalender1(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                month = month+1;

                String fmonth=""+month;
                String fDate=""+dayOfMonth;

                if(month<10){
                    fmonth ="0"+month;
                }
                if (dayOfMonth<10){
                    fDate="0"+dayOfMonth;
                }

                date = year+"-"+fmonth+"-"+fDate;
                //String date = year+"-"+month+"-"+day;
                binding.editDateBrith.setText(date);


            }
        },year,month,day);

        datePickerDialog.show();
    }
    public void registerDetails(String userId,String dob,String profile_for,String candidate_name,
                                String gender,String marital_status,String living_since,String pob,
                                String nationality,String visa_status){

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
                params.put("user_id",userId);
                params.put("candidate_name",candidate_name);
                params.put("dob",dob);
                params.put("profile_for",profile_for);
                params.put("marital_status",marital_status);
                params.put("gender",gender);
                params.put("living_since",living_since);
                params.put("pob",pob);
                params.put("nationality",nationality);
                params.put("visa_status",visa_status);
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

                Log.d("registerdetails",userId+", "+candidate_name+", "+dob+", "+profile_for
                        +", "+marital_status+", "+gender+", "+living_since+", "+pob+", "+nationality+", "+visa_status);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void masterApiList4() {
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

                        //    if (country_id.equals(countryId)) {

                                JSONArray jsonArray_states = new JSONArray(states);

                                for (int j = 0; j < jsonArray_states.length(); j++) {

                                    JSONObject jsonObject1_states = jsonArray_states.getJSONObject(j);
                                    String state_id = jsonObject1_states.getString("state_id");
                                    String state_name = jsonObject1_states.getString("state_name");
                                    String districts = jsonObject1_states.getString("districts");

                                //    if (state_id.equals(stateId)) {

                                        JSONArray jsonArray_districts = new JSONArray(districts);

                                        for (int k = 0; k < jsonArray_districts.length(); k++) {

                                            JSONObject jsonObject1_districts = jsonArray_districts.getJSONObject(k);
                                            String district_id = jsonObject1_districts.getString("district_id");
                                            String district_name = jsonObject1_districts.getString("district_name");
                                            String cities = jsonObject1_districts.getString("cities");

                                    //        if (district_id.equals(districtId)){

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

                                           /* }else{

                                            }*/

                                        }

                                  /*  } else {


                                    }*/

                                }

                          /*  } else {

                            }*/

                        }

                        City_Name.add(0, "Select City");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, City_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerPlaceBrith.setAdapter(nationalityAdapter);

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

}
