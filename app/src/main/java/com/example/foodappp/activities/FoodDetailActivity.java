package com.example.foodappp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodappp.R;
import com.example.foodappp.models.HomeViewModel;
import com.example.foodappp.models.PopularModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FoodDetailActivity extends AppCompatActivity {



    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    ImageView detailedImg,order_image;
    TextView price, rating, description,name;

    Button addToCart;
    ImageView addItem, removeItem;


    HomeViewModel homeViewModel = null;

    Toolbar toolbar;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        quantity = findViewById(R.id.quantity);

        firestore = FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();



        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof HomeViewModel){
            homeViewModel = (HomeViewModel) object;

        }


        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  addedToCart();
            }
        });



        if(homeViewModel != null){
            Glide.with(getApplicationContext()).load(homeViewModel.getImg_url()).into(detailedImg);
           // rating.setText(popularModel.getRating());
           description.setText(homeViewModel.getTiming());
            price.setText("Price: $" +homeViewModel.getPrice()+"/kg");
            name.setText(homeViewModel.getName());
            rating.setText(homeViewModel.getRating());



         //   totalPrice = viewAllModel.getPrice() * totalQuantity;


//            if (viewAllModel.getType().equals("wine")){
//                price.setText("Price: $" +viewAllModel.getPrice()+"/chai");
//                totalPrice = viewAllModel.getPrice() * totalQuantity;
//            }
//            if (viewAllModel.getType().equals("milk")){
//                price.setText("Price: $" +viewAllModel.getPrice()+"/litter");
//                totalPrice = viewAllModel.getPrice() * totalQuantity;
//            }
        }

        price =findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_des);
        name = findViewById(R.id.name);




//        addItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (totalQuantity < 10){
//                    totalQuantity++;
//                    quantity.setText(String.valueOf(totalQuantity));
//                    totalPrice = viewAllModel.getPrice() * totalQuantity;
//                }
//
//            }
//        });
//
//        removeItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (totalQuantity > 1){
//                    totalQuantity--;
//                    quantity.setText(String.valueOf(totalQuantity));
//                    totalPrice = viewAllModel.getPrice() * totalQuantity;
//                }
//
//            }
//        });

    }


}