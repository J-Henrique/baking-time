package com.jhbb.baking_time.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jhbb.baking_time.model.RecipeModel;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private static final String TAG = RecipesAdapter.class.getSimpleName();

    private List<RecipeModel> mRecipesDataset;

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder recipesViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mRecipesDataset != null ? mRecipesDataset.size() : 0;
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
