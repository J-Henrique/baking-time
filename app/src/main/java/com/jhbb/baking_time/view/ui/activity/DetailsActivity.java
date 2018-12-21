package com.jhbb.baking_time.view.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jhbb.baking_time.R;
import com.jhbb.baking_time.model.entity.RecipeModel;
import com.jhbb.baking_time.view.ui.fragment.DetailsFragment;
import com.jhbb.baking_time.view.ui.fragment.StepFragment;

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

            Bundle detailsArgs = new Bundle();
            detailsArgs.putParcelable("stepsList", Parcels.wrap(mRecipe.getSteps()));
            detailsArgs.putParcelable("ingredientsList", Parcels.wrap(mRecipe.getIngredients()));

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(detailsArgs);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.steps_container, detailsFragment)
                    .commit();

            if (getApplicationContext().getResources().getBoolean(R.bool.isLargeScreen)) {
                Bundle stepArgs = new Bundle();
                stepArgs.putParcelable("currentStep", Parcels.wrap(mRecipe.getSteps().get(0)));

                StepFragment stepFragment = new StepFragment();
                stepFragment.setArguments(stepArgs);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.current_step_container, stepFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onStepClickListener(int itemClickedIndex) {
        Log.v(TAG, "indice do step clicado: " + itemClickedIndex);

        if (getApplicationContext().getResources().getBoolean(R.bool.isLargeScreen)) {
            Bundle stepArgs = new Bundle();
            stepArgs.putParcelable("currentStep", Parcels.wrap(mRecipe.getSteps().get(itemClickedIndex)));

            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(stepArgs);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.current_step_container, stepFragment)
                    .commit();
        } else {
            Intent startStepActivity = new Intent(this, StepActivity.class);
            startStepActivity.putExtra("stepIndex", itemClickedIndex);
            startStepActivity.putExtra("stepList", Parcels.wrap(mRecipe.getSteps()));

            startActivity(startStepActivity);
        }

    }
}
