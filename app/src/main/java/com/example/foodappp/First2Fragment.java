package com.example.foodappp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappp.adapters.FeatureVerticalAdapter;
import com.example.foodappp.adapters.FeaturedAdapter;
import com.example.foodappp.databinding.FragmentFirst2Binding;
import com.example.foodappp.models.FeaturedModel;
import com.example.foodappp.models.featuredvermodel;
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

public class First2Fragment extends Fragment {



    RecyclerView featured_rec,featured_ver_rec;

    List<featuredvermodel> featuredvermodels;
    FeatureVerticalAdapter featureVerticalAdapter;


    //Firebase Firestore
    FirebaseFirestore db;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseUser user;
    FirebaseAuth fAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.featured_fragment, container, false);


        featured_rec = root.findViewById(R.id.featured_rec);
        db= FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        featured_ver_rec = root.findViewById(R.id.featured_ver_rec);


        //Vertical Featured
        featured_ver_rec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        featuredvermodels = new ArrayList<>();
        featureVerticalAdapter = new FeatureVerticalAdapter(getActivity(), featuredvermodels);
        featured_ver_rec.setAdapter(featureVerticalAdapter);

        db.collection("Featured_Vertical")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                featuredvermodel  featuredvermodel = document.toObject(featuredvermodel.class);

                                featuredvermodels.add(featuredvermodel);
                                featureVerticalAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });






        return  root;
    }



}