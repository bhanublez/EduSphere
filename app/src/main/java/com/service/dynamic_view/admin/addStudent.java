package com.service.dynamic_view.admin;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

public class addStudent extends AppCompatActivity {

    //Personal Details
    private String name,degree,branch,Section,Religion,category,motherName,fatherName,Gender,BloodGroup,DOB,enrollmentNo,academicYear,semester,medium;
    private String studentId,adharNo;

    private String password,confirmPassword;
    int check=0;


    //Contact Details
    private String email,contactNo,fatherEmail,permanentAddress,localAddress,district,state,pincode,Country,fatherNumber;

    //Firbase Data Base
    private DatabaseReference mDatabase;
    private  FirebaseAuth mAuth;
    private EditText editTextName,
            editTextMotherName,editTextFatherName,editTextDOB,editTextEnrollmentNumber,
            editTextAcademicYear,editTextStudentId,editTextAdhar,editTextEmail,editTextMobileNumber,editTextFatherEmail,
            editTextAddress,editTextLocalAddress,editTextCity,editTextState,editTextPincode,editTextCountry,editTextPassword,
            editTextConfirmPassword,editTextFatherNumber;
    private ProgressDialog progressDialog;

    private Spinner spinnerSection,spinnerSemester,spinnerBranch,spinnerDegree,spinnerMedium,spinnerCategory,spinnerRelion,spinnerBloddGroup,spinnerGender;

    private Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_registraion_student);

        //Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();



        editTextName=findViewById(R.id.editTextName);
        editTextMotherName=findViewById(R.id.editTextMotherName);
        editTextFatherName=findViewById(R.id.editTextFatherName);
        editTextDOB=findViewById(R.id.editTextDOB);
        editTextEnrollmentNumber=findViewById(R.id.editTextEnrollmentNumber);
        editTextAcademicYear=findViewById(R.id.editTextAcademicYear);
        editTextStudentId=findViewById(R.id.editTextStudentId);
        editTextAdhar=findViewById(R.id.editTextAdhar);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextMobileNumber=findViewById(R.id.editTextMobileNumber);
        editTextFatherEmail=findViewById(R.id.editTextFatherEmail);
        editTextAddress=findViewById(R.id.editTextAddress);
        editTextLocalAddress=findViewById(R.id.editTextLocalAddress);
        editTextCity=findViewById(R.id.editTextCity);
        editTextState=findViewById(R.id.editTextState);
        editTextPincode=findViewById(R.id.editTextPincode);
        editTextCountry=findViewById(R.id.editTextCountry);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        editTextFatherNumber=findViewById(R.id.editTextFatherNumber);


        buttonRegister=findViewById(R.id.buttonRegister);
        progressDialog=new ProgressDialog(this);




        spinnerSemester = findViewById(R.id.editTextSemester);
        String[] semesterArray = getResources().getStringArray(R.array.semester_array);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesterArray);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(semesterAdapter);

        spinnerBranch = findViewById(R.id.editTextBranch);
        String[] branchArray = getResources().getStringArray(R.array.department_array);
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchArray);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(branchAdapter);

        spinnerDegree = findViewById(R.id.editTextDegree);
        String[] degreeArray = getResources().getStringArray(R.array.degree_array);
        ArrayAdapter<String> degreeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, degreeArray);
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDegree.setAdapter(degreeAdapter);


        spinnerMedium = findViewById(R.id.editTextMedium);
        String[] mediumArray = getResources().getStringArray(R.array.medium_array);
        ArrayAdapter<String> mediumAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mediumArray);
        mediumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedium.setAdapter(mediumAdapter);

        spinnerCategory = findViewById(R.id.editTextCategory);
        String[] categoryArray = getResources().getStringArray(R.array.category_array);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryArray);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerRelion = findViewById(R.id.editTextReligion);
        String[] religionArray = getResources().getStringArray(R.array.religion_array);
        ArrayAdapter<String> religionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, religionArray);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRelion.setAdapter(religionAdapter);

        spinnerBloddGroup = findViewById(R.id.editTextBloodGroup);
        String[] bloodGroupArray = getResources().getStringArray(R.array.blood_group_array);
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bloodGroupArray);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloddGroup.setAdapter(bloodGroupAdapter);

        spinnerGender = findViewById(R.id.editTextGender);
        String[] genderArray = getResources().getStringArray(R.array.gender_array);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderArray);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerSection = findViewById(R.id.editTextSection);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = spinnerBranch.getSelectedItem().toString();
                if (branch.equals("Computer Science")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_cs);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                } else if (branch.equals("Information Technology")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_it);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                } else if (branch.equals("Electronics and Communication")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_ec);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                } else if (branch.equals("Electrical Engineering")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_ee);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                } else if (branch.equals("Mechanical Engineering")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_me);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                } else if (branch.equals("Civil Engineering")) {
                    String[] sectionArray = getResources().getStringArray(R.array.section_array_ce);
                    ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(addStudent.this, android.R.layout.simple_spinner_item, sectionArray);
                    sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSection.setAdapter(sectionAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });















        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
//                Toast.makeText(addStudent.this, "Student Registered", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(addStudent.this, adminDashboard.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void registerUser(){

        try {
            name = editTextName.getText().toString().toUpperCase();
            if (name.isEmpty()) {
                editTextName.setError("Name is required");
                editTextName.requestFocus();
                return;
            }

            degree = spinnerDegree.getSelectedItem().toString();

            branch = spinnerBranch.getSelectedItem().toString();

            Section = spinnerSection.getSelectedItem().toString();
            Religion = spinnerRelion.getSelectedItem().toString();
            category = spinnerCategory.getSelectedItem().toString();
            motherName = editTextMotherName.getText().toString();
            medium = spinnerMedium.getSelectedItem().toString();
            if (motherName.isEmpty()) {
//                editTextMotherName.setError("Mother Name is required");
//                return;
                motherName = "Not Specified";
            }
            fatherName = editTextFatherName.getText().toString();
            if (fatherName.isEmpty()) {
//                editTextFatherName.setError("Father Name is required");
//                return;
                fatherName = "Not Specified";
            }

            Gender = spinnerGender.getSelectedItem().toString();

            fatherNumber = editTextFatherNumber.getText().toString();
            if(fatherNumber.isEmpty()){
                fatherNumber = "Not Specified";
            }else {
                long fatherN;
                try {
                    fatherN = Long.parseLong(fatherNumber);
                } catch (NumberFormatException e) {
                    editTextFatherNumber.setError("Invalid Father Number");
                    editTextFatherNumber.requestFocus();
                    return;
                }

                if (String.valueOf(fatherN).length() != 10) {
                    editTextFatherNumber.setError("Invalid Father Number");
                    editTextFatherNumber.requestFocus();
                    return;
                }
            }

            semester = spinnerSemester.getSelectedItem().toString();



            BloodGroup = spinnerBloddGroup.getSelectedItem().toString();

            DOB = editTextDOB.getText().toString();
            if (DOB.isEmpty()) {
                editTextDOB.setError("Date of Birth is required in YYYY-MM-DD format");
                editTextDOB.requestFocus();
                editTextDOB.requestFocus();
                return;
            } else {
                //Check for valid formating of date
                String[] dateParts = DOB.split("-");
                if (dateParts.length != 3) {
                    editTextDOB.setError("Invalid date format");
                    editTextDOB.requestFocus();
                    return;
                }

                for (String part : dateParts) {
                    try {
                        Integer.parseInt(part);
                    } catch (NumberFormatException e) {
                        editTextDOB.setError("Invalid date format");
                        editTextDOB.requestFocus();
                        return;
                    }
                }

                Integer[] date = new Integer[dateParts.length];

                for (int i = 0; i < dateParts.length; i++) {
                    date[i] = Integer.parseInt(dateParts[i]);
                }
                if (date[1] < 1 || date[1] > 12) {
                    editTextDOB.setError("Invalid Month");
                    editTextDOB.requestFocus();
                    return;
                }
                if (date[2] < 1 || date[2] > 31) {
                    editTextDOB.setError("Invalid Day");
                    editTextDOB.requestFocus();
                    return;
                }
                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                if (date[0] < 1900 || date[0] > currentYear) {
                    editTextDOB.setError("Invalid Year");
                    editTextDOB.requestFocus();
                    return;
                }
            }
            enrollmentNo = editTextEnrollmentNumber.getText().toString();

            if (enrollmentNo.isEmpty()) {
                editTextEnrollmentNumber.setError("Enrollment Number is required");
                editTextEnrollmentNumber.requestFocus();
                return;
            } else {
                //Check if enrollment number already exists
                mDatabase=FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        check=0;
                        List<String> existingEnrollmentNumbers = new ArrayList<>();
                        // Iterate through each student ID
                        for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                            DataSnapshot personalDetails = studentSnapshot.child("Personal Details");
                            if (personalDetails.exists()) {
                                // Get enrollment number for each student
                                String enrollmentNumber = personalDetails.child("Enrollment Number").getValue(String.class);
                                if (enrollmentNumber != null) {
//                                    System.out.println(enrollmentNumber);
                                    existingEnrollmentNumbers.add(enrollmentNumber);
                                }
                            }
                        }

                        // Now you have a list of existing enrollment numbers
                        // Check if the enrollment number exists in the list
                        enrollmentNo=enrollmentNo.toUpperCase().trim();
                        String newEnrollmentNumber = enrollmentNo; // Replace with the enrollment number you want to check
                        if (existingEnrollmentNumbers.contains(newEnrollmentNumber)) {
                            editTextEnrollmentNumber.setError("Enrollment Number already exists");
                        } else {
                            check=1;
                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle potential errors here
                    }
                });

                if(check==0){
                    editTextEnrollmentNumber.requestFocus();
                    return;
                }



            }

            academicYear = editTextAcademicYear.getText().toString();

            if (academicYear.isEmpty()) {
                editTextAcademicYear.setError("Academic Year is required in YYYY-YYYY format");
                editTextAcademicYear.requestFocus();
                return;
            } else {
                //check format
                String[] dateParts = academicYear.split("-");
                if (dateParts.length != 2) {
                    editTextAcademicYear.setError("Invalid academic year format. Use YYYY-YYYY format");
                    editTextAcademicYear.requestFocus();
                    return;
                }



                try {
                    int startYear = Integer.parseInt(dateParts[0]);
                    int endYear = Integer.parseInt(dateParts[1]);
                    if (startYear <1000 || startYear>endYear || endYear<1000 ) {
                        editTextAcademicYear.setError("Invalid academic year range. Year should be greater than 1900, and start year should be less than end year.");
                        editTextAcademicYear.requestFocus();
                        return;
                    }
                } catch (NumberFormatException e) {
                    editTextAcademicYear.setError("Invalid academic year format. Use numeric values");
                    editTextAcademicYear.requestFocus();
                    return;
                }

            }

            studentId = editTextStudentId.getText().toString();
            if (studentId.isEmpty()) {
                editTextStudentId.setError("Student ID is required");
                editTextStudentId.requestFocus();
                return;
            }



            int sId;
            try {
                sId = Integer.parseInt(studentId);
            } catch (NumberFormatException e) {
                editTextStudentId.setError("Invalid student ID");
                editTextStudentId.requestFocus();
                return;
            }

            if (sId <= 0) {
                editTextStudentId.setError("Student ID must be a positive number");
                editTextStudentId.requestFocus();
                return;
            }

            check=1;
// Check if student ID already exists in the database
            mDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        if(snapshot.getKey().equals(studentId)){
                            editTextStudentId.setError("Student ID already exists");
                            check=0;

                            return;
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle potential errors here
                }
            });
    if(check==0){
        editTextStudentId.requestFocus();
        return;
    }



                    adharNo = editTextAdhar.getText().toString();
            if (!adharNo.isEmpty()) {
                long adharN;
                try {
                    adharN = Long.parseLong(adharNo);
                } catch (NumberFormatException e) {
                    editTextAdhar.setError("Invalid Aadhar Number");
                    editTextAdhar.requestFocus();
                    return;
                }

                if (String.valueOf(adharN).length() != 12) {
                    editTextAdhar.setError("Invalid Aadhar Number");
                    editTextAdhar.requestFocus();
                    return;
                }

                // Check if Aadhar number already exists in the database
                mDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.child("Personal Details").child("Aadhar Number").getValue().equals(adharNo)){
                                editTextAdhar.setError("Aadhar Number already exists");
                                return;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle potential errors here
                    }
                });

                }else {
                adharNo = "Not Specified";
            }

            email = editTextEmail.getText().toString();
            if (email.isEmpty()) {
                editTextEmail.setError("Email is required");
                editTextEmail.requestFocus();
                return;
            } else {
                //Valid Email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Invalid Email");
                    editTextEmail.requestFocus();
                    return;
                }

                mDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        check = 0;

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.child("Contact Details").child("Email").getValue().equals(email)){
                                editTextEmail.setError("Email already exists");
                                check = 1;
                                return;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle potential errors here
                    }
                });




















                    }

            contactNo = editTextMobileNumber.getText().toString();
            if (!contactNo.isEmpty()) {
                long contact;
                try {
                    contact = Long.parseLong(contactNo);
                } catch (NumberFormatException e) {
                    editTextMobileNumber.setError("Invalid Mobile Number");
                    editTextMobileNumber.requestFocus();
                    return;
                }

                if (String.valueOf(contact).length() != 10) {
                    editTextMobileNumber.setError("Invalid Mobile Number");
                    editTextMobileNumber.requestFocus();
                    return;
                }

                // Check if mobile number already exists in the database
                check=1;
                mDatabase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.child("Contact Details").child("Mobile Number").getValue().equals(contactNo)){
                                editTextMobileNumber.setError("Mobile Number already exists");
                                return;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle potential errors here
                    }
                });
                }else {
                contactNo = "Not Specified";
            }

            check=1;
            fatherEmail = editTextFatherEmail.getText().toString();
            if (fatherEmail.isEmpty()) {
//                editTextFatherEmail.setError("Father Email is required");
//                return;
                fatherEmail = "Not Specified";
            } else {
                //Valid Email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(fatherEmail).matches()) {
                    editTextFatherEmail.setError("Invalid Email ");
                    editTextFatherEmail.requestFocus();
                    return;
                }
            }
            permanentAddress = editTextAddress.getText().toString();
            if (permanentAddress.isEmpty()) {
//                editTextAddress.setError("Permanent Address is required");
//                return;
                permanentAddress = "Not Specified";
            }
            localAddress = editTextLocalAddress.getText().toString();
            if (localAddress.isEmpty()) {
//                editTextLocalAddress.setError("Local Address is required");
//                return;
                localAddress = "Not Specified";
            }
            district = editTextCity.getText().toString();
            if (district.isEmpty()) {
//                editTextCity.setError("City is required");
//                return;
                district = "Not Specified";
            }
            state = editTextState.getText().toString();
            if (state.isEmpty()) {
//                editTextState.setError("State is required");
//                return;
                state = "Not Specified";
            }

            pincode = editTextPincode.getText().toString();
            if (!pincode.isEmpty()) {
                int pin;
                try {
                    pin = Integer.parseInt(pincode);
                } catch (NumberFormatException e) {
                    editTextPincode.setError("Invalid Pincode");
                    editTextPincode.requestFocus();
                    return;
                }

                if (pin <= 0) {
                    editTextPincode.setError("Invalid Pincode");
                    editTextPincode.requestFocus();
                    return;
                }
            }

// If pincode is empty or valid, continue with further operations
// For example, you can save the pincode to the database




            Country = editTextCountry.getText().toString();
            if (Country.isEmpty()) {
//                editTextCountry.setError("Country is required");
//                return;
                Country = "Not Specified";
            }
            password = editTextPassword.getText().toString();
            if (password.isEmpty()) {
                editTextPassword.setError("Password is required");
                editTextPassword.requestFocus();
                return;
            } else if (password.length() < 12) {
                editTextPassword.setError("Password must be greater than 12 characters");
                editTextPassword.requestFocus();
                return;
            } else {
                boolean containsUpperCase = false;
                boolean containsLowerCase = false;
                boolean containsDigit = false;
                boolean containsSpecialChar = false;

                for (char c : password.toCharArray()) {
                    if (Character.isUpperCase(c)) {
                        containsUpperCase = true;
                    } else if (Character.isLowerCase(c)) {
                        containsLowerCase = true;
                    } else if (Character.isDigit(c)) {
                        containsDigit = true;
                    } else if (!Character.isLetterOrDigit(c)) {
                        containsSpecialChar = true;
                    }
                }

                if (!containsUpperCase || !containsLowerCase || !containsDigit || !containsSpecialChar) {
                    String errorMessage = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character";
                    editTextPassword.setError(errorMessage);
                    editTextPassword.requestFocus();
                    return;
                }
            }

            confirmPassword = editTextConfirmPassword.getText().toString();
            if (confirmPassword.isEmpty()) {
                editTextConfirmPassword.setError("Confirm Password is required");
                editTextPassword.requestFocus();
                return;
            }
            if (!password.equals(confirmPassword)) {
                editTextConfirmPassword.setError("Password do not match");
                editTextConfirmPassword.requestFocus();
                return;
            }



    if(check==1) {
        progressDialog.setTitle("Creating New Account");
        progressDialog.setMessage("please wait, While we are creating a new account for you...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        try {
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Name").setValue(name.trim());//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Degree").setValue(degree);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Branch").setValue(branch);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Section").setValue(Section);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Religion").setValue(Religion);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Category").setValue(category);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Mother Name").setValue(motherName.trim());//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Father Name").setValue(fatherName.trim());//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Gender").setValue(Gender);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Blood Group").setValue(BloodGroup.trim());//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("DOB").setValue(DOB);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Enrollment Number").setValue(enrollmentNo.trim());//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Academic Year").setValue(academicYear);//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Student Id").setValue(studentId.trim());//
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Aadhar Number").setValue(adharNo.trim());//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Semester").setValue(semester);//D


            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Email").setValue(email.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Mobile Number").setValue(contactNo.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Father Email").setValue(fatherEmail.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Permanent Address").setValue(permanentAddress.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Local Address").setValue(localAddress.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("City").setValue(district.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("State").setValue(state.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Pincode").setValue(pincode.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Country").setValue(Country.trim());
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Father Number").setValue(fatherNumber.trim());

            //Firebase Authentication for geting


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        // Add your onCompleteListener code here
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser usr = task.getResult().getUser();
                                String userID = usr.getUid();
                                mDatabase.child("Authentication").child("Student").child(userID).child("StudentId").setValue(studentId);
                                mDatabase.child("Authentication").child("Student").child(userID).child("Status").setValue("Offline");
                                mDatabase.child("Authentication").child("Student").child(userID).child("category").setValue("Student");

                                //orginal Datbase storing
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Name").setValue(name.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Degree").setValue(degree);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Branch").setValue(branch);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Section").setValue(Section);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Religion").setValue(Religion);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Category").setValue(category);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Mother Name").setValue(motherName.trim().toUpperCase());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Father Name").setValue(fatherName.trim().toUpperCase());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Gender").setValue(Gender);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Blood Group").setValue(BloodGroup.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("DOB").setValue(DOB);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Enrollment Number").setValue(enrollmentNo.trim().toUpperCase());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Academic Year").setValue(academicYear);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Student Id").setValue(studentId.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Aadhar Number").setValue(adharNo.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Semester").setValue(semester);
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Personal Details").child("Medium").setValue(medium);

                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Email").setValue(email.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Mobile Number").setValue(contactNo.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Father Email").setValue(fatherEmail.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Permanent Address").setValue(permanentAddress.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Local Address").setValue(localAddress.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("City").setValue(district.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("State").setValue(state.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Pincode").setValue(pincode.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Country").setValue(Country.trim());
                                mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(Section).child("Students").child(studentId).child("Contact Details").child("Father Number").setValue(fatherNumber.trim());


                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                Toast.makeText(addStudent.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(addStudent.this,adminDashboard.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            Log.i("Error", e.getMessage());
            Log.d("Error", e.getMessage());
            Log.v("Error", e.getMessage());
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            progressDialog.dismiss();
            Toast.makeText(this, "Invalid data Entered ", Toast.LENGTH_SHORT).show();
        }

        progressDialog.dismiss();
    }

        }catch (Exception e){
            Log.e("Error",e.getMessage());
            Log.i("Error",e.getMessage());
            Log.d("Error",e.getMessage());
            Log.v("Error",e.getMessage());
//            Intent intent = getIntent();
//            finish();
            Toast.makeText(this, "Invalid data Entered "+e.getMessage(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss();

//            startActivity(intent);
        }




    }




}
