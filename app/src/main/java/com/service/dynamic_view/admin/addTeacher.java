package com.service.dynamic_view.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class addTeacher extends AppCompatActivity {

    //Personal Details
    private String name,degree,Department,Religion,category,motherName,fatherName,Gender,BloodGroup,DOB,teacherCollogeId,Experience;
    private String teacherID,aadharNumber;

    private String password,confirmPassword;


    //Contact Details
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String email,contactNo,fatherEmail,permanentAddress,localAddress,district,state,pincode,Country,fatherNumber;


    private EditText etName,etDegree,etDepartment,etReligion,etCategory,etMotherName,etFatherName,etGender,etBloodGroup,etDOB,etTeacherCollogeId,etExperience,etTeacherID,etAadharNumber;

    private EditText etPassword,etConfirmPassword;

    private EditText etEmail,etContactNo,etFatherEmail,etPermanentAddress,etLocalAddress,etDistrict,etState,etPincode,etCountry,etFatherNumber;

    private Button btnAddTeacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register_teacher);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        etName = findViewById(R.id.editTextName);
        etDegree = findViewById(R.id.editTextDegree);
        etDepartment = findViewById(R.id.editTextDepartment);
        etReligion = findViewById(R.id.editTextReligion);
        etCategory = findViewById(R.id.editTextCategory);
        etMotherName = findViewById(R.id.editTextMotherName);
        etFatherName = findViewById(R.id.editTextFatherName);
        etGender = findViewById(R.id.editTextGender);
        etBloodGroup = findViewById(R.id.editTextBloodGroup);
        etDOB = findViewById(R.id.editTextDOB);
        etTeacherCollogeId = findViewById(R.id.editTextTeacherCollegeId);
        etExperience = findViewById(R.id.editTextExperience);
        etTeacherID = findViewById(R.id.editTextTeacherId);
        etAadharNumber = findViewById(R.id.editTextAadharNumber);
        etEmail = findViewById(R.id.editTextEmail);
        etContactNo = findViewById(R.id.editTextPhoneNumber);
        etFatherEmail = findViewById(R.id.editTextFatherEmail);
        etPermanentAddress = findViewById(R.id.editTextPermanentAddress);
        etLocalAddress = findViewById(R.id.editTextTemporaryAddress);
        etDistrict = findViewById(R.id.editTextCity);
        etState = findViewById(R.id.editTextState);
        etPincode = findViewById(R.id.editTextPincode);
        etCountry = findViewById(R.id.editTextCountry);
        etFatherNumber = findViewById(R.id.editTextFatherNumber);
        etPassword = findViewById(R.id.editTextPassword);
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        btnAddTeacher = findViewById(R.id.buttonRegister);

       btnAddTeacher.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerTeacher();
           }
       });
    }
     private void registerTeacher() {
        try{
            name=etName.getText().toString();
            if(name.isEmpty()){
                etName.setError("Name is required");
                etName.requestFocus();
                return;
            }
            degree=etDegree.getText().toString();
            if(degree.isEmpty()){
                etDegree.setError("Degree is required");
                etDegree.requestFocus();
                return;
            }

            Department=etDepartment.getText().toString();
            if(Department.isEmpty()){
                etDepartment.setError("Department is required");
                etDepartment.requestFocus();
                return;
            }

            Religion=etReligion.getText().toString();
            if(Religion.isEmpty()){
                Religion="Not Specified";
            }

            category=etCategory.getText().toString();
            if(category.isEmpty()){
                category="Not Specified";
            }

            motherName=etMotherName.getText().toString();
            if(motherName.isEmpty()){
                motherName="Not Specified";
            }

            fatherName=etFatherName.getText().toString();
            if(fatherName.isEmpty()){
                fatherName="Not Specified";
            }

            Gender=etGender.getText().toString();
            if(Gender.isEmpty()){
                etGender.setError("Gender is required");
                etGender.requestFocus();
                return;
            }else{
                //Check Gender in male,female, and other
                String gender=Gender.toLowerCase();
                if(gender.equals("male") || gender.equals("female")||gender.equals("other") ){
                    Gender=gender;
                }else{
                    etGender.setError("Gender Should be Male, Female or other");
                    etGender.requestFocus();
                    return;
                }
            }

            BloodGroup=etBloodGroup.getText().toString();
            if(BloodGroup.isEmpty()){
                BloodGroup="Not Specified";
            }

            DOB = etDOB.getText().toString();
            if (DOB.isEmpty()) {
                etDOB.setError("Date of Birth is required in YYYY-MM-DD format");
                return;
            } else {
                //Check for valid formating of date
                String[] dateParts = DOB.split("-");
                if (dateParts.length != 3) {
                    etDOB.setError("Invalid date format");
                    return;
                }

                for (String part : dateParts) {
                    try {
                        Integer.parseInt(part);
                    } catch (NumberFormatException e) {
                        etDOB.setError("Invalid date format");
                        return;
                    }
                }

                Integer[] date = new Integer[dateParts.length];

                for (int i = 0; i < dateParts.length; i++) {
                    date[i] = Integer.parseInt(dateParts[i]);
                }
                if (date[1] < 1 || date[1] > 12) {
                    etDOB.setError("Invalid Month");
                    return;
                }
                if (date[2] < 1 || date[2] > 31) {
                    etDOB.setError("Invalid Day");
                    return;
                }
                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                if (date[0] < 1900 || date[0] > currentYear) {
                    etDOB.setError("Invalid Year");
                    return;
                }
            }

            teacherCollogeId=etTeacherCollogeId.getText().toString();
            if(teacherCollogeId.isEmpty()){
                etTeacherCollogeId.setError("Teacher College Id is required");
                etTeacherCollogeId.requestFocus();
                return;
            }

            Experience=etExperience.getText().toString();
            if(Experience.isEmpty()){
                etExperience.setError("Experience is required");
                etExperience.requestFocus();
                return;
            }else{
                try{
                    Integer.parseInt(Experience);
                }catch (NumberFormatException e){
                    etExperience.setError("Experience should be in years");
                    etExperience.requestFocus();
                    return;
                }
            }

            teacherID=etTeacherID.getText().toString();
            if(teacherID.isEmpty()){
                etTeacherID.setError("Teacher ID is required");
                etTeacherID.requestFocus();
                return;
            }

            aadharNumber=etAadharNumber.getText().toString();
            if(aadharNumber.isEmpty()){
                aadharNumber="Not Specified";
            }else{
                if(aadharNumber.length()!=12){
                    etAadharNumber.setError("Aadhar Number should be 12 digit");
                    etAadharNumber.requestFocus();
                    return;
                }

                try{
                    Long.parseLong(aadharNumber);
                }catch (NumberFormatException e){
                    etAadharNumber.setError("Aadhar Number should be numeric");
                    etAadharNumber.requestFocus();
                    return;
                }
            }

            email=etEmail.getText().toString();
            if(email.isEmpty()){
                etEmail.setError("Email is required");
                etEmail.requestFocus();
                return;
            }else{
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("Invalid Email");
                    etEmail.requestFocus();
                    return;
                }
            }

            contactNo=etContactNo.getText().toString();
            if(contactNo.isEmpty()){
                etContactNo.setError("Contact Number is required");
                etContactNo.requestFocus();
                return;
            }else{
                if(contactNo.length()!=10){
                    etContactNo.setError("Contact Number should be 10 digit");
                    etContactNo.requestFocus();
                    return;
                }

                try{
                    Long.parseLong(contactNo);
                }catch (NumberFormatException e){
                    etContactNo.setError("Contact Number should be numeric");
                    etContactNo.requestFocus();
                    return;
                }
            }

            fatherEmail=etFatherEmail.getText().toString();
            if(fatherEmail.isEmpty()){
                fatherEmail="Not Specified";
            }else{
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(fatherEmail).matches()){
                    etFatherEmail.setError("Invalid Email");
                    etFatherEmail.requestFocus();
                    return;
                }
            }

            permanentAddress=etPermanentAddress.getText().toString();
            if(permanentAddress.isEmpty()){
                permanentAddress="Not Specified";
            }

            localAddress=etLocalAddress.getText().toString();
            if(localAddress.isEmpty()){
                localAddress="Not Specified";
            }

            district=etDistrict.getText().toString();
            if(district.isEmpty()){
                district="Not Specified";
            }

            state=etState.getText().toString();
            if(state.isEmpty()){
                state="Not Specified";
            }

            pincode=etPincode.getText().toString();
            if(pincode.isEmpty()){
                pincode="Not Specified";
            }else{
                if(pincode.length()!=6){
                    etPincode.setError("Pincode should be 6 digit");
                    etPincode.requestFocus();
                    return;
                }

                try{
                    Long.parseLong(pincode);
                }catch (NumberFormatException e){
                    etPincode.setError("Pincode should be numeric");
                    etPincode.requestFocus();
                    return;
                }
            }

            Country=etCountry.getText().toString();
            if(Country.isEmpty()){
                Country="Not Specified";
            }

            fatherNumber=etFatherNumber.getText().toString();
            if(fatherNumber.isEmpty()){
                fatherNumber="Not Specified";
            }else{
                if(fatherNumber.length()!=10){
                    etFatherNumber.setError("Father Number should be 10 digit");
                    etFatherNumber.requestFocus();
                    return;
                }

                try{
                    Long.parseLong(fatherNumber);
                }catch (NumberFormatException e){
                    etFatherNumber.setError("Father Number should be numeric");
                    etFatherNumber.requestFocus();
                    return;
                }
            }

            password = etPassword.getText().toString();
            if (password.isEmpty()) {
                etPassword.setError("Password is required");
                return;
            } else if (password.length() < 12) {
                etPassword.setError("Password must be greater than 12 characters");
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
                    etPassword.setError(errorMessage);
                    return;
                }
            }

            confirmPassword = etConfirmPassword.getText().toString();
            if (confirmPassword.isEmpty()) {
                etConfirmPassword.setError("Confirm Password is required");
                return;
            } else if (!confirmPassword.equals(password)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Invalid Data Entered "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try{
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Name").setValue(name.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Degree").setValue(degree.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Department").setValue(Department.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Religion").setValue(Religion.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Category").setValue(category.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Mother Name").setValue(motherName.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Father Name").setValue(fatherName.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Gender").setValue(Gender.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Blood Group").setValue(BloodGroup.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("DOB").setValue(DOB.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Teacher College Id").setValue(teacherCollogeId.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Experience").setValue(Experience.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Teacher ID").setValue(teacherID.trim());
            mDatabase.child("Teacher").child(teacherID).child("Personal Details").child("Aadhar Number").setValue(aadharNumber.trim());

            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Email").setValue(email.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Contact Number").setValue(contactNo.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Father Email").setValue(fatherEmail.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Permanent Address").setValue(permanentAddress.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Local Address").setValue(localAddress.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("District").setValue(district.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("State").setValue(state.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Pincode").setValue(pincode.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Country").setValue(Country.trim());
            mDatabase.child("Teacher").child(teacherID).child("Contact Details").child("Father Number").setValue(fatherNumber.trim());

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String uid = user.getUid();
mDatabase.child("Authentication").child("Teacher").child(uid).child("TeacherId").setValue(teacherID.trim());
mDatabase.child("Authentication").child("Teacher").child(uid).child("Status").setValue("Offline");
mDatabase.child("Authentication").child("Teacher").child(uid).child("category").setValue("Teacher");

                    Toast.makeText(this, "Teacher Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(this, "Error in Registering Teacher "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error in Registering Teacher "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
     }
}
