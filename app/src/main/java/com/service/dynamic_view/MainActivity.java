package com.service.dynamic_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);


    }

    public void goToDash(View view) {
        try {
            Intent intent = new Intent(this, dashBoard.class);
            this.startActivity(intent);
            System.out.println("Kuch toh hua hai");
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }




}