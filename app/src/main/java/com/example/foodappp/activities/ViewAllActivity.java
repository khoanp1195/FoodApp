package com.example.foodappp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.foodappp.R;
import com.example.foodappp.adapters.ViewAllAdapter;
import com.example.foodappp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {


    FirebaseFirestore firestore;

    RecyclerView recyclerView;

    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firestore = FirebaseFirestore.getInstance();


        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.detail_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);

        recyclerView.setAdapter(viewAllAdapter);



        //getting ice cream
        if(type != null && type.equalsIgnoreCase("cream")){
            firestore.collection("AllProducts").whereEqualTo("type","cream").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }


        //getting pizza
        if(type != null && type.equalsIgnoreCase("pizza")){
            firestore.collection("AllProducts").whereEqualTo("type","pizza").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }
            });
        }


    }
}