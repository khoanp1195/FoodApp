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
import com.example.foodappp.models.HomeHorModel;
import com.example.foodappp.models.HomeViewModel;
import com.example.foodappp.models.PopularModel;
import com.example.foodappp.models.ViewAllModel;
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

    ViewAllModel viewAllModel = null;
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


        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;

        }





        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price =findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_des);
        name = findViewById(R.id.name);

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  addedToCart();
            }
        });




        if( viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            price.setText(viewAllModel.getTiming());
            description.setText(viewAllModel.getDescription());
            //price.setText("Price: $" + viewAllModel.getPrice()+"/kg");
            name.setText(viewAllModel.getName());
            rating.setText(viewAllModel.getRating());


        }






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