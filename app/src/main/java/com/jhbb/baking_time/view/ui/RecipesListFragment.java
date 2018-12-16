package com.jhbb.baking_time.view.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;
import com.jhbb.baking_time.utilities.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RecipesListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<RecipeModel>> {

    public static final int RECIPES_LOADER = 1;

    List<RecipeModel> recipesList;

    public RecipesListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(RECIPES_LOADER, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_list, container);

        return view;
    }

    @NonNull
    @Override
    public Loader<List<RecipeModel>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<List<RecipeModel>>(getContext()) {

            @Override
            protected void onStartLoading() {
                forceLoad();
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
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<RecipeModel>> loader, List<RecipeModel> recipeModels) {
        recipesList = recipeModels;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<RecipeModel>> loader) {

    }
}
