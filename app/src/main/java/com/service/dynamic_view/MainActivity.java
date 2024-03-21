package com.service.dynamic_view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.service.dynamic_view.studentLayouts.dashBoard;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry); // It acts as a splash screen

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Entry acts as a splash screen. After a few seconds, start the sign-in page.
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000); // Sleep for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Start the login activity after the delay

                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                    finish(); // Finish the current activity to prevent going back to it when pressing back from login
                }
            }
        };

        // Start the thread
        thread.start();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    if(mAuth.getCurrentUser()!=null) {
                        Intent intent = new Intent(MainActivity.this, dashBoard.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
    }




//    public void goToDash(View view) {
//        try {
////            Intent intent = new Intent(this, dashBoard.class);
//            Intent intent = new Intent(this, dashBoard.class);
//            this.startActivity(intent);
//            System.out.println("Kuch toh hua hai");
//        } catch (Exception e) {
//            System.out.println("This is error" + e);
//        }
//
//    }

//    public void goTeacherDashBoard(View view){
//        try {
//            Intent intent = new Intent(this, com.service.dynamic_view.teacherLayouts.teacherDashboard.class);
//            this.startActivity(intent);
////            System.out.println("Kuch toh hua hai");
//            //Testing Retrofit connection
////            retrofitService retrofitService = new retrofitService();
////
////            //using get post
////            studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);
//
////            studentAPI.getAllStudents().enqueue(new Callback<List<Student>>() {
////                @Override
////                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
////                    System.out.println("This is response" + response.body());
////                }
////
////                @Override
////                public void onFailure(Call<List<Student>> call, Throwable t) {
////                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
////
////                }
////            });
//        } catch (Exception e) {
//            System.out.println("This is error" + e);
//        }
//    }




}