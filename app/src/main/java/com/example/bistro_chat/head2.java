package com.example.bistro_chat;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class head2 extends AppCompatActivity {
    CircleImageView Header_profile;
    TextView Header_user_name, Header_user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head2);
        Header_profile = findViewById(R.id.Header_profile);
        Header_user_name = findViewById(R.id.Header_user_name);
        Header_user_email = findViewById(R.id.Header_user_email);
        Header_user_name.setText(profile.getMyData().getName());
        Header_user_email.setText(profile.getMyData().getEmail());
        Header_profile.setImageURI(Uri.parse(profile.getMyData().getPicture()));


    }
}