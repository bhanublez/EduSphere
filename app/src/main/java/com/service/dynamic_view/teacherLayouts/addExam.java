package com.service.dynamic_view.teacherLayouts;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addExam extends AppCompatActivity {
    AlertDialog dialog;
    LinearLayout layout;
    ImageView add;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examall);

        add = findViewById(R.id.addExam);
        layout = findViewById(R.id.examContainer);
        back=findViewById(R.id.backFromExam);

        buildDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        back.setOnClickListener(v -> {
            finish();
        }  );

    }

    @SuppressLint("MissingInflatedId")
    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.examallot, null);

        final EditText name = view.findViewById(R.id.teacherName_e);
        final EditText subject = view.findViewById(R.id.subNaam_e);
        final  EditText adate1=view.findViewById(R.id.assDate_e);
        final  EditText adate2=view.findViewById(R.id.exam_date_e);
        final  EditText txtArea=view.findViewById(R.id.txt_area_e);
        final  EditText marks;
        marks = view.findViewById(R.id.marks_e);

        builder.setView(view);
        builder.setTitle("Enter Exam Details")



                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameText = name.getText().toString().trim();
                        String subjectText = subject.getText().toString().trim();
                        String adate1Text = adate1.getText().toString().trim();
                        String adate2Text = adate2.getText().toString().trim();
                        String data=txtArea.getText().toString().trim();
                        int mks=-1;
                        try {
                            mks = Integer.parseInt(marks.getText().toString().trim());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Please enter valid Digit", Toast.LENGTH_SHORT).show();
                        }
                        if (!nameText.isEmpty() && !subjectText.isEmpty() && !adate1Text.isEmpty() && !adate2Text.isEmpty() && (mks >=1)) {
                            // Check date formats
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            try {
                                // Parsing dates
                                Date date1 = dateFormat.parse(adate1Text);
                                Date date2 = dateFormat.parse(adate2Text);

                                // Proceed with your logic
                                addCard(nameText,subjectText,date1,date2,mks,data);
                            } catch (ParseException e) {
                                // Show toast indicating date format error
                                Toast.makeText(getApplicationContext(), "Please enter dates in the format yyyy-MM-dd", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Show toast indicating that all fields are required
                            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog = builder.create();
    }

    private void addCard(String name,String subject,Date adate,Date sdate,int set,String data) {


       final View view2 =getLayoutInflater().inflate(R.layout.examdialog,null);



        TextView nameView = view2.findViewById(R.id.teacherName_ve);
        TextView subjectView = view2.findViewById(R.id.subjectName_ve);

        TextView assDate = view2.findViewById(R.id.daetAss_ve);
        TextView subDate = view2.findViewById(R.id.examDate_ve);

        TextView mksId = view2.findViewById(R.id.markss_ve);
        TextView setId = view2.findViewById(R.id.txtArea_ve);

        ImageView delete=view2.findViewById(R.id.del_e);

        // Set values to respective TextViews
        nameView.setText(name);
        subjectView.setText(subject);
        // Assuming adate and sdate are Date objects and you want to display them as strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        assDate.setText(dateFormat.format(adate));
        subDate.setText(dateFormat.format(sdate));
        mksId.setText(String.valueOf(set)); // Assuming set is an integer
        setId.setText(data);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view2);
            }
        });

        layout.addView(view2);
    }

}
