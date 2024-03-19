package com.service.dynamic_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.studentLayouts.dashBoard;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);



    }

    public void goToDash(View view) {
        try {
//            Intent intent = new Intent(this, dashBoard.class);
            Intent intent = new Intent(this, dashBoard.class);
            this.startActivity(intent);
            System.out.println("Kuch toh hua hai");
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }

    }

    public void goTeacherDashBoard(View view){
        try {
            Intent intent = new Intent(this, com.service.dynamic_view.teacherLayouts.teacherDashboard.class);
            this.startActivity(intent);
//            System.out.println("Kuch toh hua hai");
            //Testing Retrofit connection
//            retrofitService retrofitService = new retrofitService();
//
//            //using get post
//            studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);

//            studentAPI.getAllStudents().enqueue(new Callback<List<Student>>() {
//                @Override
//                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//                    System.out.println("This is response" + response.body());
//                }
//
//                @Override
//                public void onFailure(Call<List<Student>> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
//
//                }
//            });
        } catch (Exception e) {
            System.out.println("This is error" + e);
        }
    }




}