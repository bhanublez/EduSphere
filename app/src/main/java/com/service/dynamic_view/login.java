package com.service.dynamic_view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.service.dynamic_view.studentLayouts.dashBoard;


public class login extends AppCompatActivity {
    private EditText userID,password;
    private TextView sgn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userID = findViewById(R.id.user_idor_email);
        password = findViewById(R.id.password);
        sgn = findViewById(R.id.sign_in);
        mAuth = FirebaseAuth.getInstance();

        sgn.setOnClickListener(v -> {
            try {
               String email = userID.getText().toString();
                String pass = password.getText().toString();
                loginuser(email,pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }


    public void loginuser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Login successful, navigate to dashboard
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, dashBoard.class));
                        finish(); // Finish the current activity to prevent going back to the login page
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Login failed, display error message
                        Toast.makeText(login.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(login.this, dashBoard.class));
            finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Sign out
        mAuth.signOut();
    }

    //    public Student login(long userID,String password) throws Exception {
//        com.service.dynamic_view.retrofit.retrofitService retrofitService = new retrofitService();
//        studentAPI studentAPI = retrofitService.getRetrofit().create(studentAPI.class);
//
//        CountDownLatch latch = new CountDownLatch(1);
//        final Student[] studentDetails = {new Student()};
//
//       studentAPI.getStudentById(userID,password).enqueue(new Callback<Student>() {
//            @Override
//            public void onResponse(Call<Student> call, Response<Student> response) {
//                System.out.println("This is response" + response.body());
//                Student student = response.body();
//                if(student!=null){
//                    System.out.println("Student is not null");
//                    studentDetails[0] = student;
//                }else{
//                    System.out.println("Student is null");
//                }
//                latch.countDown();
//            }
//
//            @Override
//            public void onFailure(Call<Student> call, Throwable t) {
//                System.out.println("This is error" + t);
//                latch.countDown();
//            }
//        });
//
//        latch.await();
//
//        return studentDetails[0];
//    }
//
//    ImageView sgn;
//    EditText userID;
//    EditText password;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_page);
//        sgn = findViewById(R.id.sgn);
//
//        sgn.setOnClickListener(v -> {
//            try {
//                if(userID.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
//                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Student student = login(Long.parseLong(userID.getText().toString()),password.getText().toString());
//                if(student!=null){
//                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//        });
//
//
//
//
//    }



}
