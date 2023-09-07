package com.example.matrimonyapp.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matrimonyapp.R;
import com.example.matrimonyapp.modelclass.SuccessStories_model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SuccessStoriesAdapter extends RecyclerView.Adapter<SuccessStoriesAdapter.ViewHolder> {

    ArrayList<SuccessStories_model> successStoriesModels;
    Context context;

    public SuccessStoriesAdapter(Context context, ArrayList<SuccessStories_model> successStoriesModels) {

        this.context = context;
        this.successStoriesModels = successStoriesModels;
    }

    @NonNull
    @Override
    public SuccessStoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.successstories,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuccessStoriesAdapter.ViewHolder holder, int position) {

        SuccessStories_model success = successStoriesModels.get(position);

        String url = "https://collegeprojectz.com/matrimonial//uploads/"+success.getImage();
        Picasso.with(context).load(url).into(holder.imag_uniform);

        holder.textname.setText(success.getName());
        holder.texttitle.setText(success.getTitle());
        holder.textmessage.setText(Html.fromHtml(success.getMessage()));

    }

    @Override
    public int getItemCount() {
        return successStoriesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imag_uniform;
        TextView textname,texttitle,textmessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imag_uniform = itemView.findViewById(R.id.imag_uniform);
            textname = itemView.findViewById(R.id.textname);
            texttitle = itemView.findViewById(R.id.texttitle);
            textmessage = itemView.findViewById(R.id.textmessage);
        }
    }
}
