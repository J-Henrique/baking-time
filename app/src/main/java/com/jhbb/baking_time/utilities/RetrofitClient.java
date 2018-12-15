package com.jhbb.baking_time.utilities;

import android.util.Log;

import com.jhbb.baking_time.model.RecipeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitClient {

    private static final String TAG = RetrofitClient.class.getSimpleName();
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    RecipesService recipesService;

    List<RecipeModel> recipes;

    interface RecipesService {
        @GET("baking.json")
        Call<List<RecipeModel>> getRecipes();
    }

    public RetrofitClient() {

        Log.v(TAG, "Criando RetrofitClient");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipesService = retrofit.create(RecipesService.class);
    }

    public void getRecipes() {
        Call<List<RecipeModel>> call = recipesService.getRecipes();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                Log.v(TAG, "" + response.body().size());

                recipes = response.body();
                Log.v(TAG, "" + recipes);
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Log.v(TAG, t.getMessage());
            }
        });
    }
}
