package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.matrimonyapp.ApiList;
import com.example.matrimonyapp.databinding.ViewuserdetailsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SingleViewProfile extends Fragment {

    ViewuserdetailsBinding binding;
    String userId,profileId,message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = ViewuserdetailsBinding.inflate(inflater,container,false);

        Bundle arguments = getArguments();
        if (arguments != null) {

            userId = arguments.getString("userId");
            profileId = arguments.getString("profileId");
            message = arguments.getString("message");

            getProfileDetails(userId,profileId);

        }

        return binding.getRoot();
    }

    public void getProfileDetails(String customer_id,String profile_id){

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("View Profile Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.Single_profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");

                    if (status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode  = jsonObject_message.getString("responsecode");
                        String statusarray  = jsonObject_message.getString("status");
                        JSONObject jsonObject_status = new JSONObject(statusarray);
                        String customerdata = jsonObject_status.getString("customerdata");

                        JSONArray jsonArray_data = new JSONArray(customerdata);

                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject1_data = jsonArray_data.getJSONObject(0);

                            String id = jsonObject1_data.getString("id");
                            String full_name = jsonObject1_data.getString("full_name");
                            String user_name = jsonObject1_data.getString("user_name");
                            String password = jsonObject1_data.getString("password");
                            String email = jsonObject1_data.getString("email");
                            String alter_cnum = jsonObject1_data.getString("alter_cnum");
                            String contact_no = jsonObject1_data.getString("contact_no");
                            String profile_image = jsonObject1_data.getString("profile_image");
                            String profi_image = jsonObject1_data.getString("profi_image");
                            String candidate_name = jsonObject1_data.getString("candidate_name");
                            String dob = jsonObject1_data.getString("dob");
                            String gender = jsonObject1_data.getString("gender");
                            String profilefor_name = jsonObject1_data.getString("profilefor_name");
                            String maritalstatus_name = jsonObject1_data.getString("maritalstatus_name");
                            String living_with_family = jsonObject1_data.getString("living_with_family");
                            String father_name = jsonObject1_data.getString("father_name");
                            String father_occupation = jsonObject1_data.getString("father_occupation");
                            String mother_name = jsonObject1_data.getString("mother_name");
                            String mother_occupation = jsonObject1_data.getString("mother_occupation");
                            String familystatus_name = jsonObject1_data.getString("familystatus_name");
                            String family_type = jsonObject1_data.getString("family_type");
                            String no_of_brother = jsonObject1_data.getString("no_of_brother");
                            String no_of_sister = jsonObject1_data.getString("no_of_sister");
                            String height = jsonObject1_data.getString("height");
                            String weight = jsonObject1_data.getString("weight");
                            String diet = jsonObject1_data.getString("diet");
                            String drink = jsonObject1_data.getString("drink");
                            String smoke = jsonObject1_data.getString("smoke");
                            String any_disability = jsonObject1_data.getString("any_disability");
                            String genetic_disease = jsonObject1_data.getString("genetic_disease");
                            String skill = jsonObject1_data.getString("skill");
                            String hobbies = jsonObject1_data.getString("hobbies");
                            String candidate_details = jsonObject1_data.getString("candidate_details");
                            String living_since = jsonObject1_data.getString("living_since");
                            String mangalik = jsonObject1_data.getString("mangalik");
                            String astrology = jsonObject1_data.getString("astrology");
                            String candidate_country_name = jsonObject1_data.getString("candidate_country_name");
                            String candidate_state_name = jsonObject1_data.getString("candidate_state_name");
                            String district_name = jsonObject1_data.getString("district_name");
                            String candidate_city_name = jsonObject1_data.getString("candidate_city_name");
                            String birth_city_name = jsonObject1_data.getString("birth_city_name");
                            String nationality_name = jsonObject1_data.getString("nationality_name");
                            String visastatus_name = jsonObject1_data.getString("visastatus_name");
                            String candidate_religion_name = jsonObject1_data.getString("candidate_religion_name");
                            String candidate_caste_name = jsonObject1_data.getString("candidate_caste_name");
                            String candidate_subcaste_name = jsonObject1_data.getString("candidate_subcaste_name");
                            String candidate_ethnicity_name = jsonObject1_data.getString("candidate_ethnicity_name");
                            String candidate_education_name = jsonObject1_data.getString("candidate_education_name");
                            String candidate_profession_name = jsonObject1_data.getString("candidate_profession_name");
                            String candidate_income_name = jsonObject1_data.getString("candidate_income_name");
                            String candidate_bodytype_name = jsonObject1_data.getString("candidate_bodytype_name");
                            String candidate_complexion_name = jsonObject1_data.getString("candidate_complexion_name");
                            String candidate_star_name = jsonObject1_data.getString("candidate_star_name");
                            String candidate_rashi_name = jsonObject1_data.getString("candidate_rashi_name");
                            String candidate_lagna_name = jsonObject1_data.getString("candidate_lagna_name");
                            String candidate_gotra_name = jsonObject1_data.getString("candidate_gotra_name");
                            String candidate_language_name = jsonObject1_data.getString("candidate_language_name");

                            binding.textYourFullName.setText(full_name);
                            binding.textEmail.setText(email);
                            binding.textPhoneNumber.setText(contact_no);
                            binding.textPassword.setText(password);

                            binding.textDateOfBirth.setText(dob);
                            binding.textCreatingFor.setText(profilefor_name);
                            binding.textCandidateName.setText(candidate_name);
                            binding.textGender.setText(gender);
                            binding.textMaritalStatus.setText(maritalstatus_name);
                            binding.textCountrySince.setText(living_since+"  Year");
                            binding.textPlaceOfBirth.setText(birth_city_name);
                            binding.textNationality.setText(nationality_name);
                            binding.textVisaStatus.setText(visastatus_name);

                            binding.textRashi.setText(candidate_rashi_name);
                            binding.textGotra.setText(candidate_gotra_name);
                            binding.textStar.setText(candidate_star_name);
                            binding.textLagna.setText(candidate_lagna_name);
                            binding.textAreYouMangalik.setText(mangalik);
                            binding.textWantAstrology.setText(astrology);

                            binding.textYourCountry.setText(candidate_country_name);
                            binding.textYourState.setText(candidate_state_name);
                            binding.textYourDistrict.setText(district_name);
                            binding.textYourCity.setText(candidate_city_name);

                            binding.textHighestEducation.setText(candidate_education_name);
                            binding.textProfession.setText(candidate_profession_name);
                            binding.textIncome.setText(candidate_income_name);

                            binding.textLivingWithFamily.setText(living_with_family);
                            binding.textFatherName.setText(father_name);
                            binding.textFatherOccupation.setText(father_occupation);
                            binding.textMotherName.setText(mother_name);
                            binding.textMotherOccupation.setText(mother_occupation);
                            binding.textFamilyType.setText(family_type);
                            binding.textFamilyStatus.setText(familystatus_name);
                            binding.textNoofBrother.setText(no_of_brother);
                            binding.textNoOfSister.setText(no_of_sister);

                            binding.textReligion.setText(candidate_religion_name);
                            binding.textCaste.setText(candidate_caste_name);
                            binding.textSubcaste.setText(candidate_subcaste_name);

                            binding.textMotherTongue.setText(candidate_language_name);
                            binding.textYourSkill.setText(skill);
                            binding.textYourHobbies.setText(hobbies);
                            binding.textSomethingAboutYou.setText(candidate_details);

                            binding.textEthnicity.setText(candidate_ethnicity_name);
                            binding.textHeight.setText(height);
                            binding.textWeight.setText(weight);
                            binding.textBodyType.setText(candidate_bodytype_name);
                            binding.textComplexion.setText(candidate_complexion_name);

                            binding.textAnyDisability.setText(any_disability);
                            binding.textGenetiocDisease.setText(genetic_disease);

                            binding.textDiet.setText(diet);
                            binding.textDrink.setText(drink);
                            binding.textSmoke.setText(smoke);

                        }
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
                params.put("customer_id",customer_id);
                params.put("profile_id",profile_id);

                Log.d("userdetails",params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
