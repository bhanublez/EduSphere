package com.service.dynamic_view.teacherLayouts;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class studentList extends AppCompatActivity{
    private String branch, semester, section;

    private DatabaseReference mDatabase;
    private  FirebaseAuth mAuth;
    private ConstraintLayout layout;
    private ImageView back;

    private String naam, enrl;

    private FirebaseUser currentUser ;
    AlertDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_student_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated

        branch = getIntent().getStringExtra("branch");
        semester = getIntent().getStringExtra("semester");
        section = getIntent().getStringExtra("section");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();



        buildLayout();


    }

    private void buildLayout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.student_attendance_struct, null);
        final TextView enrl = view.findViewById(R.id.enrl);
        final TextView name = view.findViewById(R.id.stdnaam);

        layout = findViewById(R.id.container);
        String userid= currentUser.getUid();
        DatabaseReference ref = mDatabase.child("studentAttendance").child(branch).child(semester).child(section).child("attendance");

        //Iterate ref to get all the students










    }


}
