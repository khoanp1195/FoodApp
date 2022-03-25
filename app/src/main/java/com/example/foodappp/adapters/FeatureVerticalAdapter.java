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
import com.example.foodappp.models.featuredvermodel;

import java.util.List;

public class FeatureVerticalAdapter extends RecyclerView.Adapter<FeatureVerticalAdapter.ViewHolder> {

    private Context context;
    private List<featuredvermodel> featuredvermodels;

    public FeatureVerticalAdapter(Context context, List<featuredvermodel> featuredvermodels) {
        this.context = context;
        this.featuredvermodels = featuredvermodels;
    }

    @NonNull
    @Override
    public FeatureVerticalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeatureVerticalAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_ver_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureVerticalAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(featuredvermodels.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(featuredvermodels.get(holder.getAdapterPosition()).getName());
        holder.desc.setText(featuredvermodels.get(holder.getAdapterPosition()).getDescription());
        holder.rating.setText(featuredvermodels.get(holder.getAdapterPosition()).getRating());
        holder.time.setText(featuredvermodels.get(holder.getAdapterPosition()).getTime());

    }

    @Override
    public int getItemCount() {
        return featuredvermodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,desc,rating,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            desc = itemView.findViewById(R.id.detailed_desc);
            rating = itemView.findViewById(R.id.detailed_rating);
            time = itemView.findViewById(R.id.detailed_timing);
        }
    }
}
