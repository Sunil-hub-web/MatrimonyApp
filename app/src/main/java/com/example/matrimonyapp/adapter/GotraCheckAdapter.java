package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.databinding.ShowcheckboxrecyBinding;
import com.example.matrimonyapp.modelclass.EducationModel;
import com.example.matrimonyapp.modelclass.GotraModel;

import java.util.ArrayList;

public class GotraCheckAdapter extends RecyclerView.Adapter<GotraCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<GotraModel> gotraModels;
    ArrayList<String> listData = new ArrayList<>();

    public GotraCheckAdapter(ArrayList<GotraModel> gotraModels, FragmentActivity activity) {

        this.context = activity;
        this.gotraModels = gotraModels;
    }

    @NonNull
    @Override
    public GotraCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new GotraCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GotraCheckAdapter.ViewHolder holder, int position) {

        GotraModel education = gotraModels.get(position);
        binding.showCheckbox.setText(education.getGotra_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                //    binding.showCheckbox.setChecked(false);

                    listData.add(education.getGotra_id());
                  //  notifyDataSetChanged();

                }else{

                   // binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getGotra_id());
                 //   notifyDataSetChanged();
                }
            }
        });


    }

    public ArrayList<String> showdata(){
        return listData;
    }

    @Override
    public int getItemCount() {
        return gotraModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
