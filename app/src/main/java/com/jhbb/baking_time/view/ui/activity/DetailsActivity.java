package com.jhbb.baking_time.view.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.RecipeModel;
import com.jhbb.baking_time.view.ui.fragment.DetailsFragment;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity
        implements DetailsFragment.OnStepClickListener {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    RecipeModel mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "DetailsActivity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra("recipeModel"));
        if (mRecipe != null) {
            Log.v(TAG, "receita selecionada: " + mRecipe.getName());

            Bundle args = new Bundle();
            args.putParcelable("stepsList", Parcels.wrap(mRecipe.getSteps()));
            args.putParcelable("ingredientsList", Parcels.wrap(mRecipe.getIngredients()));

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.steps_container, detailsFragment)
                    .commit();
        }
    }

    @Override
    public void onStepClickListener(int itemClickedIndex) {
        Log.v(TAG, "indice do step clicado: " + itemClickedIndex);

        Intent startStepActivity = new Intent(this, StepActivity.class);
        startStepActivity.putExtra("stepIndex", itemClickedIndex);
        startStepActivity.putExtra("stepList", Parcels.wrap(mRecipe.getSteps()));

        startActivity(startStepActivity);
    }
}
