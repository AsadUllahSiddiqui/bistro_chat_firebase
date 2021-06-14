package com.example.bistro_chat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sign_in extends AppCompatActivity {
    TextView click_here;
    ImageButton login;
    profile p = new profile();
    EditText email, password;
    FirebaseUser firebaseuser;
    private FirebaseAuth auth;
    contact Profile;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.siusername);
        password = findViewById(R.id.sipassword);
        auth = FirebaseAuth.getInstance();
        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseuser != null) {
            contact a = new contact(firebaseuser.getEmail(), firebaseuser.getUid());
            p.setMyData(a);
            Intent i = new Intent(sign_in.this, chat_container.class);
            startActivity(i);
            finish();
        }
        configure_click_here();
        configure_login_button();
    }

    private void configure_click_here() {
        click_here = findViewById(R.id.register_now_id);
        click_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(sign_in.this, sign_up.class));
                finish();
            }
        });
    }

    private void configure_login_button() {
        login = findViewById(R.id.register_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                final String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                //checking firebase
                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(password_text)) {
                    Toast.makeText(sign_in.this, "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, password_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        firebaseuser = FirebaseAuth.getInstance().getCurrentUser();
                                        contact a = new contact(firebaseuser.getEmail(), firebaseuser.getUid());
                                        p.setMyData(a);
                                        startActivity(new Intent(sign_in.this, chat_container.class));
                                        finish();
                                    } else {
                                        Toast.makeText(sign_in.this, "No user exist please register first!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    private void getProfile() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (profile.myData.getEmail().equals(snapshot.getValue(contact.class).getEmail())) {
                        Profile = (snapshot.getValue(contact.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}