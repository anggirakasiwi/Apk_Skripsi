package com.anggi.belajaranggi.server;

import com.anggi.belajaranggi.server.Response.Response_Coffeeshop;
import com.anggi.belajaranggi.server.Response.Response_Menu;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login_coffeeshop.php")
    Call<ResponseBody> userLoginCoffeeshop(
            @Field("email") String email,
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

    @FormUrlEncoded
    @POST("register_coffeeshop.php")
    Call<ResponseBody> userRegisCoffeeshop(
            @Field("nama_coffeeshop") String nama_coffeeshop,
            @Field("alamat") String alamat,
            @Field("phonenumber") String phonenumber,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("coffeeshop.php")
    Call<Response_Coffeeshop> tampil_semuadata();

    @FormUrlEncoded
    @POST("menu.php")
    Call<Response_Menu> tampil_data(
            @Field("id_coffeeshop") String Id
    );

    @FormUrlEncoded
    @POST("inputmenu.php")
    Call<ResponseBody> userInput_Menu(
            @Field("nama_menu") String nama_menu,
            @Field("jenis") String jenis,
            @Field("harga") String harga,
            @Field("gambar") String gambar
    );

    @Multipart
    @POST("uploadimage.php")
    Call uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name);

}
