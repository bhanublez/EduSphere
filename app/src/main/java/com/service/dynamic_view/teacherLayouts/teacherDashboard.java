package com.service.dynamic_view.teacherLayouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class teacherDashboard extends AppCompatActivity{

    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.teacherdashboard);


    }

    public void goToAddAssignment(View view) {
        try {
            Intent intent = new Intent(this, addAssignment.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToAddExam(View view){
        try {
            Intent intent = new Intent(this, addExam.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }
}
