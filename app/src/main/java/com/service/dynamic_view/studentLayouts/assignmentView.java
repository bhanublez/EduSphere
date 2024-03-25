package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;

public class assignmentView extends AppCompatActivity {
    LinearLayout layout;
    ImageView back;
    TextView title_id ;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private DatabaseReference mDatabase;

    private String branch, semester, section,studentId;
    private String subjectName,teacherName,subjectCode,data,marks,submissionDate,assignmentDate,set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontainer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        title_id=findViewById(R.id.title_id);
        layout= findViewById(R.id.viewContainer);
        back=findViewById(R.id.back_icon);
        title_id.setText("Assignments");
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
    @SuppressLint("MissingInflatedId")
    public void addCards() {
        //Database of Exam sai hum haha data leke aaye


//        String name, subject, adata,sdata, data;
//        int set,mks;
//        //Sample inputs
//        name = "Teacher Name";
//        subject = "Subject Name";
//        adata = "2021-12-12";
//        sdata = "2021-12-12";
//        set = 100;
//        mks = 100;
//        data = "Sample Data";
//        addCard(name, subject, adata, sdata, set, mks);

        DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference().child("studentAttendance").child(branch).child(semester).child(section).child("assignment");
        attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    System.out.println("Snap: " + snap);
                    subjectName = snap.getKey();
                    for (DataSnapshot snap2 : snap.getChildren()) {
//                        System.out.println("Snap2: " + snap2);
                        System.out.println(snap2.getKey());
//                        System.out.println(snap2.getValue());
//                        String assignNumber=snap2.getKey();
                        assignmentDate = snap2.child("Assigned_Date").getValue().toString();
                        submissionDate = snap2.child("Submission_Date").getValue().toString();
                        teacherName = snap2.child("Teacher_Name").getValue().toString();
                        set = snap2.child("Set").getValue().toString();
                        marks = snap2.child("Marks").getValue().toString();
                        data = snap2.child("data").getValue().toString();
//                        System.out.println(assignmentDate+ " " + submissionDate + " " + teacherName + " " + set + " " + marks + " " + data);
                        if (data.equals("null")) {
                            data = "No Data";
                        }
                        addCard(teacherName, subjectName, assignmentDate, submissionDate, Integer.parseInt(set), Integer.parseInt(marks),data);

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void addCard(String name,String subject,String adate,String sdate,int set,int mks,String data) {


 final View view2 =getLayoutInflater().inflate(R.layout.dialog_assignment,null);

        TextView nameView = view2.findViewById(R.id.teacherNaam_va);
        TextView subjectView = view2.findViewById(R.id.nameSub_va);
        TextView subDate = view2.findViewById(R.id.subDate_va);
        TextView assDate = view2.findViewById(R.id.assDate_va);
        TextView setId = view2.findViewById(R.id.setId_va);
        TextView mksId = view2.findViewById(R.id.marks_va);
        EditText dataView = view2.findViewById(R.id.data_c);
        dataView.setText(data);
        dataView.setEnabled(false);

        ImageView delete=view2.findViewById(R.id.delID);
        nameView.setText(name);
        subjectView.setText(subject);
        subDate.setText(sdate);
        assDate.setText(adate);
        setId.setText(String.valueOf(set));
        mksId.setText(String.valueOf(mks));

        delete.setVisibility(View.GONE);

//separator
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
