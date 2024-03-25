package com.service.dynamic_view.studentLayouts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

   private FirebaseAuth mAuth;
   private FirebaseUser currentUser ;

    private TextView studentnaam,wiseId,enrollmentNumber,academicYears;
    private String username,wiseid,enrollmentnumber,academicyears,studentId;

    private Boolean check= false;//Check if the user is student or not

    protected void onCreate(Bundle bun) {
        check=false;
        super.onCreate(bun);
        setContentView(R.layout.dashboard);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated

        //No internet connection back to login
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            logout(findViewById(R.id.logout));
        }


        studentnaam = findViewById(R.id.studentnaam);
        wiseId = findViewById(R.id.wiseID);
        enrollmentNumber = findViewById(R.id.enrollmentNumber);
        academicYears = findViewById(R.id.academicYears);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudentDetails();

        int hour = java.time.LocalTime.now().getHour();
        if (hour >= 0 && hour < 12) {
            wiseId.setText("Good Morning");
        } else if (hour >= 12 && hour < 16) {
            wiseId.setText("Good Afternoon");
        } else {
            wiseId.setText("Good Evening");
        }
    }

    private void getStudentDetails() {
        if (currentUser != null) {

            String userId = currentUser.getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Student").child(userId);

            //Below onDataChange online run when the loin user here is Student so that it want effect other category authenticate user
            //check userRef is exist or not

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if ( dataSnapshot.exists()) {
//                        String category = dataSnapshot.child("category").getValue().toString();
//
//                        if(category.equals("Student")){

                        studentId = dataSnapshot.child("StudentId").getValue().toString();
                        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("Student").child(studentId);

                        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    username = dataSnapshot.child("Personal Details").child("Name").getValue().toString();
                                    enrollmentnumber = dataSnapshot.child("Personal Details").child("Enrollment Number").getValue().toString();
                                    academicyears = dataSnapshot.child("Personal Details").child("Academic Year").getValue().toString();
                                    studentnaam.setText(username);
                                    enrollmentNumber.setText(enrollmentnumber);
                                    academicYears.setText(academicyears);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(dashBoard.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("Student").child(currentUser.getUid()).child("Authentication").child("Status").setValue("Offline");
                                mAuth.signOut();
                                Toast.makeText(dashBoard.this, "No user Founded", Toast.LENGTH_SHORT).show();
                            }
                        });


//                    }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(dashBoard.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Student").child(currentUser.getUid()).child("Authentication").child("Status").setValue("Offline");
                    mAuth.signOut();
                    Toast.makeText(dashBoard.this, "Try Login Again", Toast.LENGTH_SHORT).show();

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
            finish();
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }



}

