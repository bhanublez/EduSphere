package com.service.dynamic_view;

import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {













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
