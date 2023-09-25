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
import com.example.matrimonyapp.modelclass.ComplexionModel;
import com.example.matrimonyapp.modelclass.EducationModel;

import java.util.ArrayList;

public class ComplexionCheckAdapter extends RecyclerView.Adapter<ComplexionCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<ComplexionModel> complexionModels;
    ArrayList<String> listData = new ArrayList<>();

    public ComplexionCheckAdapter(ArrayList<ComplexionModel> complexionModels, FragmentActivity activity) {

        this.context = activity;
        this.complexionModels = complexionModels;
    }

    @NonNull
    @Override
    public ComplexionCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ComplexionCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplexionCheckAdapter.ViewHolder holder, int position) {

        ComplexionModel education = complexionModels.get(position);
        binding.showCheckbox.setText(education.getComlexion_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                  //  binding.showCheckbox.setChecked(false);

                    listData.add(education.getComplexion_id());
                 //   notifyDataSetChanged();

                }else{

                    // binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getComplexion_id());
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
        return complexionModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
