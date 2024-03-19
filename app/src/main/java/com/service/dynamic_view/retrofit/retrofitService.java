package com.service.dynamic_view.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitService {
    private Retrofit retrofit;

    public retrofitService() {
        initializeRetrofit();//Create retrofit object
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()//builder class
                .baseUrl("http://192.168.1.2:9005")//Are network
                .addConverterFactory(GsonConverterFactory.create(new Gson()))//used to convert into Gson
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;//use this more modifying are retrofit and also to use retrofit
    }
}
