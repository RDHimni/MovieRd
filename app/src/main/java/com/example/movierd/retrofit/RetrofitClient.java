package com.example.movierd.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Rida Dhimni
 * 25/11/2020
 **/

public class RetrofitClient {

    private static final String BASE_URL = "https://androidappsforyoutube.s3.Ap-south-1.amazonaws.com/primevideo/api/";
    public static ApiInterface getRetrofitClient(){

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BASE_URL);

        return builder.build().create(ApiInterface.class);

    }

}
