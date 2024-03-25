package com.service.dynamic_view.studentLayouts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.service.dynamic_view.R;

public class studentTable extends AppCompatActivity {
    private LinearLayout layout;
    private ImageView back;
    private ImageView add;

    private TextView tabMon, tabTue, tabWed, tabThu, tabFri, tabSat, tabSun;

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser ;

    private String branch, semester, section, subject,studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeslot);
        layout= findViewById(R.id.tableContainer);
        back=findViewById(R.id.back_icon);
        add = findViewById(R.id.add_Slot);
        add.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        studentId=getIntent().getStringExtra("studentId");
        branch=getIntent().getStringExtra("branch");
        semester=getIntent().getStringExtra("semester");
        section=getIntent().getStringExtra("section");


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
            //Layout add its Holiday
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


            AnimationSet animationSet = new AnimationSet(true);

            ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(1000);

            RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1000);

            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(1000);

            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            translateAnimation.setDuration(1000);

            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(rotateAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.addAnimation(translateAnimation);

            textView.startAnimation(animationSet);

            layout.addView(textView);
//            int rows = 4;
//            int cols = 4;
//
//            ImageView[][] imageViews = new ImageView[rows][cols];
//
//            GridLayout gridLayout = new GridLayout(this);
//            gridLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            gridLayout.setColumnCount(cols);
//            gridLayout.setRowCount(rows);
//
//            for (int i = 0; i < rows; i++) {
//                for (int j = 0; j < cols; j++) {
//                    ImageView imageView = new ImageView(this);
//                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//                    gridLayout.addView(imageView);
//                    imageViews[i][j] = imageView;
//                }
//            }
//
//// Now you have a 4x4 grid of ImageViews without any background color
//
//// Add this grid layout to your linear layout
//            layout.addView(gridLayout);
        });

        back.setOnClickListener(v -> {
            finish();
        }  );
    }

    @SuppressLint("MissingInflatedId")
    public void addCards(String day) {
        //Database of Exam sai hum haha data leke aaye

        DatabaseReference studentList= mDatabase.child("department").child(branch).child("semesters").child(semester).child("section").child(section).child("timeTableSchema").child(day);
//        studentList.child(studentId).get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                Toast.makeText(this, "Data Fetched", Toast.LENGTH_SHORT).show();
////                String startTime = task.getResult().child("1").child("start").getValue(String.class);
////                Toast.makeText(this, startTime, Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, task.getResult().toString(), Toast.LENGTH_SHORT).show();
//                System.out.println(task.getResult().toString());
////                for (int i = 1; i <= 8; i++) {
////                    String startTime = task.getResult().child(String.valueOf(i)).child("start").getValue(String.class);
////                    String endTime = task.getResult().child(String.valueOf(i)).child("end").getValue(String.class);
////                    String subjectCode = task.getResult().child(String.valueOf(i)).child("subject").getValue(String.class);
////                    String teacherName = task.getResult().child(String.valueOf(i)).child("faculty").getValue(String.class);
////                    String subjectName = task.getResult().child(String.valueOf(i)).child("subjectName").getValue(String.class);
////                    addCard(subjectName, teacherName, startTime, endTime, subjectCode, i);
////                }
//
//            }
//        });

        studentList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Toast.makeText(studentTable.this, "Entered", Toast.LENGTH_SHORT).show();
//                System.out.println(dataSnapshot.toString());
//                System.out.println("Child "+dataSnapshot.getChildrenCount());
//                System.out.println("Key "+dataSnapshot.getKey());
//                System.out.println("Value "+dataSnapshot.getValue());

                int i=0;
                for (DataSnapshot periodSnapshot : dataSnapshot.getChildren()) {

                    String startTime = periodSnapshot.child("start").getValue(String.class);
                    String endTime = periodSnapshot.child("end").getValue(String.class);
                    String subjectCode = periodSnapshot.child("subjectCode").getValue(String.class);
                    String teacherName = periodSnapshot.child("faculty").getValue(String.class);
                    String subjectName = periodSnapshot.child("subject").getValue(String.class);

                    // Check for null values before adding to layout
                    if (startTime != null && endTime != null && subjectCode != null && teacherName != null && subjectName != null) {
                        addCard(subjectName, teacherName, startTime, endTime, subjectCode, ++i);
                    }
//                    else {
//                        //Lunch break LinearLayout
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        TextView textView = new TextView(studentTable.this);
//                        textView.setLayoutParams(params);
//                        textView.setText("Lunch Break");
//                        textView.setTextSize(20);
//                        textView.setPadding(10,10,10,10);
//                        layout.addView(textView);
//
//                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /* Handle error */
                Log.e("Firebase", "Error getting timetable data: " + databaseError.getMessage());
            }
        });
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

        //Set separtion layout
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        View view = new View(this);
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.black));
        layout.addView(view);

        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 0, 0, 15);
        view2.setLayoutParams(paramss);

        layout.addView(view2);
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
}
