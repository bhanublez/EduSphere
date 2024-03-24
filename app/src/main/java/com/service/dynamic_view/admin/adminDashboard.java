package com.service.dynamic_view.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class adminDashboard extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindasboard);

         Button btnAddStudent = findViewById(R.id.btnAddStudent);
         Button btnAddTeacher = findViewById(R.id.btnAddTeacher);
         Button modifyTimetable = findViewById(R.id.btnModifyTimetable);
         Button addNotice = findViewById(R.id.btnAddNotification);
         Button readFeedback = findViewById(R.id.btnReadFeedback);
         Button modifyAssignment = findViewById(R.id.btnModifyAssignment);
         Button modifyAttendance = findViewById(R.id.btnModifyAttendance);
         Button modifyResult = findViewById(R.id.btnModifyResult);
         Button studentPasswordManagement = findViewById(R.id.btnStudentPasswordManager);
         Button teacherPasswordManagement = findViewById(R.id.btnTeacherPasswordManager);
         Button holidayManagement = findViewById(R.id.btnHolidaysManagement);
         Button modifyExam = findViewById(R.id.btnModifyExams);
         Button btnAddSubject = findViewById(R.id.btnAddSubject);
         Button modifySubject = findViewById(R.id.btnModifyStudent);
         Button mdoifyTeacher = findViewById(R.id.btnModifyTeacher);


         btnAddTeacher.setOnClickListener(v -> {
             try{
                      Intent intent = new Intent(this, addTeacher.class);
                      this.startActivity(intent);

                 }catch (Exception e){
                      System.out.println("This is error" + e);
                 }
         });

            btnAddStudent.setOnClickListener(v -> {
                try{
                    Intent intent = new Intent(this, addStudent.class);
                    this.startActivity(intent);

                }catch (Exception e){
                    System.out.println("This is error" + e);
                }
            });

            modifyTimetable.setOnClickListener(v -> {
                // Modify Timetable
            });

            addNotice.setOnClickListener(v -> {
                // Add Notice
            });

            readFeedback.setOnClickListener(v -> {
                // Read Feedback
            });

            modifyAssignment.setOnClickListener(v -> {
                // Modify Assignment
            });

            modifyAttendance.setOnClickListener(v -> {
                // Modify Attendance
            });


            modifyResult.setOnClickListener(v -> {
                // Modify Result
            });

            studentPasswordManagement.setOnClickListener(v -> {
                // Student Password Management
            });

            teacherPasswordManagement.setOnClickListener(v -> {
                // Teacher Password Management
            });

            holidayManagement.setOnClickListener(v -> {
                // Holiday Management
            });

            modifyExam.setOnClickListener(v -> {
                // Modify Exam
            });

            btnAddSubject.setOnClickListener(v -> {
                try{
                    Intent intent = new Intent(this, addSubject.class);
                    this.startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(this, "Error Encounter "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });




    }
}
