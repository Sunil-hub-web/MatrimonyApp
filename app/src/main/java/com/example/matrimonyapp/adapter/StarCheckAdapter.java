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
import com.example.matrimonyapp.modelclass.StarModel;

import java.util.ArrayList;

public class StarCheckAdapter extends RecyclerView.Adapter<StarCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<StarModel> starModels;
    ArrayList<String> listData = new ArrayList<>();

    public StarCheckAdapter(ArrayList<StarModel> starModels, FragmentActivity activity) {

        this.context = activity;
        this.starModels = starModels;
    }

    @NonNull
    @Override
    public StarCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StarCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StarCheckAdapter.ViewHolder holder, int position) {

        StarModel education = starModels.get(position);
        binding.showCheckbox.setText(education.getStar_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                 //   binding.showCheckbox.setChecked(false);

                    listData.add(education.getStar_id());
                 //   notifyDataSetChanged();

                }else{

                 //   binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getStar_id());
                  //  notifyDataSetChanged();
                }
            }
        });

    }

    public ArrayList<String> showdata(){
        return listData;
    }

    @Override
    public int getItemCount() {
        return starModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
