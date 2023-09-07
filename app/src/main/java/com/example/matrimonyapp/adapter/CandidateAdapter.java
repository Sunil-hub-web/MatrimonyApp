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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.SessionManager;
import com.example.matrimonyapp.fragment.SingleViewProfile;
import com.example.matrimonyapp.modelclass.CandidateDetails_Model;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    Context context;
    ArrayList<CandidateDetails_Model> candidateDetailsModels;

    SessionManager sessionManager;
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

        String url = "https://collegeprojectz.com/matrimonial//uploads/"+candidate.getImage();
        Picasso.with(context).load(url).into(holder.imag_uniform);

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

                SingleViewProfile singleViewProfile = new SingleViewProfile();
                Bundle bundle = new Bundle();
                bundle.putString("userId", sessionManager.getUSERID());
                bundle.putString("profileId",candidate.getId());
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
}