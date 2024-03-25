package com.service.dynamic_view.teacherLayouts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class timeslot extends AppCompatActivity {
   private LinearLayout layout;
   private ImageView back;
    private ImageView add;

    private TextView tabMon, tabTue, tabWed, tabThu, tabFri, tabSat, tabSun;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private String branch, semester, section, subject,studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeslot);
        layout= findViewById(R.id.tableContainer);
        back=findViewById(R.id.back_icon);
        add = findViewById(R.id.add_Slot);
        add.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        studentId=getIntent().getStringExtra("studentId");
        branch=getIntent().getStringExtra("branch");
        semester=getIntent().getStringExtra("semester");
        section=getIntent().getStringExtra("section");


        tabMon = findViewById(R.id.tabMon);
        tabTue = findViewById(R.id.tabTue);
        tabWed = findViewById(R.id.tabWed);
        tabThu = findViewById(R.id.tabThu);
        tabFri = findViewById(R.id.tabFri);
        tabSat = findViewById(R.id.tabSat);
        tabSun = findViewById(R.id.tabSun);


        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }
    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye
//
//        String subjectName, teacherName,startTime, endTime, subjectCode;
//        int period;
//        //Sample inputs
//        subjectName = "Teacher Naam";
//        teacherName = "Subject Naam";
//        startTime = "2021-12-12";
//        endTime = "2021-12-12";
//        subjectCode = "CS 900";
//        period = 2;
//        addCard(subjectName, teacherName, startTime,endTime, subjectCode, period);
//        Toast.makeText(this, branch+ " "+semester+" "+section, Toast.LENGTH_SHORT).show();
        DatabaseReference studentList= mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(section).child("timetableSchema").child("studentList");
        studentList.child(studentId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Data Fetched", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addCard(String subjectName, String teacherName, String startTime, String endTime, String subjectCode, int period) {


        final View view2 =getLayoutInflater().inflate(R.layout.timedialog,null);
        TextView duration = view2.findViewById(R.id.duration_slot);
        TextView periodView = view2.findViewById(R.id.periodNumber);
        TextView subjectNameView = view2.findViewById(R.id.subject_naaam);
        TextView teacherNameView = view2.findViewById(R.id.teacher_nameS);
        TextView subjectCodeView = view2.findViewById(R.id.subject_code);
//

        duration.setText(startTime + " - " + endTime);
        periodView.setText("Period " + period);
        subjectNameView.setText(subjectName);
        teacherNameView.setText(teacherName);
        subjectCodeView.setText(subjectCode);

        layout.addView(view2);
    }
}
