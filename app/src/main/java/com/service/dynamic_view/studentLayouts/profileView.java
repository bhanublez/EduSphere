package com.service.dynamic_view.studentLayouts;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class profileView extends AppCompatActivity {
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        back=findViewById(R.id.backFromProfile);

        addValues();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    private void addValues() {
       //sample inputs
        //add Personal
        String adhar,studentId,Degree,Dob,Category,motherName,fatherName,gender,bloodGroup,Religion,section;
        adhar="123456789";
        studentId="123456789";
        Degree="B.Tech";
        Dob="12/12/1999";
        Category="General";
        motherName="Mother Name";
        fatherName="Father Name";
        gender="Male";
        bloodGroup="O+";
        Religion="Hindu";
        section="A";
        addPersonal(adhar,studentId,Degree,Dob,Category,motherName,fatherName,gender,bloodGroup,Religion,section);

        //add Contact
        String email,phone,fathernumber,fatherEmail,Peraddress,tempAdd,district,state,country,pincode;
        email="abdc@gmail.com";
        phone="123456789";
        fathernumber="123456789";
        fatherEmail="Ravi";
        Peraddress="Delhi";
        tempAdd="Delhi";
        district="Delhi";
        state="Delhi";
        country="India";
        pincode="110001";
//        addContact(email,phone,fathernumber,fatherEmail,Peraddress,tempAdd,district,state,country,pincode);


        //add Data
        String name,enrllmentNumber;
        name="Ravi";
        enrllmentNumber="123456789";
        addData(name,enrllmentNumber);

    }
    private void addPersonal(String adhar,String studentId,String Degree,String Dob,String Category,String motherName,String fatherName,String gender,String bloodGroup,String Religion,String section){
        //Add value to the profile
        final View view2 =getLayoutInflater().inflate(R.layout.profile,null);
        TextView adharView = view2.findViewById(R.id.text);
        TextView studentIdView = view2.findViewById(R.id.text_3);
//        TextView DegreeView = view2.findViewById(R.id.text_5);
//        TextView DobView = view2.findViewById(R.id.text_6);
//        TextView CategoryView = view2.findViewById(R.id.text_10_ek3);
        TextView motherNameView = view2.findViewById(R.id.text_9);
//        TextView fatherNameView = view2.findViewById(R.id.text_8);
//        TextView genderView = view2.findViewById(R.id.text_10);
//        TextView bloodGroupView = view2.findViewById(R.id.text_10_ek1);
//        TextView ReligionView = view2.findViewById(R.id.text_10_ek2);
//        TextView sectionView = view2.findViewById(R.id.text_4);
        adharView.setText(adhar);
//        studentIdView.setText(studentId);
//        DegreeView.setText(Degree);
//        DobView.setText(Dob);
//        CategoryView.setText(Category);
        motherNameView.setText(motherName);
//        fatherNameView.setText(fatherName);
//        genderView.setText(gender);
//        bloodGroupView.setText(bloodGroup);
//        ReligionView.setText(Religion);
//        sectionView.setText(section);

    }
private void addContact(String email,String phone,String fathernumber,String fatherEmail,String Peraddress,String tempAdd,String district,String state,String country,String pincode){
        //Add value to the profile
        final View view2 =getLayoutInflater().inflate(R.layout.profile,null);
           TextView emailView = view2.findViewById(R.id.email_c);
              TextView phoneView = view2.findViewById(R.id.number_contact);
                TextView fathernumberView = view2.findViewById(R.id.father_c);
                    TextView fatherEmailView = view2.findViewById(R.id.father_email);
                        TextView PeraddressView = view2.findViewById(R.id.permanentadd_c);
                        TextView tempAddView = view2.findViewById(R.id.localadd_c);
                            TextView districtView = view2.findViewById(R.id.district_c);
                                TextView countryView = view2.findViewById(R.id.country_c);
                                TextView pincodeView = view2.findViewById(R.id.pincode_c);
                                emailView.setText(email);
                                phoneView.setText(phone);
                                fathernumberView.setText(fathernumber);
                                fatherEmailView.setText(fatherEmail);
                                PeraddressView.setText(Peraddress);
                                tempAddView.setText(tempAdd);
                                districtView.setText(district);
                                countryView.setText(country);
                                pincodeView.setText(pincode);
    }

    private void addData(String name,String enrllmentNumber){
        //Add value to the profile
        final View view2 =getLayoutInflater().inflate(R.layout.profile,null);
        TextView nameView = view2.findViewById(R.id.studentName);
        TextView enrllmentNumberView = view2.findViewById(R.id.enrollmentNumber);
        String onld = nameView.getText().toString();
        nameView.setText(name);
        enrllmentNumberView.setText(enrllmentNumber);

        Toast.makeText(this, "Data Added"+onld+" "+enrllmentNumber, Toast.LENGTH_SHORT).show();

    }

}
