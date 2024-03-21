package com.service.dynamic_view.studentLayouts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;
import com.service.dynamic_view.Universal.feedBack;
import com.service.dynamic_view.Universal.holidayView;

public class dashBoard extends AppCompatActivity{

    FirebaseAuth mAuth;
    FirebaseUser currentUser ;

    TextView studentnaam,wiseId,enrollmentNumber,academicYears;

    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.dashboard);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated


        if (currentUser != null) {
            String userId = currentUser.getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Student").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
//                        String userName = dataSnapshot.child(String.valueOf(currentUser)).child("Personal Details").child("Name");

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }


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

    public void goToTimeTableStudent(View view) {
        try {
            Intent intent = new Intent(this, studentTable.class);
//            Toast.makeText(this, "Chala yeh toh", Toast.LENGTH_SHORT).show();
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void goToPassChange(View view) {
        try {
            Intent intent = new Intent(this, passChange.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToAttendance(View view) {
        try {
            Intent intent = new Intent(this, attendanceMark.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToAnnouncement(View view) {
        try {
            Intent intent = new Intent(this, notificationS.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToFeedBack(View view) {
        try {
            Intent intent = new Intent(this, feedBack.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }
    public void goToHoliday(View view){
        try {
            Intent intent = new Intent(this, holidayView.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void logout(View view){
        try {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(this, com.service.dynamic_view.MainActivity.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }


}

