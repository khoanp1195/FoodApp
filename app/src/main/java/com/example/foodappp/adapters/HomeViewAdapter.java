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
import com.example.foodappp.models.HomeViewModel;

import java.util.List;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.ViewHolder> {

    private Context context;
    private List<HomeViewModel> homeViewModelList;

    public HomeViewAdapter(Context context, List<HomeViewModel> homeViewModelList) {
        this.context = context;
        this.homeViewModelList = homeViewModelList;
    }



    @NonNull
    @Override
    public HomeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(homeViewModelList.get(position).getImg_url()).into(holder.imageView);


        holder.name.setText(homeViewModelList.get(holder.getAdapterPosition()).getName());
        holder.timing.setText(homeViewModelList.get(holder.getAdapterPosition()).getTiming());
        holder.rating.setText(homeViewModelList.get(holder.getAdapterPosition()).getRating());
        holder.price.setText(homeViewModelList.get(holder.getAdapterPosition()).getPrice());





    }

    @Override
    public int getItemCount() {
        return homeViewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView name,timing, rating, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.timing);
            rating = itemView.findViewById(R.id.ratting);
            price = itemView.findViewById(R.id.price);
        }
    }
}
