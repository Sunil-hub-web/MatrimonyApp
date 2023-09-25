package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.fragment.SingleViewProfile;
import com.example.matrimonyapp.modelclass.CandidateDetails_Model;
import com.example.matrimonyapp.modelclass.ProfileSelected_ModelClass;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class ViewProfileSelectedAdapter extends RecyclerView.Adapter<ViewProfileSelectedAdapter.ViewHolder> {

    ArrayList<ProfileSelected_ModelClass> profileSelectedMode;
    Context context;
    SessionManager sessionManager;

    public ViewProfileSelectedAdapter(FragmentActivity activity, ArrayList<ProfileSelected_ModelClass> profileSelectedMode) {

        this.context = activity;
        this.profileSelectedMode = profileSelectedMode;

    }

    @NonNull
    @Override
    public ViewProfileSelectedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectviewprofile,parent,false);
        return new ViewProfileSelectedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProfileSelectedAdapter.ViewHolder holder, int position) {

        sessionManager = new SessionManager(context);

        ProfileSelected_ModelClass candidate = profileSelectedMode.get(position);

        String url = "https://collegeprojectz.com/matrimonial//uploads/"+candidate.getProfile_image();
        Picasso.with(context).load(url).into(holder.imag_uniform);

        holder.textname.setText(candidate.getCandidate_name());
        holder.textgender.setText(candidate.getGender());
        holder.textlocation.setText(candidate.getCountry_name());

        holder.btn_ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleViewProfile singleViewProfile = new SingleViewProfile();
                Bundle bundle = new Bundle();
                bundle.putString("userId", sessionManager.getUSERID());
                bundle.putString("profileId",candidate.getProfile_id());
                bundle.putString("message","Homeprofile");
                singleViewProfile.setArguments(bundle);
                FragmentTransaction transaction =((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framLayout, singleViewProfile); // Add your fragment class
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return profileSelectedMode.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imag_uniform;
        TextView textname,textgender,textlocation;
        MaterialButton btn_ViewProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imag_uniform = itemView.findViewById(R.id.imag_uniform);
            textname = itemView.findViewById(R.id.textname);
            textgender = itemView.findViewById(R.id.textgender);
            btn_ViewProfile = itemView.findViewById(R.id.btn_ViewProfile);
            textlocation = itemView.findViewById(R.id.textlocation);
        }
    }
}
