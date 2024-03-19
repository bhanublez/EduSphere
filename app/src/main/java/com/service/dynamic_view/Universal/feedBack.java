package com.service.dynamic_view.Universal;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class feedBack extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userfeedback);
        ImageView back = findViewById(R.id.back_icon);
//        String feedback=(String) findViewById(R.id.editTextFeedback).toString();
//        String email=(String) findViewById(R.id.editTextEmail).toString();
        back.setOnClickListener(v -> {
            finish();
        }  );


    }
}
