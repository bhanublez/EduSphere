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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class examView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id = findViewById(R.id.title_id);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id.setText("Exams");

        addCards();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(examView.this, dashBoard.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye

        String name, subject, adata,sdata, data;
        int set;
        //Sample inputs
        name = "Teacher Name";
        subject = "Subject Name";
        adata = "2021-12-12";
        sdata = "2021-12-12";
        set = 100;
        data = "Sample Data";
        addCard(name, subject, new Date(), new Date(), set, data);
    }

    private void addCard(String name, String subject, Date adate, Date sdate, int set, String data) {


        final View view2 =getLayoutInflater().inflate(R.layout.examdialog,null);



        TextView nameView = view2.findViewById(R.id.teacherName_ve);
        TextView subjectView = view2.findViewById(R.id.subjectName_ve);

        TextView assDate = view2.findViewById(R.id.daetAss_ve);
        TextView subDate = view2.findViewById(R.id.examDate_ve);

        TextView mksId = view2.findViewById(R.id.markss_ve);
        TextView setId = view2.findViewById(R.id.txtArea_ve);

        ImageView delete=view2.findViewById(R.id.del_e);

        // Set values to respective TextViews
        nameView.setText(name);
        subjectView.setText(subject);
        // Assuming adate and sdate are Date objects and you want to display them as strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        assDate.setText(dateFormat.format(adate));
        subDate.setText(dateFormat.format(sdate));
        mksId.setText(String.valueOf(set)); // Assuming set is an integer
        setId.setText(data);

        //Remove the view of delete ImageView
        layout.removeView(delete);

        layout.addView(view2);
    }
}




