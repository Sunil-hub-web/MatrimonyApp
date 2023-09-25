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
import com.example.matrimonyapp.modelclass.LagnaModel;

import java.util.ArrayList;

public class LagnaCheckAdapter extends RecyclerView.Adapter<LagnaCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<LagnaModel> lagnaModels;
    ArrayList<String> listData = new ArrayList<>();

    public LagnaCheckAdapter(ArrayList<LagnaModel> lagnaModels, FragmentActivity activity) {

        this.context = activity;
        this.lagnaModels = lagnaModels;
    }

    @NonNull
    @Override
    public LagnaCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LagnaCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LagnaCheckAdapter.ViewHolder holder, int position) {

        LagnaModel education = lagnaModels.get(position);
        binding.showCheckbox.setText(education.getLagna_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                 //   binding.showCheckbox.setChecked(false);

                    listData.add(education.getLagna_id());
                //    notifyDataSetChanged();

                }else{

                   // binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getLagna_id());
                //    notifyDataSetChanged();
                }
            }
        });

    }

    public ArrayList<String> showdata(){
        return listData;
    }

    @Override
    public int getItemCount() {
        return lagnaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
