package com.example.matrimonyapp.fragment;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.databinding.FragmentRegister4Binding;
import com.example.matrimonyapp.modelclass.GotraModel;
import com.example.matrimonyapp.modelclass.LagnaModel;
import com.example.matrimonyapp.modelclass.MaritalStatusModel;
import com.example.matrimonyapp.modelclass.NationalityModel;
import com.example.matrimonyapp.modelclass.ProfileForModel;
import com.example.matrimonyapp.modelclass.RashiModel;
import com.example.matrimonyapp.modelclass.StarModel;
import com.example.matrimonyapp.modelclass.VisaStatusModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JatakaDetailsFragment extends Fragment {

    FragmentRegister4Binding binding;

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

    String rashi_Name,rashiId,gotra_Name,gotraId,lagna_Name,lagnaId,star_Name,starId,selectPaymentOption;
    RadioButton selectedRadioButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // View view = inflater.inflate(R.layout.fragment_register4,container,false);

        binding = FragmentRegister4Binding.inflate(inflater,container,false);
        masterApiList();

        binding.radioMangalik.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                String text = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        binding.radioAstrology.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                selectedRadioButton = (RadioButton) group.findViewById(checkedId);
                String text = selectedRadioButton.getText().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new EducationProfessionFragment();
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

        binding.spinnerRashi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                rashi_Name = binding.spinnerRashi.getItemAtPosition(binding.spinnerRashi.getSelectedItemPosition()).toString();

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

        binding.spinnerStar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                star_Name = binding.spinnerStar.getItemAtPosition(binding.spinnerStar.getSelectedItemPosition()).toString();

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

        binding.spinnerGotra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gotra_Name = binding.spinnerGotra.getItemAtPosition(binding.spinnerGotra.getSelectedItemPosition()).toString();

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

        binding.spinnerLagna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lagna_Name = binding.spinnerLagna.getItemAtPosition(binding.spinnerLagna.getSelectedItemPosition()).toString();

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

                        rashiName.add(0, "Select Rashi");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, rashiName);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerRashi.setAdapter(nationalityAdapter);

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

                        gotraName.add(0, "Select Gotra");

                        ArrayAdapter<String> gotratyAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, gotraName);
                        gotratyAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerGotra.setAdapter(gotratyAdapter);

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

                        lagnaName.add(0, "Select Lagna");

                        ArrayAdapter<String> lagnayAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, lagnaName);
                        lagnayAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerLagna.setAdapter(lagnayAdapter);

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

                        starName.add(0, "Select Star");

                        ArrayAdapter<String> staryAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, starName);
                        staryAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerStar.setAdapter(staryAdapter);

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
