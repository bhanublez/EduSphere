package com.service.dynamic_view.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.R;

public class readFeedBack extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private AlertDialog dialog;
    private LinearLayout layout;




    private TextView title_id;
    private ImageView back;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_read_feedback);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        title_id=findViewById(R.id.title_id);
        back=findViewById(R.id.back_icon);

        buildDialog();

        back.setOnClickListener(v->{
            finish();
        });




    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.admin_feedback_dialog, null);

       final EditText userName,userEmail;
       userName=view.findViewById(R.id.editTextName);
       userEmail=view.findViewById(R.id.editTextEmail);
        final EditText feedBackCategory=view.findViewById(R.id.feedBackCategory);
       final EditText feedbackDate=view.findViewById(R.id.dateAndtime);
        final EditText feedbackBox=findViewById(R.id.editTextFeedback);















    }
}
