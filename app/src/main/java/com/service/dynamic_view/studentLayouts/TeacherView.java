package com.service.dynamic_view.studentLayouts;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
import com.service.dynamic_view.R;

public class TeacherView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id ;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private DatabaseReference mDatabase;

    private String branch, semester, section,studentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id=findViewById(R.id.title_id);
        title_id.setText("Teacher List");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        studentId=getIntent().getStringExtra("studentId");
        branch=getIntent().getStringExtra("branch");
        semester=getIntent().getStringExtra("semester");
        section=getIntent().getStringExtra("section");


        addCards();
        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    public void addCards() {
        DatabaseReference teacherIdList= mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(section).child("Teacher");
        teacherIdList.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
//                String data=task.getResult().getValue().toString();
//                System.out.println(data);

                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        String teacherId = childSnapshot.getValue().toString();
//                        System.out.println(teacherId);
                        DatabaseReference teacherDetails = mDatabase.child("Teacher").child(teacherId);
                        teacherDetails.get().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                DataSnapshot dataSnapshot1 = task1.getResult();
                               String username = dataSnapshot1.child("Personal Details").child("Name").getValue().toString();
                               DataSnapshot sub=dataSnapshot1.child("Teaches").child(branch).child(semester).child(section);
                               for(DataSnapshot index:sub.getChildren()){
//                                   System.out.println(index.getKey());
//                                   System.out.println(index.getValue());//{Project Management=CS-604(E2)}
                                   for (DataSnapshot subIndex:index.getChildren()) {
                                        String subject = subIndex.getKey();
                                        String subCode = subIndex.getValue().toString();
                                        addCard(username, subject,subCode);
                                   }

                               }
                            }
                        });
                    }
                }
            }
        });


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

        //Separation set
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        View view3 = new View(this);
        view3.setLayoutParams(params);
        view3.setBackgroundColor(getResources().getColor(R.color.black));
        layout.addView(view3);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 0, 0, 15);
        view2.setLayoutParams(paramss);




        layout.addView(view2);
    }

}