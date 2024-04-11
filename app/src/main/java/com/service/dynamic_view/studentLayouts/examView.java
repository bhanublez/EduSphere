package com.service.dynamic_view.studentLayouts;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class examView extends AppCompatActivity {
    private LinearLayout layout;
    private ImageView back;
    private TextView title_id ;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    private FirebaseUser currentUser ;
    private String branch, semester, section,studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id=findViewById(R.id.title_id);
        title_id.setText("Exams");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        studentId=getIntent().getStringExtra("studentId");
        branch=getIntent().getStringExtra("branch");
        semester=getIntent().getStringExtra("semester");
        section=getIntent().getStringExtra("section");



        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye

        String name;
        String adata;
        String sdata;
        String data;
        int set;

        DatabaseReference examRef = FirebaseDatabase.getInstance().getReference().child("studentAttendance").child(branch).child(semester).child(section).child("Exam");
        examRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for(DataSnapshot snapshot : task.getResult().getChildren()) {
                    String subject = snapshot.getKey();
                    System.out.println(subject);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String date = snapshot1.getKey();
                        System.out.println(date);
                        String teacherName= snapshot1.child("Teacher Name").getValue().toString();
                        String assignmentDate= snapshot1.child("Assigned Date").getValue().toString();
                        String ExamDate= snapshot1.child("Exam Date").getValue().toString();
                        String Marks= snapshot1.child("Marks").getValue().toString();
                        String subName= snapshot1.child("Subject Name").getValue().toString();
                        String topic_data= snapshot1.child("Topic Description").getValue().toString();
                        System.out.println(teacherName+" "+assignmentDate+" "+ExamDate+" "+Marks+" "+subName+" "+topic_data);
                        addCard(teacherName, subName, assignmentDate, ExamDate, Integer.parseInt(Marks), topic_data);

                    }

                }
            }
        });
    }

    private void addCard(String name, String subject, String adate, String sdate, int set, String data) {


        final View view2 =getLayoutInflater().inflate(R.layout.examdialog,null);



        TextView nameView = view2.findViewById(R.id.teacherName_ve);
        TextView subjectView = view2.findViewById(R.id.subjectName_ve);
//
        TextView assDate = view2.findViewById(R.id.daetAss_ve);
        TextView subDate = view2.findViewById(R.id.examDate_ve);
//
        TextView mksId = view2.findViewById(R.id.markss_ve);
        TextView setId = view2.findViewById(R.id.txtArea_ve);
//
        ImageView delete=view2.findViewById(R.id.del_e);

        // Set values to respective TextViews
        nameView.setText(name);
        subjectView.setText(subject);
        assDate.setText(adate);
        subDate.setText(sdate);
        mksId.setText(String.valueOf(set));
        setId.setText(data);
        //diable the setId
        setId.setEnabled(false);

        //Remove the view of delete ImageView
        delete.setVisibility(View.GONE);
        delete.setEnabled(false);

        //Separation set
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        View view3 = new View(this);
        view3.setLayoutParams(params);
        view3.setBackgroundColor(getResources().getColor(R.color.black));
        layout.addView(view3);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 0, 0, 15);
        view2.setLayoutParams(paramss);

        layout.addView(view2);
    }
}




