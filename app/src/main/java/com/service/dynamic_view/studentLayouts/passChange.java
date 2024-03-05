package com.service.dynamic_view.studentLayouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        back=findViewById(R.id.back_icon);
        oldPass=findViewById(R.id.oldPass);
        newPass=findViewById(R.id.newPass);
        confirmPass=findViewById(R.id.confirmPass);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(passChange.this, dashBoard.class);
                startActivity(intent);
            }
        });
    }
}
