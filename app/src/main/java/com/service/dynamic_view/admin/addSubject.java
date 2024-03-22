package com.service.dynamic_view.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class addSubject extends AppCompatActivity {

    String subjectName, subjectCode,  subjectSemester, subjectDepartment;
    EditText subjectNameET, subjectCodeET;
    Spinner subjectSemesterET, subjectDepartmentET;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;
    Button addSubjectButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_subjects);

        subjectNameET = findViewById(R.id.editTextSubjectName);
        subjectCodeET = findViewById(R.id.editTextSubjectCode);
        subjectSemesterET = findViewById(R.id.spinnerSemester);
        subjectDepartmentET = findViewById(R.id.spinnerBranch);
        addSubjectButton = findViewById(R.id.buttonAddSubject);


        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(this, R.array.semester_array, android.R.layout.simple_spinner_item);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSemesterET.setAdapter(semesterAdapter);

        ArrayAdapter<CharSequence> departmentAdapter = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectDepartmentET.setAdapter(departmentAdapter);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        addSubjectButton.setOnClickListener(v -> {
            add_Subject();
        });



    }

    private void add_Subject() {
        subjectName = subjectNameET.getText().toString().trim();
        subjectCode = subjectCodeET.getText().toString().trim();
        subjectSemester = subjectSemesterET.getSelectedItem().toString();
        subjectDepartment = subjectDepartmentET.getSelectedItem().toString();

        if (subjectName.isEmpty()) {
            subjectNameET.setError("Subject Name is required");
            subjectNameET.requestFocus();
            return;
        }

        if (subjectCode.isEmpty()) {
            subjectCodeET.setError("Subject Code is required");
            subjectCodeET.requestFocus();
            return;
        }

        progressDialog.setMessage("Adding Subject...");
        progressDialog.show();
        try {
            mDatabase.child("Subjects").child(subjectDepartment).child(subjectSemester).child(subjectCode).child("subjectName").setValue(subjectName);
        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, "Error Encounter "+e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.dismiss();
        Toast.makeText(this, "Subject Added Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}
