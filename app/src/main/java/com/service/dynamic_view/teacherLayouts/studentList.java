package com.service.dynamic_view.teacherLayouts;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

public class studentList extends AppCompatActivity{
    private String branch, semester, section, subject;

    private DatabaseReference mDatabase;
    private  FirebaseAuth mAuth;
    private LinearLayout layout;
    private ImageView back;

    private String naam, enrl;

    private FirebaseUser currentUser ;
    private TextView teachername,subjectname,todaydate,sectionname;
    private String teacherid,teachernaam,subjectnaam,sectionnaam;
    private String studentName,studentEnrollment;
    AlertDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_student_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Roatated attendanceList

        layout= findViewById(R.id.attendanceList);

        branch = getIntent().getStringExtra("branch");
        semester = getIntent().getStringExtra("semester");
        section = getIntent().getStringExtra("section");
        subject = getIntent().getStringExtra("subject");

        teachername=findViewById(R.id.teacher_name);
        subjectname=findViewById(R.id.subject_name);
        todaydate=findViewById(R.id.date);
        sectionname=findViewById(R.id.section);
        todaydate.setText("Date: "+java.time.LocalDate.now());
        subjectname.setText("Subject: "+subject);
        sectionname.setText("Section: "+section);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        back=findViewById(R.id.back_icon);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
        }

        setValue();








//        buildLayout();
//        bachokodalo();

        back.setOnClickListener(v -> {
            finish();
        }  );


    }
    private  List<String> listStudID=new ArrayList<>();

    private void bachokodalo() {

        DatabaseReference studentList= mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(section).child("Students");



        studentList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    listStudID.add(snapshot.getKey());
                }
                System.out.println("List of Students: "+listStudID);
                for(String studID: listStudID){
                   DatabaseReference studentList= mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(section).child("Students").child(studID);
                    studentList.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            System.out.println("Student ID: "+studID);
                            studentName=dataSnapshot.child("Personal Details").child("Name").getValue().toString();
                            studentEnrollment=dataSnapshot.child("Personal Details").child("Enrollment Number").getValue().toString();
//                            System.out.println("Student Name: "+studentName);
//                            System.out.println("Student Enrollment: "+studentEnrollment);
                            addCard(studentEnrollment,studentName,studID);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(studentList.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(studentList.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

//        Toast.makeText(this, "List of Students: "+listStudID, Toast.LENGTH_SHORT).show();


    }


    private void setValue() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            String userId = currentUser.getUid();
//            Toast.makeText(this, "User ID: "+userId, Toast.LENGTH_SHORT).show();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Teacher").child(userId);
        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                teacherid= task.getResult().child("TeacherId").getValue().toString();
//                System.out.println("Teacher ID: "+teacherid);
                DatabaseReference teacherRef=FirebaseDatabase.getInstance().getReference().child("Teacher").child(teacherid);
                teacherRef.get().addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        teachernaam=task1.getResult().child("Personal Details").child("Name").getValue().toString();
//                        System.out.println("Teacher Name: "+teachernaam);
                        teachername.setText("Teacher: "+teachernaam);

                        DatabaseReference subjectsRef = FirebaseDatabase.getInstance().getReference().child("Subjects").child(branch).child(semester).child(subject);
                        subjectsRef.get().addOnCompleteListener(task2 -> {
                            if(task2.isSuccessful()){
                                subjectnaam=task2.getResult().getValue().toString();
//                                System.out.println("Subject Name: "+subjectnaam);
                                subjectname.setText("Subject: "+subjectnaam);
                                subject=subjectnaam;
                            }
                        });

                        bachokodalo();


                    }
                });
            }
        });


        }
        else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            FirebaseAuth mAuth1 = FirebaseAuth.getInstance();
            mAuth1.signOut();
        }
    }

    private void addCard(String enrl, String naam,String stuId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.student_attendance_struct, null);
        final TextView enrl1 = view.findViewById(R.id.enrl);
        final TextView name = view.findViewById(R.id.stdnaam);
//        final CheckBox attendance = view.findViewById(R.id.attendance);
        final Button present = view.findViewById(R.id.present);
        final Button absent = view.findViewById(R.id.absent);

        LinearLayout layouted = view.findViewById(R.id.container); // Use view.findViewById() instead

        if (layouted != null) {
            enrl1.setText(enrl);
            name.setText(naam);
            builder.setView(view);

            builder.setCancelable(false);

            final AlertDialog dialog = builder.create();
            dialog.show();
            int pre=(int)Math.random()*6+1;

            present.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss(); // Dismiss the dialog regardless of the checked state

                    // Get the reference to the location where attendance data is stored for the student
                    DatabaseReference mdata = FirebaseDatabase.getInstance().getReference()
                            .child("studentAttendance").child(branch).child(semester)
                            .child(section).child("attendance").child(subject).child(stuId);

                    // Get the current date in the desired format
                    java.util.Date date = new java.util.Date();
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(date);

                    // Add attendance data for present status
                    mdata.child(formattedDate).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                Toast.makeText(studentList.this, "Attendance for this date already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                // Add attendance data for present status
                                java.util.Map<String, Object> atted = new java.util.HashMap<>();
                                atted.put("end_time", "10:30");
                                atted.put("period", pre); // random value from 1 to 6
                                atted.put("start_time", "09:30");
                                atted.put("status", "Present");
                                atted.put("teacher", teachernaam);
                                mdata.child(formattedDate).updateChildren(atted);

                                //present button background color change
                                present.setBackgroundColor(getResources().getColor(R.color.adminGreen));
                                absent.setBackgroundColor(getResources().getColor(R.color.white));


                                Toast.makeText(studentList.this, "Attendance marked as Present for " + formattedDate, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle any errors
                            Log.e("Firebase", "Error checking attendance data: " + databaseError.getMessage());
                        }
                    });
                }
            });

            absent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss(); // Dismiss the dialog regardless of the checked state

                    // Get the reference to the location where attendance data is stored for the student
                    DatabaseReference mdata = FirebaseDatabase.getInstance().getReference()
                            .child("studentAttendance").child(branch).child(semester)
                            .child(section).child("attendance").child(subject).child(stuId);

                    // Get the current date in the desired format
                    java.util.Date date = new java.util.Date();
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(date);

                    // Add attendance data for present status
                    java.util.Map<String, Object> atted = new java.util.HashMap<>();
                    atted.put("end_time", "10:30");
                    atted.put("period", pre);
                    atted.put("start_time", "09:30");
                    atted.put("status", "Absent");
                    atted.put("teacher", teachernaam);
                    mdata.child(formattedDate).updateChildren(atted);
                    present.setBackgroundColor(getResources().getColor(R.color.white));
                    absent.setBackgroundColor(getResources().getColor(R.color.adminRed));
                    Toast.makeText(studentList.this, "Attendance marked as Absent for " + formattedDate, Toast.LENGTH_SHORT).show();
                }
            });

//            attendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    dialog.dismiss(); // Dismiss the dialog regardless of the checked state
//
//                    // Get the reference to the location where attendance data is stored for the student
//                    DatabaseReference mdata = FirebaseDatabase.getInstance().getReference()
//                            .child("studentAttendance").child(branch).child(semester)
//                            .child(section).child("attendance").child(subject).child(stuId);
//
//                    // Get the current date in the desired format
//                    Date date = new Date();
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    String formattedDate = dateFormat.format(date);
//
//                    // Check if attendance is checked
//                    if (isChecked) {
//                        // Add attendance data for present status
//                        mdata.child(formattedDate).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    Toast.makeText(studentList.this, "Attendance for this date already exists", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    // Add attendance data for present status
//                                    Map<String, Object> atted = new HashMap<>();
//                                    atted.put("end_time", "10:30");
//                                    atted.put("period", (int) (Math.random() * 6 + 1)); // random value from 1 to 6
//                                    atted.put("start_time", "09:30");
//                                    atted.put("status", "Present");
//                                    atted.put("teacher", teachernaam);
//                                    mdata.child(formattedDate).updateChildren(atted);
//                                    Toast.makeText(studentList.this, "Attendance marked as Present for " + formattedDate, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle any errors
//                                Log.e("Firebase", "Error checking attendance data: " + databaseError.getMessage());
//                            }
//                        });
//                    } else {
//                        Map<String, Object> atted = new HashMap<>();
//                        atted.put("end_time", "10:30");
//                        atted.put("period", (int) (Math.random() * 6 + 1));
//                        atted.put("start_time", "09:30");
//                        atted.put("status", "Absent");
//                        atted.put("teacher", teachernaam);
//                        mdata.child(formattedDate).updateChildren(atted);
//                        Toast.makeText(studentList.this, "Attendance marked as Absent for " + formattedDate, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

        } else {
            Log.e("Error", "Layout is null");
            return; // Exit the method if layout is null
        }

        // Add layouted to the parent layout and set margins
//         if (layout != null) {
//            layout.addView(layouted); // Add layouted to the parent layout
//
//            // Set layout parameters with 20dp top margin
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//            params.setMargins(0, 20, 0, 0); // Set top margin of 20dp
//            layouted.setLayoutParams(params); // Apply layout parameters to layouted
//        } else {
//            Log.e("Error", "Parent layout is null");
//        }
    }

//    private void add(String enrl, String naam,String stuId) {
//        final View view = getLayoutInflater().inflate(R.layout.student_attendance_struct, null);
//        TextView enrl1 = view.findViewById(R.id.enrl);
//        TextView name = view.findViewById(R.id.stdnaam);
//        Button present = view.findViewById(R.id.present);
//        Button absent = view.findViewById(R.id.absent);
//        enrl1.setText(enrl);
//        name.setText(naam);
//
//
//    }
//





}
