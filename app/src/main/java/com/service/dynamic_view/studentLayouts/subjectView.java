package com.service.dynamic_view.studentLayouts;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Student").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String studentId= dataSnapshot.child("StudentId").getValue().toString();


                        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("Student").child(studentId);
                        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String department = dataSnapshot.child("Personal Details").child("Branch").getValue(String.class);
                                    String semester = dataSnapshot.child("Personal Details").child("Semester").getValue(String.class);

                                    // Check if the department and semester data exist
                                    if (department != null && semester != null) {
                                        // Construct the path to the subjects based on department and semester
                                        String pathToSubjects = "Subjects/" + department + "/" + semester;

                                        // Retrieve the subjects data for the given department and semester
                                        DataSnapshot subjectsSnapshot = dataSnapshot.child(pathToSubjects);
                                        DatabaseReference subjectsRef = FirebaseDatabase.getInstance().getReference().child("Subjects").child(department).child(semester);
                                        subjectsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    for (DataSnapshot subjectSnapshot : dataSnapshot.getChildren()) {
                                                        code = subjectSnapshot.getKey();
                                                        subject = subjectSnapshot.getValue(String.class);
                                                        addCard(code, subject);
                                                    }
                                                } else {
                                                    // Handle case where subjects data doesn't exist
                                                    Log.d(TAG, "Subjects data doesn't exist");
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError databaseError) {

                                            }
                                        });
                                    } else {
                                        // Handle case where department or semester data is missing
                                        Log.d(TAG, "Department or semester data is missing");
                                    }
                                } else {
                                    // Handle case where user data doesn't exist
                                    Log.d(TAG, "User data doesn't exist");
                                }

                            }

                            @Override
                            public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError databaseError) {

                            }
                        });

                    }
                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError databaseError) {

                }
            });


        }
    }

    @SuppressLint("MissingInflatedId")
    private void addCard(String ccode, String subject) {


        final View view2 =getLayoutInflater().inflate(R.layout.subjects,null);


        TextView code = view2.findViewById(R.id.subCode2);
        TextView subjectView = view2.findViewById(R.id.subId2);
        code.setText(ccode);
        subjectView.setText(subject);
        //Create margin in view
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 20);
        view2.setLayoutParams(params);

        layout.addView(view2);
    }

}
