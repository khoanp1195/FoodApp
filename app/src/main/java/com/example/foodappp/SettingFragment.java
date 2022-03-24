package com.example.foodappp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodappp.activities.WelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingFragment extends Fragment {

    private FirebaseAuth mAuth;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_setting, container, false );
        TextView logout = view.findViewById( R.id.layout_logout );
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getResources().getString(R.string.question_logout));
                builder.setPositiveButton(getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mAuth.signOut();
                                        checkUserStatus();

                                    }
                                });
                builder.create().show();
            }
        } );
        return view;
    }

    private void checkUserStatus() {
        startActivity(new Intent(getContext(), WelcomeActivity.class));
    }
    }
