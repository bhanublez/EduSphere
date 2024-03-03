package com.service.dynamic_view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id = findViewById(R.id.title_id);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id.setText("Teacher List");
        
//        addCards();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherView.this, dashBoard.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {

        String name, subject,subCode;
        //Sample inputs
        name = "Teacher Name";
        subject = "Subject Name";
        subCode="CS210";
        addCard(name, subject,subCode);
    }

    @SuppressLint("MissingInflatedId")
    private void addCard(String name, String subject,String subCode) {


        final View view2 =getLayoutInflater().inflate(R.layout.teacherdialog,null);


        TextView nameView = view2.findViewById(R.id.teacherID1);
        TextView subjectView = view2.findViewById(R.id.subId1);
        TextView subjectCView = view2.findViewById(R.id.subCode1);


        // Set values to respective TextViews
        nameView.setText(name);
        subjectView.setText(subject);
        subjectCView.setText(subCode);

        layout.addView(view2);
    }

}