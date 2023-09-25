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
import com.example.matrimonyapp.modelclass.BodyTypeModel;
import com.example.matrimonyapp.modelclass.EducationModel;

import java.util.ArrayList;

public class BodyTypeCheckAdapter extends RecyclerView.Adapter<BodyTypeCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<BodyTypeModel> bodyTypeModels;
    ArrayList<String> listData = new ArrayList<>();

    public BodyTypeCheckAdapter(ArrayList<BodyTypeModel> bodyTypeModels, FragmentActivity activity) {

        this.context = activity;
        this.bodyTypeModels = bodyTypeModels;
    }

    @NonNull
    @Override
    public BodyTypeCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BodyTypeCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyTypeCheckAdapter.ViewHolder holder, int position) {

        BodyTypeModel education = bodyTypeModels.get(position);
        binding.showCheckbox.setText(education.getBodytype_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    listData.add(education.getBodytype_id());
                  //  notifyDataSetChanged();

                    holder.binding.showCheckbox.setChecked(true);
                  //  binding.showCheckbox.setChecked(false);

                }else{

                    listData.remove(education.getBodytype_id());
                 //   notifyDataSetChanged();

                   // binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);
                }
            }
        });

    }

    public ArrayList<String> showdata(){
        return listData;
    }


    @Override
    public int getItemCount() {
        return bodyTypeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
