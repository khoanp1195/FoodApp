package com.example.foodappp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodappp.R;
import com.example.foodappp.adapters.HomeHorAdapter;
import com.example.foodappp.adapters.HomeViewAdapter;
import com.example.foodappp.models.HomeHorModel;
import com.example.foodappp.models.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class NewFoodFragment extends Fragment {


    List<HomeViewModel> homeViewModelList;
    HomeViewAdapter homeViewAdapter;


    List<HomeHorModel> homeHorModels;
    HomeHorAdapter homeHorAdapter;


    RecyclerView homeHorizontalRec,homeVerticalRec,popularRec;




    //Firebase Firestore
    FirebaseFirestore db;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseAuth fAuth;
    String userId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_new_food, container, false);


        db= FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        homeVerticalRec = root.findViewById(R.id.home_view_rec);
        homeViewModelList = new ArrayList<>();
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));






        //Home View
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        homeViewModelList = new ArrayList<>();
        homeViewAdapter = new HomeViewAdapter(getActivity(), homeViewModelList);
        homeVerticalRec.setAdapter(homeViewAdapter);
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setNestedScrollingEnabled(false);



        db.collection("homeview")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeViewModel homeViewModel = document.toObject(HomeViewModel.class);

                                homeViewModelList.add(homeViewModel);
                                homeViewAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });





        return root;



    }
}