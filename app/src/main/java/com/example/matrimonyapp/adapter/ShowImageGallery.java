package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.fragment.SingleViewProfile;
import com.example.matrimonyapp.modelclass.ShowImageGallery_Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowImageGallery extends RecyclerView.Adapter<ShowImageGallery.ViewHolder> {

    Context context;
    ArrayList<ShowImageGallery_Model> showImageGallerymodels;

    public ShowImageGallery(ArrayList<ShowImageGallery_Model> showImageGallery_models,
                            FragmentActivity activity) {

        this.showImageGallerymodels = showImageGallery_models;
        this.context = activity;
    }

    @NonNull
    @Override
    public ShowImageGallery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showgallery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowImageGallery.ViewHolder holder, int position) {

        ShowImageGallery_Model gallery_model = showImageGallerymodels.get(position);

        String url = "https://collegeprojectz.com/matrimonial//uploads/"+gallery_model.getImage();
        Picasso.with(context).load(url).into(holder.profileImage);

        holder.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return showImageGallerymodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
