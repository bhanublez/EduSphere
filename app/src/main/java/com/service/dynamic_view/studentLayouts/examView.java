package com.service.dynamic_view.studentLayouts;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class examView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id=findViewById(R.id.title_id);
        title_id.setText("Exams");

        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye

        String name, subject, adata,sdata, data;
        int set;
        //Sample inputs
        name = "Teacher Naam";
        subject = "Subject Naam";
        adata = "2021-12-12";
        sdata = "2021-12-12";
        set = 100;
        data = "Sample Data";
        addCard(name, subject, adata,sdata, set, data);
    }

    private void addCard(String name, String subject, String adate, String sdate, int set, String data) {


        final View view2 =getLayoutInflater().inflate(R.layout.examdialog,null);



        TextView nameView = view2.findViewById(R.id.teacherName_ve);
        TextView subjectView = view2.findViewById(R.id.subjectName_ve);
//
        TextView assDate = view2.findViewById(R.id.daetAss_ve);
        TextView subDate = view2.findViewById(R.id.examDate_ve);
//
        TextView mksId = view2.findViewById(R.id.markss_ve);
        TextView setId = view2.findViewById(R.id.txtArea_ve);
//
        ImageView delete=view2.findViewById(R.id.del_e);

        // Set values to respective TextViews
        nameView.setText(name);
        subjectView.setText(subject);
        assDate.setText(adate);
        subDate.setText(sdate);
        mksId.setText(String.valueOf(set));
        setId.setText(data);
        //diable the setId
        setId.setEnabled(false);

        //Remove the view of delete ImageView
        delete.setVisibility(View.GONE);

        layout.addView(view2);
    }
}




