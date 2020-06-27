package com.anggi.belajaranggi.server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> userRegis(
            @Field("fullname") String firstname,
            @Field("username") String lastname,
            @Field("phonenumber") String phonenumber,
            @Field("email") String email,
            @Field("password") String password
    );

}
