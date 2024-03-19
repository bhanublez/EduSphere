package com.service.dynamic_view.admin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class addStudent extends AppCompatActivity {

    //Personal Details
    private String name,degree,branch,Section,Religion,category,motherName,fatherName,Gender,BloodGroup,DOB,enrollmentNo,academicYear;
    private int studentId,adharNo;

    private String password,confirmPassword;

    //Contact Details
    private String email,contactNo,fatherEmail,permanentAddress,localAddress,district,state,pincode,Country;

    //Firbase Data Base
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_registraion_student);

        //Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        try {
            //Get Data from the form
            name = String.valueOf(findViewById(R.id.editTextName));
            degree = String.valueOf(findViewById(R.id.editTextDegree));
            Section = String.valueOf(findViewById(R.id.editTextSection));
            Religion = String.valueOf(findViewById(R.id.editTextReligion));
            category = String.valueOf(findViewById(R.id.editTextCategory));
            motherName = String.valueOf(findViewById(R.id.editTextMotherName));
            fatherName = String.valueOf(findViewById(R.id.editTextFatherName));
            branch = String.valueOf(findViewById(R.id.editTextBranch));
            Gender = String.valueOf(findViewById(R.id.editTextGender));
            BloodGroup = String.valueOf(findViewById(R.id.editTextBloodGroup));
            DOB = String.valueOf(findViewById(R.id.editTextDOB));
            enrollmentNo = String.valueOf(findViewById(R.id.editTextEnrollmentNumber));
            academicYear = String.valueOf(findViewById(R.id.editTextAcademicYear));

            studentId = Integer.parseInt(String.valueOf(findViewById(R.id.editTextStudentId)));

            adharNo = Integer.parseInt(String.valueOf(findViewById(R.id.editTextAdhar)));
            email = String.valueOf(findViewById(R.id.editTextEmail));
            contactNo = String.valueOf(findViewById(R.id.editTextMobileNumber));
            fatherEmail = String.valueOf(findViewById(R.id.editTextFatherEmail));
            permanentAddress = String.valueOf(findViewById(R.id.editTextAddress));
            localAddress = String.valueOf(findViewById(R.id.editTextLocalAddress));
            district = String.valueOf(findViewById(R.id.editTextCity));
            state = String.valueOf(findViewById(R.id.editTextState));
            pincode = String.valueOf(findViewById(R.id.editTextPincode));
            Country = String.valueOf(findViewById(R.id.editTextCountry));

            //Password and Confirm password
            password = String.valueOf(findViewById(R.id.editTextPassword));
            confirmPassword = String.valueOf(findViewById(R.id.editTextConfirmPassword));

        }catch (Exception e){
            Log.e("Error",e.getMessage());
            Log.i("Error",e.getMessage());
            Log.d("Error",e.getMessage());
            Log.v("Error",e.getMessage());
        }
//Event driven programming
        





    }
}
