package com.jhbb.baking_time.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;
import com.jhbb.baking_time.view.adapter.RecipesAdapter;
import com.jhbb.baking_time.view.loader.RecipesLoader;

import java.util.List;

public class RecipesListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<List<RecipeModel>>, RecipesAdapter.AdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private RecipesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    OnRecipeClickListener mCallback;

    @Override
    public void onAdapterClick(RecipeModel recipeModel) {
        mCallback.onRecipeClickListener(recipeModel);
    }

    public interface OnRecipeClickListener {
        void onRecipeClickListener(RecipeModel recipeModel);
    }

    public static final int RECIPES_LOADER = 1;

    public RecipesListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnRecipeClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnRecipeClickListener");
        }
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

        mRecyclerView = view.findViewById(R.id.recipes_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new RecipesAdapter(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @NonNull
    @Override
    public Loader<List<RecipeModel>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return RecipesLoader.getRecipesAsyncTask(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<RecipeModel>> loader, List<RecipeModel> recipeModels) {
        mAdapter.setRecipesDataset(recipeModels);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<RecipeModel>> loader) {

    }
}
