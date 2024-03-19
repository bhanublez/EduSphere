package com.service.dynamic_view.retrofit;

import com.service.dynamic_view.studentLayouts.testStudent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface testAPI {
    @GET("/test/get-all")
    Call<List<testStudent>> getTestStudent();

    @POST("/test/save")
    Call<testStudent> save(@Body  testStudent testStudent);
}
