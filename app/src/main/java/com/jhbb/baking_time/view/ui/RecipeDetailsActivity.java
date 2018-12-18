package com.jhbb.baking_time.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;

import org.parceler.Parcels;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String TAG = RecipeDetailsActivity.class.getSimpleName();

    RecipeModel mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "RecipeDetailsActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipeModel"));
        if (mRecipe != null) {
            Log.v(TAG, "receita selecionada: " + mRecipe.getName());

            Bundle args = new Bundle();
            args.putParcelable("stepsList", Parcels.wrap(mRecipe.getSteps()));

            RecipeDetailsFragment detailsFragment = new RecipeDetailsFragment();
            detailsFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.steps_container, detailsFragment)
                    .commit();
        }
    }
}
