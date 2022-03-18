package com.example.foodappp.ui.home;

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
import com.example.foodappp.adapters.HomeHorAdapter;
import com.example.foodappp.adapters.HomeViewAdapter;
import com.example.foodappp.databinding.FragmentHomeBinding;
import com.example.foodappp.models.DailyMealModel;
import com.example.foodappp.models.HomeHorModel;
import com.example.foodappp.models.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecyclerView homeHorizontalRec,homeVerticalRec;





    List<HomeViewModel> homeViewModelList;
    HomeViewAdapter homeViewAdapter;


    List<HomeHorModel> homeHorModels;
    HomeHorAdapter homeHorAdapter;



    //Firebase Firestore
    FirebaseFirestore db;
    StorageReference storageReference;
    FirebaseFirestore fStore;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);



        db= FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();




        homeVerticalRec = root.findViewById(R.id.home_view_rec);
        homeViewModelList = new ArrayList<>();
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));



        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeHorModels = new ArrayList<>();
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));


        //Home Category
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorModels = new ArrayList<>();
        homeHorAdapter = new HomeHorAdapter(getActivity(), homeHorModels);
        homeHorizontalRec.setAdapter(homeHorAdapter);



        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeHorModel homeHorModel = document.toObject(HomeHorModel.class);

                                homeHorModels.add(homeHorModel);
                                homeHorAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




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