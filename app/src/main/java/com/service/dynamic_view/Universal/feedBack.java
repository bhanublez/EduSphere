package com.service.dynamic_view.Universal;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

import java.util.Date;

public class feedBack extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private DatabaseReference mDatabase;
    private TextView name,email,feedback;
    private String branch, semester, section,studentId,emailId;
    private Spinner category;
    private String naam,mail,feed,cat;
    private Button submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userfeedback);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        studentId=getIntent().getStringExtra("studentId");
        branch=getIntent().getStringExtra("branch");
        semester=getIntent().getStringExtra("semester");
        section=getIntent().getStringExtra("section");
        emailId=getIntent().getStringExtra("email");
        name=findViewById(R.id.editTextName);
        email=findViewById(R.id.editTextEmail);
        feedback=findViewById(R.id.editTextFeedback);
        category=findViewById(R.id.spinnerFeedbackType);
        submit=findViewById(R.id.buttonSubmit);
        email.setText(emailId);
        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("Student").child(studentId);
        if(studentRef!=null){
            studentRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    naam=task.getResult().child("Personal Details").child("Name").getValue().toString();
                    name.setText(naam);
                    mail=task.getResult().child("Contact Details").child("Email").getValue().toString();
                    email.setText(mail);
                }
            });
        }

//        DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child("ID").child(studentId);


        submit.setOnClickListener(v -> {
            feed=feedback.getText().toString();
            cat=category.getSelectedItem().toString();
            if(feed.isEmpty()){
                feedback.setError("Please enter feedback");
                feedback.requestFocus();
                return;
            }
            DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference().child("Feedback").child("ID").child(studentId);
            if(feedbackRef!=null){
                DatabaseReference feedbackarray = feedbackRef.push();
                feedbackarray.child("Name").setValue(naam);
                feedbackarray.child("Email").setValue(mail);
                feedbackarray.child("Feedback").setValue(feed);
                feedbackarray.child("Category").setValue(cat);
                Date date = new Date();
                feedbackarray.child("Date").setValue(date.toString());
            }
            //S
            Toast.makeText(this, "Feedback Posted", Toast.LENGTH_SHORT).show();
            finish();
        });














        ImageView back = findViewById(R.id.back_icon);
        back.setOnClickListener(v -> {
            finish();
        }  );


    }
}
