package com.service.dynamic_view.teacherLayouts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class teacherDashboard extends AppCompatActivity{


    private TextView teacherName,wiseId,teacherCollogeId,experience;
    private String username,wiseid,teacherCollgID,teacherID;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;


    protected void onCreate(Bundle bun) {
        super.onCreate(bun);
        setContentView(R.layout.teacherdashboard);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            logoutT(findViewById(R.id.logout));
        }

        teacherName = findViewById(R.id.teacherNameID);
        wiseId = findViewById(R.id.wiseID);
        teacherCollogeId = findViewById(R.id.teacherCollogeId);
        experience = findViewById(R.id.experience);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTeacherDetails();

        int hour = java.time.LocalTime.now().getHour();
        if (hour >= 0 && hour < 12) {
            wiseId.setText("Good Morning");
        } else if (hour >= 12 && hour < 16) {
            wiseId.setText("Good Afternoon");
        } else {
            wiseId.setText("Good Evening");
        }
    }

    private void getTeacherDetails() {
      if (currentUser != null) {
          String userId = currentUser.getUid();
          DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Teacher").child(userId);
          userRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
              @Override
              public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()) {
                      teacherID= dataSnapshot.child("TeacherId").getValue().toString();
                      DatabaseReference teacherRef = FirebaseDatabase.getInstance().getReference().child("Teacher").child(teacherID);
                        teacherRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                            @Override
                            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    username = dataSnapshot.child("Personal Details").child("Name").getValue().toString();
                                    teacherCollgID = dataSnapshot.child("Personal Details").child("Teacher College Id").getValue().toString();
                                    teacherName.setText(username);
                                    teacherCollogeId.setText(teacherCollgID);
                                    experience.setText(dataSnapshot.child("Personal Details").child("Experience").getValue().toString()+" years");
                                }
                            }

                            @Override
                            public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
                            }
                        });
                  }
              }

              @Override
              public void onCancelled(com.google.firebase.database.DatabaseError databaseError) {
              }
          });



      }
    }


    public void goToAddAssignment(View view) {
        try {
            Intent intent = new Intent(this, addAssignment.class);
            intent.putExtra("teacherId",teacherID);

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



    public void logoutT(View view) {
        try {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(this, com.service.dynamic_view.MainActivity.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goTOScheduleTimeTable(View view) {
        try {
            Intent intent = new Intent(this, schedule_TimeTable.class);
            //pass teacher id
            intent.putExtra("teacherId",teacherID);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goToHolidayT(View view){
        try {
//            Intent intent = new Intent(this, holidayView.class);
//            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void goToTeachingSubject(View view){
        try {
//            Intent intent = new Intent(this, teachingSubject.class);
//            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void MarksAttendance(View view) {
        try {
            Intent intent = new Intent(this, select_class.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void StudentList(View view) {
        try {
//            Intent intent = new Intent(this, studentList.class);
//            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void addFeedback(View view) {
        try {
//            Intent intent = new Intent(this, addFeedback.class);
//            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void goToAnnouncementT(View view) {
        try {
//            Intent intent = new Intent(this, notificationT.class);
//            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }

    public void goToProfileT(View view) {
        try {
            Intent intent = new Intent(this, teacherProfile.class);
            this.startActivity(intent);
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }
}
