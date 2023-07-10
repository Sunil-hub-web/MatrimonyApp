package com.example.matrimonyapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.matrimonyapp.databinding.FragmentRegister7Binding;
import com.example.matrimonyapp.modelclass.EducationModel;
import com.example.matrimonyapp.modelclass.IncomeModel;
import com.example.matrimonyapp.modelclass.LanguageModel;
import com.example.matrimonyapp.modelclass.ProfessionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkillDetailsFragment extends Fragment {

    ArrayList<LanguageModel> languageModels;
    ArrayList<String> language_Name;
    Map<String,String> language_Map;
    FragmentRegister7Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_register7,container,false);

        binding = FragmentRegister7Binding.inflate(inflater,container,false);

        masterApiList();

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

                        languageModels = new ArrayList<>();
                        language_Name = new ArrayList<>();
                        language_Map = new HashMap<>();

                        languageModels.clear();
                        language_Name.clear();
                        language_Map.clear();

                        JSONArray jsonArray_language = new JSONArray(language);

                        for (int i=0;i<jsonArray_language.length();i++){

                            JSONObject jsonObject1_education = jsonArray_language.getJSONObject(i);
                            String language_id = jsonObject1_education.getString("language_id");
                            String language_name = jsonObject1_education.getString("language_name");

                            LanguageModel languageModel = new LanguageModel(language_id,language_name);

                            languageModels.add(languageModel);
                            language_Name.add(language_name);

                            language_Map.put(language_name,language_id);
                        }

                        language_Name.add(0, "Select Language");

                        ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>(getContext(),
                                R.layout.spinnerfront2, language_Name);
                        languageAdapter.setDropDownViewResource(R.layout.spinneritem);
                        binding.spinnerLanguage.setAdapter(languageAdapter);

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
