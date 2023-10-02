package com.example.matrimonyapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.matrimonyapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewSuccessStories extends Fragment {

    ImageView imag_uniform;
    TextView textname,texttitle,textmessage,textdate;
    String userId,profileId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_successstories,container,false);

        textname = view.findViewById(R.id.textname);
        texttitle = view.findViewById(R.id.texttitle);
        textmessage = view.findViewById(R.id.textmessage);
        textdate = view.findViewById(R.id.textdate);
        imag_uniform = view.findViewById(R.id.imag_uniform);

        Bundle arguments = getArguments();
        if (arguments != null) {

            userId = arguments.getString("userId");
            profileId = arguments.getString("profileId");

            successStories(profileId);

        }

        return view;
    }

    public void successStories(String stories_id){

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("View Stories Please Wait.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiList.single_Success_stories, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if(status.equals("200")){

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject1_messages = new JSONObject(messages);
                        String responsecode = jsonObject1_messages.getString("responsecode");
                        String status_resp = jsonObject1_messages.getString("status");
                        JSONObject jsonObject1_status_resp = new JSONObject(status_resp);
                        String singleBlog = jsonObject1_status_resp.getString("singleBlog");
                        JSONArray jsonArray = new JSONArray(singleBlog);
                        if(jsonArray.length() != 0){

                            JSONObject jsonObject1_singleBlog = jsonArray.getJSONObject(0);
                            String blog_id = jsonObject1_singleBlog.getString("blog_id");
                            String name = jsonObject1_singleBlog.getString("name");
                            String title = jsonObject1_singleBlog.getString("title");
                            String message = jsonObject1_singleBlog.getString("message");
                            String category = jsonObject1_singleBlog.getString("category");
                            String image = jsonObject1_singleBlog.getString("image");
                            String date = jsonObject1_singleBlog.getString("date");

                            String url = "https://collegeprojectz.com/matrimonial//uploads/"+image;
                            Picasso.with(getActivity()).load(url).into(imag_uniform);

                            textname.setText(name);
                            texttitle.setText(Html.fromHtml(title));
                            textmessage.setText(message);
                            textdate.setText(date);

                        }else{


                        }
                    }else{

                        Toast.makeText(getActivity(), "Data Not View", Toast.LENGTH_SHORT).show();

                    }


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
                params.put("stories_id",stories_id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}
