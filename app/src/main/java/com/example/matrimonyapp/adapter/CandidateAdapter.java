package com.example.matrimonyapp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.fragment.SingleViewProfile;
import com.example.matrimonyapp.modelclass.CandidateDetails_Model;
import com.example.matrimonyapp.modelclass.ShowImageGallery_Model;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    Context context;
    ArrayList<CandidateDetails_Model> candidateDetailsModels;
    SessionManager sessionManager;
    String resoanseData;
    public CandidateAdapter(Context context, ArrayList<CandidateDetails_Model> candidateDetailsModels) {

        this.context = context;
        this.candidateDetailsModels = candidateDetailsModels;

    }

    @NonNull
    @Override
    public CandidateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candaditedetails,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateAdapter.ViewHolder holder, int position) {

        sessionManager = new SessionManager(context);

        CandidateDetails_Model candidate = candidateDetailsModels.get(position);

        if (candidate.getImage().equals("null")){

            holder.imag_uniform.setImageResource(R.drawable.domyprofileimage);

        }else{

            String url = "https://collegeprojectz.com/matrimonial//uploads/"+candidate.getImage();
            Picasso.with(context).load(url).into(holder.imag_uniform);
        }



        holder.textname.setText(candidate.getName());
        holder.textdenigation.setText(candidate.getProfession_name());
        holder.textgender.setText(candidate.getGender());

        String nullvaluev = String.valueOf(candidate.getAge());

        if (!nullvaluev.equals("null")){

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDate localDate1 = LocalDate.parse(candidate.getAge());
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
                String str = formatter.format(date);

                LocalDate localDate2 = LocalDate.parse(str);

                int yearage = Period.between(localDate1, localDate2).getYears();

                holder.textage.setText(String.valueOf(yearage)+"year");
            }
        }

        holder.btn_ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getProfileDetails(sessionManager.getUSERID(),candidate.getId());

//                if (resoanseData.equals("NotValide")){
//
//                }else{
//
//
//                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return candidateDetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imag_uniform;
        TextView textname,textdenigation,textgender,textage;
        MaterialButton btn_ViewProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imag_uniform = itemView.findViewById(R.id.imag_uniform);
            textname = itemView.findViewById(R.id.textname);
            textdenigation = itemView.findViewById(R.id.textdenigation);
            textgender = itemView.findViewById(R.id.textgender);
            textage = itemView.findViewById(R.id.textage);
            btn_ViewProfile = itemView.findViewById(R.id.btn_ViewProfile);
        }
    }

    public void getProfileDetails(String customer_id,String profile_id){

        ProgressDialog progressDialog = new ProgressDialog(context);
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

                        resoanseData = "Valide";

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject_message = new JSONObject(messages);
                        String responsecode  = jsonObject_message.getString("responsecode");
                        String statusarray  = jsonObject_message.getString("status");
                        JSONObject jsonObject_status = new JSONObject(statusarray);
                        String customerdata = jsonObject_status.getString("customerdata");
                        String gallery = jsonObject_status.getString("gallery");

                        JSONArray jsonArray_data = new JSONArray(customerdata);

                        for (int i=0;i<jsonArray_data.length();i++){

                            JSONObject jsonObject1_data = jsonArray_data.getJSONObject(0);

                        }

                        SingleViewProfile singleViewProfile = new SingleViewProfile();
                        Bundle bundle = new Bundle();
                        bundle.putString("userId", sessionManager.getUSERID());
                        bundle.putString("profileId",profile_id);
                        bundle.putString("message","Homeprofile");
                        singleViewProfile.setArguments(bundle);
                        FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.framLayout, singleViewProfile); // Add your fragment class
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }else{

                        String error = jsonObject.getString("error");
                        String messages = jsonObject.getString("messages");
                        JSONObject jsonObject1_message = new JSONObject(messages);
                        String responsecode = jsonObject1_message.getString("responsecode");
                        String statusresp = jsonObject1_message.getString("status");
                        resoanseData = "NotValide";
                        Toast.makeText(context, statusresp, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
