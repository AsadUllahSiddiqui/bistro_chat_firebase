package com.example.bistro_chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class contact_profile extends AppCompatActivity {
    CircleImageView cv;
    TextView bio, numb, nam, age, gend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
        cv = findViewById(R.id.contact_profile_img);
        bio = findViewById(R.id.contact_bio);
        numb = findViewById(R.id.profile_user_number);
        nam = findViewById(R.id.profile_user_name);
        age = findViewById(R.id.profile_user_age);
        gend = findViewById(R.id.profile_user_gender);
        Intent i = getIntent();
        bio.setText(i.getStringExtra(chatting_terminal.bioj));
        numb.setText(i.getStringExtra(chatting_terminal.numberj));
        nam.setText(i.getStringExtra(chatting_terminal.first_namej));
        age.setText(i.getStringExtra(chatting_terminal.agej));
        gend.setText(i.getStringExtra(chatting_terminal.genderj));
        numb.setText(i.getStringExtra(chatting_terminal.numberj));
        cv.setImageURI(Uri.parse(i.getStringExtra(chatting_terminal.urij)));


    }
}