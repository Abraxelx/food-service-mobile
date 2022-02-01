package com.abraxel.suggestafood;

import android.net.Uri;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.abraxel.suggestafood.entity.Food;
import com.abraxel.suggestafood.repository.FoodService;
import com.abraxel.suggestafood.service.ApiClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView foodImage;
    private TextView foodName;
    private ListView ingredients;
    private TextView recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodImage = findViewById(R.id.foodImage);
        foodName = findViewById(R.id.foodName);
        ingredients = findViewById(R.id.ingredients);
        recipe = findViewById(R.id.recipe);


        Call<Food> foodList = ApiClient.getFoodService().getAllFoods();

        foodList.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, response.body().getIngredients());
                ingredients.setAdapter(arrayAdapter);
                foodName.setText(response.body().getFoodName());
                recipe.setText(response.body().getRecipe());
                Picasso.get().load(response.body().getImageURL()).into(foodImage);

            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}