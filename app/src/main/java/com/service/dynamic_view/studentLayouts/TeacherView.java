package com.service.dynamic_view.studentLayouts;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class TeacherView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id=findViewById(R.id.title_id);
        title_id.setText("Teacher List");
        
        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    public void addCards() {

        String name, subject,subCode;
        //Sample inputs
        name = "Teacher Name";
        subject = "Subject Name";
        subCode="CS210";
        addCard(name, subject,subCode);
    }

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