package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.databinding.ShowcheckboxrecyBinding;
import com.example.matrimonyapp.modelclass.EducationModel;

import java.util.ArrayList;

public class EductionCheckAdapter extends RecyclerView.Adapter<EductionCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<EducationModel> educationModels;
    ArrayList<String> listData = new ArrayList<>();

    public EductionCheckAdapter(ArrayList<EducationModel> educationModels, FragmentActivity activity) {

        this.context = activity;
        this.educationModels = educationModels;

    }

    @NonNull
    @Override
    public EductionCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EductionCheckAdapter.ViewHolder holder, int position) {

        EducationModel education = educationModels.get(position);
        binding.showCheckbox.setText(education.getEducation_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                  //  binding.showCheckbox.setChecked(false);

                    listData.add(education.getEducation_id());
                //    notifyDataSetChanged();

                }else{

                   // binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getEducation_id());
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
        return educationModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
