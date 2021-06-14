package com.example.bistro_chat;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_profile extends AppCompatActivity {
    TextView name, number, gender_age, bio;
    CircleImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        name = findViewById(R.id.user_name);
        number = findViewById(R.id.user_number);
        gender_age = findViewById(R.id.user_gender_age);
        bio = findViewById(R.id.user_bio);
        pic = findViewById(R.id.user_pic);
        name.setText(profile.getMyData().getName());
        number.setText(profile.getMyData().getNumber());
        bio.setText(profile.getMyData().getBio());
        String x = profile.getMyData().getGender();
        x += ",";
        x += profile.getMyData().getAge();
        gender_age.setText(x);
        pic.setImageURI(Uri.parse(profile.getMyData().getPicture()));
    }
}