package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;

import java.util.ArrayList;
import java.util.List;

public class attendanceMark extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    AlertDialog dialog;
    String branch,semester,section,studentId;

    private FirebaseUser currentUser ;
    String facultyName;
    private  int prsent=0,absent=0;

    private String enrollNo,naam;
    TextView en,sname1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentattendance);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout= findViewById(R.id.container);
        back=findViewById(R.id.backFromProfile);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        en=findViewById(R.id.enrollmentNumber);
        sname1=findViewById(R.id.studentName);

        currentUser = mAuth.getCurrentUser();

        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye
        if(currentUser!=null){
            //Database of Exam sai hum haha data leke aaye
            String userId = currentUser.getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Student").child(userId);
            userRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    studentId= task.getResult().child("StudentId").getValue().toString();

                    DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("Student").child(studentId);
                    studentRef.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {

                            section= task1.getResult().child("Personal Details").child("Section").getValue().toString();
                            branch= task1.getResult().child("Personal Details").child("Branch").getValue().toString();
                            semester= task1.getResult().child("Personal Details").child("Semester").getValue().toString();
                            naam= task1.getResult().child("Personal Details").child("Name").getValue().toString();
                            enrollNo= task1.getResult().child("Personal Details").child("Enrollment Number").getValue().toString();
                            en.setText(enrollNo);
                            sname1.setText(naam);
                            DatabaseReference subjectsRef = FirebaseDatabase.getInstance().getReference().child("Subjects").child(branch).child(semester);
                            List<String> studentSubjects = new ArrayList<>();
                            //                            //Getting list of all subjects

                            subjectsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot subjectSnapshot : dataSnapshot.getChildren()) {
                                        String subjectCode = subjectSnapshot.getKey();
                                        String subjectName = subjectSnapshot.getValue(String.class);
//                                        System.out.println(subjectCode + ": \"" + subjectName + "\"");
                                        studentSubjects.add(subjectName);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    System.out.println("Error fetching subjects: " + databaseError.getMessage());
                                }
                            });

                            DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference().child("studentAttendance").child(branch).child(semester).child(section).child("attendance");
//                            Toast.makeText(this, branch+" "+semester+" "+section, Toast.LENGTH_SHORT).show();
                            attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    //Iterating through all subjects
                                    for (String subject : studentSubjects) {

                                        DatabaseReference subjectRef = attendanceRef.child(subject).child(studentId);
                                        subjectRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                                prsent=0;absent=0;
                                                facultyName = "Not Available";
                                                //Traverse day by day 0 , 1, 2..
//                                                System.out.println("Subject: " + subject);

//                                                facultyName = dataSnapshot.child("0").child("teacher").getValue(String.class);

                                                for (DataSnapshot daySnapshot : dataSnapshot.getChildren()) {
//                                                    System.out.println("Day: " + daySnapshot.getKey());
                                                    String day = daySnapshot.getKey();
//                                                    System.out.println("Day: " + day);
//                                                    for (DataSnapshot attendanceSnapshot : daySnapshot.getChildren()) {
////                                                        System.out.println("Attendance: " + attendanceSnapshot.getKey() + ": " + attendanceSnapshot.getValue());
//                                                        String key = attendanceSnapshot.getKey();
//                                                        String value = attendanceSnapshot.getValue().toString();
//                                                        if(value.equals("Present")) {
//                                                            prsent++;break;
//                                                        } else {
//                                                            absent++;break;
//                                                        }
//
//                                                    }
//                                                    facultyName =dataSnapshot.child("0").getValue().toString();
//                                                    DataSnapshot attendanceSnapshot = daySnapshot.getChildren();

//                                                    facultyName =dataSnapshot.child("0").getValue().toString();
//                                                    System.out.println("Faculty Name: " + facultyName);
                                                    assert day != null;
                                                    DataSnapshot facultySnapshot = dataSnapshot.child(day);

                                                    String status = facultySnapshot.child("status").getValue(String.class);
//                                                    System.out.println("Status: " + status);


                                                    assert status != null;
                                                    if(status.equals("Present")) {
                                                        prsent++;
                                                    } else {
                                                        absent++;
                                                    }

                                                    facultyName = facultySnapshot.child("teacher").getValue(String.class);



//                                                    if(attendanceSnapshot.getValue().toString().equals("Present")) {
//                                                        prsent++;
//                                                    } else {
//                                                        absent++;
//                                                    }
//                                                    addCards(subject, String.valueOf(prsent + absent), String.valueOf(prsent), String.valueOf(absent), "Riya");

                                                }
//                                                String facultyName =dataSnapshot.getChildren().("0").

                                                addCard(subject,String.valueOf(prsent + absent),String.valueOf(prsent),String.valueOf(absent),facultyName);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                System.out.println("Error fetching attendance: " + databaseError.getMessage());
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    System.out.println("Error fetching subjects: " + databaseError.getMessage());
                                }
                            });

                        }
                    });



                }
            });


        }
    }

    private void addCard(String subName,String totalAttendance,String totalPresent,String totalAbsent,String nameOfFaculty){

        final View view=getLayoutInflater().inflate(R.layout.attendance_student_container,null);
        TextView sname=view.findViewById(R.id.subName);
        TextView tAtt=view.findViewById(R.id.totalAtt);
        TextView tPres=view.findViewById(R.id.presentAtt);
        TextView tAbs=view.findViewById(R.id.absentAtt);
        TextView per=view.findViewById(R.id.icon);

        TextView fname=view.findViewById(R.id.facultyName);

        int present=Integer.parseInt(totalPresent);
        int absent=Integer.parseInt(totalAbsent);
        int total=Integer.parseInt(totalAttendance);
        float percentage=(float)present/total*100;
        //set till 2 decimal places
        per.setText(String.format("%.2f",percentage)+"%");
        sname.setText(subName);
        tAtt.setText(totalAttendance);
        tPres.setText(totalPresent);
        tAbs.setText(totalAbsent);
        fname.setText(nameOfFaculty);
        ImageView next=view.findViewById(R.id.nextMove);

        //create a separtion between cards
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 20);
        view.setLayoutParams(params);

        layout.addView(view);

    }

    }
