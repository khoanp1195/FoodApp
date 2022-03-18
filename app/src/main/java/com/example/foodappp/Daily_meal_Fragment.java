package com.example.foodappp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodappp.adapters.DailyMealAdapter;
import com.example.foodappp.adapters.HomeHorAdapter;
import com.example.foodappp.adapters.HomeViewAdapter;
import com.example.foodappp.models.DailyMealModel;
import com.example.foodappp.models.HomeHorModel;
import com.example.foodappp.models.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Daily_meal_Fragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_daily_meal_, container, false);



        return  root;
    }
}