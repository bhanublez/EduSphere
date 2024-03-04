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

public class subjectView extends AppCompatActivity {
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
        title_id.setText("Register Subjects");

        addCards();
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

        String code, subject;
        //Sample inputs
        code = "Teacher Name";
        subject = "Subject Name";
        addCard(code, subject);
    }

    @SuppressLint("MissingInflatedId")
    private void addCard(String ccode, String subject) {


        final View view2 =getLayoutInflater().inflate(R.layout.subjects,null);


        TextView code = view2.findViewById(R.id.subCode2);
        TextView subjectView = view2.findViewById(R.id.subId2);

//         Set values to respective TextViews
        code.setText(ccode);
        subjectView.setText(subject);

        layout.addView(view2);
    }

}
