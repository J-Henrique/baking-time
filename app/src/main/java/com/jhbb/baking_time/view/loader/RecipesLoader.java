package com.jhbb.baking_time.view.loader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.jhbb.baking_time.model.RecipeModel;
import com.jhbb.baking_time.utilities.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RecipesLoader {
    public static Loader<List<RecipeModel>> getRecipesAsyncTask(Context context) {
        return new AsyncTaskLoader<List<RecipeModel>>(context) {

            List<RecipeModel> mCachedRecipesList;

            @Override
            protected void onStartLoading() {
                if (mCachedRecipesList != null) {
                    deliverResult(mCachedRecipesList);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List<RecipeModel> loadInBackground() {
                RetrofitClient.RecipesService recipesService
                        = RetrofitClient.getRetrofitClient().create(RetrofitClient.RecipesService.class);
                Call<List<RecipeModel>> call = recipesService.getRecipes();

                try {
                    Response<List<RecipeModel>> response = call.execute();
                    return response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            public void deliverResult(@Nullable List<RecipeModel> data) {
                mCachedRecipesList = data;
                super.deliverResult(data);
            }
        };
    }
}
