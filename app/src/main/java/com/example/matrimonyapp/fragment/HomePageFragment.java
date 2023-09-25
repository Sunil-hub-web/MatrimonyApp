package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.adapter.CandidateAdapter;
import com.example.matrimonyapp.adapter.NewCandidateAdapter;
import com.example.matrimonyapp.adapter.SliderAdapterExample;
import com.example.matrimonyapp.adapter.SuccessStoriesAdapter;
import com.example.matrimonyapp.modelclass.BannerModelClass;
import com.example.matrimonyapp.modelclass.CandidateDetails_Model;
import com.example.matrimonyapp.modelclass.NewCandidate_ModelClass;
import com.example.matrimonyapp.modelclass.SuccessStories_model;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;


public class HomePageFragment extends Fragment {

    ImageView imageview,imageview1;
    TextView text_details,homeAddress,textheader;
    ArrayList<CandidateDetails_Model> candidateDetailsModels = new ArrayList<>();
    ArrayList<NewCandidate_ModelClass> newCandidateModelClasses = new ArrayList<>();
    ArrayList<SuccessStories_model> successStoriesModels = new ArrayList<>();
    RecyclerView recyclerRecentProfile,recyclersuccessstories,recyclerNewCandidateProfile;
    SliderView imageSlider;
    ArrayList<BannerModelClass> bannerModel = new ArrayList<>();
    private SliderAdapterExample adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);

        imageview = view.findViewById(R.id.imageview);
        text_details = view.findViewById(R.id.text_details);
        textheader = view.findViewById(R.id.textheader);
        recyclerRecentProfile = view.findViewById(R.id.recyclerRecentProfile);
        recyclersuccessstories = view.findViewById(R.id.recyclersuccessstories);
        imageSlider = view.findViewById(R.id.imageSlider);
        recyclerNewCandidateProfile = view.findViewById(R.id.recyclerNewCandidateProfile);

       // Picasso.with(getContext()).load("https://collegeprojectz.com/matrimonial//assets/user/img/banner.jpg").into(imageview);

        getHomedetails();

        return view;
    }

    public void getHomedetails(){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Home Details Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiList.user_home, new Response.Listener<String>() {
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

                    String banner_dtl = jsonObject_statues.getString("banner_dtl");
                    String cms_data = jsonObject_statues.getString("cms_data");
                    String Success_Stories = jsonObject_statues.getString("Success_Stories");
                    String Allcandidate_data = jsonObject_statues.getString("Allcandidate_data");
                    String Newcandidate_data = jsonObject_statues.getString("Newcandidate_data");

                    JSONArray jsonArray_banner = new JSONArray(banner_dtl);

                    for (int j=0;j<jsonArray_banner.length();j++){

                        JSONObject jsonObject_banner = jsonArray_banner.getJSONObject(j);

                        String banner_id = jsonObject_banner.getString("banner_id");
                        String banner_title = jsonObject_banner.getString("banner_title");
                        String banner_subtitle = jsonObject_banner.getString("banner_subtitle");
                        String description = jsonObject_banner.getString("description");
                        String urrl = jsonObject_banner.getString("urrl");
                        String type = jsonObject_banner.getString("type");
                        String orderby = jsonObject_banner.getString("orderby");
                        String image = jsonObject_banner.getString("image");

                        BannerModelClass bannerModelClass = new BannerModelClass(banner_id,banner_title,banner_subtitle,description,
                                urrl,type,orderby,image);
                        bannerModel.add(bannerModelClass);
                    }

                    adapter = new SliderAdapterExample(getActivity(), bannerModel);
                    imageSlider.setSliderAdapter(adapter);
                    imageSlider.setIndicatorAnimation(IndicatorAnimationType.DROP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    imageSlider.setIndicatorSelectedColor(Color.WHITE);
                    imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                    imageSlider.setScrollTimeInSec(3);
                    imageSlider.setAutoCycle(true);
                    imageSlider.startAutoCycle();



                    JSONArray jsonArray_cms_data = new JSONArray(cms_data);

                    for (int i=0;i<jsonArray_cms_data.length();i++){

                        JSONObject jsonObject1_cms_data = jsonArray_cms_data.getJSONObject(0);

                        String page_name = jsonObject1_cms_data.getString("page_name");
                        String details = jsonObject1_cms_data.getString("details");
                        String image = jsonObject1_cms_data.getString("image");

                       // String url = "https://collegeprojectz.com/matrimonial//uploads/"+image;

                        // Picasso.with(getContext()).load(url).into(imageview1);

                        textheader.setText(page_name);
                        text_details.setText(details);
                    }

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
                    recyclerRecentProfile.setLayoutManager(linearLayoutManager);
                    recyclerRecentProfile.setHasFixedSize(true);
                    recyclerRecentProfile.setAdapter(candidateAdapter);

                    JSONArray jsonArray_Newcandidatedata = new JSONArray(Newcandidate_data);

                    for (int j=0;j<jsonArray_Newcandidatedata.length();j++){

                        JSONObject jsonObject_Newcandidatedata = jsonArray_Newcandidatedata.getJSONObject(j);

                        String id = jsonObject_Newcandidatedata.getString("candidate_id");
                        String full_name = jsonObject_Newcandidatedata.getString("candidate_name");
                        String user_id = jsonObject_Newcandidatedata.getString("user_id");
                        String profi_image = jsonObject_Newcandidatedata.getString("profile_image");
                        String candidate_name = jsonObject_Newcandidatedata.getString("candidate_name");
                        String dob = jsonObject_Newcandidatedata.getString("dob");
                        String gender = jsonObject_Newcandidatedata.getString("gender");
                       // String candidate_profession_name = jsonObject_Newcandidatedata.getString("candidate_profession_name");

                        NewCandidate_ModelClass newCandidate_modelClass = new NewCandidate_ModelClass(
                                id,full_name,dob,"",gender,profi_image
                        );

                        newCandidateModelClasses.add(newCandidate_modelClass);
                    }

                    LinearLayoutManager linearLayoutManager1 =
                            new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                    NewCandidateAdapter newCandidateAdapter = new NewCandidateAdapter(getContext(),newCandidateModelClasses);
                    recyclerNewCandidateProfile.setLayoutManager(linearLayoutManager1);
                    recyclerNewCandidateProfile.setHasFixedSize(true);
                    recyclerNewCandidateProfile.setAdapter(newCandidateAdapter);

                    JSONArray jsonArray_Success_Stories = new JSONArray(Success_Stories);

                    for (int k=0;k<jsonArray_Success_Stories.length();k++){

                        JSONObject jsonObject_Success_Stories = jsonArray_Success_Stories.getJSONObject(k);

                        String blog_id = jsonObject_Success_Stories.getString("blog_id");
                        String name = jsonObject_Success_Stories.getString("name");
                        String title = jsonObject_Success_Stories.getString("title");
                        String message = jsonObject_Success_Stories.getString("message");
                        String category = jsonObject_Success_Stories.getString("category");
                        String image = jsonObject_Success_Stories.getString("image");
                        String date = jsonObject_Success_Stories.getString("date");

                        SuccessStories_model successStories_model = new SuccessStories_model(
                                blog_id,name,title,message,category,image,date
                        );

                        successStoriesModels.add(successStories_model);
                    }

                    LinearLayoutManager linearLayoutManager2 =
                            new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                    SuccessStoriesAdapter successStoriesAdapter = new SuccessStoriesAdapter(getContext(),successStoriesModels);
                    recyclersuccessstories.setLayoutManager(linearLayoutManager2);
                    recyclersuccessstories.setHasFixedSize(true);
                    recyclersuccessstories.setAdapter(successStoriesAdapter);

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
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
