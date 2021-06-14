package com.example.bistro_chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class welcome_page extends AppCompatActivity {
    ImageButton sign_in, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        configure_sign_in_button();
        configure_register_button();
    }

    private void configure_sign_in_button() {
        sign_in = findViewById(R.id.sign_in_button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(welcome_page.this, sign_in.class));
            }
        });
    }

    private void configure_register_button() {
        register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(welcome_page.this, sign_up.class));
            }
        });
    }

}