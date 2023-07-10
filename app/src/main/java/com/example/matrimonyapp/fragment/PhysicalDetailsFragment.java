package com.example.matrimonyapp.fragment;

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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.databinding.FragmentRegister9Binding;
import com.example.matrimonyapp.modelclass.BodyTypeModel;
import com.example.matrimonyapp.modelclass.ComplexionModel;
import com.example.matrimonyapp.modelclass.EthnicityModel;
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

public class PhysicalDetailsFragment extends Fragment {

    FragmentRegister9Binding binding;
    ArrayList<String> name_Height = new ArrayList<>();
    HashMap<String, String> Height_Map = new HashMap<>();

    ArrayList<EthnicityModel> ethnicityModels;
    ArrayList<String> ethnicity_Name;
    Map<String,String> ethnicity_Map;

    ArrayList<BodyTypeModel> bodyTypeModels;
    ArrayList<String> bodyType_Name;
    Map<String,String> bodyType_Map;

    ArrayList<ComplexionModel> complexionModels;
    ArrayList<String> complexion_Name;
    Map<String,String> complexion_Map;
    String ethnicityName,ethnicityId,bodyTypeName,bodyTypeId,complexionName,complexionid,nameHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       // View view = inflater.inflate(R.layout.fragment_register9,container,false);

        binding = FragmentRegister9Binding.inflate(inflater,container,false);

        masterApiList();

        name_Height.add("Height");
        name_Height.add("4ft 5inc - 135cms");
        name_Height.add("4ft 6inc - 137cms");
        name_Height.add("4ft 7inc - 140cms");
        name_Height.add("4ft 8inc - 142cms");
        name_Height.add("4ft 9inc - 145cms");
        name_Height.add("4ft 10inc - 147cms");
        name_Height.add("4ft 11inc - 150cms");
        name_Height.add("5 ft - 152cms");
        name_Height.add("5ft 1inc - 155cms");
        name_Height.add("5ft 2inc - 157cms");
        name_Height.add("5ft 3inc - 160cms");
        name_Height.add("5ft 4inc - 162cms");
        name_Height.add("5ft 5inc - 165cms");
        name_Height.add("5ft 6inc - 167cms");
        name_Height.add("5ft 7inc - 170cms");
        name_Height.add("5ft 8inc - 172cms");
        name_Height.add("5ft 9inc - 175cms");
        name_Height.add("5ft 10inc - 177cms");
        name_Height.add("5ft 11inc - 180cms");
        name_Height.add("6ft - 183cms");
        name_Height.add("6ft 1inc - 186cms");
        name_Height.add("6ft 2inc - 188cms");


        ArrayAdapter<String> livingCountryAdapter = new ArrayAdapter<String>(getContext(),
                R.layout.spinnerfront2, name_Height);
        livingCountryAdapter.setDropDownViewResource(R.layout.spinneritem);
        binding.spinnerHeight.setAdapter(livingCountryAdapter);

        nameHeight = binding.spinnerHeight.getSelectedItem().toString();
        Toast.makeText(getActivity(), nameHeight, Toast.LENGTH_SHORT).show();

        binding.spinnerEthnicity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ethnicityName = binding.spinnerEthnicity.getItemAtPosition(binding.spinnerEthnicity.getSelectedItemPosition()).toString();

                if (ethnicityName.equalsIgnoreCase("Select Ethnicity")) {

                    ethnicityId = "";

                } else {

                    ethnicityId = ethnicity_Map.get(ethnicityName);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.spinnerBodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bodyTypeName = binding.spinnerBodyType.getItemAtPosition(binding.spinnerBodyType.getSelectedItemPosition()).toString();

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
        binding.spinnerComplexion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                complexionName = binding.spinnerComplexion.getItemAtPosition(binding.spinnerComplexion.getSelectedItemPosition()).toString();

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

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new HealthDetailsFragment();
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

                        ethnicityModels = new ArrayList<>();
                        ethnicity_Name = new ArrayList<>();
                        ethnicity_Map = new HashMap<>();

                        ethnicityModels.clear();
                        ethnicity_Name.clear();
                        ethnicity_Map.clear();

                        JSONArray jsonArray_ethnicity = new JSONArray(ethnicity);

                        for (int i=0;i<jsonArray_ethnicity.length();i++){

                            JSONObject jsonObject1_ethnicity = jsonArray_ethnicity.getJSONObject(i);
                            String ethnicity_id = jsonObject1_ethnicity.getString("ethnicity_id");
                            String ethnicity_name = jsonObject1_ethnicity.getString("ethnicity_name");

                            EthnicityModel ethnicityModel = new EthnicityModel(ethnicity_id,ethnicity_name);

                            ethnicityModels.add(ethnicityModel);
                            ethnicity_Name.add(ethnicity_name);

                            ethnicity_Map.put(ethnicity_name,ethnicity_id);
                        }

                        ethnicity_Name.add(0, "Select Ethnicity");

                        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, ethnicity_Name);
                        nationalityAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerEthnicity.setAdapter(nationalityAdapter);

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

                        bodyType_Name.add(0, "Select BodyType");

                        ArrayAdapter<String> gotratyAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, bodyType_Name);
                        gotratyAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerBodyType.setAdapter(gotratyAdapter);

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

                        complexion_Name.add(0, "Select Complexion");

                        ArrayAdapter<String> lagnayAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, complexion_Name);
                        lagnayAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerComplexion.setAdapter(lagnayAdapter);
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
