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
import com.example.matrimonyapp.modelclass.RashiModel;

import java.util.ArrayList;

public class RashiCheckAdapter extends RecyclerView.Adapter<RashiCheckAdapter.ViewHolder> {

    ShowcheckboxrecyBinding binding;
    Context context;
    ArrayList<RashiModel> rashiModels;
    ArrayList<String> listData = new ArrayList<>();

    public RashiCheckAdapter(ArrayList<RashiModel> rashiModels, FragmentActivity activity) {

        this.context = activity;
        this.rashiModels = rashiModels;
    }

    @NonNull
    @Override
    public RashiCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ShowcheckboxrecyBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RashiCheckAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RashiCheckAdapter.ViewHolder holder, int position) {

        RashiModel education = rashiModels.get(position);
        binding.showCheckbox.setText(education.getRashi_name());

        holder.binding.showCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    holder.binding.showCheckbox.setChecked(true);
                 //   binding.showCheckbox.setChecked(false);

                    listData.add(education.getRashi_id());
                //    notifyDataSetChanged();

                }else{

                  //  binding.showCheckbox.setChecked(true);
                    holder.binding.showCheckbox.setChecked(false);

                    listData.remove(education.getRashi_id());
               //     notifyDataSetChanged();

                }
            }
        });
    }

    public ArrayList<String> showdata(){
        return listData;
    }

    @Override
    public int getItemCount() {
        return rashiModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShowcheckboxrecyBinding binding;

        public ViewHolder(@NonNull ShowcheckboxrecyBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
