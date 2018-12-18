package com.jhbb.baking_time.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    private List<RecipeModel> mRecipesDataset;

    private final AdapterOnClickHandler mAdapterOnClickHandler;

    public interface AdapterOnClickHandler {
        void onAdapterClick(RecipeModel recipeModel);
    }

    public RecipesAdapter(AdapterOnClickHandler mAdapterOnClickHandler) {
        this.mAdapterOnClickHandler = mAdapterOnClickHandler;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_card, viewGroup, false);

        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder recipesViewHolder, int i) {
        final RecipeModel recipeModel = mRecipesDataset.get(i);

        if (recipeModel != null) {
            recipesViewHolder.mRecipeNameTextView.setText(recipeModel.getName());
        }

        recipesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterOnClickHandler.onAdapterClick(recipeModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipesDataset != null ? mRecipesDataset.size() : 0;
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {

        TextView mRecipeNameTextView;

        public RecipesViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeNameTextView = itemView.findViewById(R.id.recipe_name_text_view);
        }
    }

    public void setRecipesDataset(List<RecipeModel> recipesList) {
        mRecipesDataset = recipesList;
        notifyDataSetChanged();
    }
}
