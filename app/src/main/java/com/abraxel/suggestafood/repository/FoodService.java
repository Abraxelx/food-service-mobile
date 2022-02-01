package com.abraxel.suggestafood.repository;

import com.abraxel.suggestafood.entity.Food;
import retrofit2.Call;
import retrofit2.http.GET;


public interface FoodService {
    @GET("hello")
    Call<Food> getAllFoods();
}
