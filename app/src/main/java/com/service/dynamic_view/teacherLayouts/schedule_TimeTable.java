package com.service.dynamic_view.teacherLayouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;

public class schedule_TimeTable extends AppCompatActivity {
    private LinearLayout layout;
    private ImageView back;
    private ImageView add;

    private TextView tabMon, tabTue, tabWed, tabThu, tabFri, tabSat, tabSun;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private String branch, semester, section, subjectName,teacherId,room,subjectCode,start,end,period;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_schedule_table);
        layout= findViewById(R.id.tableContainer);
        back=findViewById(R.id.back_icon);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        teacherId=getIntent().getStringExtra("teacherId");


        tabMon = findViewById(R.id.tabMon);
        tabTue = findViewById(R.id.tabTue);
        tabWed = findViewById(R.id.tabWed);
        tabThu = findViewById(R.id.tabThu);
        tabFri = findViewById(R.id.tabFri);
        tabSat = findViewById(R.id.tabSat);
        tabSun = findViewById(R.id.tabSun);

        addCards("Monday");

        tabMon.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabMon.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabMon.setTextColor(getResources().getColor(R.color.white));

            addCards("Monday");
        });

        tabTue.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabTue.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabTue.setTextColor(getResources().getColor(R.color.white));

            addCards("Tuesday");
        });

        tabWed.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabWed.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabWed.setTextColor(getResources().getColor(R.color.white));

            addCards("Wednesday");
        });

        tabThu.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabThu.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabThu.setTextColor(getResources().getColor(R.color.white));

            addCards("Thursday");
        });

        tabFri.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabFri.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabFri.setTextColor(getResources().getColor(R.color.white));

            addCards("Friday");
        });


        tabSat.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabSat.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabSat.setTextColor(getResources().getColor(R.color.white));

            addCards("Saturday");
        });

        tabSun.setOnClickListener(v -> {
            layout.removeAllViews();
            makeAllTabsWhite();
            tabSun.setBackgroundColor(getResources().getColor(R.color.ellipse_2_color));
            tabSun.setTextColor(getResources().getColor(R.color.white));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //orien
            params.setMargins(0, 0, 0, 15);



            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            //Add gravity center
            textView.setGravity(1);
            textView.setText("Holiday Enjoy!");
            textView.setTextSize(40);
            textView.setTextColor(getResources().getColor(R.color.adminRed));
            textView.setPadding(10, 10, 10, 10);
            layout.addView(textView);


        });



        back.setOnClickListener(v -> {
            finish();
        }  );

    }

    private void makeAllTabsWhite() {
        tabMon.setBackgroundColor(getResources().getColor(R.color.white));
        tabTue.setBackgroundColor(getResources().getColor(R.color.white));
        tabWed.setBackgroundColor(getResources().getColor(R.color.white));
        tabThu.setBackgroundColor(getResources().getColor(R.color.white));
        tabFri.setBackgroundColor(getResources().getColor(R.color.white));
        tabSat.setBackgroundColor(getResources().getColor(R.color.white));
        tabSun.setBackgroundColor(getResources().getColor(R.color.white));

        //change there text color to black
        tabMon.setTextColor(getResources().getColor(R.color.black));
        tabTue.setTextColor(getResources().getColor(R.color.black));
        tabWed.setTextColor(getResources().getColor(R.color.black));
        tabThu.setTextColor(getResources().getColor(R.color.black));
        tabFri.setTextColor(getResources().getColor(R.color.black));
        tabSat.setTextColor(getResources().getColor(R.color.black));
        tabSun.setTextColor(getResources().getColor(R.color.black));

    }


    public void addCards(String day) {
        DatabaseReference ref = mDatabase.child("Teacher").child(teacherId).child("Schedule").child(day);
       ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
//               Toast.makeText(schedule_TimeTable.this, "Entered", Toast.LENGTH_SHORT).show();
               int i = 0;
               for (DataSnapshot periodSnapshot : snapshot.getChildren()) {
                   System.out.println(periodSnapshot.getKey());
                   period = periodSnapshot.getKey();
                     branch = periodSnapshot.child("branch").getValue().toString();
                        semester = periodSnapshot.child("semester").getValue().toString();
                        section = periodSnapshot.child("section").getValue().toString();
                        subjectName = periodSnapshot.child("subjectName").getValue().toString();
                        room = periodSnapshot.child("room").getValue().toString();
                        subjectCode = periodSnapshot.child("subjectCode").getValue().toString();
                        start = periodSnapshot.child("start").getValue().toString();
                        end = periodSnapshot.child("end").getValue().toString();
//                        System.out.println(branch + " " + semester + " " + section + " " + subjectName + " " + room + " " + subjectCode + " " + start + " " + end);
                       add(branch, semester, section, subjectName, room, subjectCode, start, end, period);

               }

               }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    public void add(String branch, String semester, String section, String subjectName, String room, String subjectCode, String start, String end, String period) {
        final View view = LayoutInflater.from(this).inflate(R.layout.teacher_sch_cont, null);
        TextView branchText = view.findViewById(R.id.branch_name);
        TextView semesterText = view.findViewById(R.id.semester_name);
        TextView sectionText = view.findViewById(R.id.section_name);
        TextView subjectNameText = view.findViewById(R.id.subject_naaam);
        TextView roomText = view.findViewById(R.id.room_number);
        TextView subjectCodeText = view.findViewById(R.id.subject_code);
        TextView timeText = view.findViewById(R.id.start_end_time);
        TextView periodText = view.findViewById(R.id.periodNumber);

        branchText.setText(branch);
        semesterText.setText(semester);
        sectionText.setText(section);
        subjectNameText.setText(subjectName);
        roomText.setText(room);
        subjectCodeText.setText(subjectCode);
        timeText.setText(start + " - " + end);
        periodText.setText("Period "+period);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        View view2 = new View(this);
        view2.setLayoutParams(params);
        view2.setBackgroundColor(getResources().getColor(R.color.black));
        layout.addView(view2);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 0, 0, 15);
        view.setLayoutParams(paramss);

        layout.addView(view);




    }
}
