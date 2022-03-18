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
import com.example.foodappp.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

   private Context context;
    private  List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_daily_meal_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);

        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.rating.setText(list.get(position).getDescription());
      //  holder.price.setText(list.get(position).getPrice());
        holder.timing.setText(list.get(position).getTiming());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, description,price, timing, rating;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            description = itemView.findViewById(R.id.detailed_desc);
            price = itemView.findViewById(R.id.detailed_price);
            timing= itemView.findViewById(R.id.detailed_timing);
            rating = itemView.findViewById(R.id.detailed_rating);
        }
    }
}
