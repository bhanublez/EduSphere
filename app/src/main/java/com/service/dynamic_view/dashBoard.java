package com.service.dynamic_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class dashBoard extends AppCompatActivity{
    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.dashboard);
    }
    public void goTOAssignment(View view) {
        try {
            Intent intent = new Intent(this, addAssignment.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToExam(View view){
        try {
            Intent intent = new Intent(this, addExam.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void oToTeacherList(View view){
        try {
            Intent intent = new Intent(this, TeacherView.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }
}

