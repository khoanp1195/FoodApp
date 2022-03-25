package com.example.foodappp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappp.R;
import com.example.foodappp.models.FeaturedModel;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {


    private Context context;
    private List<FeaturedModel> featuredModels;

    public FeaturedAdapter(Context context, List<FeaturedModel> featuredModels) {
        this.context = context;
        this.featuredModels = featuredModels;
    }

    @NonNull
    @Override
    public FeaturedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeaturedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_hor_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(featuredModels.get(position).getImg_url()).into(holder.featured_img);
        holder.name.setText(featuredModels.get(holder.getAdapterPosition()).getName());
        holder.description.setText(featuredModels.get(holder.getAdapterPosition()).getDescription());



    }

    @Override
    public int getItemCount() {
        return featuredModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView featured_img;
        TextView name,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            featured_img = itemView.findViewById(R.id.featured_img);
            name = itemView.findViewById(R.id.feautred_name);
            description= itemView.findViewById(R.id.feature_des);
        }
    }
}
