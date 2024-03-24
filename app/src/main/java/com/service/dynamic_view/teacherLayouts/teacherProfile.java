package com.service.dynamic_view.teacherLayouts;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class teacherProfile extends AppCompatActivity {

    ImageView back;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;
    private  String adhar,teacherId,Degree,Dob,Category,motherName,gender,fatherName,bloodGroup,Religion,experience,name,teacherCollegeId,department;

    private String email,phone,fathernumber,fatherEmail,Peraddress,tempAdd,district,state,country,pincode;

    TextView teacherName,teacherIdText,teacherEmail,teacherPhone,teacherFatherName,teacherFatherNumber,teacherFatherEmail,teacherMotherName,teacherDob,teacherGender,teacherBloodGroup,teacherReligion,teacherCategory,teacherExperience,teacherDegree,teacherCollegeIdText,teacherDepartment,teacherPerAddress,teacherTempAddress,teacherDistrict,teacherState,teacherCountry,teacherPincode;
    TextView aadharNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        back=findViewById(R.id.backFromProfile);

        teacherName=findViewById(R.id.teacherNameId);
        teacherIdText=findViewById(R.id.teacherID);
        teacherEmail=findViewById(R.id.email_c);
        teacherPhone=findViewById(R.id.number_contact);
        teacherFatherName=findViewById(R.id.fatherId);
        teacherFatherNumber=findViewById(R.id.father_c);
        teacherFatherEmail=findViewById(R.id.fatherEmail_c);
        teacherMotherName=findViewById(R.id.motherId);
        teacherDob=findViewById(R.id.dobId);
        teacherGender=findViewById(R.id.genderId);
        teacherBloodGroup=findViewById(R.id.bloodId);
        teacherReligion=findViewById(R.id.religionId);
        teacherCategory=findViewById(R.id.categoryId);
        teacherExperience=findViewById(R.id.experienceID);
        teacherDegree=findViewById(R.id.degreeId);
        teacherCollegeIdText=findViewById(R.id.teacherColloge_Id);
        teacherDepartment=findViewById(R.id.departmentID);
        teacherPerAddress=findViewById(R.id.permanentadd_c);
        teacherTempAddress=findViewById(R.id.localadd_c);
        teacherDistrict=findViewById(R.id.district_c);
        teacherState=findViewById(R.id.state);
        teacherCountry=findViewById(R.id.country_c);
        teacherPincode=findViewById(R.id.pincode_c);
        aadharNumber=findViewById(R.id.sadharID);

        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @Override
    protected void onResume() {
        super.onResume();
        addValues();
    }

    private void addValues() {
        if(currentUser!=null) {
            String uid = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Teacher").child(uid);
            Log.d("TAG", "addValues: "+uid);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        teacherId=snapshot.child("TeacherId").getValue().toString();
                        Log.d("TAG", "addValues: "+teacherId);
                        DatabaseReference teacherRef = FirebaseDatabase.getInstance().getReference().child("Teacher").child(teacherId);
                        teacherRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()) {
//                                    Toast.makeText(teacherProfile.this, "Data Found", Toast.LENGTH_SHORT).show();
                                    adhar=snapshot.child("Personal Details").child("Aadhar Number").getValue().toString();
                                    Degree=snapshot.child("Personal Details").child("Degree").getValue().toString();
                                    Dob=snapshot.child("Personal Details").child("DOB").getValue().toString();
                                    Category=snapshot.child("Personal Details").child("Category").getValue().toString();
                                    motherName=snapshot.child("Personal Details").child("Mother Name").getValue().toString();
                                    fatherName=snapshot.child("Personal Details").child("Father Name").getValue().toString();
                                    bloodGroup=snapshot.child("Personal Details").child("Blood Group").getValue().toString();
                                    Religion=snapshot.child("Personal Details").child("Religion").getValue().toString();
                                    experience=snapshot.child("Personal Details").child("Experience").getValue().toString();
                                    name=snapshot.child("Personal Details").child("Name").getValue().toString();
                                    teacherCollegeId=snapshot.child("Personal Details").child("Teacher College Id").getValue().toString();
                                    department=snapshot.child("Personal Details").child("Department").getValue().toString();
                                    gender=snapshot.child("Personal Details").child("Gender").getValue().toString();
                                    adhar=snapshot.child("Personal Details").child("Aadhar Number").getValue().toString();
//
                                    email=snapshot.child("Contact Details").child("Email").getValue().toString();
                                    phone=snapshot.child("Contact Details").child("Contact Number").getValue().toString();
                                    fathernumber=snapshot.child("Contact Details").child("Father Number").getValue().toString();
                                    fatherEmail=snapshot.child("Contact Details").child("Father Email").getValue().toString();
                                    Peraddress=snapshot.child("Contact Details").child("Permanent Address").getValue().toString();
                                    tempAdd=snapshot.child("Contact Details").child("Local Address").getValue().toString();
                                    district=snapshot.child("Contact Details").child("District").getValue().toString();
                                    state=snapshot.child("Contact Details").child("State").getValue().toString();
                                    country=snapshot.child("Contact Details").child("Country").getValue().toString();
                                    pincode=snapshot.child("Contact Details").child("Pincode").getValue().toString();
//
                                    teacherName.setText(name);
                                    teacherIdText.setText(teacherId);
                                    teacherEmail.setText(email);
                                    teacherPhone.setText(phone);
                                    teacherFatherName.setText(fatherName);
                                    teacherFatherNumber.setText(fathernumber);
                                    teacherFatherEmail.setText(fatherEmail);
                                    teacherMotherName.setText(motherName);
                                    teacherDob.setText(Dob);
                                    teacherGender.setText(gender);
                                    teacherBloodGroup.setText(bloodGroup);
                                    teacherReligion.setText(Religion);
                                    teacherCategory.setText(Category);
                                    teacherExperience.setText(experience);
                                    teacherDegree.setText(Degree);
                                    teacherCollegeIdText.setText(teacherCollegeId);
                                    teacherDepartment.setText(department);
                                    teacherPerAddress.setText(Peraddress);
                                    teacherTempAddress.setText(tempAdd);
                                    teacherDistrict.setText(district);
                                    teacherState.setText(state);
                                    teacherCountry.setText(country);
                                    teacherPincode.setText(pincode);
                                    aadharNumber.setText(adhar);




                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
