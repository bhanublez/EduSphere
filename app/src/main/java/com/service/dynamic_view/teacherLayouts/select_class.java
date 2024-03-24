package com.service.dynamic_view.teacherLayouts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.service.dynamic_view.R;

public class select_class extends AppCompatActivity {

    String branch, semester, section;
    Spinner branchSpinner, semesterSpinner, sectionSpinner;
    Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_class_attendance);
        branchSpinner = findViewById(R.id.branch);
    semesterSpinner = findViewById(R.id.semester);
    sectionSpinner = findViewById(R.id.section);
    submit = findViewById(R.id.search);
        back=findViewById(R.id.backArrowOfAm);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }

        submit.setOnClickListener(v -> {
        branch = branchSpinner.getSelectedItem().toString();
        semester = semesterSpinner.getSelectedItem().toString();
        section = sectionSpinner.getSelectedItem().toString();
        if(currentUser!= null) {
            Intent intent = new Intent(select_class.this, studentList.class);
            intent.putExtra("branch", branch);
            intent.putExtra("semester", semester);
            intent.putExtra("section", section);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please Login Again", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }
    });



        back.setOnClickListener(v -> {
            finish();
        }  );


    }
}
