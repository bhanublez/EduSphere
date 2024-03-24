package com.service.dynamic_view.studentLayouts;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;

public class profileView extends AppCompatActivity {
    ImageView back;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;
    private  String adhar,studentId,Degree,Dob,Category,motherName,gender,fatherName,bloodGroup,Religion,section,name,enrollmentBumber;

    private String email,phone,fathernumber,fatherEmail,Peraddress,tempAdd,district,state,country,pincode,academicyear;
    TextView adharView,studentIdView,DegreeView,DobView,CategoryView,motherNameView,fatherNameView,genderView,bloodGroupView,ReligionView,sectionView,studentNameView,enrollmentNumberView ;

    TextView emailView,phoneView,fathernumberView,fatherEmailView,PeraddressView,tempAddView,districtView,stateView,countryView,pincodeView,acacdemicYearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        back=findViewById(R.id.backFromProfile);

        adharView = findViewById(R.id.sadharID);
        studentIdView = findViewById(R.id.studentIdP);
        DegreeView = findViewById(R.id.degreeId);
        sectionView = findViewById(R.id.sectionId);
        acacdemicYearView = findViewById(R.id.acacdemicYear);

        DobView = findViewById(R.id.dobId);
        CategoryView = findViewById(R.id.categoryId);
        motherNameView = findViewById(R.id.motherId);
        fatherNameView = findViewById(R.id.fatherId);
        genderView = findViewById(R.id.genderId);
        bloodGroupView = findViewById(R.id.bloodId);
        ReligionView = findViewById(R.id.religionId);

        emailView = findViewById(R.id.email_c);
        phoneView = findViewById(R.id.number_contact);
        fathernumberView = findViewById(R.id.father_c);
        fatherEmailView = findViewById(R.id.fatherEmail_c);
        PeraddressView = findViewById(R.id.permanentadd_c);
        tempAddView = findViewById(R.id.localadd_c);
        districtView = findViewById(R.id.district_c);
        countryView = findViewById(R.id.country_c);
        pincodeView = findViewById(R.id.pincode_c);
        stateView = findViewById(R.id.state);

        studentNameView = findViewById(R.id.studentName);
        enrollmentNumberView = findViewById(R.id.enrollmentNumber);






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

        if (currentUser != null) {

            String userId = currentUser.getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Student").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        studentId= dataSnapshot.child("StudentId").getValue().toString();


                        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("Student").child(studentId);

                        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {


                                    adhar = dataSnapshot.child("Personal Details").child("Aadhar Number").getValue().toString();
                                    Degree = dataSnapshot.child("Personal Details").child("Degree").getValue().toString();
                                    Dob = dataSnapshot.child("Personal Details").child("DOB").getValue().toString();
                                    Category = dataSnapshot.child("Personal Details").child("Category").getValue().toString();
                                    motherName = dataSnapshot.child("Personal Details").child("Mother Name").getValue().toString();
                                    fatherName = dataSnapshot.child("Personal Details").child("Father Name").getValue().toString();
                                     gender =dataSnapshot.child("Personal Details").child(("Gender")).getValue().toString();
                                    bloodGroup = dataSnapshot.child("Personal Details").child("Blood Group").getValue().toString();
                                    Religion = dataSnapshot.child("Personal Details").child("Religion").getValue().toString();
                                    section = dataSnapshot.child("Personal Details").child("Section").getValue().toString();
                                    academicyear = dataSnapshot.child("Personal Details").child("Academic Year").getValue().toString();
                                    addPersonal(adhar,studentId,Degree,Dob,Category,motherName,fatherName,gender,bloodGroup,Religion,section,academicyear);

                                    email = dataSnapshot.child("Contact Details").child("Email").getValue().toString();
                                    phone = dataSnapshot.child("Contact Details").child("Mobile Number").getValue().toString();
                                    fathernumber = dataSnapshot.child("Contact Details").child("Father Number").getValue().toString();//n
                                    fatherEmail = dataSnapshot.child("Contact Details").child("Father Email").getValue().toString();
                                    Peraddress = dataSnapshot.child("Contact Details").child("Permanent Address").getValue().toString();
                                    tempAdd = dataSnapshot.child("Contact Details").child("Local Address").getValue().toString();
                                    district = dataSnapshot.child("Contact Details").child("City").getValue().toString();//n
                                    state = dataSnapshot.child("Contact Details").child("State").getValue().toString();
                                    country = dataSnapshot.child("Contact Details").child("Country").getValue().toString();
                                    pincode = dataSnapshot.child("Contact Details").child("Pincode").getValue().toString();
                                    addContact(email,phone,fathernumber,fatherEmail,Peraddress,tempAdd,district,state,country,pincode);

                                    name = dataSnapshot.child("Personal Details").child("Name").getValue().toString();
                                    enrollmentBumber = dataSnapshot.child("Personal Details").child("Enrollment Number").getValue().toString();
                                    studentNameView.setText(name);
                                    enrollmentNumberView.setText(enrollmentBumber);





                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(profileView.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("Student").child(currentUser.getUid()).child("Authentication").child("Status").setValue("Offline");
                                mAuth.signOut();
                                }
                        });


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(profileView.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Student").child(currentUser.getUid()).child("Authentication").child("Status").setValue("Offline");
                    mAuth.signOut();
                    Toast.makeText(profileView.this, "Try Login Again", Toast.LENGTH_SHORT).show();
                }
            });
        }




    }
    private void addPersonal(String adhar,String studentId,String Degree,String Dob,String Category,String motherName,String fatherName,String gender,String bloodGroup,String Religion,String section,String academicyear){
        adharView.setText(adhar);
        studentIdView.setText(studentId);
        DegreeView.setText(Degree);
        DobView.setText(Dob);
        CategoryView.setText(Category);
        motherNameView.setText(motherName);
        fatherNameView.setText(fatherName);
        genderView.setText(gender);
        bloodGroupView.setText(bloodGroup);
        ReligionView.setText(Religion);
        sectionView.setText(section);
        acacdemicYearView.setText(academicyear);

    }
private void addContact(String email,String phone,String fathernumber,String fatherEmail,String Peraddress,String tempAdd,String district,String state,String country,String pincode){
        emailView.setText(email);
        phoneView.setText(phone);
        fathernumberView.setText(fathernumber);
        fatherEmailView.setText(fatherEmail);
        PeraddressView.setText(Peraddress);
        tempAddView.setText(tempAdd);
        districtView.setText(district);
        stateView.setText(state);
        countryView.setText(country);
        pincodeView.setText(pincode);
    }

    private void addData(String name,String enrllmentNumber){
//        //Add value to the profile
//        final View view2 =getLayoutInflater().inflate(R.layout.profile,null);
//        TextView nameView = view2.findViewById(R.id.studentName);
//        TextView enrllmentNumberView = view2.findViewById(R.id.enrollmentNumber);
//        String onld = nameView.getText().toString();
//        nameView.setText(name);
//        enrllmentNumberView.setText(enrllmentNumber);
//
//        Toast.makeText(this, "Data Added"+onld+" "+enrllmentNumber, Toast.LENGTH_SHORT).show();

    }

}
