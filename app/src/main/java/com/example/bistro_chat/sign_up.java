package com.example.bistro_chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_up extends AppCompatActivity {
    public static final String emails = "key1";
    public static final String ids = "key2";
    EditText email, password, reEnter_password;
    Context c;
    TextView click_here;
    ImageButton register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        reEnter_password = findViewById(R.id.reEnter_password);
        configure_click_here();
        configure_register_button();
    }

    private void configure_click_here() {
        click_here = findViewById(R.id.Sign_in_here);
        click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(sign_up.this, sign_in.class));
                finish();
            }
        });
    }

    private void configure_register_button() {
        register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                        password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            String userEmail = firebaseUser.getEmail();
                            Intent intent = new Intent(sign_up.this, create_profile.class);
                            intent.putExtra(emails, userEmail);
                            intent.putExtra(ids, userId);
                            Toast.makeText(sign_up.this, "successfully to register",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(sign_up.this, "Failed to Register!",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(sign_up.this, currentUser.getUid() + "",
                    Toast.LENGTH_SHORT).show();
        }
    }
}