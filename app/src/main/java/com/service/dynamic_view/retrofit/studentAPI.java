package com.service.dynamic_view.retrofit;

import com.service.dynamic_view.studentLayouts.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface studentAPI {
    @GET("/student/get-all")
    Call<List<Student>> getAllStudents();

    @POST("/student/save")
    Call<Student> save(@Body Student student);

    @GET("/student/get-by-id")
    Call<Student> getStudentById(long id,String password);
}
