package com.service.dynamic_view.teacherLayouts;

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

public class addAssignment extends AppCompatActivity {
    AlertDialog dialog;
    LinearLayout layout;
    ImageView add;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignmentallotment);
        add = findViewById(R.id.addBlock);
        layout = findViewById(R.id.addContainer);
        back=findViewById(R.id.backArrowOfAm);

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

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.assignmentdialog, null);

        final EditText name = view.findViewById(R.id.teacherNaam_aa);
        final EditText subject = view.findViewById(R.id.subjectName_a);
        final  EditText adate1=view.findViewById(R.id.assDate_a);
        final  EditText adate2=view.findViewById(R.id.subDate_a);
        final  EditText set=view.findViewById(R.id.set_id_a);
        final  EditText marks=view.findViewById(R.id.marks_a);





        builder.setView(view);
        builder.setTitle("Enter Details")



                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nameText = name.getText().toString().trim();
                        String subjectText = subject.getText().toString().trim();
                        String adate1Text = adate1.getText().toString().trim();
                        String adate2Text = adate2.getText().toString().trim();
                        int setNumber=-1,mks=-1;
                        try {
                            setNumber = Integer.parseInt(set.getText().toString().trim());
                            mks=Integer.parseInt(marks.getText().toString().trim());
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Please enter valid Digit", Toast.LENGTH_SHORT).show();
                        }
                         if (!nameText.isEmpty() && !subjectText.isEmpty() && !adate1Text.isEmpty() && !adate2Text.isEmpty() && (setNumber >= 1)) {
                            // Check date formats
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            try {
                                // Parsing dates
                                Date date1 = dateFormat.parse(adate1Text);
                                Date date2 = dateFormat.parse(adate2Text);

                                // Proceed with your logic
                                addCard(nameText,subjectText,adate1Text,adate2Text,setNumber,mks);
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

    private void addCard(String name,String subject,String adate,String sdate,int set,int mks) {



//        final View view = getLayoutInflater().inflate(R.layout.card, null);
        final View view2 =getLayoutInflater().inflate(R.layout.dialog_assignment,null);

//        TextView nameView = view.findViewById(R.id.name);


        TextView nameView = view2.findViewById(R.id.teacherNaam_va);
        TextView subjectView = view2.findViewById(R.id.nameSub_va);
        TextView subDate = view2.findViewById(R.id.subDate_va);
        TextView assDate = view2.findViewById(R.id.assDate_va);
        TextView setId = view2.findViewById(R.id.setId_va);
        TextView mksId = view2.findViewById(R.id.marks_va);

//        Button delete = view.findViewById(R.id.delete);
        ImageView delete=view2.findViewById(R.id.delID);
        try {
            nameView.setText(name);
            subjectView.setText(subject);
            subDate.setText((CharSequence) subDate);
            assDate.setText(String.format("%s", assDate));
            setId.setText("" + set);
            mksId.setText("" + mks);
        }catch (Exception e){
            System.out.println(("Kuch toh gadbad hai"));
        }

        //

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view2);
            }
        });

        layout.addView(view2);
    }

}
