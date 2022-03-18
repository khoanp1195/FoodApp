package com.example.foodappp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodappp.R;
import com.example.foodappp.activities.ViewAllActivity;
import com.example.foodappp.models.DailyMealModel;

import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.ViewHolder> {

        private Context context;
        private List<DailyMealModel> dailyMealModelList;

    public DailyMealAdapter(Context context, List<DailyMealModel> dailyMealModelList) {
        this.context = context;
        this.dailyMealModelList = dailyMealModelList;
    }

    @NonNull
    @Override
    public DailyMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyMealAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(dailyMealModelList.get(position).getImg_url()).into(holder.imageView);

        holder.name.setText(dailyMealModelList.get(holder.getAdapterPosition()).getName());
        holder.description.setText(dailyMealModelList.get(holder.getAdapterPosition()).getDescription());
        holder.discount.setText(dailyMealModelList.get(holder.getAdapterPosition()).getDiscount());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",dailyMealModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dailyMealModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description, discount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.descripton);
            discount = itemView.findViewById(R.id.discount);
        }
    }
}
