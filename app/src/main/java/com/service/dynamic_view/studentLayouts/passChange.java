package com.service.dynamic_view.studentLayouts;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.dynamic_view.R;

public class passChange extends AppCompatActivity {

    ImageView back;
    EditText oldPass, newPass, confirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        back=findViewById(R.id.back_icon);
        oldPass=findViewById(R.id.oldPass);
        newPass=findViewById(R.id.newPass);
        confirmPass=findViewById(R.id.confirmPass);


        back.setOnClickListener(v -> {
            finish();
        }  );
    }
}
