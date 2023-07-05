package com.example.matrimonyapp.fragment;

import static com.example.matrimonyapp.MasterdataApi.masterApiList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.MasterdataApi;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.UserInformation;
import com.example.matrimonyapp.databinding.FragmentRegister2Binding;
import com.example.matrimonyapp.modelclass.MaritalStatusModel;
import com.example.matrimonyapp.modelclass.NationalityModel;
import com.example.matrimonyapp.modelclass.ProfileForModel;
import com.example.matrimonyapp.modelclass.VisaStatusModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
            livingCountryId,livingCountryName,visaStatuesId,visaStatuesName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegister2Binding.inflate(inflater, container, false);

        masterApiList();

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

                if (maritalStatuesName.equalsIgnoreCase("Select Nationality")) {

                    maritalStatuesId = "";

                } else {

                    maritalStatuesId = maritalstatus_Map.get(maritalStatuesName);

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

                if (livingCountryName.equalsIgnoreCase("Select Nationality")) {

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

                if (visaStatuesName.equalsIgnoreCase("Select Nationality")) {

                    visaStatuesId = "";

                } else {

                    visaStatuesId = visa_status_Map.get(visaStatuesName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // View view = inflater.inflate(R.layout.fragment_register2,container,false);

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
                            maritalstatus_Map.put(maritalstatus_id, maritalstatus_name);

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
                            name_visastatus.add(visastatus_id);
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
}
