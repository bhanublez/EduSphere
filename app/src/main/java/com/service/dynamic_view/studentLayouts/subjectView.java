package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.service.dynamic_view.R;

public class subjectView extends AppCompatActivity {
   private LinearLayout layout;
    private ImageView back;
    private TextView title_id ;
    String code, subject;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id=findViewById(R.id.title_id);
        title_id.setText("Register Subjects");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @Override
    protected void onResume() {
        super.onResume();
        addCards();
    }

    @SuppressLint("MissingInflatedId")
    public void addCards() {
        if (currentUser != null) {
            String userId = currentUser.getUid();
           //First DatabaseReference


        }



        addCard(code, subject);
    }

    @SuppressLint("MissingInflatedId")
    private void addCard(String ccode, String subject) {


        final View view2 =getLayoutInflater().inflate(R.layout.subjects,null);


        TextView code = view2.findViewById(R.id.subCode2);
        TextView subjectView = view2.findViewById(R.id.subId2);
        code.setText(ccode);
        subjectView.setText(subject);

        layout.addView(view2);
    }

}
