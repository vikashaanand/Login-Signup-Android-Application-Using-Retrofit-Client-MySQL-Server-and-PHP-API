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

    @FormUrlEncoded
    @POST("login.php")
    Call<StudentPOJO> getStudent(
            @Field("ROLL") int roll,
            @Field("PASSWORD") String password
    );

    @FormUrlEncoded
    @POST("getEmail.php")
    Call<StudentPOJO> getEmail(
            @Field("ROLL") int roll
    );

    @FormUrlEncoded
    @POST("updatePassword.php")
    Call<ResponsePOJO> updatePassword(
            @Field("ROLL") int roll,
            @Field("PASSWORD") String password
    );


}
