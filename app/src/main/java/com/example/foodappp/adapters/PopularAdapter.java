package com.example.foodappp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappp.R;
import com.example.foodappp.activities.FoodDetailActivity;
import com.example.foodappp.models.PopularModel;

import java.io.Serializable;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    Context context;
  List<PopularModel> list;

    public PopularAdapter(Context context, List<PopularModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_food_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.foodname.setText(list.get(position).getName());
      //  holder.foodprice.setText(list.get(position).getPrice());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("detail ", list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView foodname,foodprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.food_image);
            foodname = itemView.findViewById(R.id.food_name);
            foodprice = itemView.findViewById(R.id.food_price);
        }
    }
}
