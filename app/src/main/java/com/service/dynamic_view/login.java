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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.service.dynamic_view.studentLayouts.dashBoard;


public class login extends AppCompatActivity {
    private EditText userID,password;
    private TextView sgn,forgetPassword;

//    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        userID = findViewById(R.id.user_idor_email);
        password = findViewById(R.id.password);
        sgn = findViewById(R.id.sign_in);
        forgetPassword = findViewById(R.id.forgot_passwordId);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(login.this, dashBoard.class));
//            finish();
        }


        sgn.setOnClickListener(v -> {
            try {
               String email = userID.getText().toString();
                String pass = password.getText().toString();
                //Check if the email is valid
                if (email.isEmpty()) {
                    Toast.makeText(login.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if the password is valid
                if (pass.isEmpty()) {
                    Toast.makeText(login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }



                loginuser(email,pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        forgetPassword.setOnClickListener(v -> {
            String email = userID.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(login.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                return;
            }
            //Check if the email is valid
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(login.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            //Check if the email is registered

            mAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(login.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(login.this, "Failed to send password reset email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });



    }


    public void loginuser(String email, String password) {
//        progressDialog.setTitle("Log In");
//        progressDialog.setMessage("please wait...");
//        progressDialog.setCanceledOnTouchOutside(true);
//        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // Login successful, navigate to dashboard
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser currentUser= mAuth.getCurrentUser();
                        String userId = currentUser.getUid();
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Authentication").child("Student").child(userId);
                        userRef.child("Status").setValue("Online");
                        startActivity(new Intent(login.this, dashBoard.class));
                        finish(); // Finish the current activity to prevent going back to the login page
//                        System.out.println("This is user id" + userId);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Login failed, display error message
                        Toast.makeText(login.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//        progressDialog.dismiss();

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
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(login.this, dashBoard.class));
            finish();
        }
    }

    protected void onNoNetwork() {
        Toast.makeText(login.this, "No network connection", Toast.LENGTH_SHORT).show();
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
