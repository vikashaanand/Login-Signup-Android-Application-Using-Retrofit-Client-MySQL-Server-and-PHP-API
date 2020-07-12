package com.example.signuplogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("sign_up.php")
    Call<ResponsePOJO> saveStudent(

            @Field("ROLL") int roll,
            @Field("NAME") String name,
            @Field("MOBILE") String mobile,
            @Field("EMAIL") String email,
            @Field("GENDER") String gender,
            @Field("PASSWORD") String password
    );

}
