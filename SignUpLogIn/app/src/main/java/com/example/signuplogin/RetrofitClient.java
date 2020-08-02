package com.example.signuplogin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "http://192.168.43.244/Android%20Tutorials/";
    private static RetrofitClient myClient;
    private Retrofit retrofit;

    private RetrofitClient(){

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static synchronized RetrofitClient getInstance(){

        if(myClient == null){
            myClient = new RetrofitClient();
        }
        return myClient;

    }

    public Api getApi(){

        return retrofit.create(Api.class);
    }


}
