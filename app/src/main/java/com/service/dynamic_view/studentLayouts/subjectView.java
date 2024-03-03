package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;
import com.service.dynamic_view.dashBoard;

public class subjectView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id = findViewById(R.id.title_id);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id.setText("Register Subjects");

//        addCards();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(subjectView.this, dashBoard.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {

        String name, subject;
        //Sample inputs
        name = "Teacher Name";
        subject = "Subject Name";
        addCard(name, subject);
    }

    @SuppressLint("MissingInflatedId")
    private void addCard(String name, String subject) {


        final View view2 =getLayoutInflater().inflate(R.layout.subjects,null);


//        TextView nameView = view2.findViewById(R.id.teacherID);
//        TextView subjectView = view2.findViewById(R.id.subId);

        // Set values to respective TextViews
//        nameView.setText(name);
//        subjectView.setText(subject);

        layout.addView(view2);
    }

}
