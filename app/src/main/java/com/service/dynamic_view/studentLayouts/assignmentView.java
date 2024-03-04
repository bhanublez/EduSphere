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

public class assignmentView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);
        title_id=findViewById(R.id.title_id);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id.setText("Assignments");

        addCards();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(assignmentView.this, dashBoard.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye

        String name, subject, adata,sdata, data;
        int set,mks;
        //Sample inputs
        name = "Teacher Name";
        subject = "Subject Name";
        adata = "2021-12-12";
        sdata = "2021-12-12";
        set = 100;
        mks = 100;
        data = "Sample Data";
        addCard(name, subject, adata, sdata, set, mks);
    }

    private void addCard(String name,String subject,String adate,String sdate,int set,int mks) {


 final View view2 =getLayoutInflater().inflate(R.layout.dialog_assignment,null);

        TextView nameView = view2.findViewById(R.id.teacherNaam_va);
        TextView subjectView = view2.findViewById(R.id.nameSub_va);
        TextView subDate = view2.findViewById(R.id.subDate_va);
        TextView assDate = view2.findViewById(R.id.assDate_va);
        TextView setId = view2.findViewById(R.id.setId_va);
        TextView mksId = view2.findViewById(R.id.marks_va);

        ImageView delete=view2.findViewById(R.id.delID);
        nameView.setText(name);
        subjectView.setText(subject);
        subDate.setText(sdate);
        assDate.setText(adate);
        setId.setText(String.valueOf(set));
        mksId.setText(String.valueOf(mks));

        delete.setVisibility(View.GONE);








        layout.addView(view2);
    }
}
