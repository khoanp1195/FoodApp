package com.example.foodappp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappp.R;
import com.example.foodappp.adapters.DailyMealAdapter;
import com.example.foodappp.models.DailyMealModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class DailyMealFragment extends Fragment {


    RecyclerView dailymealrec;


    List<DailyMealModel> dailyMealModelList;
    DailyMealAdapter dailyMealAdapter;


    //Firebase Firestore
    FirebaseFirestore db;
    StorageReference storageReference;
    FirebaseFirestore fStore;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


       View root = inflater.inflate(R.layout.fragment_daily_meal_, container, false);

        db= FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        dailymealrec = root.findViewById(R.id.daily_meal_rec);
        dailyMealModelList = new ArrayList<>();
        dailymealrec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));



        //Daily Meal
        dailymealrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        dailyMealModelList = new ArrayList<>();
        dailyMealAdapter = new DailyMealAdapter(getActivity(), dailyMealModelList);
        dailymealrec.setAdapter(dailyMealAdapter);

        db.collection("daily")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                DailyMealModel dailyMealModel = document.toObject(DailyMealModel.class);

                                dailyMealModelList.add(dailyMealModel);
                                dailyMealAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




        return root;
    }

}