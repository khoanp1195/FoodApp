package com.example.foodappp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    EditText edtName,edtEmail,Edtpass;
    Button regsterbtn;
    TextView signin;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    String userID;


    private static Animation shakeAnimation;
    private static ConstraintLayout RegisterLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();



        regsterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = edtEmail.getText().toString().trim();
                String password = Edtpass.getText().toString().trim();
                final  String name = edtName.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    edtName.setError("Name is Required");
                    RegisterLayout.startAnimation(shakeAnimation);
                    vibrate(200);
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    edtEmail.setError("Email is Required.");
                    RegisterLayout.startAnimation(shakeAnimation);
                    vibrate(200);
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Edtpass.setError("Password is Required.");
                    RegisterLayout.startAnimation(shakeAnimation);
                    vibrate(200);
                    return;
                }

                if(password.length() < 6){
                    Edtpass.setError("Password Must be >= 6 Characters");
                    RegisterLayout.startAnimation(shakeAnimation);
                    vibrate(200);
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            // send verification link

                            FirebaseUser fuser = firebaseAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Email xác thực đã được gửi đến hộp thư của bản", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });

                            Toast.makeText(RegisterActivity.this, "Người dùng đã được tạo", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            user.put("password",password);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "Thành Công: Hồ sơ cá nhân đã được tạo với "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Thất Bại: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }else {
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });


                signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });



            }
        });


    }
    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    public void anhxa()
    {
        edtName =findViewById(R.id.editName);
        edtEmail = findViewById(R.id.edt_email);
        Edtpass = findViewById(R.id.edt_password);
        regsterbtn = findViewById(R.id.button);
        signin = findViewById(R.id.signin);

        //firebase
        firebaseAuth =FirebaseAuth.getInstance();
        firestore =FirebaseFirestore.getInstance();

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);

        RegisterLayout = findViewById(R.id.register_layout);



    }
}