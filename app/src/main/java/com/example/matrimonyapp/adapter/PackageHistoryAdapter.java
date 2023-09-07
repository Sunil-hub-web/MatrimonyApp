package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.modelclass.PackageHistoryModel;

import java.util.ArrayList;

public class PackageHistoryAdapter extends RecyclerView.Adapter<PackageHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<PackageHistoryModel> packageHistoryModels;

    public PackageHistoryAdapter(ArrayList<PackageHistoryModel> packageHistoryModels, FragmentActivity activity) {

        this.context = activity;
        this.packageHistoryModels = packageHistoryModels;
    }

    @NonNull
    @Override
    public PackageHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showpackagehistory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageHistoryAdapter.ViewHolder holder, int position) {

        PackageHistoryModel packagepr = packageHistoryModels.get(position);

        holder.packageName.setText("<font color='#6c757d'>PackageName :<br></font>"+packagepr.getPack_name());
        holder.candidateName.setText("<font color='#6c757d'>CandidateName :<br></font>"+packagepr.getCandidate_name());
        holder.packageDescription.setText("<font color='#6c757d'>PackageDescription :<br></font>"+packagepr.getPack_description());
        holder.activeDay.setText("<font color='#6c757d'>ActiveDay :<br></font>"+packagepr.getPack_activeday());
        holder.packageProfile.setText("<font color='#6c757d'>PackageProfile :<br></font>"+packagepr.getNo_of_profile());
        holder.packagePrice.setText("<font color='#6c757d'>PackagePrice :<br></font>"+packagepr.getPack_price());
        holder.packageActiveDate.setText("<font color='#6c757d'>PackageActiveDate :<br></font>"+packagepr.getPackageactive_date());
        holder.packageExpiryDate.setText("<font color='#6c757d'>PackageExpiryDate :<br></font>"+packagepr.getPackageexpiry_date());

    }

    @Override
    public int getItemCount() {
        return packageHistoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView packageName,candidateName,packageDescription,activeDay,packageProfile,packagePrice,
                packageActiveDate,packageExpiryDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            packageName = itemView.findViewById(R.id.packageName);
            candidateName = itemView.findViewById(R.id.candidateName);
            packageDescription = itemView.findViewById(R.id.packageDescription);
            activeDay = itemView.findViewById(R.id.activeDay);
            packageProfile = itemView.findViewById(R.id.packageProfile);
            packagePrice = itemView.findViewById(R.id.packagePrice);
            packageActiveDate = itemView.findViewById(R.id.packageActiveDate);
            packageExpiryDate = itemView.findViewById(R.id.packageExpiryDate);
        }
    }
}
