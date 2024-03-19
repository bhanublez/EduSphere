package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class studentTable extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeslot);
        layout= findViewById(R.id.tableContainer);
        back=findViewById(R.id.back_icon);
        add = findViewById(R.id.add_Slot);
        add.setVisibility(View.GONE);

        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye

        String subjectName, teacherName,startTime, endTime, subjectCode;
        int period;
        //Sample inputs
        subjectName = "Teacher Naam";
        teacherName = "Subject Naam";
        startTime = "2021-12-12";
        endTime = "2021-12-12";
        subjectCode = "CS 800";
        period = 2;
        addCard(subjectName, teacherName, startTime,endTime, subjectCode, period);
    }

    private void addCard(String subjectName, String teacherName, String startTime, String endTime, String subjectCode, int period) {


        final View view2 =getLayoutInflater().inflate(R.layout.timedialog,null);
        TextView duration = view2.findViewById(R.id.duration_slot);
        TextView periodView = view2.findViewById(R.id.periodNumber);
        TextView subjectNameView = view2.findViewById(R.id.subject_naaam);
        TextView teacherNameView = view2.findViewById(R.id.teacher_nameS);
        TextView subjectCodeView = view2.findViewById(R.id.subject_code);
        ImageView delete = view2.findViewById(R.id.del_e);
        delete.setVisibility(View.GONE);
        delete.setEnabled(false);
//

        duration.setText(startTime + " - " + endTime);
        periodView.setText("Period " + period);
        subjectNameView.setText(subjectName);
        teacherNameView.setText(teacherName);
        subjectCodeView.setText(subjectCode);

        layout.addView(view2);
    }
}
