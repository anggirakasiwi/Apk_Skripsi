package com.anggi.belajaranggi.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static final String BASE_URL ="http://192.168.43.16/Api_Skripsi/";

    private static Network mInstance;
    private Retrofit retrofit;

    private Network(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Network getInstance(){
        if (mInstance == null ){
            mInstance = new Network();
        }
        return mInstance;
    }
    public ApiServices getApi(){
        return retrofit.create(ApiServices.class);
    }
}
