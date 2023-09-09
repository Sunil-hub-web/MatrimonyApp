package com.example.matrimonyapp.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.adapter.CandidateAdapter;
import com.example.matrimonyapp.adapter.SuccessStoriesAdapter;
import com.example.matrimonyapp.databinding.FindmatchFragmentBinding;
import com.example.matrimonyapp.modelclass.BodyTypeModel;
import com.example.matrimonyapp.modelclass.CandidateDetails_Model;
import com.example.matrimonyapp.modelclass.CitiesModel;
import com.example.matrimonyapp.modelclass.ComplexionModel;
import com.example.matrimonyapp.modelclass.DistrictsModel;
import com.example.matrimonyapp.modelclass.EducationModel;
import com.example.matrimonyapp.modelclass.GotraModel;
import com.example.matrimonyapp.modelclass.LagnaModel;
import com.example.matrimonyapp.modelclass.LocationDataModel;
import com.example.matrimonyapp.modelclass.RashiModel;
import com.example.matrimonyapp.modelclass.ReligionDataModel;
import com.example.matrimonyapp.modelclass.StarModel;
import com.example.matrimonyapp.modelclass.StatesModel;
import com.example.matrimonyapp.modelclass.SuccessStories_model;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FillteViewProfileFragment extends Fragment {

    FindmatchFragmentBinding binding;
    Dialog dialogConfirm;
    RadioGroup radioGroupGender;
    Spinner spinnerCountry,spinnerState,spinnerDistrict,spinnerCity,spinnerReligion,spinnerEducation,
            spinnerBodyType,spinnerComplexion,spinnerRashi,spinnerGotra,spinnerStar,spinnerLagna;
    EditText edit_MinAge,edit_MaxAge;
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

    ArrayList<ReligionDataModel> religionDataModels;
    ArrayList<String> nameReligion;
    HashMap<String,String> mapReligion;

    ArrayList<EducationModel> educationModels;
    ArrayList<String> education_Name;
    Map<String,String> education_Map;

    ArrayList<BodyTypeModel> bodyTypeModels;
    ArrayList<String> bodyType_Name;
    Map<String,String> bodyType_Map;
    ArrayList<ComplexionModel> complexionModels;
    ArrayList<String> complexion_Name;
    Map<String,String> complexion_Map;
    String CountryName,CountryId,StateName,StateId,DistrictName,DistrictId,CityName,CityId,userId,
            genderName,rashi_Name,rashiId,gotra_Name,gotraId,lagna_Name,lagnaId,star_Name,starId,bodyTypeName,
            bodyTypeId,complexionName,complexionid,educationName,educationId,religionId,religionName;
    SessionManager sessionManager;
    RadioButton selectedRadioButton;
    ArrayList<RashiModel> rashiModels;
    ArrayList<String> rashiName;
    HashMap<String,String> rashiMap;
    ArrayList<GotraModel> gotraModels;
    ArrayList<String> gotraName;
    HashMap<String,String> gotraMap;
    ArrayList<LagnaModel> lagnaModels;
    ArrayList<String> lagnaName;
    HashMap<String,String> lagnaMap;
    ArrayList<StarModel> starModels;
    ArrayList<String> starName;
    HashMap<String,String> starMap;

    ArrayList<CandidateDetails_Model> candidateDetailsModels = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FindmatchFragmentBinding.inflate(inflater,container,false);

        sessionManager = new SessionManager(getActivity());
        userId = sessionManager.getUSERID();
        viewSingleprofile("136");
        return binding.getRoot();
    }

    public void getFiltearDetails(){

        dialogConfirm = new Dialog(getActivity());
        dialogConfirm.getWindow().setWindowAnimations(R.style.DialogAnimation);
        dialogConfirm.setContentView(R.layout.filterdetailslayout);
        dialogConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogConfirm.setCanceledOnTouchOutside(true);
        dialogConfirm.setCancelable(true);
        dialogConfirm.getWindow().setGravity(Gravity.CENTER);
        Window window = dialogConfirm.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        spinnerCountry = dialogConfirm.findViewById(R.id.spinnerCountry);
        spinnerState = dialogConfirm.findViewById(R.id.spinnerState);
        spinnerDistrict = dialogConfirm.findViewById(R.id.spinnerDistrict);
        spinnerCity = dialogConfirm.findViewById(R.id.spinnerCity);
        spinnerReligion = dialogConfirm.findViewById(R.id.spinnerReligion);
        spinnerEducation = dialogConfirm.findViewById(R.id.spinnerEducation);
        spinnerBodyType = dialogConfirm.findViewById(R.id.spinnerBodyType);
        spinnerComplexion = dialogConfirm.findViewById(R.id.spinnerComplexion);
        spinnerRashi = dialogConfirm.findViewById(R.id.spinnerRashi);
        spinnerGotra = dialogConfirm.findViewById(R.id.spinnerGotra);
        spinnerStar = dialogConfirm.findViewById(R.id.spinnerStar);
        spinnerLagna = dialogConfirm.findViewById(R.id.spinnerLagna);
        edit_MinAge = dialogConfirm.findViewById(R.id.edit_MinAge);
        edit_MaxAge = dialogConfirm.findViewById(R.id.edit_MaxAge);
        radioGroupGender = dialogConfirm.findViewById(R.id.radioGroupGender);

        masterApiList1();


        Country_Name.add(0, "Select Country");

        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, Country_Name);
        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerCountry.setAdapter(nationalityAdapter);

        State_Name.add(0, "Select State");

        ArrayAdapter<String> nationalityAdapter1 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, State_Name);
        nationalityAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spinnerState.setAdapter(nationalityAdapter1);

        District_Name.add(0, "Select District");

        ArrayAdapter<String> nationalityAdapter2 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, District_Name);
        nationalityAdapter2.setDropDownViewResource(R.layout.spinneritem);
        spinnerDistrict.setAdapter(nationalityAdapter2);

        City_Name.add(0, "Select City");

        ArrayAdapter<String> nationalityAdapter3 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, City_Name);
        nationalityAdapter3.setDropDownViewResource(R.layout.spinneritem);
        spinnerCity.setAdapter(nationalityAdapter3);

        nameReligion.add(0, "Select Religion");

        ArrayAdapter<String> nameReligionAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, nameReligion);
        nameReligionAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerReligion.setAdapter(nameReligionAdapter);

        education_Name.add(0, "Select Eduction");

        ArrayAdapter<String> educationyAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, education_Name);
        educationyAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerEducation.setAdapter(educationyAdapter);

        bodyType_Name.add(0, "Select BodyType");

        ArrayAdapter<String> gotratyAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, bodyType_Name);
        gotratyAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerBodyType.setAdapter(gotratyAdapter);

        complexion_Name.add(0, "Select Complexion");

        ArrayAdapter<String> lagnayAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, complexion_Name);
        lagnayAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerComplexion.setAdapter(lagnayAdapter);

        starName.add(0, "Select Star");

        ArrayAdapter<String> staryAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, starName);
        staryAdapter.setDropDownViewResource(R.layout.spinneritem);
        spinnerStar.setAdapter(staryAdapter);

        rashiName.add(0, "Select Rashi");

        ArrayAdapter<String> nationalityAdapter4 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, rashiName);
        nationalityAdapter4.setDropDownViewResource(R.layout.spinneritem);
        spinnerRashi.setAdapter(nationalityAdapter4);

        gotraName.add(0, "Select Gotra");

        ArrayAdapter<String> gotratyAdapter1 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, gotraName);
        gotratyAdapter1.setDropDownViewResource(R.layout.spinneritem);
        spinnerGotra.setAdapter(gotratyAdapter1);

        lagnaName.add(0, "Select Lagna");

        ArrayAdapter<String> lagnayAdapter5 = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, lagnaName);
        lagnayAdapter5.setDropDownViewResource(R.layout.spinneritem);
        spinnerLagna.setAdapter(lagnayAdapter5);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CountryName =spinnerCountry.getItemAtPosition(spinnerCountry.getSelectedItemPosition()).toString();

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
        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                StateName =spinnerState.getItemAtPosition(spinnerState.getSelectedItemPosition()).toString();

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
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DistrictName =spinnerDistrict.getItemAtPosition(spinnerDistrict.getSelectedItemPosition()).toString();

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
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CityName =spinnerCity.getItemAtPosition(spinnerCity.getSelectedItemPosition()).toString();

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
        spinnerReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                religionName =spinnerReligion.getItemAtPosition(spinnerReligion.getSelectedItemPosition()).toString();

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
        spinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                educationName =spinnerEducation.getItemAtPosition(spinnerEducation.getSelectedItemPosition()).toString();

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
        spinnerBodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bodyTypeName =spinnerBodyType.getItemAtPosition(spinnerBodyType.getSelectedItemPosition()).toString();

                if (bodyTypeName.equalsIgnoreCase("Select BodyType")) {

                    bodyTypeId = "";

                } else {

                    bodyTypeId = bodyType_Map.get(bodyTypeName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerComplexion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                complexionName =spinnerComplexion.getItemAtPosition(spinnerComplexion.getSelectedItemPosition()).toString();

                if (complexionName.equalsIgnoreCase("Select Complexion")) {

                    complexionid = "";

                } else {

                    complexionid = complexion_Map.get(complexionName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRashi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                rashi_Name =spinnerRashi.getItemAtPosition(spinnerRashi.getSelectedItemPosition()).toString();

                if (rashi_Name.equalsIgnoreCase("Select Rashi")) {

                    rashiId = "";

                } else {

                    rashiId = rashiMap.get(rashi_Name);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerStar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                star_Name =spinnerStar.getItemAtPosition(spinnerStar.getSelectedItemPosition()).toString();

                if (star_Name.equalsIgnoreCase("Select Star")) {

                    starId = "";

                } else {

                    starId = starMap.get(star_Name);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGotra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gotra_Name =spinnerGotra.getItemAtPosition(spinnerGotra.getSelectedItemPosition()).toString();

                if (gotra_Name.equalsIgnoreCase("Select Gotra")) {

                    gotraId = "";

                } else {

                    gotraId = gotraMap.get(gotra_Name);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerLagna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lagna_Name = spinnerLagna.getItemAtPosition(spinnerLagna.getSelectedItemPosition()).toString();

                if (lagna_Name.equalsIgnoreCase("Select Lagna")) {

                    lagnaId = "";

                } else {

                    lagnaId = lagnaMap.get(lagna_Name);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                genderName = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), genderName, Toast.LENGTH_SHORT).show();
            }
        });


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

                        }

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

                        }else{

                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }

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

                        bodyTypeModels = new ArrayList<>();
                        bodyType_Name = new ArrayList<>();
                        bodyType_Map = new HashMap<>();

                        bodyTypeModels.clear();
                        bodyType_Name.clear();
                        bodyType_Map.clear();

                        JSONArray jsonArray_bodytype = new JSONArray(bodytype);

                        for (int j=0;j<jsonArray_bodytype.length();j++){

                            JSONObject jsonObject1_bodytype = jsonArray_bodytype.getJSONObject(j);
                            String bodytype_id = jsonObject1_bodytype.getString("bodytype_id");
                            String bodytype_name = jsonObject1_bodytype.getString("bodytype_name");

                            BodyTypeModel bodyTypeModel = new BodyTypeModel(bodytype_id,bodytype_name);

                            bodyTypeModels.add(bodyTypeModel);
                            bodyType_Name.add(bodytype_name);

                            bodyType_Map.put(bodytype_name,bodytype_id);
                        }

                        complexionModels = new ArrayList<>();
                        complexion_Name = new ArrayList<>();
                        complexion_Map = new HashMap<>();

                        complexionModels.clear();
                        complexion_Name.clear();
                        complexion_Map.clear();

                        JSONArray jsonArray_complexion = new JSONArray(complexion);

                        for (int j=0;j<jsonArray_complexion.length();j++){

                            JSONObject jsonObject1_complexion = jsonArray_complexion.getJSONObject(j);
                            String complexion_id = jsonObject1_complexion.getString("complexion_id");
                            String comlexion_name = jsonObject1_complexion.getString("comlexion_name");

                            ComplexionModel complexionModel = new ComplexionModel(complexion_id,comlexion_name);

                            complexionModels.add(complexionModel);
                            complexion_Name.add(comlexion_name);

                            complexion_Map.put(comlexion_name,complexion_id);
                        }

                        rashiModels = new ArrayList<>();
                        rashiName = new ArrayList<>();
                        rashiMap = new HashMap<>();

                        rashiModels.clear();
                        rashiName.clear();
                        rashiMap.clear();

                        JSONArray jsonArray_rashi = new JSONArray(rashi);

                        for (int i=0;i<jsonArray_rashi.length();i++){

                            JSONObject jsonObject1_rashi = jsonArray_rashi.getJSONObject(i);
                            String rashi_id = jsonObject1_rashi.getString("rashi_id");
                            String rashi_name = jsonObject1_rashi.getString("rashi_name");

                            RashiModel rashiModel = new RashiModel(rashi_id,rashi_name);

                            rashiModels.add(rashiModel);
                            rashiName.add(rashi_name);

                            rashiMap.put(rashi_name,rashi_id);
                        }

                        gotraModels = new ArrayList<>();
                        gotraName = new ArrayList<>();
                        gotraMap = new HashMap<>();

                        gotraModels.clear();
                        gotraName.clear();
                        gotraMap.clear();

                        JSONArray jsonArray_gotra = new JSONArray(gotra);

                        for (int j=0;j<jsonArray_gotra.length();j++){

                            JSONObject jsonObject1_gotra = jsonArray_gotra.getJSONObject(j);
                            String gotra_id = jsonObject1_gotra.getString("gotra_id");
                            String gotra_name = jsonObject1_gotra.getString("gotra_name");

                            GotraModel gotraModel = new GotraModel(gotra_id,gotra_name);

                            gotraModels.add(gotraModel);
                            gotraName.add(gotra_name);

                            gotraMap.put(gotra_name,gotra_id);
                        }

                        lagnaModels = new ArrayList<>();
                        lagnaName = new ArrayList<>();
                        lagnaMap = new HashMap<>();

                        lagnaModels.clear();
                        lagnaName.clear();
                        lagnaMap.clear();

                        JSONArray jsonArray_lagna = new JSONArray(lagna);

                        for (int j=0;j<jsonArray_lagna.length();j++){

                            JSONObject jsonObject1_lagna = jsonArray_lagna.getJSONObject(j);
                            String lagna_id = jsonObject1_lagna.getString("lagna_id");
                            String lagna_name = jsonObject1_lagna.getString("lagna_name");

                            LagnaModel lagnaModel = new LagnaModel(lagna_id,lagna_name);

                            lagnaModels.add(lagnaModel);
                            lagnaName.add(lagna_name);

                            lagnaMap.put(lagna_name,lagna_id);
                        }

                        starModels = new ArrayList<>();
                        starName = new ArrayList<>();
                        starMap = new HashMap<>();

                        starModels.clear();
                        starName.clear();
                        starMap.clear();

                        JSONArray jsonArray_star = new JSONArray(star);

                        for (int j=0;j<jsonArray_star.length();j++){

                            JSONObject jsonObject1_star = jsonArray_star.getJSONObject(j);
                            String star_id = jsonObject1_star.getString("star_id");
                            String star_name = jsonObject1_star.getString("star_name");

                            StarModel starModel = new StarModel(star_id,star_name);

                            starModels.add(starModel);
                            starName.add(star_name);

                            starMap.put(star_name,star_id);
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

    public void viewSingleprofile(String customer_id){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("All candidate Details");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.All_candidate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String error = jsonObject.getString("error");
                    String messages = jsonObject.getString("messages");
                    JSONObject jsonObject_message = new JSONObject(messages);
                    String responsecode = jsonObject_message.getString("responsecode");
                    String status_mess = jsonObject_message.getString("status");
                    JSONObject jsonObject_statues = new JSONObject(status_mess);

                    String Allcandidate_data = jsonObject_statues.getString("Allcandidate_data");

                    JSONArray jsonArray_Allcandidate = new JSONArray(Allcandidate_data);

                    for (int j=0;j<jsonArray_Allcandidate.length();j++){

                        JSONObject jsonObject_Allcandidate = jsonArray_Allcandidate.getJSONObject(j);

                        String id = jsonObject_Allcandidate.getString("id");
                        String full_name = jsonObject_Allcandidate.getString("full_name");
                        String user_name = jsonObject_Allcandidate.getString("user_name");
                        String password = jsonObject_Allcandidate.getString("password");
                        String contact_no = jsonObject_Allcandidate.getString("contact_no");
                        String email = jsonObject_Allcandidate.getString("email");
                        String profi_image = jsonObject_Allcandidate.getString("profi_image");
                        String candidate_name = jsonObject_Allcandidate.getString("candidate_name");
                        String dob = jsonObject_Allcandidate.getString("dob");
                        String gender = jsonObject_Allcandidate.getString("gender");
                        String candidate_profession_name = jsonObject_Allcandidate.getString("candidate_profession_name");

                        CandidateDetails_Model candidateDetails_model = new CandidateDetails_Model(
                                id,full_name,dob,candidate_profession_name,gender,profi_image
                        );

                        candidateDetailsModels.add(candidateDetails_model);
                    }

                    LinearLayoutManager linearLayoutManager =
                            new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                    CandidateAdapter candidateAdapter = new CandidateAdapter(getContext(),candidateDetailsModels);
                    binding.findmatchRecycler.setLayoutManager(linearLayoutManager);
                    binding.findmatchRecycler.setHasFixedSize(true);
                    binding.findmatchRecycler.setAdapter(candidateAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("customer_id",customer_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}
