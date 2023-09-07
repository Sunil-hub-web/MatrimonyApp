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
import com.example.matrimonyapp.modelclass.PackagePriceModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class PackagePriceAdapter extends RecyclerView.Adapter<PackagePriceAdapter.ViewHolder> {

    Context context;
    ArrayList<PackagePriceModel> packagePriceModels;

    public PackagePriceAdapter(ArrayList<PackagePriceModel> packagePriceModels, FragmentActivity activity) {

        this.context = activity;
        this.packagePriceModels = packagePriceModels;

    }

    @NonNull
    @Override
    public PackagePriceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showpackage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackagePriceAdapter.ViewHolder holder, int position) {

        PackagePriceModel priceModel = packagePriceModels.get(position);

        holder.packagename.setText(priceModel.getPackage_name());
        holder.packageprice.setText("Rs. "+priceModel.getPackage_name()+" /-");
        holder.packagedescription.setText(priceModel.getPackage_description());
        holder.activeday.setText("Active Day : "+priceModel.getActive_day());
        holder.noofprofile.setText("No Of Profile : "+priceModel.getNo_of_profile());
    }

    @Override
    public int getItemCount() {
        return packagePriceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView packagename,packageprice,packagedescription,activeday,noofprofile;
        MaterialButton btn_BuyNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            packagename = itemView.findViewById(R.id.packagename);
            packageprice = itemView.findViewById(R.id.packageprice);
            packagedescription = itemView.findViewById(R.id.packagedescription);
            activeday = itemView.findViewById(R.id.activeday);
            noofprofile = itemView.findViewById(R.id.noofprofile);
            btn_BuyNow = itemView.findViewById(R.id.btn_BuyNow);
        }
    }
}
