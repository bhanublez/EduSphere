package com.service.dynamic_view.teacherLayouts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

public class select_class extends AppCompatActivity {

    String branch, semester, section, subject;
    Spinner branchSpinner, semesterSpinner, sectionSpinner, subjectSpinner;
    Button submit;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private ImageView back;
    DatabaseReference subjectsRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_class_attendance);
        branchSpinner = findViewById(R.id.branch);
    semesterSpinner = findViewById(R.id.semester);
    sectionSpinner = findViewById(R.id.section);
    subjectSpinner = findViewById(R.id.teacher_subject);

    submit = findViewById(R.id.search);
    back=findViewById(R.id.back_icon);

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

        //spinner item setting array
        String[] branchArray = getResources().getStringArray(R.array.department_array);
        String[] semesterArray = getResources().getStringArray(R.array.semester_array);
        final String[][] sectionArray = new String[1][1];

        //spinner adapter
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchArray);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesterArray);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        branchSpinner.setAdapter(branchAdapter);
        semesterSpinner.setAdapter(semesterAdapter);


        //Now chose section array according to department/brach


        //As branchh or semester changes, subject list will change









        // Declare a DatabaseReference object to refer to the Firebase database


// Create an empty list to hold student subjects
        List<String> studentSubjects = new ArrayList<>();

// Define a listener to observe changes in branch and semester spinners
        AdapterView.OnItemSelectedListener branchSemesterListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected branch and semester
                String selectedBranch = branchSpinner.getSelectedItem().toString();
                String selectedSemester = semesterSpinner.getSelectedItem().toString();

                // Update the subjects reference based on the selected branch and semester
                subjectsRef = FirebaseDatabase.getInstance().getReference().child("Subjects").child(selectedBranch).child(selectedSemester);

                // Clear the studentSubjects list to avoid duplicates
                studentSubjects.clear();

                // Add a listener to fetch subjects data from the Firebase database
                subjectsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Iterate through the children nodes to fetch subject names
                        for (DataSnapshot subjectSnapshot : dataSnapshot.getChildren()) {
                            // Get the subject name and add it to the studentSubjects list
                            String subjectName = subjectSnapshot.getKey();
                            studentSubjects.add(subjectName);
                        }

                        // Update the spinner adapter with the new subject list
                        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(select_class.this, android.R.layout.simple_spinner_item, studentSubjects);
                        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectSpinner.setAdapter(subjectAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors if any
                        Log.e("Firebase", "Error fetching subjects: " + databaseError.getMessage());
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected
            }
        };

// Set the listener to both branch and semester spinners
        branchSpinner.setOnItemSelectedListener(branchSemesterListener);
        semesterSpinner.setOnItemSelectedListener(branchSemesterListener);

        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = branchSpinner.getSelectedItem().toString();
                if (branch.equals("Computer Science")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_cs);
                } else if (branch.equals("Information Technology")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_it);
                } else if (branch.equals("Electronics and Communication")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_ec);
                } else if (branch.equals("Electrical Engineering")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_ee);
                } else if (branch.equals("Mechanical Engineering")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_me);
                } else if (branch.equals("Civil Engineering")) {
                    sectionArray[0] = getResources().getStringArray(R.array.section_array_ce);
                }
                ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(select_class.this, android.R.layout.simple_spinner_item, sectionArray[0]);
                sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sectionSpinner.setAdapter(sectionAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });














        submit.setOnClickListener(v -> {
        branch = branchSpinner.getSelectedItem().toString();
        semester = semesterSpinner.getSelectedItem().toString();
        section = sectionSpinner.getSelectedItem().toString();
        subject = subjectSpinner.getSelectedItem().toString();
        if(currentUser!= null) {
            Intent intent = new Intent(select_class.this, studentList.class);
            intent.putExtra("branch", branch);
            intent.putExtra("semester", semester);
            intent.putExtra("section", section);
            intent.putExtra("subject", subject);
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
