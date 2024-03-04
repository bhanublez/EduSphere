package com.service.dynamic_view.studentLayouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class dashBoard extends AppCompatActivity{

    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.dashboard);

    }
    public void goTOAssignment(View view) {
        try {
            Intent intent = new Intent(this, assignmentView.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToExam(View view){
        try {
            Intent intent = new Intent(this, examView.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }


    public void goToTeacherList(View view) {

        try {
            Intent intent = new Intent(this, TeacherView.class);
//            Toast.makeText(this, "Chala yeh toh", Toast.LENGTH_SHORT).show();
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }
    public void goToSubjectList(View view){
        try {

            Intent intent = new Intent(this, subjectView.class);
//            Toast.makeText(this, "Chala yeh toh", Toast.LENGTH_SHORT).show();
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void goToProfile(View view){
        try {
            Intent intent = new Intent(this, profileView.class);
            Toast.makeText(this, "Chala yeh toh", Toast.LENGTH_SHORT).show();

            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }
}

