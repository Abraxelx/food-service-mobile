package com.abraxel.suggestafood.service;

import com.abraxel.suggestafood.repository.FoodService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.21:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }

    public static FoodService getFoodService(){
        FoodService foodService = getRetrofit().create(FoodService.class);
        return foodService;
    }



}
