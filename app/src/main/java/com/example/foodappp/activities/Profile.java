package com.example.foodappp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.foodappp.EditProfile;
import com.example.foodappp.MainActivity;
import com.example.foodappp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class Profile extends AppCompatActivity {

    /*------------------------------------Drawer Menu-------------------------- */

    /*------------------Login----------------------------------------*/
   // private static final int GALLERY_INTENT_CODE = 1023;
    TextView  email, phone, verifyMsg,adress,job,tv_name,tv_adress,reset,edit;
    FirebaseAuth fAuth;
    EditText fullName;
    FirebaseFirestore fStore;
    String userId;
    Button resendCode,persona,profileinfo;
    Button resetPassLocal, changeProfileImage;
    FirebaseUser user;
    ImageView profileImageView;
    StorageReference storageReference;


    /*------------------Bottom NavigationView----------------------------------------*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.tv_name);
        email = findViewById(R.id.profileEmailAddress);
        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                intent.putExtra("name", fullName.getText().toString());
                intent.putExtra("email", email.getText().toString());
                startActivity(intent);
            }
        });


        reset = findViewById(R.id.reset);
        profileImageView = findViewById(R.id.profileImageView);


//        profileinfo = findViewById(R.id.profileinfo);
//        profileinfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // open gallery
//                Intent i = new Intent(v.getContext(), ProfileInfo.class);
//                i.putExtra("fullName", fullName.getText().toString());
//                i.putExtra("email", email.getText().toString());
//                i.putExtra("phone", phone.getText().toString());
//                i.putExtra("job", job.getText().toString());
//                i.putExtra("adress", adress.getText().toString());
//
//
//                startActivity(i);
////
//
//            }
//        });





//        persona= findViewById(R.id.persona);
//        persona.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // open gallery
//                Intent i = new Intent(v.getContext(), EditProfile.class);
//                i.putExtra("fullName", fullName.getText().toString());
//                i.putExtra("email", email.getText().toString());
//                i.putExtra("phone", phone.getText().toString());
//                i.putExtra("job", job.getText().toString());
//                i.putExtra("adress", adress.getText().toString());
//
//                startActivity(i);
////
//
//            }
//        });

       fAuth = FirebaseAuth.getInstance();
       fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImageView);
            }
        });





        DocumentReference documentReference1 = fStore.collection("users").document(userId);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    fullName.setText(documentSnapshot.getString("name"));
                    email.setText(documentSnapshot.getString("email"));



                } else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });









        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetPassword = new EditText(v.getContext());

                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter New Password > 6 Characters long.");
                passwordResetDialog.setView(resetPassword);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String newPassword = resetPassword.getText().toString();
                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Profile.this, "Password Reset Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Profile.this, "Password Reset Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close
                    }
                });

                passwordResetDialog.create().show();

            }
        });






        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.Profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.Profile:
                        return true;


                        /*
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.feedback:
                        startActivity(new Intent(getApplicationContext(), ReportProblemActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                         */
                }
                return false;
            }
        });
        /*------------------------------------------Bottom Navigation View----------------------------------------------------------------------*/






/*-----------------------drawer menu-----------------------------------------------------------------/*




    }
/*
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

 */
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goToMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToMainActivity() {
        Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentMainActivity);
    }
//
//    public void logout(View view) {
//        FirebaseAuth.getInstance().signOut();//logout
//        final android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(Profile.this);
//        builder.setIcon(R.drawable.back1);
//        builder.setTitle("Th??ng b??o");
//        builder.setMessage("B???n c?? ch???c mu???n tho??t hay kh??ng?");
//        builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent =  new Intent(Profile.this, Sanh.class);
//                startActivity(intent);
//                fAuth.signOut();
//                finish();
//            }
//        });
//        builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
//
//        builder.show();
//    }




}