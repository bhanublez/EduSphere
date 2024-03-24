package com.service.dynamic_view.admin;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class addStudent extends AppCompatActivity {

    //Personal Details
    private String name,degree,branch,Section,Religion,category,motherName,fatherName,Gender,BloodGroup,DOB,enrollmentNo,academicYear,semester;
    private String studentId,adharNo;

    private String password,confirmPassword;


    //Contact Details
    private String email,contactNo,fatherEmail,permanentAddress,localAddress,district,state,pincode,Country,fatherNumber;

    //Firbase Data Base
    private DatabaseReference mDatabase;
    private  FirebaseAuth mAuth;
    private EditText editTextName,editTextDegree,editTextBranch,editTextSection,editTextReligion,editTextCategory,
            editTextMotherName,editTextFatherName,editTextGender,editTextBloodGroup,editTextDOB,editTextEnrollmentNumber,
            editTextAcademicYear,editTextStudentId,editTextAdhar,editTextEmail,editTextMobileNumber,editTextFatherEmail,
            editTextAddress,editTextLocalAddress,editTextCity,editTextState,editTextPincode,editTextCountry,editTextPassword,
            editTextConfirmPassword,editTextFatherNumber,editTextSemester;
    private ProgressDialog progressDialog;

    private Button buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_registraion_student);

        //Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
         mAuth = FirebaseAuth.getInstance();



        editTextName=findViewById(R.id.editTextName);
        editTextDegree=findViewById(R.id.editTextDegree);
        editTextBranch=findViewById(R.id.editTextBranch);
        editTextSection=findViewById(R.id.editTextSection);
        editTextReligion=findViewById(R.id.editTextReligion);
        editTextCategory=findViewById(R.id.editTextCategory);
        editTextMotherName=findViewById(R.id.editTextMotherName);
        editTextFatherName=findViewById(R.id.editTextFatherName);
        editTextGender=findViewById(R.id.editTextGender);
        editTextBloodGroup=findViewById(R.id.editTextBloodGroup);
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
        editTextSemester=findViewById(R.id.editTextSemester);


        buttonRegister=findViewById(R.id.buttonRegister);
        progressDialog=new ProgressDialog(this);


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
            name = editTextName.getText().toString();
            if (name.isEmpty()) {
                editTextName.setError("Name is required");
                return;
            }

            degree = editTextDegree.getText().toString();
            if (degree.isEmpty()) {
                editTextDegree.setError("Degree is required");
                return;
            }


            branch = editTextBranch.getText().toString();
            if (branch.isEmpty()) {
                editTextBranch.setError("Branch is required");
                return;
            }
            Section = editTextSection.getText().toString();
            if (Section.isEmpty()) {
                editTextSection.setError("Section is required");
                return;
            }
            Religion = editTextReligion.getText().toString();
            if (Religion.isEmpty()) {
//                editTextReligion.setError("Religion is required");
//                return;
                Religion = "Not Specified";
            }
            category = editTextCategory.getText().toString();
            if (category.isEmpty()) {
//                editTextCategory.setError("Category is required");
//                return;
                category = "Not Specified";
            }
            motherName = editTextMotherName.getText().toString();
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

            Gender = editTextGender.getText().toString().toLowerCase();
            if (Gender.isEmpty()) {
                editTextGender.setError("Gender is required either male, female, or other");
                return;
            } else if (!(Gender.equals("female") || Gender.equals("male") || Gender.equals("other"))) {
                editTextGender.setError("Gender should be either male, female, or other");
                return;
            }

            fatherNumber = editTextFatherNumber.getText().toString();
            if(fatherNumber.isEmpty()){
                fatherNumber = "Not Specified";
            }else {
                long fatherN;
                try {
                    fatherN = Long.parseLong(fatherNumber);
                } catch (NumberFormatException e) {
                    editTextFatherNumber.setError("Invalid Father Number");
                    return;
                }

                if (String.valueOf(fatherN).length() != 10) {
                    editTextFatherNumber.setError("Invalid Father Number");
                    return;
                }
            }

            semester = editTextSemester.getText().toString();
            if (semester.isEmpty()) {
                editTextSemester.setError("Semester is required");
                return;
            }


            BloodGroup = editTextBloodGroup.getText().toString();
            if (BloodGroup.isEmpty()) {
//                editTextBloodGroup.setError("Blood Group is required");
//                return;
                BloodGroup = "Not Specified";
            }

            DOB = editTextDOB.getText().toString();
            if (DOB.isEmpty()) {
                editTextDOB.setError("Date of Birth is required in YYYY-MM-DD format");
                return;
            } else {
                //Check for valid formating of date
                String[] dateParts = DOB.split("-");
                if (dateParts.length != 3) {
                    editTextDOB.setError("Invalid date format");
                    return;
                }

                for (String part : dateParts) {
                    try {
                        Integer.parseInt(part);
                    } catch (NumberFormatException e) {
                        editTextDOB.setError("Invalid date format");
                        return;
                    }
                }

                Integer[] date = new Integer[dateParts.length];

                for (int i = 0; i < dateParts.length; i++) {
                    date[i] = Integer.parseInt(dateParts[i]);
                }
                if (date[1] < 1 || date[1] > 12) {
                    editTextDOB.setError("Invalid Month");
                    return;
                }
                if (date[2] < 1 || date[2] > 31) {
                    editTextDOB.setError("Invalid Day");
                    return;
                }
                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                if (date[0] < 1900 || date[0] > currentYear) {
                    editTextDOB.setError("Invalid Year");
                    return;
                }
            }
            enrollmentNo = editTextEnrollmentNumber.getText().toString();
            if (enrollmentNo.isEmpty()) {
                editTextEnrollmentNumber.setError("Enrollment Number is required");
                return;
            } else {
                //Check if enrollment number already exists

                mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Enrollment Number").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                editTextEnrollmentNumber.setError("Enrollment Number already exists");
                                return;
                            }
                        }
                    }
                });
            }

            academicYear = editTextAcademicYear.getText().toString();

            if (academicYear.isEmpty()) {
                editTextAcademicYear.setError("Academic Year is required in YYYY-YYYY format");
                return;
            } else {
                //check format
                String[] dateParts = academicYear.split("-");
                if (dateParts.length != 2) {
                    editTextAcademicYear.setError("Invalid academic year format. Use YYYY-YYYY format");
                    return;
                }


                try {
                    int startYear = Integer.parseInt(dateParts[0]);
                    int endYear = Integer.parseInt(dateParts[1]);
                    if (startYear <1000 || startYear>endYear || endYear<1000 ) {
                        editTextAcademicYear.setError("Invalid academic year range. Year should be greater than 1900, and start year should be less than end year.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    editTextAcademicYear.setError("Invalid academic year format. Use numeric values");
                    return;
                }

            }

            studentId = editTextStudentId.getText().toString();
            if (studentId.isEmpty()) {
                editTextStudentId.setError("Student ID is required");
                return;
            }

            int sId;
            try {
                sId = Integer.parseInt(studentId);
            } catch (NumberFormatException e) {
                editTextStudentId.setError("Invalid student ID");
                return;
            }

            if (sId <= 0) {
                editTextStudentId.setError("Student ID must be a positive number");
                return;
            }

// Check if student ID already exists in the database
            mDatabase.child("Student").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        DataSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            editTextStudentId.setError("Student ID already exists");
                            return;
                        }
                    } else {
                        // Handle database error
                        Log.e("Firebase", "Error getting student ID from database", task.getException());
                        // You may display a toast or handle the error in another way
                    }
                }
            });


            adharNo = editTextAdhar.getText().toString();
            if (!adharNo.isEmpty()) {
                long adharN;
                try {
                    adharN = Long.parseLong(adharNo);
                } catch (NumberFormatException e) {
                    editTextAdhar.setError("Invalid Aadhar Number");
                    return;
                }

                if (String.valueOf(adharN).length() != 12) {
                    editTextAdhar.setError("Invalid Aadhar Number");
                    return;
                }

                // Check if Aadhar number already exists in the database
                mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Adhar Number").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                editTextAdhar.setError("Aadhar Number already exists");
                                return;
                            }
                        } else {
                            // Handle database error
                            Log.e("Firebase", "Error getting Aadhar number from database", task.getException());

                        }
                    }
                });
            }else {
                adharNo = "Not Specified";
            }

            email = editTextEmail.getText().toString();
            if (email.isEmpty()) {
                editTextEmail.setError("Email is required");
                return;
            } else {
                //Valid Email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Invalid Email");
                    return;
                }
                mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                editTextEmail.setError("Email already exists");
                                return;
                            }
                        }
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
                    return;
                }

                if (String.valueOf(contact).length() != 10) {
                    editTextMobileNumber.setError("Invalid Mobile Number");
                    return;
                }

                // Check if mobile number already exists in the database

                mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Mobile Number").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            DataSnapshot snapshot = task.getResult();
                            if (snapshot.exists()) {
                                editTextMobileNumber.setError("Mobile Number already exists");
                                return;
                            }
                        } else {
                            // Handle database error
                            Log.e("Firebase", "Error getting mobile number from database", task.getException());
                            // You may display a toast or handle the error in another way
                        }
                    }
                });
            }else {
                contactNo = "Not Specified";
            }


            fatherEmail = editTextFatherEmail.getText().toString();
            if (fatherEmail.isEmpty()) {
//                editTextFatherEmail.setError("Father Email is required");
//                return;
                fatherEmail = "Not Specified";
            } else {
                //Valid Email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(fatherEmail).matches()) {
                    editTextFatherEmail.setError("Invalid Email ");
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
                    return;
                }

                if (pin <= 0) {
                    editTextPincode.setError("Invalid Pincode");
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
                return;
            } else if (password.length() < 12) {
                editTextPassword.setError("Password must be greater than 12 characters");
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
                    return;
                }
            }

            confirmPassword = editTextConfirmPassword.getText().toString();
            if (confirmPassword.isEmpty()) {
                editTextConfirmPassword.setError("Confirm Password is required");
                return;
            }
            if (!password.equals(confirmPassword)) {
                editTextConfirmPassword.setError("Password do not match");
                return;
            }
        }catch (Exception e){
            Log.e("Error",e.getMessage());
            Log.i("Error",e.getMessage());
            Log.d("Error",e.getMessage());
            Log.v("Error",e.getMessage());
//            Intent intent = getIntent();
//            finish();
            Toast.makeText(this, "Invalid data Entered "+e.getMessage(), Toast.LENGTH_LONG).show();

//            startActivity(intent);
        }




        progressDialog.setTitle("Creating New Account");
        progressDialog.setMessage("please wait, While we are creating a new account for you...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        try{
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Name").setValue(name);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Degree").setValue(degree);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Branch").setValue(branch);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Section").setValue(Section);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Religion").setValue(Religion);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Category").setValue(category);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Mother Name").setValue(motherName);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Father Name").setValue(fatherName);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Gender").setValue(Gender);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Blood Group").setValue(BloodGroup);//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("DOB").setValue(DOB);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Enrollment Number").setValue(enrollmentNo);//d
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Academic Year").setValue(academicYear);//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Student Id").setValue(studentId);//
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Aadhar Number").setValue(adharNo);//D
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Personal Details").child("Semester").setValue(semester);//D


            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Email").setValue(email);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Mobile Number").setValue(contactNo);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Father Email").setValue(fatherEmail);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Permanent Address").setValue(permanentAddress);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Local Address").setValue(localAddress);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("City").setValue(district);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("State").setValue(state);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Pincode").setValue(pincode);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Country").setValue(Country);
            mDatabase.child("Student").child(String.valueOf(studentId)).child("Contact Details").child("Father Number").setValue(fatherNumber);

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


                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user =  mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                Toast.makeText(addStudent.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }catch (Exception e){
            Log.e("Error",e.getMessage());
            Log.i("Error",e.getMessage());
            Log.d("Error",e.getMessage());
            Log.v("Error",e.getMessage());
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Toast.makeText(this, "Invalid data Entered ", Toast.LENGTH_SHORT).show();
        }


    }




}
